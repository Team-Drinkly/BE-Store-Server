package com.drinkhere.drinklystore.domain.entity;

import com.drinkhere.drinklystore.domain.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeDrinkHistory extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_drink_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_nickname")
    private String memberNickname;

    @Column(name = "subscribe_id")
    private Long subscribeId;

    @Column(name = "provided_drink_image_id")
    private Long providedDrinkImageId;

    @Column(name = "provided_drink")
    private String providedDrink;

    @Builder
    public FreeDrinkHistory(Store store, Long memberId, String memberNickname, Long subscribeId, Long providedDrinkImageId, String providedDrink) {
        this.store = store;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.subscribeId = subscribeId;
        this.providedDrinkImageId = providedDrinkImageId;
        this.providedDrink = providedDrink;
    }
}
