package com.drac.model;


/**
 * Created by anil on 3/17/17.
 */
public class ResponseObj {
    private Boolean responseStatus = true;
    private Object responseData;

    public ResponseObj(Object responseData) {
        this.responseData = responseData;
    }

    public ResponseObj(Boolean responseStatus, Object responseData) {
        this.responseStatus = responseStatus;
        this.responseData = responseData;
    }

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
