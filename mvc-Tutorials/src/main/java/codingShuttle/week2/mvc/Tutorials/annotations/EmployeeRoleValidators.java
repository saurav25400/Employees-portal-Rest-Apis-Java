package codingShuttle.week2.mvc.Tutorials.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.*;

public class EmployeeRoleValidators implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String>roles =List.of("ADMIN","USER");
        return roles.contains(inputRole);
    }
}
