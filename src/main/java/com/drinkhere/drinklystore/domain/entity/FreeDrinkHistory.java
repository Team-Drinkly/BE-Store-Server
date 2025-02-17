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
    @Column(name = "history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String providedDrink;

    @Builder
    public FreeDrinkHistory(String providedDrink, Store store) {
        this.providedDrink = providedDrink;
        this.store = store;
    }
}
