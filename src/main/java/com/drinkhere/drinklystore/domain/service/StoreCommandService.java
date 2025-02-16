package com.drinkhere.drinklystore.domain.service;

import com.drinkhere.drinklystore.common.annotation.DomainService;
import com.drinkhere.drinklystore.domain.entity.Store;
import com.drinkhere.drinklystore.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class StoreCommandService {
    private final StoreRepository storeRepository;
    
    public void save(final Store store) {
        storeRepository.save(store);
    }
}
