package com.prueba.istrategiesspring.responses;

import org.springframework.stereotype.Component;

@Component
public class ServiceResponse {
    private Boolean status;

    private String message;

    private Object data;

    public ServiceResponse() { }

    public ServiceResponse(Boolean status, String message,Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ServiceResponse printMessage(){
        System.out.println(this.message);
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
