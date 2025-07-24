package com.drinkhere.drinklystore.domain.entity.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_event_id", nullable = false)
    private Event event;

    @Column(name = "event_image_url", nullable = false, length = 500)
    private String eventImageUrl;

    @Column(name = "event_image_type", nullable = false, length = 20)
    private String eventImageType; // enum으로 분리하고 싶다면 아래 설명 참고

    @Column(name = "event_image_description", nullable = false)
    private String eventImageDescription;

    @Builder
    public EventImage(Event event, String eventImageUrl, String eventImageType, String eventImageDescription) {
        this.event = event;
        this.eventImageUrl = eventImageUrl;
        this.eventImageType = eventImageType;
        this.eventImageDescription = eventImageDescription;
    }
}