package com.drinkhere.drinklystore.domain.service;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryService {
    private final StoreRepository storeRepository;

    public Store findById(Long id) {
        return storeRepository.findByIdOrThrow(id);
    }
}
