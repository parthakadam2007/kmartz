package com.example.kmartz.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "shopkeepers",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"phone_no"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopKeeperId;

    @Column(name = "firstName", nullable = true)
    private String firstName;

    @Column(name = "lastName", nullable = true)
    private String lastName;

    @Column(nullable = false, unique = true )
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "phoneNo", nullable = true, unique = true, length = 15)
    private String phoneNo;

    @Column(name = "address", nullable = true)
    private String address;
}
