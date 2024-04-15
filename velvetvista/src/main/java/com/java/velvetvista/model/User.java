package com.java.velvetvista.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="student_email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name="phno")
    private long phoneNumber;
    
    @OneToMany(mappedBy = "user")
    private List<Furniture> furnitureList;
    
    public User(String name, String email, String password, String gender, long phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
    
}
