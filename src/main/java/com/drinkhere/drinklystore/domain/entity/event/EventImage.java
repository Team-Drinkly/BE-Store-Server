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

    @Column(name = "event_image_url", nullable = false)
    private String eventImageUrl;

    @Builder
    public EventImage(Event event, String eventImageUrl) {
        this.event = event;
        this.eventImageUrl = eventImageUrl;
    }
}