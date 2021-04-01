package com.mondris.question2.demo.Util.Api.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {
    private String status;
    private HttpStatus httpStatus;
    private  String message;
    private  Object data;


    public ApiResponse(String status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
