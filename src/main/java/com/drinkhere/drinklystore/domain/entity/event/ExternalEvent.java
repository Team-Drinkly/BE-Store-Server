package com.drinkhere.drinklystore.domain.entity.event;

import com.drinkhere.drinklystore.domain.enums.EventCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "external_event_id")
    private Long id;

    // 썸네일 이미지
    @Column(nullable = false)
    private String thumbnailPath;

    // 이벤트 제목
    @Column(nullable = false)
    private String title;

    // 주최 기관
    @Column(nullable = false)
    private String organizer;

    // 한줄 설명
    @Column(nullable = false)
    private String description;

    // 클릭 시 이동할 URL
    @Column(nullable = false)
    private String redirectUrl;

    // 이벤트 기간
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Builder
    public ExternalEvent(String thumbnailPath, String title, String organizer, String description, String redirectUrl, LocalDateTime startDate, LocalDateTime endDate) {
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.organizer = organizer;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
