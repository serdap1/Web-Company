package com.company.web.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ur_id;

    @Column(name = "ur_username")
    private String username;

    @Column(name = "ur_password")
    private String password;

    @Column(name = "ur_email")
    private String email;

    @Column(name = "ur_address")
    private String address;

    @Column(name = "ur_created_date")
    private LocalDate createdDate;

    @Column(name = "ur_mobile")
    private String mobile;

    @Column(name = "ur_status")
    private String status;

    @Column(name = "gr_id")
    private Integer gr_id;
}
