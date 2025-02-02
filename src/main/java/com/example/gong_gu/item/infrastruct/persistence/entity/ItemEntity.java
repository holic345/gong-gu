package com.example.gong_gu.item.infrastruct.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ITEM", indexes = {
    @Index(name = "IDX_ITEM_01", columnList = "name"),
    @Index(name = "IDX_ITEM_02", columnList = "status"),
    @Index(name = "IDX_ITEM_03", columnList = "created_date"),
    @Index(name = "IDX_ITEM_04", columnList = "is_free_delivery")})
@EntityListeners(AuditingEntityListener.class)
public class ItemEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "`key`", columnDefinition = "bigint", nullable = false)
  private Long key;

  @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
  private String name;

  @Column(name = "status", columnDefinition = "varchar(10)", nullable = false)
  private String status;

  @Column(name = "price", columnDefinition = "int", nullable = false)
  private Integer price;

  @Column(name = "total_count", columnDefinition = "int", nullable = false)
  private Integer totalCount;

  @Column(name = "remain_count", columnDefinition = "int", nullable = false)
  private Integer remainCount;

  @Column(name = "sold_count", columnDefinition = "int", nullable = false)
  private Integer soldCount;

  @Column(name = "is_free_delivery", columnDefinition = "tinyint", nullable = false)
  private Boolean isFreeDelivery;

  @CreatedDate
  @Column(name = "created_date", columnDefinition = "datetime", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "modified_date", columnDefinition = "datetime")
  private LocalDateTime modifiedDate;

  @Builder.Default
  @BatchSize(size = 6)
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<GroupPurchaseEntity> groupPurchases = new ArrayList<>();

  /**
   * @apiNote 양방향 매핑 메서드
   * @param groupPurchaseEntity
   */
  public void addGroupPurchase(GroupPurchaseEntity groupPurchaseEntity) {
    this.groupPurchases.add(groupPurchaseEntity);
    groupPurchaseEntity.mappingItem(this);
  }
}
