package com.mullya.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mullya.app.domain.enumeration.PGType;
import com.mullya.app.domain.enumeration.PaymentStatus;
import com.mullya.app.domain.enumeration.PaymentType;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PaymentDetails.
 */
@Entity
@Table(name = "payment_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "online_pg_type")
    private PGType onlinePgType;

    @Column(name = "pg_txn_id")
    private String pgTxnId;

    @Column(name = "pg_status")
    private String pgStatus;

    @Column(name = "offline_txn_id")
    private String offlineTxnId;

    @Column(name = "offline_txn_details")
    private String offlineTxnDetails;

    @Column(name = "offline_txn_collected_by")
    private String offlineTxnCollectedBy;

    @Column(name = "offline_txn_clearing_status")
    private String offlineTxnClearingStatus;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_init_time")
    private LocalDate paymentInitTime;

    @Column(name = "payment_update_time")
    private LocalDate paymentUpdateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne
    @JsonIgnoreProperties(value = { "paymentDetails", "remittances", "requirement", "bid", "assignedAgent", "stock" }, allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PaymentDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return this.amount;
    }

    public PaymentDetails amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public PaymentDetails paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PGType getOnlinePgType() {
        return this.onlinePgType;
    }

    public PaymentDetails onlinePgType(PGType onlinePgType) {
        this.setOnlinePgType(onlinePgType);
        return this;
    }

    public void setOnlinePgType(PGType onlinePgType) {
        this.onlinePgType = onlinePgType;
    }

    public String getPgTxnId() {
        return this.pgTxnId;
    }

    public PaymentDetails pgTxnId(String pgTxnId) {
        this.setPgTxnId(pgTxnId);
        return this;
    }

    public void setPgTxnId(String pgTxnId) {
        this.pgTxnId = pgTxnId;
    }

    public String getPgStatus() {
        return this.pgStatus;
    }

    public PaymentDetails pgStatus(String pgStatus) {
        this.setPgStatus(pgStatus);
        return this;
    }

    public void setPgStatus(String pgStatus) {
        this.pgStatus = pgStatus;
    }

    public String getOfflineTxnId() {
        return this.offlineTxnId;
    }

    public PaymentDetails offlineTxnId(String offlineTxnId) {
        this.setOfflineTxnId(offlineTxnId);
        return this;
    }

    public void setOfflineTxnId(String offlineTxnId) {
        this.offlineTxnId = offlineTxnId;
    }

    public String getOfflineTxnDetails() {
        return this.offlineTxnDetails;
    }

    public PaymentDetails offlineTxnDetails(String offlineTxnDetails) {
        this.setOfflineTxnDetails(offlineTxnDetails);
        return this;
    }

    public void setOfflineTxnDetails(String offlineTxnDetails) {
        this.offlineTxnDetails = offlineTxnDetails;
    }

    public String getOfflineTxnCollectedBy() {
        return this.offlineTxnCollectedBy;
    }

    public PaymentDetails offlineTxnCollectedBy(String offlineTxnCollectedBy) {
        this.setOfflineTxnCollectedBy(offlineTxnCollectedBy);
        return this;
    }

    public void setOfflineTxnCollectedBy(String offlineTxnCollectedBy) {
        this.offlineTxnCollectedBy = offlineTxnCollectedBy;
    }

    public String getOfflineTxnClearingStatus() {
        return this.offlineTxnClearingStatus;
    }

    public PaymentDetails offlineTxnClearingStatus(String offlineTxnClearingStatus) {
        this.setOfflineTxnClearingStatus(offlineTxnClearingStatus);
        return this;
    }

    public void setOfflineTxnClearingStatus(String offlineTxnClearingStatus) {
        this.offlineTxnClearingStatus = offlineTxnClearingStatus;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public PaymentDetails paymentDate(String paymentDate) {
        this.setPaymentDate(paymentDate);
        return this;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getPaymentInitTime() {
        return this.paymentInitTime;
    }

    public PaymentDetails paymentInitTime(LocalDate paymentInitTime) {
        this.setPaymentInitTime(paymentInitTime);
        return this;
    }

    public void setPaymentInitTime(LocalDate paymentInitTime) {
        this.paymentInitTime = paymentInitTime;
    }

    public LocalDate getPaymentUpdateTime() {
        return this.paymentUpdateTime;
    }

    public PaymentDetails paymentUpdateTime(LocalDate paymentUpdateTime) {
        this.setPaymentUpdateTime(paymentUpdateTime);
        return this;
    }

    public void setPaymentUpdateTime(LocalDate paymentUpdateTime) {
        this.paymentUpdateTime = paymentUpdateTime;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public PaymentDetails paymentStatus(PaymentStatus paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public PaymentDetails createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public PaymentDetails createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public PaymentDetails updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public PaymentDetails updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentDetails order(Order order) {
        this.setOrder(order);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDetails)) {
            return false;
        }
        return id != null && id.equals(((PaymentDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDetails{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", paymentType='" + getPaymentType() + "'" +
            ", onlinePgType='" + getOnlinePgType() + "'" +
            ", pgTxnId='" + getPgTxnId() + "'" +
            ", pgStatus='" + getPgStatus() + "'" +
            ", offlineTxnId='" + getOfflineTxnId() + "'" +
            ", offlineTxnDetails='" + getOfflineTxnDetails() + "'" +
            ", offlineTxnCollectedBy='" + getOfflineTxnCollectedBy() + "'" +
            ", offlineTxnClearingStatus='" + getOfflineTxnClearingStatus() + "'" +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", paymentInitTime='" + getPaymentInitTime() + "'" +
            ", paymentUpdateTime='" + getPaymentUpdateTime() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
