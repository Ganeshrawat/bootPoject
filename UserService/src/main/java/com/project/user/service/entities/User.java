package com.project.user.service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "id")
    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient
    private List<Ratings> rating = new ArrayList<>();


}
