package com.example.TheFit.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class TmpResponse {
    private HttpStatus status;
    private String message;
    private Object result;
}
