package com.journi.challenge.models;

import javax.persistence.*;

@Entity
@Table
public class ProductPurchase implements java.io.Serializable {

  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "productId", column = @Column(name = "productId", nullable = false)),
    @AttributeOverride(
        name = "invoiceNumber",
        column = @Column(name = "invoiceNumber", nullable = false))
  })
  private ProductPurchaseId productPurchaseId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns({
    @JoinColumn(
        name = "productId",
        referencedColumnName = "productId",
        nullable = false,
        insertable = false,
        updatable = false)
  })
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumns({
    @JoinColumn(
        name = "invoiceNumber",
        referencedColumnName = "invoiceNumber",
        nullable = false,
        insertable = false,
        updatable = false)
  })
  private Purchase purchase;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Purchase getPurchase() {
    return purchase;
  }

  public void setPurchase(Purchase purchase) {
    this.purchase = purchase;
  }

  public ProductPurchaseId getProductPurchaseId() {
    return productPurchaseId;
  }

  public void setProductPurchaseId(ProductPurchaseId productPurchaseId) {
    this.productPurchaseId = productPurchaseId;
  }
}
