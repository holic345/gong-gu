package com.example.gong_gu.item.infrastruct.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
<<<<<<< HEAD
=======
import jakarta.persistence.FetchType;
>>>>>>> 8bf67595761e7b43e76835e9888400f22f8e46f0
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======
import jakarta.persistence.ManyToOne;
>>>>>>> 8bf67595761e7b43e76835e9888400f22f8e46f0
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter @Builder @ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "GROUP_PURCHASE")
@EntityListeners(AuditingEntityListener.class)
public class GroupPurchaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`key`", columnDefinition = "bigint", nullable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_key", referencedColumnName = "`key`", foreignKey = @ForeignKey(name = "FK_GROUP_PURCHASE_TO_ITEM"))
    private ItemEntity item;

    @Column(name = "requried_participants", columnDefinition = "int", nullable = false)
    private Integer requiredParticipants;

    @Column(name = "current_participants", columnDefinition = "int", nullable = false)
    private Integer currentParticipants;

    @Column(name = "status", columnDefinition = "varchar(10)", nullable = false)
    private String status;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "datetime", nullable = false)
    private LocalDateTime createdDate;

    @OneToMany
    @JoinColumn(name = "group_purchase_key", referencedColumnName = "`key`", foreignKey = @ForeignKey(name = "FK_GROUP_PURCHASE_TO_GROUP_PURCHASE_PARTICIPANT"))
    private List<GroupPurchaseParticipantEntity> groupPurchaseParticipants;

    /**
     * @apiNote 양방향 매핑 메서드
     * @param item
     */
    public void mappingItem(ItemEntity item) {
        this.item = item;
    }

}
