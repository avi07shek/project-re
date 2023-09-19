package com.system.hotel_reservation_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @SequenceGenerator(name = "hrs_room_seq_gen", sequenceName = "pms_room_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hrs_room_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String checkin;

    @Column
    private String checkout;

    @Column
    private String People;
    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_roomId"))
    private Room roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_userId"))
    private User userId;
}
