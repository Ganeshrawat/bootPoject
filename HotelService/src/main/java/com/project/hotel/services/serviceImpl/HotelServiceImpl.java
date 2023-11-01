package com.project.hotel.serviceImpl;

import com.project.hotel.entities.Hotel;
import com.project.hotel.exception.ResourceNotFoundException;
import com.project.hotel.repositories.HotelRepo;
import com.project.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {
        Hotel hotel= hotelRepo.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Hotel not found with id" + id));
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }
}
