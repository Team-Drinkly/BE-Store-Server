package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.event.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventImageRepository extends JpaRepository<EventImage, Long> {
}
