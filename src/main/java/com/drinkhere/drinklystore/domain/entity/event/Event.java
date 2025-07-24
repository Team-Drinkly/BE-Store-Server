package com.drinkhere.drinklystore.domain.entity.event;

import com.drinkhere.drinklystore.domain.entity.store.StoreImage;
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
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    // 썸네일 이미지
    @Column(nullable = false)
    private String thumbnailPath;

    // 이벤트 제목
    @Column(nullable = false)
    private String title;

    // 참여 혜택
    @Column(nullable = false)
    private String benefit;

    // 이벤트 기간
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;

    // 상세 설명 (텍스트 블럭)
    @Lob @Column(nullable = false)
    private String description;

    // 참여 버튼 클릭 시 이동할 URL (없을 수도 있음)
    @Column(nullable = true)
    private String redirectUrl;

    // 이벤트 카테고리 (일반 이벤트, 친구 초대 이벤트 등)
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private EventCategory eventCategory;

    // 이벤트 관련 이미지들
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventImage> eventImages;

    @Builder
    public Event(String thumbnailPath, String title, String benefit, LocalDateTime startDate, LocalDateTime endDate,
                 String description, String redirectUrl, EventCategory eventCategory) {
        this.thumbnailPath = thumbnailPath;
        this.title = title;
        this.benefit = benefit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.eventCategory = eventCategory;
    }
}
