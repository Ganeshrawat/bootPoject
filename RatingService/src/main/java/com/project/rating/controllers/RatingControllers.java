package com.project.rating.controllers;

import com.project.rating.entities.Rating;
import com.project.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingControllers {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1=ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);

    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllRating")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> rating= ratingService.getAllRatings();
        return ResponseEntity.ok(rating);
    }


    @GetMapping("/getRatingByUserId/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> ratingList= ratingService.getRatingByUsderId(userId);
        return ResponseEntity.ok(ratingList);

    }

    @GetMapping("/getRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingList= ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratingList);
    }

}
