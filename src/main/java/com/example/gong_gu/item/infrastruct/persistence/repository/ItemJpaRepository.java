package com.example.gong_gu.item.infrastruct.persistence.repository;

import com.example.gong_gu.item.infrastruct.persistence.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {

}
