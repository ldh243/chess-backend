package com.chess.chessapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Length(max = 1000,message = "Content is required not large than 1000 characters")
    private String content;

    @NotNull
    @Length(max = 255,message = "step_code is required not large than 255 characters")
    @Column(name = "step_code")
    private String stepCode;

    @NotNull
    @Length(max = 255,message = "learner_step is required not large than 255 characters")
    @Column(name = "learner_step")
    private String learnerStep;

    @NotNull
    @Length(max = 255,message = "right_response is required not large than 255 characters")
    @Column(name = "right_response")
    private String rightResponse;

    @NotNull
    @Length(max = 255,message = "wrong_response is required not large than 255 characters")
    @Column(name = "wrong_response")
    private String wrongResponse;

    @NotNull
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ilesson_id")
    @JsonBackReference
    private InteractiveLesson interactiveLesson;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public String getLearnerStep() {
        return learnerStep;
    }

    public void setLearnerStep(String learnerStep) {
        this.learnerStep = learnerStep;
    }

    public String getRightResponse() {
        return rightResponse;
    }

    public void setRightResponse(String rightResponse) {
        this.rightResponse = rightResponse;
    }

    public String getWrongResponse() {
        return wrongResponse;
    }

    public void setWrongResponse(String wrongResponse) {
        this.wrongResponse = wrongResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InteractiveLesson getInteractiveLesson() {
        return interactiveLesson;
    }

    public void setInteractiveLesson(InteractiveLesson interactiveLesson) {
        this.interactiveLesson = interactiveLesson;
    }
}