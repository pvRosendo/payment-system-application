package com.rosendo.transferSystem.domain.scheduledTransfer.models;

import com.rosendo.transferSystem.domain.transactions.models.StatusTransactionEnum;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="TB_SCHEDULED")
public class ScheduledTransferModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idScheduledTransfer;
    private String SenderScheduledTransfer;
    private String receiverScheduledTransfer;
    private BigDecimal balanceTransaction;
    private LocalDateTime timeStamp;
    private StatusTransactionEnum statusTransaction;

    public UUID getIdScheduledTransfer() {
        return idScheduledTransfer;
    }

    public void setIdScheduledTransfer(UUID idScheduledTransfer) {
        this.idScheduledTransfer = idScheduledTransfer;
    }

    public String getSenderScheduledTransfer() {
        return SenderScheduledTransfer;
    }

    public void setSenderScheduledTransfer(String senderScheduledTransfer) {
        SenderScheduledTransfer = senderScheduledTransfer;
    }

    public String getReceiverScheduledTransfer() {
        return receiverScheduledTransfer;
    }

    public void setReceiverScheduledTransfer(String receiverScheduledTransfer) {
        this.receiverScheduledTransfer = receiverScheduledTransfer;
    }

    public BigDecimal getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(BigDecimal balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public StatusTransactionEnum getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(StatusTransactionEnum statusTransaction) {
        this.statusTransaction = statusTransaction;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledTransferModel that = (ScheduledTransferModel) o;

        if (!Objects.equals(idScheduledTransfer, that.idScheduledTransfer))
            return false;
        if (!Objects.equals(SenderScheduledTransfer, that.SenderScheduledTransfer))
            return false;
        if (!Objects.equals(receiverScheduledTransfer, that.receiverScheduledTransfer))
            return false;
        return Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        int result = idScheduledTransfer != null ? idScheduledTransfer.hashCode() : 0;
        result = 31 * result + (SenderScheduledTransfer != null ? SenderScheduledTransfer.hashCode() : 0);
        result = 31 * result + (receiverScheduledTransfer != null ? receiverScheduledTransfer.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }
}
