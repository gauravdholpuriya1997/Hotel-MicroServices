package com.lcws.hotel.services;

import com.lcws.hotel.emtites.Hotel;

import java.util.List;

public interface HotelService {

    // Create

    Hotel create(Hotel hotel);

    // GET ALL
    List<Hotel> getAll();

    //GET SINGLE

    Hotel get(String id);



}
