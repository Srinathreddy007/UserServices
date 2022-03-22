package com.example.userservice.model;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User
{
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sex;


}
