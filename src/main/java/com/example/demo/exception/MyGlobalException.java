package com.example.demo.exception;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Result;

@ControllerAdvice
@ResponseBody
public class MyGlobalException {
    @ExceptionHandler({Exception.class})
    public Result ReturnException(Exception e, HttpServletResponse response){        response.setStatus(500);
        if (e instanceof BindException){
            BindException b = (BindException) e;//判断抛出的异常是不是BindException异常,如果是则强转为BindException异常
            List<ObjectError> allErrors = b.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(defaultMessage);
        }//上面代码是固定的,可以debug看到相互之间的从属关系以及message在BindException的位置 . 总之就是找出我们写的message发给网页

        String message = e.getMessage();

        String secondPart = "";
        if (message != null) {
            // 使用换行符分隔消息并获取第二段数据
            String[] messageParts = message.split("\n");
            
            // 检查数组是否非空，并且第二段数据存在
            if (messageParts.length > 1) {
                secondPart = messageParts[1]; // 获取第二段数据
            } else {
                // 处理第二段数据不存在的情况
                secondPart = messageParts[0];
            }
        } else {
            // 处理 message 为 null 的情况
            secondPart = "消息为 null";
        }

        if (e instanceof RuntimeException){
            if (e instanceof MethodArgumentNotValidException){
                MethodArgumentNotValidException m = (MethodArgumentNotValidException) e;
                String errorMsg = m.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)//import org.springframework.context.support.DefaultMessageSourceResolvable;
                        .collect(Collectors.joining("\n", "", ""));
            }
            if (e instanceof ConstraintViolationException){
                ConstraintViolationException c = (ConstraintViolationException) e;
                String errorMsg = c.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)//import javax.validation.ConstraintViolation;
                        .collect(Collectors.joining("\n", "", ""));
                return Result.error(errorMsg);
            }
            if (e instanceof SQLException){
                SQLException s = (SQLException) e;
                return Result.error(e.getMessage());
            }
            if(e instanceof ArithmeticException){
                return Result.error("系统出错,请联系管理员");
            }
            if (message.length()<=20){
                return Result.error(message);
            }else{
                return Result.error(secondPart);
            }
        }
        if (message.length()<=20){
            return Result.error(message);
        }else{
            return Result.error(secondPart);
        }
    }
    }