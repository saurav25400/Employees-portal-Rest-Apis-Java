package codingShuttle.week2.mvc.Tutorials.dto;

import codingShuttle.week2.mvc.Tutorials.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int id;
    @NotBlank(message="name is required")
    private String name;

   @Min(value=18)
   @Max(value=80)
    private int age;
   @Email(message="email must be valid")
    private String email;
   @Past(message = "joining date must be of the past")
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    @AssertTrue(message="only active employee required")
    public boolean isActive;

    //@Pattern(regexp = "^(USER|ADMIN)$")   //---> as we will use custom validations here.
//    custom validation here
    @EmployeeRoleValidation
    private String role;   //USER,ADMIN ---> means role can be either user or admin

    @Digits(integer = 6,fraction=2)
//    @DecimalMax(value= "150000.80",message="max value can not be greater than the specified value")
//    @DecimalMin(value="100000.90",message="min value can not be greater than the specified value")
    private Double salary;





//    public EmployeeDTO(){
//
//    }

    // Constructor
//    public EmployeeDTO(int id, String name, int age, String email, LocalDate doj) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.email = email;
//        this.dateOfJoining = doj;
//    }

//    // Getters
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
}
