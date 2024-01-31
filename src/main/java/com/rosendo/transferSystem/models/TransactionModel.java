package com.rosendo.transferSystem.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_TRANSACTION")
public class TransactionModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idTransaction;
  private UserModel senderTransaction;
  private UserModel receiverTransaction;
  private BigDecimal balanceTransaction;
  private Date timeStamp;
  private StatusTransactionEnum statusTransaction;


  public UUID getIdTransaction() {return idTransaction; }
  public void setIdTransaction(UUID idTransaction) {this.idTransaction = idTransaction; }
  
  public UserModel getSenderTransaction() {return senderTransaction; }
  public void setSenderTransaction(UserModel senderTransaction) {this.senderTransaction = senderTransaction; }
  
  public UserModel getReceiverTransaction() {return receiverTransaction; }
  public void setReceiverTransaction(UserModel receiverTransaction) {this.receiverTransaction = receiverTransaction; }
  
  public BigDecimal getBalanceTransaction() {return balanceTransaction; }
  public void setBalanceTransaction(BigDecimal balanceTransaction) {this.balanceTransaction = balanceTransaction; }
  
  public Date getTimeStamp() {return timeStamp; }
  public void setTimeStamp(Date timeStamp) {this.timeStamp = timeStamp; }
  
  public StatusTransactionEnum getStatusTransaction() {return statusTransaction; }
  public void setStatusTransaction(StatusTransactionEnum statusTransaction) {this.statusTransaction = statusTransaction; }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idTransaction == null) ? 0 : idTransaction.hashCode());
    result = prime * result + ((senderTransaction == null) ? 0 : senderTransaction.hashCode());
    result = prime * result + ((receiverTransaction == null) ? 0 : receiverTransaction.hashCode());
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
    if (senderTransaction == null) {
      if (other.senderTransaction != null)
        return false;
    } else if (!senderTransaction.equals(other.senderTransaction))
      return false;
    if (receiverTransaction == null) {
      if (other.receiverTransaction != null)
        return false;
    } else if (!receiverTransaction.equals(other.receiverTransaction))
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
    if (statusTransaction != other.statusTransaction)
      return false;
    return true;
  }

  
  
}
