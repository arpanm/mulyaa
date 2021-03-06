package com.mullya.app.service.dto;

import com.mullya.app.config.Constants;
import com.mullya.app.domain.Authority;
import com.mullya.app.domain.User;
import com.mullya.app.domain.enumeration.ActorType;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.*;

/**
 * A DTO representing a user, with his authorities.
 */
public class AdminUserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private String createdBy;

    private Instant createdOn;

    private String updatedBy;

    private Instant updatedOn;

    private Set<String> authorities;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private Long phone;

    private boolean isEmailVerified;

    private boolean isPhoneVerified;

    private boolean isActive;

    private ActorType type;

    public AdminUserDTO() {
        // Empty constructor needed for Jackson.
    }

    public AdminUserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.phone = user.getPhone();
        this.isEmailVerified = user.getIsEmailVerified();
        this.isPhoneVerified = user.getIsPhoneVerified();
        this.isActive = user.getIsActive();
        this.type = user.getType();
        this.createdBy = user.getCreatedBy();
        this.createdOn = user.getCreatedOn();
        this.updatedBy = user.getUpdatedBy();
        this.updatedOn = user.getUpdatedOn();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public boolean getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ActorType getType() {
        return type;
    }

    public void setType(ActorType type) {
        this.type = type;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminUserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", phone=" + getPhone() +
            ", isEmailVerified='" + getIsEmailVerified() + "'" +
            ", isPhoneVerified='" + getIsPhoneVerified() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", type='" + getType() + "'" +
            ", createdBy=" + createdBy +
            ", createdOn=" + createdOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", authorities=" + authorities +
            "}";
    }
}
