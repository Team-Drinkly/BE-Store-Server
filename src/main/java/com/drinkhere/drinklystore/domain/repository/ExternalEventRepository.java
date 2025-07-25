package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.event.ExternalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalEventRepository extends JpaRepository<ExternalEvent, Long> {
}
