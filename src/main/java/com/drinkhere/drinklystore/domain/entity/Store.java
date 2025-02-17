package com.drinkhere.drinklystore.domain.entity;

import com.drinkhere.drinklystore.domain.auditing.BaseEntity;
import com.drinkhere.drinklystore.domain.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "store_name", nullable = false, length = 100)
    private String storeName;

    @Column(name = "store_main_image_url", length = 100)
    private String storeMainImageUrl;

    @Column(name = "store_description", columnDefinition = "TEXT")
    private String storeDescription;

    @Column(name = "opening_hours", columnDefinition = "TEXT")
    private String openingHours; // JSON을 직렬화해서 저장

    @Column(name = "store_tel", nullable = false, length = 20)
    private String storeTel;

    @Column(name = "store_address", nullable = false, length = 255)
    private String storeAddress;

    @Column(name = "instagram_url", length = 255)
    private String instagramUrl;

    @Column(name = "available_days", length = 50)
    private String availableDays;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreImage> storeImages;

    @Builder
    public Store(Long ownerId, String storeName, String storeTel, String storeAddress, String latitude, String longitude) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeTel = storeTel;
        this.storeAddress = storeAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreMainImageUrl(String storeMainImageUrl) {
        this.storeMainImageUrl = storeMainImageUrl;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }
}

