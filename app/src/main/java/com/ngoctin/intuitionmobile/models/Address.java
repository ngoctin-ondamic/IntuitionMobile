package com.ngoctin.intuitionmobile.models;

import java.io.Serializable;

public class Address implements Serializable  {
    private int id;
    private String value;

    public Address() {
    }

    public Address(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public Address(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
