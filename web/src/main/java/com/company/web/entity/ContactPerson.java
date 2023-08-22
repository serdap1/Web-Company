package com.company.web.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Table(name = "contact")
public class ContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ur_id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "company")
    private String company;
    @Column(name = "message")
    private String message;
    @Column(name = "datestamp")
    private String datestamp;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public String toString() {
        return "{" +
                " ur_id='" + getUr_id() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", mobile='" + getMobile() + "'" +
                ", company='" + getCompany() + "'" +
                ", message='" + getMessage() + "'" +
                ", datestamp='" + getDatestamp() + "'" +
                "}";
    }

    @PrePersist
    public void prePersist() {
        // Set the current date as the datestamp before persisting the entity
        this.datestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

}
