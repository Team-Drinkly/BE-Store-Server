package com.drinkhere.drinklystore.scheduler;

import com.drinkhere.drinklystore.infras3.service.PresignedUrlService;
import com.drinkhere.drinklystore.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ViewCountScheduler {

    private final RedisUtil redisUtil;
    private final PresignedUrlService presignedUrlService;

    /**
     * 업체 상세 페이지 조회 수 엑셀로 추출하여 월요일 11:00마다 S3에 Save
     */
    @Scheduled(cron = "0 30 11 ? * MON")
    public void exportViewCountToExcel() {
        try {
            Map<Object, Object> viewCountMap = redisUtil.getAllHash("store:viewCount");
            if (viewCountMap.isEmpty()) {
                log.info("No view count data to export.");
                return;
            }

            // Excel 생성
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("조회수 통계");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("가게 ID:이름");
            header.createCell(1).setCellValue("조회수");

            int rowNum = 1;
            for (Map.Entry<Object, Object> entry : viewCountMap.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getKey().toString());
                row.createCell(1).setCellValue(entry.getValue().toString());
            }

            // 엑셀 -> S3 업로드
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());

            String fileName = "view-stats/view-count-" + LocalDate.now() + ".xlsx";
            presignedUrlService.uploadFileToS3(inputStream, fileName, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            log.info("조회수 엑셀 업로드 완료: {}", fileName);

            redisUtil.delete("store:viewCount");
        } catch (Exception e) {
            log.error("조회수 엑셀 업로드 실패", e);
        }
    }
}
