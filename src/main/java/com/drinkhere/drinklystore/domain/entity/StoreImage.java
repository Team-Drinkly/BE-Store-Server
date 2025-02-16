package com.drinkhere.drinklystore.domain.entity;

import com.drinkhere.drinklystore.domain.enums.StoreImageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "store_image_url", nullable = false, length = 500)
    private String storeImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "store_image_type", nullable = false, length = 20)
    private StoreImageType storeImageType;

    @Column(name = "image_index", nullable = false)
    private Integer imageIndex;

    @Builder
    public StoreImage(Store store, String storeImageUrl, StoreImageType storeImageType, Integer imageIndex) {
        this.store = store;
        this.storeImageUrl = storeImageUrl;
        this.storeImageType = storeImageType;
        this.imageIndex = imageIndex;
    }
}
