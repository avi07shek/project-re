package com.system.hotel_reservation_system.pojo;

import com.system.hotel_reservation_system.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPojo {

    private Integer id;

    private Integer location;

    private Integer pricing;

    private Integer comfort;

    private Integer service;

    private String name;

    private String email;

    private String description;

    public ReviewPojo(Review review){
        this.id= review.getId();
        this.location= review.getLocation();
        this.pricing= review.getPricing();
        this.comfort= review.getComfort();
        this.service= review.getService();
        this.name= review.getName();
        this.email= review.getEmail();
        this.description= review.getDescription();
    }

}


