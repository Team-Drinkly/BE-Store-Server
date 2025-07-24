package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.domain.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
