package com.chess.chessapi.entities;

import com.chess.chessapi.constants.AuthProvider;
import com.chess.chessapi.viewmodels.CourseDetailViewModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="userId",scope = User.class)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "getUsersByCourseid",
                procedureName = "get_users_by_courseid",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "courseId",type = Long.class)
                }
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Certificates> cetificates;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonIgnore
    private List<UserHasCourse> userHasCourses;

    @Transient
    private List<CourseDetailViewModel> courseDetailViewModels;

    @NotNull
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


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
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

    public List<Certificates> getCetificates() {
        return cetificates;
    }

    public void setCetificates(List<Certificates> cetificates) {
        this.cetificates = cetificates;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    @JsonIgnore
    public List<UserHasCourse> getUserHasCourses() {
        return userHasCourses;
    }

    public void setUserHasCourses(List<UserHasCourse> userHasCourses) {
        this.userHasCourses = userHasCourses;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public List<CourseDetailViewModel> getCourseDetailViewModels() {
        return courseDetailViewModels;
    }

    public void setCourseDetailViewModels(List<CourseDetailViewModel> courseDetailViewModels) {
        this.courseDetailViewModels = courseDetailViewModels;
    }
}
