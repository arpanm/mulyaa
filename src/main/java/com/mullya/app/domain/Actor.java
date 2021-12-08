package com.mullya.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mullya.app.domain.enumeration.ActorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Actor.
 */
@Entity
@Table(name = "actor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email")
    private String email;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    @Column(name = "phone")
    private Long phone;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ActorType type;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "buyerActor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orders", "buyerAddress", "buyerActor", "category", "variant", "subVariant" }, allowSetters = true)
    private Set<Requirement> requirements = new HashSet<>();

    @OneToMany(mappedBy = "actor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "oTPAttempts", "actor" }, allowSetters = true)
    private Set<OTP> oTPS = new HashSet<>();

    @OneToMany(mappedBy = "actor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "hub", "actor" }, allowSetters = true)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "farmer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "biddingDetails", "orders", "farmerAddress", "farmer", "category", "variant", "subVariant" },
        allowSetters = true
    )
    private Set<Stock> stocks = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "orders", "buyerAddress", "biddingDetails", "buyer" }, allowSetters = true)
    private Set<Bids> bids = new HashSet<>();

    @OneToMany(mappedBy = "assignedAgent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "paymentDetails", "remittances", "requirement", "bid", "assignedAgent", "stock" }, allowSetters = true)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "farmer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "farmer", "orders" }, allowSetters = true)
    private Set<RemittanceDetails> remittanceDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Actor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public Actor email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return this.phone;
    }

    public Actor phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getIsEmailVerified() {
        return this.isEmailVerified;
    }

    public Actor isEmailVerified(Boolean isEmailVerified) {
        this.setIsEmailVerified(isEmailVerified);
        return this;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsPhoneVerified() {
        return this.isPhoneVerified;
    }

    public Actor isPhoneVerified(Boolean isPhoneVerified) {
        this.setIsPhoneVerified(isPhoneVerified);
        return this;
    }

    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Actor isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return this.password;
    }

    public Actor password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ActorType getType() {
        return this.type;
    }

    public Actor type(ActorType type) {
        this.setType(type);
        return this;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public Actor createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Actor createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public Actor updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Actor updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<Requirement> getRequirements() {
        return this.requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        if (this.requirements != null) {
            this.requirements.forEach(i -> i.setBuyerActor(null));
        }
        if (requirements != null) {
            requirements.forEach(i -> i.setBuyerActor(this));
        }
        this.requirements = requirements;
    }

    public Actor requirements(Set<Requirement> requirements) {
        this.setRequirements(requirements);
        return this;
    }

    public Actor addRequirement(Requirement requirement) {
        this.requirements.add(requirement);
        requirement.setBuyerActor(this);
        return this;
    }

    public Actor removeRequirement(Requirement requirement) {
        this.requirements.remove(requirement);
        requirement.setBuyerActor(null);
        return this;
    }

    public Set<OTP> getOTPS() {
        return this.oTPS;
    }

    public void setOTPS(Set<OTP> oTPS) {
        if (this.oTPS != null) {
            this.oTPS.forEach(i -> i.setActor(null));
        }
        if (oTPS != null) {
            oTPS.forEach(i -> i.setActor(this));
        }
        this.oTPS = oTPS;
    }

    public Actor oTPS(Set<OTP> oTPS) {
        this.setOTPS(oTPS);
        return this;
    }

    public Actor addOTP(OTP oTP) {
        this.oTPS.add(oTP);
        oTP.setActor(this);
        return this;
    }

    public Actor removeOTP(OTP oTP) {
        this.oTPS.remove(oTP);
        oTP.setActor(null);
        return this;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        if (this.addresses != null) {
            this.addresses.forEach(i -> i.setActor(null));
        }
        if (addresses != null) {
            addresses.forEach(i -> i.setActor(this));
        }
        this.addresses = addresses;
    }

    public Actor addresses(Set<Address> addresses) {
        this.setAddresses(addresses);
        return this;
    }

    public Actor addAddress(Address address) {
        this.addresses.add(address);
        address.setActor(this);
        return this;
    }

    public Actor removeAddress(Address address) {
        this.addresses.remove(address);
        address.setActor(null);
        return this;
    }

    public Set<Stock> getStocks() {
        return this.stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        if (this.stocks != null) {
            this.stocks.forEach(i -> i.setFarmer(null));
        }
        if (stocks != null) {
            stocks.forEach(i -> i.setFarmer(this));
        }
        this.stocks = stocks;
    }

    public Actor stocks(Set<Stock> stocks) {
        this.setStocks(stocks);
        return this;
    }

    public Actor addStock(Stock stock) {
        this.stocks.add(stock);
        stock.setFarmer(this);
        return this;
    }

    public Actor removeStock(Stock stock) {
        this.stocks.remove(stock);
        stock.setFarmer(null);
        return this;
    }

    public Set<Bids> getBids() {
        return this.bids;
    }

    public void setBids(Set<Bids> bids) {
        if (this.bids != null) {
            this.bids.forEach(i -> i.setBuyer(null));
        }
        if (bids != null) {
            bids.forEach(i -> i.setBuyer(this));
        }
        this.bids = bids;
    }

    public Actor bids(Set<Bids> bids) {
        this.setBids(bids);
        return this;
    }

    public Actor addBids(Bids bids) {
        this.bids.add(bids);
        bids.setBuyer(this);
        return this;
    }

    public Actor removeBids(Bids bids) {
        this.bids.remove(bids);
        bids.setBuyer(null);
        return this;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        if (this.orders != null) {
            this.orders.forEach(i -> i.setAssignedAgent(null));
        }
        if (orders != null) {
            orders.forEach(i -> i.setAssignedAgent(this));
        }
        this.orders = orders;
    }

    public Actor orders(Set<Order> orders) {
        this.setOrders(orders);
        return this;
    }

    public Actor addOrder(Order order) {
        this.orders.add(order);
        order.setAssignedAgent(this);
        return this;
    }

    public Actor removeOrder(Order order) {
        this.orders.remove(order);
        order.setAssignedAgent(null);
        return this;
    }

    public Set<RemittanceDetails> getRemittanceDetails() {
        return this.remittanceDetails;
    }

    public void setRemittanceDetails(Set<RemittanceDetails> remittanceDetails) {
        if (this.remittanceDetails != null) {
            this.remittanceDetails.forEach(i -> i.setFarmer(null));
        }
        if (remittanceDetails != null) {
            remittanceDetails.forEach(i -> i.setFarmer(this));
        }
        this.remittanceDetails = remittanceDetails;
    }

    public Actor remittanceDetails(Set<RemittanceDetails> remittanceDetails) {
        this.setRemittanceDetails(remittanceDetails);
        return this;
    }

    public Actor addRemittanceDetails(RemittanceDetails remittanceDetails) {
        this.remittanceDetails.add(remittanceDetails);
        remittanceDetails.setFarmer(this);
        return this;
    }

    public Actor removeRemittanceDetails(RemittanceDetails remittanceDetails) {
        this.remittanceDetails.remove(remittanceDetails);
        remittanceDetails.setFarmer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Actor)) {
            return false;
        }
        return id != null && id.equals(((Actor) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Actor{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", isEmailVerified='" + getIsEmailVerified() + "'" +
            ", isPhoneVerified='" + getIsPhoneVerified() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", password='" + getPassword() + "'" +
            ", type='" + getType() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
