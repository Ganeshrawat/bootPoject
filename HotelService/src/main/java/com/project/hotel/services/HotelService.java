package com.project.hotel.services;

import com.project.hotel.entities.Hotel;
import com.project.hotel.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel getHotel(String id);

    List<Hotel> getAllHotels( );


}
