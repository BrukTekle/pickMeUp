package com.example.pickmeup.server;

import com.google.gson.annotations.SerializedName;

public class User {
    //{"authorized":true,"":"altima","":"white","":"os","":"os","":2,"":1,"":"os","":123,"":"os1234"}

    @SerializedName("authorized")
    public boolean authorized;

    @SerializedName("car_type")
    public String car_type;

    @SerializedName("color")
    public String color;

    @SerializedName("email")
    public String email;

    @SerializedName("firstname")
    public String firstname;

    @SerializedName("lastname")
    public String lastname;

    @SerializedName("is_driver")
    public int is_driver;

    @SerializedName("id")
    public int id;

    @SerializedName("phone")
    public int phone;

    @SerializedName("plate")
    public String plate;

}
