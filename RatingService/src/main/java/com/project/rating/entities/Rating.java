package com.project.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    public String ratingId;
    public String hotelId;
    public String userId;
    public int rating;
    public String feedback;

}
