package com.postit.postit.common.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private boolean success = false;
    private T data;

    public ApiResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;

        this.success = statusCode == HttpStatus.OK.value();

    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(int statusCode, String message, T data){
        ApiResponse<T> response = new ApiResponse<>(statusCode, message);
        response.setSuccess(false);
        response.setData(data);
        return  ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(int statusCode, String message){
        return failedResponse(statusCode, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(String message){
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(T data){
        return failedResponse(HttpStatus.BAD_REQUEST.value(), null, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(int statusCode, String message, T data){
        ApiResponse<T> response = new ApiResponse<>(statusCode, message);
        response.setSuccess(true);
        response.setData(data);
        return  ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(String message){
        return successResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successResponse(T data){
        return successResponse(HttpStatus.OK.value(), null, data);
    }

}
