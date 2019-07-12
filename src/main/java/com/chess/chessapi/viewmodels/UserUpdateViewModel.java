package com.chess.chessapi.viewmodels;

import com.chess.chessapi.constants.AuthProvider;
import com.chess.chessapi.entities.Certificate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserUpdateViewModel {
    @NotNull(message = "User id is required not null")
    private long userId;

    @Email
    @Length(max = 255, message = "Email shouldn't larger than 255 characters")
    @NotNull(message = "Email is required not null")
    private String email;

    @NotNull(message = "Full Name is required not null")
    @Length(max = 255, message = "Full name shouldn't larger than 255 characters")
    @Column(name = "full_name")
    private String fullName;


    @Length(max = 255, message = "Link avatar shouldn't larger than 255 characters")
    private String avatar;

    @Column(name = "created_date")
    private java.sql.Timestamp createdDate;

    @Column(name = "is_active")
    private boolean isActive;

    private float point;

    @Column(name = "role_id")
    private long roleId;


    @Length(max = 255, message = "Achievement shouldn't larger than 255 characters")
    private String achievement;

    @Valid
    private List<CertificateUpdateViewModel> certificates;

    @NotNull(message = "Provider mut not be null")
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public List<CertificateUpdateViewModel> getCertificates() {
        if(certificates == null){
            return new ArrayList<CertificateUpdateViewModel>();
        }
        return certificates;
    }

    public void setCertificates(List<CertificateUpdateViewModel> certificates) {
        this.certificates = certificates;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}