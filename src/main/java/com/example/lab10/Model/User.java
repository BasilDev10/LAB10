package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role in ('JOB_SEEKER', 'EMPLOYER')")
@Check(constraints = "age > 21")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Error: name is empty!")
    @Size(min = 4 , message = "Error: name Length must be more than 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Error: name Must contain only characters (no numbers).")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "Error: email is empty!")
    @Email(message = "Error: email is not valid!")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Error: password is empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @NotNull(message = "Error: age is empty!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 21 ,message = "Error:age must be more then 21")
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "Error: role is empty!")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "Error: role Must be either JOB_SEEKER or EMPLOYER only")
    @Column(columnDefinition = "varchar(30) not null")
    private String role;
}
