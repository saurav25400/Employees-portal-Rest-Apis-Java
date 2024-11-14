package codingShuttle.week2.mvc.Tutorials.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // for auto increment we use it here.
    private Long id;
    private String name;
    private int age;
    private String email;
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private boolean isActive;

    private String role;

    private Double salary;


//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
}
