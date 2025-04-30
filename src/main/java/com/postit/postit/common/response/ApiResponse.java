package com.postit.postit.common.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private boolean success;
    private T data;

    public ApiResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;

        this.success = statusCode == HttpStatus.OK.value();

    }

}
