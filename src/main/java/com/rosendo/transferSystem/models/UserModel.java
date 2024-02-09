package com.rosendo.transferSystem.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String userName;
  private String userDocument;
  private String userEmail;
  private String userPassword;
  private UserTypeEnum userType;
  private BigDecimal userBalance;
  

  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getUserDocument() {
    return userDocument;
  }
  public void setUserDocument(String userDocument) {
    this.userDocument = userDocument;
  }
  public String getUserEmail() {
    return userEmail;
  }
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
  public String getUserPassword() {
    return userPassword;
  }
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
  public UserTypeEnum getUserType() {
    return userType;
  }
  public void setUserType(UserTypeEnum userType) {
    this.userType = userType;
  }
  public BigDecimal getUserBalance() {
    return userBalance;
  }
  public void setUserBalance(BigDecimal userBalance) {
    this.userBalance = userBalance;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    result = prime * result + ((userDocument == null) ? 0 : userDocument.hashCode());
    result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
    result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
    result = prime * result + ((userType == null) ? 0 : userType.hashCode());
    result = prime * result + ((userBalance == null) ? 0 : userBalance.hashCode());
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
    UserModel other = (UserModel) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    if (userDocument == null) {
      if (other.userDocument != null)
        return false;
    } else if (!userDocument.equals(other.userDocument))
      return false;
    if (userEmail == null) {
      if (other.userEmail != null)
        return false;
    } else if (!userEmail.equals(other.userEmail))
      return false;
    if (userPassword == null) {
      if (other.userPassword != null)
        return false;
    } else if (!userPassword.equals(other.userPassword))
      return false;
    if (userType == null) {
      if (other.userType != null)
        return false;
    } else if (!userType.equals(other.userType))
      return false;
    if (userBalance == null) {
        return other.userBalance == null;
    } else return userBalance.equals(other.userBalance);
  }

}
