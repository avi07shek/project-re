package com.system.hotel_reservation_system.pojo;


import com.system.hotel_reservation_system.entity.User;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Integer id;

    private String email;

    private String fullname;

    private String password;

    public UserPojo(User user){
        this.id= user.getId();
        this.email= user.getEmail();
        this.fullname= user.getFullname();
        this.password=user.getPassword();
    }
}
