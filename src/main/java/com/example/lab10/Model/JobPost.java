package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "salary > 0")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Error: title is empty!")
    @Size(min = 4 , message = "Error: title Length must be more than 4 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "Error: description is empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotEmpty(message = "Error: location is empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String location;

    @NotNull(message = "Error: salary is empty!")
    @Positive(message = "Error: salary Must be a non-negative number")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @NotNull(message = "Error: postDate is null")
    @Column(columnDefinition = "date not null")
    private LocalDate postDate;
}
