package com.example.pickmeup.server;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Rides {
    @SerializedName("rides")
    public List<Ride> rides = new ArrayList<Ride>();

    @Override
    public String toString() {
        return "Rides{" +
                "rides=" + rides +
                '}';
    }
}
