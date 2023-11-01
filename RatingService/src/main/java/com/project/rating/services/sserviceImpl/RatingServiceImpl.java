package com.project.rating.services.sserviceImpl;

import com.project.rating.entities.Rating;
import com.project.rating.repositories.RatingRepo;
import com.project.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        Rating rating1 = ratingRepo.save(rating);
        return rating1;
    }

    @Override
    public List<Rating> getRatingByUsderId(String userId) {
        return ratingRepo.findByUserId(userId);

    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
