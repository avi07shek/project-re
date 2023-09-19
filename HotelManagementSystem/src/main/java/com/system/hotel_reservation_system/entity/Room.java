package com.system.hotel_reservation_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rooms")
public class Room{
    @Id
    @SequenceGenerator(name = "hrs_room_seq_gen", sequenceName = "pms_room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hrs_room_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;



    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String room_description;

    @Column(nullable = false)
    private String room_type;



    @Column(nullable = false)
    private Integer no_of_people;

    @Column(nullable = false)
    private String beds;
    

    @Column
    private String image1;

    @Transient
    private String image1Base64;

    @Column
    private String image2;
    @Transient
    private String image2Base64;
    @Column
    private String image3;
    @Transient
    private String image3Base64;
    @Column
    private String image4;
    @Transient
    private String image4Base64;
    @Column
    private String image5;
    @Transient
    private String image5Base64;
}