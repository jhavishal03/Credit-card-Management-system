package com.Dark.Creditcardmanagementsystem.enums;

public enum Status {
    SUCCESS("success"),FAILURE("failure"),INPROGRESS("inProgress");
    private String value;

    private Status(String value){
        this.value=value;
    }
    public String getStatus(){
        return this.value;
    }
}
