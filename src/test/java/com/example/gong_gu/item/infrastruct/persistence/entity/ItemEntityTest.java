package com.example.gong_gu.item.infrastruct.persistence.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.gong_gu.item.infrastruct.persistence.repository.ItemJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@Slf4j
@Rollback(value = false)
@SpringBootTest
class ItemEntityTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @DisplayName("EAGER 와 LAZY 일 경우 JPA N+1 오류 발생 테스트")
    @Test
    @Transactional
    void jpa_n_plus_1_test() {

        log.info("\n================================ JPA N+1 Test ================================\n");
        LocalDateTime now = LocalDateTime.now();

        // Item 5개당 Group_Purchase 5개씩 생성
        List<ItemEntity> entities = new ArrayList<>();
        for (int index = 1; index <= 5; index++) {

            ItemEntity entity = ItemEntity.builder()
                .name("ITEM_" + index)
                .status("STABLE")
                .price(10000)
                .totalCount(10)
                .remainCount(8)
                .soldCount(2)
                .isFreeDelivery(true)
                .createdDate(now)
                .modifiedDate(now)
                .build();

            for (int i = 1; i <= 5; i++) {

                GroupPurchaseEntity groupPurchaseEntity = GroupPurchaseEntity.builder()
                    .requiredParticipants(3)
                    .currentParticipants(1)
                    .status("WAITING")
                    .createdDate(now)
                    .build();

                entity.addGroupPurchase(groupPurchaseEntity);
            }

            entities.add(entity);
        }

        // saveAllAndFlush() 로 인해 DB에 반영된다. (아직 커밋은 되지 않았다)
        itemJpaRepository.saveAllAndFlush(entities);

        // 영속성 컨텍스트 초기화 (1차 캐시를 초기화하여 이후 해당 엔티티 조회시 새로 DB 에서 조회 되도록 하기 위함)
        entityManager.clear();

        /*
         * -- ToMany 양방향에서 발생 --
         * EAGER : findAll() 호출시 item 1개당 group_purchase 1개씩 추가 쿼리 발생
         * LAZY  : findAll() 호출시 item 조회 이후 entity.getGroupPurchases() 호출시 item 1개당 group_purchase 1개씩 추가 쿼리 발생
         */
        List<ItemEntity> findEntities = itemJpaRepository.findAll();
//        List<ItemEntity> findEntities = itemJpaRepository.findAllFetchJoin();

        findEntities.forEach(entity ->
                                 assertThat(entity.getGroupPurchases()).hasSize(5));

        log.info("===================================================================================");
    }
}