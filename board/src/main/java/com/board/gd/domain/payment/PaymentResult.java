package com.board.gd.domain.payment;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by godong9 on 2017. 5. 31..
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "payment_results")
public class PaymentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "message")
    private String message;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Column(name = "imp_uid")
    private String impUid;

    @Column(name = "pg_tid")
    private String pgTid;

    @Column(name = "fail_reason")
    private String failReason;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paid_at")
    private Date paidAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
}
