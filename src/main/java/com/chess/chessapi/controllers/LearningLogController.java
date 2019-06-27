package com.chess.chessapi.controllers;

import com.chess.chessapi.constants.AppMessage;
import com.chess.chessapi.constants.AppRole;
import com.chess.chessapi.exceptions.AccessDeniedException;
import com.chess.chessapi.models.CreateResponse;
import com.chess.chessapi.models.JsonResult;
import com.chess.chessapi.security.UserPrincipal;
import com.chess.chessapi.services.LearningLogService;
import com.chess.chessapi.services.UserService;
import com.chess.chessapi.viewmodels.LearningLogCreateViewModel;
import com.chess.chessapi.viewmodels.UninteractiveLessonCreateViewModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/learning-log")
@Api(value = "Learning Log Management")
public class LearningLogController {
    @Autowired
    private LearningLogService learningLogService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create learning log")
    @PostMapping(value = "/create-learning-log")
    @PreAuthorize("hasAuthority("+ AppRole.ROLE_LEARNER_AUTHENTICATIION+")")
    public @ResponseBody JsonResult createLearningLog(@Valid @RequestBody LearningLogCreateViewModel learningLogCreateViewModel, BindingResult bindingResult){
        String message = "";
        boolean isSuccess = true;
        long savedId = 0;
        if(bindingResult.hasErrors()){
            FieldError fieldError = (FieldError)bindingResult.getAllErrors().get(0);
            message = fieldError.getDefaultMessage();
            isSuccess = false;
        }else{
            try{
                UserPrincipal userPrincipal = this.userService.getCurrentUser();
                savedId = this.learningLogService.create(learningLogCreateViewModel,userPrincipal.getId());
                message =  AppMessage.getMessageSuccess(AppMessage.CREATE,AppMessage.LEARNING_LOG);
            }catch (DataIntegrityViolationException ex){
                message = AppMessage.getMessageFail(AppMessage.CREATE,AppMessage.LEARNING_LOG);
                isSuccess = false;
            }
        }
        CreateResponse createResponse = new CreateResponse();
        createResponse.setSavedId(savedId);
        createResponse.setSuccess(isSuccess);
        return new JsonResult(message,createResponse);
    }

    @ApiOperation(value = "get learning log of current user by courseId")
    @GetMapping("/get-current-user-learning-log-by-course-id")
    @PreAuthorize("hasAuthority("+ AppRole.ROLE_LEARNER_AUTHENTICATIION+")")
    public @ResponseBody JsonResult getLearningLogByCourseId(@RequestParam("courseId") long courseId){
        UserPrincipal userPrincipal = this.userService.getCurrentUser();
        return new JsonResult(null,this.learningLogService.getAllByCourseId(courseId,userPrincipal.getId()));
    }
}
