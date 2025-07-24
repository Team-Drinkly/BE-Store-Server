package com.drinkhere.drinklystore.domain.repository;

import com.drinkhere.drinklystore.common.exception.event.EventException;
import com.drinkhere.drinklystore.common.exception.store.StoreException;
import com.drinkhere.drinklystore.domain.entity.event.Event;
import com.drinkhere.drinklystore.domain.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import static com.drinkhere.drinklystore.common.exception.event.EventErrorCode.EVENT_NOT_FOUND;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e LEFT JOIN FETCH e.eventImages WHERE e.id = :eventId")
    Optional<Event> findWithImagesById(@Param("eventId") Long eventId);

    default Event findWithImagesByIdOrThrow(Long storeId) {
        return this.findWithImagesById(storeId)
                .orElseThrow(() -> new EventException(EVENT_NOT_FOUND));
    }
}
