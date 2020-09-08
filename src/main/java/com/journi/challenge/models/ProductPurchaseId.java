package com.journi.challenge.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductPurchaseId implements Serializable {

  @Column(name = "productId")
  private String productId;

  @Column(name = "invoiceNumber")
  private String invoiceNumber;

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductPurchaseId that = (ProductPurchaseId) o;
    return Objects.equals(productId, that.productId)
        && Objects.equals(invoiceNumber, that.invoiceNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, invoiceNumber);
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }
}
