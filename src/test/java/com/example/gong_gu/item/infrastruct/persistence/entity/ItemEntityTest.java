package com.example.gong_gu.item.infrastruct.persistence.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.gong_gu.item.infrastruct.persistence.repository.ItemJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
@SpringBootTest
class ItemEntityTest {

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @BeforeEach
    void init() {

        LocalDateTime now = LocalDateTime.now();

        List<GroupPurchaseEntity> groupPurchaseEntities = new ArrayList<>();

        GroupPurchaseEntity groupPurchaseEntity1 = GroupPurchaseEntity.builder()
            .key(1L)
            .itemKey(1L)
            .requiredParticipants(3)
            .currentParticipants(1)
            .status("WAITING")
            .createdDate(now)
            .build();

        GroupPurchaseEntity groupPurchaseEntity2 = GroupPurchaseEntity.builder()
            .key(2L)
            .itemKey(1L)
            .requiredParticipants(5)
            .currentParticipants(3)
            .status("COMPLETED")
            .createdDate(now)
            .build();

        GroupPurchaseEntity groupPurchaseEntity3 = GroupPurchaseEntity.builder()
            .key(3L)
            .itemKey(1L)
            .requiredParticipants(3)
            .currentParticipants(2)
            .status("WAITING")
            .createdDate(now)
            .build();

        groupPurchaseEntities.add(groupPurchaseEntity1);
        groupPurchaseEntities.add(groupPurchaseEntity2);
        groupPurchaseEntities.add(groupPurchaseEntity3);

        ItemEntity entity = ItemEntity.builder()
            .key(1L)
            .name("ITEN")
            .status("STABLE")
            .price(10000)
            .totalCount(10)
            .remainCount(8)
            .soldCount(2)
            .isFreeDelivery(true)
            .createdDate(now)
            .modifiedDate(now)
            .groupPurchases(groupPurchaseEntities)
            .build();

        itemJpaRepository.save(entity);
    }

    @Test
    void test() {

        ItemEntity itemEntity = itemJpaRepository.findById(1L)
            .orElseThrow(EntityNotFoundException::new);

        log.info("itemEntity: {}", itemEntity);
    }
}