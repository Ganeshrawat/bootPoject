package com.project.rating.services;

import com.project.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating( Rating rating);

    List<Rating> getRatingByUsderId(String userId);

    List<Rating> getAllRatings();

    List<Rating> getRatingByHotelId(String hotelId);
}
