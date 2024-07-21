package com.example.p.error;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserError {
    @JsonProperty("Error")
    private String error;

    @JsonIgnore
    public UserError(String s) {
        this.error = s;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
