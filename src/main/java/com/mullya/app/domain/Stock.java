package com.mullya.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mullya.app.domain.enumeration.StockStatus;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stock extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "min_price")
    private Float minPrice;

    @Column(name = "max_price")
    private Float maxPrice;

    @Column(name = "quantity_kg")
    private Float quantityKg;

    @Column(name = "expiry")
    private String expiry;

    @Column(name = "avialable_from")
    private String avialableFrom;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus = StockStatus.New;

    @NotNull
    @Column(name = "is_open_for_bidding")
    private Boolean isOpenForBidding = false;

    @OneToMany(mappedBy = "stock")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "bids", "stock" }, allowSetters = true)
    private Set<BiddingDetails> biddingDetails = new HashSet<>();

    @OneToMany(mappedBy = "stock")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "paymentDetails", "remittances", "requirement", "bid", "assignedAgent", "stock" }, allowSetters = true)
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "hub", "user" }, allowSetters = true)
    private Address farmerAddress;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "requirements", "oTPS", "addresses", "stocks", "bids", "orders", "remittanceDetails" },
        allowSetters = true
    )
    private User farmer;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "catalogues",
            "categoryStocks",
            "variantStocks",
            "subVariantStocks",
            "categoryRequirements",
            "variantRequirements",
            "subVariantRequirements",
            "parent",
        },
        allowSetters = true
    )
    private Catalogue category;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "catalogues",
            "categoryStocks",
            "variantStocks",
            "subVariantStocks",
            "categoryRequirements",
            "variantRequirements",
            "subVariantRequirements",
            "parent",
        },
        allowSetters = true
    )
    private Catalogue variant;

    @ManyToOne
    @JsonIgnoreProperties(
        value = {
            "catalogues",
            "categoryStocks",
            "variantStocks",
            "subVariantStocks",
            "categoryRequirements",
            "variantRequirements",
            "subVariantRequirements",
            "parent",
        },
        allowSetters = true
    )
    private Catalogue subVariant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Stock id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMinPrice() {
        return this.minPrice;
    }

    public Stock minPrice(Float minPrice) {
        this.setMinPrice(minPrice);
        return this;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return this.maxPrice;
    }

    public Stock maxPrice(Float maxPrice) {
        this.setMaxPrice(maxPrice);
        return this;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getQuantityKg() {
        return this.quantityKg;
    }

    public Stock quantityKg(Float quantityKg) {
        this.setQuantityKg(quantityKg);
        return this;
    }

    public void setQuantityKg(Float quantityKg) {
        this.quantityKg = quantityKg;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public Stock expiry(String expiry) {
        this.setExpiry(expiry);
        return this;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getAvialableFrom() {
        return this.avialableFrom;
    }

    public Stock avialableFrom(String avialableFrom) {
        this.setAvialableFrom(avialableFrom);
        return this;
    }

    public void setAvialableFrom(String avialableFrom) {
        this.avialableFrom = avialableFrom;
    }

    public String getDescription() {
        return this.description;
    }

    public Stock description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StockStatus getStockStatus() {
        return this.stockStatus;
    }

    public Stock stockStatus(StockStatus stockStatus) {
        this.setStockStatus(stockStatus);
        return this;
    }

    public void setStockStatus(StockStatus stockStatus) {
        if (stockStatus != null) {
            this.stockStatus = stockStatus;
        }
    }

    public Boolean getIsOpenForBidding() {
        return this.isOpenForBidding;
    }

    public Stock isOpenForBidding(Boolean isOpenForBidding) {
        this.setIsOpenForBidding(isOpenForBidding);
        return this;
    }

    public void setIsOpenForBidding(Boolean isOpenForBidding) {
        this.isOpenForBidding = isOpenForBidding;
    }

    public Set<BiddingDetails> getBiddingDetails() {
        return this.biddingDetails;
    }

    public void setBiddingDetails(Set<BiddingDetails> biddingDetails) {
        if (this.biddingDetails != null) {
            this.biddingDetails.forEach(i -> i.setStock(null));
        }
        if (biddingDetails != null) {
            biddingDetails.forEach(i -> i.setStock(this));
        }
        this.biddingDetails = biddingDetails;
    }

    public Stock biddingDetails(Set<BiddingDetails> biddingDetails) {
        this.setBiddingDetails(biddingDetails);
        return this;
    }

    public Stock addBiddingDetails(BiddingDetails biddingDetails) {
        this.biddingDetails.add(biddingDetails);
        biddingDetails.setStock(this);
        return this;
    }

    public Stock removeBiddingDetails(BiddingDetails biddingDetails) {
        this.biddingDetails.remove(biddingDetails);
        biddingDetails.setStock(null);
        return this;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        if (this.orders != null) {
            this.orders.forEach(i -> i.setStock(null));
        }
        if (orders != null) {
            orders.forEach(i -> i.setStock(this));
        }
        this.orders = orders;
    }

    public Stock orders(Set<Order> orders) {
        this.setOrders(orders);
        return this;
    }

    public Stock addOrder(Order order) {
        this.orders.add(order);
        order.setStock(this);
        return this;
    }

    public Stock removeOrder(Order order) {
        this.orders.remove(order);
        order.setStock(null);
        return this;
    }

    public Address getFarmerAddress() {
        return this.farmerAddress;
    }

    public void setFarmerAddress(Address address) {
        this.farmerAddress = address;
    }

    public Stock farmerAddress(Address address) {
        this.setFarmerAddress(address);
        return this;
    }

    public User getFarmer() {
        return this.farmer;
    }

    public void setFarmer(User user) {
        this.farmer = user;
    }

    public Stock farmer(User user) {
        this.setFarmer(user);
        return this;
    }

    public Catalogue getCategory() {
        return this.category;
    }

    public void setCategory(Catalogue catalogue) {
        this.category = catalogue;
    }

    public Stock category(Catalogue catalogue) {
        this.setCategory(catalogue);
        return this;
    }

    public Catalogue getVariant() {
        return this.variant;
    }

    public void setVariant(Catalogue catalogue) {
        this.variant = catalogue;
    }

    public Stock variant(Catalogue catalogue) {
        this.setVariant(catalogue);
        return this;
    }

    public Catalogue getSubVariant() {
        return this.subVariant;
    }

    public void setSubVariant(Catalogue catalogue) {
        this.subVariant = catalogue;
    }

    public Stock subVariant(Catalogue catalogue) {
        this.setSubVariant(catalogue);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            ", minPrice=" + getMinPrice() +
            ", maxPrice=" + getMaxPrice() +
            ", quantityKg=" + getQuantityKg() +
            ", expiry='" + getExpiry() + "'" +
            ", avialableFrom='" + getAvialableFrom() + "'" +
            ", description='" + getDescription() + "'" +
            ", stockStatus='" + getStockStatus() + "'" +
            ", isOpenForBidding='" + getIsOpenForBidding() + "'" +
            "}";
    }
}
