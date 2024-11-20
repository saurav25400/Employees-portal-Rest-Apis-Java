package codingShuttle.week2.mvc.Tutorials.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotBlank(message="Title is required")
    private String title;
    @AssertTrue(message="department must be active")
    @JsonProperty("isActive")
    private boolean isActive;
    @Past(message="please provide me the past department created date")
    private LocalDate createdAt;

}
