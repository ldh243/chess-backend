package com.chess.chessapi.entities;

import com.chess.chessapi.viewmodels.CategoryViewModel;
import com.chess.chessapi.viewmodels.LessonViewModel;
import com.chess.chessapi.viewmodels.UserDetailViewModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "course")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="courseId",scope = Course.class)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "getCoursePaginations",
                procedureName = "get_course_paginations",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "courseName",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageIndex",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageSize",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "statusId",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "userId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.INOUT,name = "totalElements",type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getCourseByUserId",
                procedureName = "get_courses_by_userid",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "userId",type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getCourseByCategoryId",
                procedureName = "get_courses_by_categoryid",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageIndex",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageSize",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "categoryId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "userId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.INOUT,name = "totalElements",type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getCoursesByLessonId",
                procedureName = "get_courses_by_lessonid",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "lessonId",type = Long.class),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "checkPermissionUserCourse",
                procedureName = "check_permission_user_course",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "userId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "courseId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.INOUT,name = "hasPermission",type = Boolean.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getCoursePaginationsByUserid",
                procedureName = "get_course_paginations_by_userid",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "courseName",type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageIndex",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "pageSize",type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "userId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.INOUT,name = "totalElements",type = Long.class)
                }
        ),
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long courseId;

    @NotNull(message = "Name must not be null")
    @Length(max = 1000,message = "name is required not larger than 1000 characters")
    private String name;

    @Length(max = 1000,message = "Description is required not larger than 1000 characters")
    private String description;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Min(value =0,message = "Point should equal or larger than 0")
    private float point;

    @Column(name = "status_id")
    private Long statusId;

    @Length(max = 255,message = "Image must not be larger than 255 characters")
    private String image;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<UserHasCourse> userHasCourses;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<CategoryHasCourse> categoryHasCourses;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<CourseHasLesson> courseHasLessons;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<LearningLog> learningLogs;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<Exercise> exercises;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    @JsonIgnore
    private List<Review> reviews;

    @Transient
    private List<UserDetailViewModel> userEnrolleds;

    @Transient
    private List<UserDetailViewModel> tutors;

    @Transient
    private List<CategoryViewModel> listCategorys;

    @Transient
    private List<Long> listLearningLogLessonIds;

    @Transient
    private List<LessonViewModel> lessonViewModels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner")
    @JsonIgnore
    private User user;


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonIgnore
    public List<UserHasCourse> getUserHasCourses() {
        return userHasCourses;
    }

    public void setUserHasCourses(List<UserHasCourse> userHasCourses) {
        this.userHasCourses = userHasCourses;
    }

    @JsonIgnore
    public List<CategoryHasCourse> getCategoryHasCourses() {
        return categoryHasCourses;
    }

    public void setCategoryHasCourses(List<CategoryHasCourse> categoryHasCourses) {
        this.categoryHasCourses = categoryHasCourses;
    }

    public List<CategoryViewModel> getListCategorys() {
        return listCategorys;
    }

    public void setListCategorys(List<CategoryViewModel> listCategorys) {
        this.listCategorys = listCategorys;
    }

    public List<CourseHasLesson> getCourseHasLessons() {
        return courseHasLessons;
    }

    public void setCourseHasLessons(List<CourseHasLesson> courseHasLessons) {
        this.courseHasLessons = courseHasLessons;
    }

    public List<LessonViewModel> getLessonViewModels() {
        return lessonViewModels;
    }

    public void setLessonViewModels(List<LessonViewModel> lessonViewModels) {
        this.lessonViewModels = lessonViewModels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LearningLog> getLearningLogs() {
        return learningLogs;
    }

    public void setLearningLogs(List<LearningLog> learningLogs) {
        this.learningLogs = learningLogs;
    }

    public List<Long> getListLearningLogLessonIds() {
        return listLearningLogLessonIds;
    }

    public void setListLearningLogLessonIds(List<Long> listLearningLogLessonIds) {
        this.listLearningLogLessonIds = listLearningLogLessonIds;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<UserDetailViewModel> getUserEnrolleds() {
        return userEnrolleds;
    }

    public void setUserEnrolleds(List<UserDetailViewModel> userEnrolleds) {
        this.userEnrolleds = userEnrolleds;
    }

    public List<UserDetailViewModel> getTutors() {
        return tutors;
    }

    public void setTutors(List<UserDetailViewModel> tutors) {
        this.tutors = tutors;
    }
}
