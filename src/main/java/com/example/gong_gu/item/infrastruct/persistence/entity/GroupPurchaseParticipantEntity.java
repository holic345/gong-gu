package com.example.gong_gu.item.infrastruct.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "group_purchase_participant")
@EntityListeners(AuditingEntityListener.class)
public class GroupPurchaseParticipantEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "`key`", columnDefinition = "bigint", nullable = false)
  private Long key;

  @Column(name = "group_purchase_key", columnDefinition = "bigint", nullable = false)
  private Long groupPurchaseKey;

  @Column(name = "user_key", columnDefinition = "bigint", nullable = false)
  private Long userKey;

  @Column(name = "user_name", columnDefinition = "varchar(255)", nullable = false)
  private String userName;

  @CreatedDate
  @Column(name = "created_date", columnDefinition = "datetime", nullable = false)
  private LocalDateTime createdDate;
}
