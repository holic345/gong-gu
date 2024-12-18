package com.example.gong_gu.item.infrastruct.persistence.repository;

import com.example.gong_gu.item.infrastruct.persistence.entity.ItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {

    @Query("select i from ItemEntity i left join fetch i.groupPurchases")
    List<ItemEntity> findAllFetchJoin();
}
