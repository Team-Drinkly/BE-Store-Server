package com.drinkhere.drinklystore.domain.entity;

import com.drinkhere.drinklystore.domain.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "store_name", nullable = false, length = 100)
    private String storeName;

    @Column(name = "store_description", columnDefinition = "TEXT")
    private String storeDescription;

    @Column(name = "opening_hours", columnDefinition = "TEXT")
    private String openingHours; // JSON 형태로 저장

    @Column(name = "store_tel", nullable = false, length = 20)
    private String storeTel;

    @Column(name = "store_address", nullable = false, length = 255)
    private String storeAddress;

    @Column(name = "instagram_url", length = 255)
    private String instagramUrl;

    @Column(name = "available_days", columnDefinition = "20")
    private String availableDays;

    @Column(name = "available_drinks", columnDefinition = "TEXT")
    private String availableDrinks; // JSON 형태로 저장

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreImage> storeImages;

    @Builder
    public Store(Long ownerId, String storeName, String storeTel, String storeAddress, Double latitude, Double longitude) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeTel = storeTel;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

