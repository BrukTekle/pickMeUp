package com.example.pickmeup.server;

import com.google.gson.annotations.SerializedName;

public class Ride {
    @SerializedName("driver_id")
    public int driver_id;

    @SerializedName("ride_id")
    public int ride_id;

    @SerializedName("firstname")
    public String firstname;

    @SerializedName("lastname")
    public String lastname;

    @SerializedName("phone")
    public String phone;

    @SerializedName("email")
    public String email;

    @SerializedName("departure")
    public String departure;

    @SerializedName("from_lng")
    public String from_lng;

    @SerializedName("from_lat")
    public String from_lat;

    @SerializedName("to_lng")
    public String to_lng;

    @SerializedName("to_lat")
    public String to_lat;

    @SerializedName("fare")
    public double fare;

    @SerializedName("seats_available")
    public int seats_available;

    @SerializedName("car_type")
    public String car_type;

    @SerializedName("plate")
    public String plate;

    @SerializedName("color")
    public String color;

    @SerializedName("comment")
    public String comment;

    @Override
    public String toString() {
        return "Ride{" +
                "driver_id=" + driver_id +
                ", ride_id=" + ride_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", departure='" + departure + '\'' +
                ", from_lng='" + from_lng + '\'' +
                ", from_lat='" + from_lat + '\'' +
                ", to_lng='" + to_lng + '\'' +
                ", to_lat='" + to_lat + '\'' +
                ", fare=" + fare +
                ", seats_available=" + seats_available +
                ", car_type='" + car_type + '\'' +
                ", plate='" + plate + '\'' +
                ", color='" + color + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
