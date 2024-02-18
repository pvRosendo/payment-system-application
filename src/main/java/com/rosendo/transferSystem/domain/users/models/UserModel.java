package com.rosendo.transferSystem.domain.users.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserModel userModel = (UserModel) o;

    if (!Objects.equals(id, userModel.id)) return false;
    if (!Objects.equals(userDocument, userModel.userDocument))
      return false;
    if (!Objects.equals(userEmail, userModel.userEmail)) return false;
    return Objects.equals(userPassword, userModel.userPassword);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (userDocument != null ? userDocument.hashCode() : 0);
    result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
    result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
    return result;
  }
}
