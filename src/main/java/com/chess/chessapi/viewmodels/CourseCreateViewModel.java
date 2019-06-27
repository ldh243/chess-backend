package com.chess.chessapi.viewmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class CourseCreateViewModel {

    @NotNull(message = "Name must not be null")
    @Length(max = 1000,message = "name is required not large than 1000 characters")
    private String name;

    private String description;

    private Float point;
    private Long statusId;

    private String image;

    @NotNull(message = "List category must not be null")
    private List<Long> listCategoryIds;

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

    public List<Long> getListCategoryIds() {
        return listCategoryIds;
    }

    public void setListCategoryIds(List<Long> listCategoryIds) {
        this.listCategoryIds = listCategoryIds;
    }
}
