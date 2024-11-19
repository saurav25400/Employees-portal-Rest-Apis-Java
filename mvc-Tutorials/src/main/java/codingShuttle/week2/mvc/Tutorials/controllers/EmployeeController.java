package codingShuttle.week2.mvc.Tutorials.controllers;

import codingShuttle.week2.mvc.Tutorials.CustomException.NoResourceFoundException;
import codingShuttle.week2.mvc.Tutorials.dto.EmployeeDTO;
import codingShuttle.week2.mvc.Tutorials.repositories.EmployeeeRepository;
import codingShuttle.week2.mvc.Tutorials.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
//@RequestMapping(path="/emp")   // this will contain all the starting common routes for all the controllers
public class EmployeeController {


    private final EmployeeService employeeService;

    //constructor injection
    public EmployeeController(EmployeeeRepository employeeeRepository, EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO>empDto=employeeService.getEmployee(id);
//        return empDto.map(emp->ResponseEntity.ok(emp)).orElse(ResponseEntity.notFound().build());
        return empDto.map(emp->ResponseEntity.ok(emp)).orElseThrow(()-> new NoResourceFoundException("no resource is found with the given id "+id));

    }


    //Handling the exceptions at the controller level
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> noFoundException(NoSuchElementException exception){
//        return new ResponseEntity<>("employee was not found",HttpStatus.NOT_FOUND);
//    }

    @PostMapping(path = "employee")
    public ResponseEntity<EmployeeDTO> postdata( @Valid @RequestBody  EmployeeDTO employee) {
        return new ResponseEntity<>(employeeService.postEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping(path = "employee/update")
    public ResponseEntity<String> updateData(@RequestParam(name = "age", required = false) String Age, @RequestParam(name = "email", required = true) String email) {
        return ResponseEntity.ok(employeeService.updateData(Age, email));
    }

    @DeleteMapping(path = "employee/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable(name = "id") Long employeeId) {
        return new ResponseEntity<>(employeeService.deleteData(employeeId),HttpStatus.OK);
    }

    @GetMapping(path = "get/all/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllData() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PutMapping(path = "employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO, employeeId));
    }
    @DeleteMapping(path = "employee/get/then-delete/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));

    }
    //patch request

    @PatchMapping(path = "employee/update/by/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialField(@RequestBody Map<String, Object> updateField, @PathVariable  Long employeeId){
        return ResponseEntity.ok(employeeService.partialUpdate(updateField,employeeId));
    }


}
