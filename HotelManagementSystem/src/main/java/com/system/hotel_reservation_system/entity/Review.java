package com.system.hotel_reservation_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="review")
public class Review {
    @Id
    @SequenceGenerator(name = "hrs_room_seq_gen", sequenceName = "pms_room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hrs_room_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private Integer location;

    @Column
    private Integer pricing;

    @Column
    private Integer comfort;

    @Column
    private Integer service;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String description;
}
