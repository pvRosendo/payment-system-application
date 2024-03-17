package com.rosendo.transferSystem.domain.transactions.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="TB_TRANSACTIONS")
public class TransactionModel extends RepresentationModel<TransactionModel>implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idTransaction;
  private String senderDocumentTransaction;
  private String receiverDocumentTransaction;
  private BigDecimal balanceTransaction;
  private LocalDate timeStamp;
  private StatusTransactionEnum statusTransaction;

  public UUID getIdTransaction() {
    return idTransaction;
  }
  public void setIdTransaction(UUID idTransaction) {
    this.idTransaction = idTransaction;
  }
  public String getSenderDocumentTransaction() {
    return senderDocumentTransaction;
  }
  public void setSenderDocumentTransaction(String senderDocumentTransaction) {
    this.senderDocumentTransaction = senderDocumentTransaction;
  }
  public String getReceiverDocumentTransaction() {
    return receiverDocumentTransaction;
  }
  public void setReceiverDocumentTransaction(String receiverDocumentTransaction) {
    this.receiverDocumentTransaction = receiverDocumentTransaction;
  }
  public BigDecimal getBalanceTransaction() {
    return balanceTransaction;
  }
  public void setBalanceTransaction(BigDecimal balanceTransaction) {
    this.balanceTransaction = balanceTransaction;
  }
  public LocalDate getTimeStamp() {
    return timeStamp;
  }
  public void setTimeStamp(LocalDate timeStamp) {
    this.timeStamp = timeStamp;
  }
  public StatusTransactionEnum getStatusTransaction() {
    return statusTransaction;
  }
  public void setStatusTransaction(StatusTransactionEnum statusTransaction) {
    this.statusTransaction = statusTransaction;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idTransaction == null) ? 0 : idTransaction.hashCode());
    result = prime * result + ((senderDocumentTransaction == null) ? 0 : senderDocumentTransaction.hashCode());
    result = prime * result + ((receiverDocumentTransaction == null) ? 0 : receiverDocumentTransaction.hashCode());
    result = prime * result + ((balanceTransaction == null) ? 0 : balanceTransaction.hashCode());
    result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
    result = prime * result + ((statusTransaction == null) ? 0 : statusTransaction.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TransactionModel other = (TransactionModel) obj;
    if (idTransaction == null) {
      if (other.idTransaction != null)
        return false;
    } else if (!idTransaction.equals(other.idTransaction))
      return false;
    if (senderDocumentTransaction == null) {
      if (other.senderDocumentTransaction != null)
        return false;
    } else if (!senderDocumentTransaction.equals(other.senderDocumentTransaction))
      return false;
    if (receiverDocumentTransaction == null) {
      if (other.receiverDocumentTransaction != null)
        return false;
    } else if (!receiverDocumentTransaction.equals(other.receiverDocumentTransaction))
      return false;
    if (balanceTransaction == null) {
      if (other.balanceTransaction != null)
        return false;
    } else if (!balanceTransaction.equals(other.balanceTransaction))
      return false;
    if (timeStamp == null) {
      if (other.timeStamp != null)
        return false;
    } else if (!timeStamp.equals(other.timeStamp))
      return false;
    return statusTransaction == other.statusTransaction;
  }
  
}
