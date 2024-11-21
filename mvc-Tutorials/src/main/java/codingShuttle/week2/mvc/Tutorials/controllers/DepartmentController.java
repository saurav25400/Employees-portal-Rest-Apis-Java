package codingShuttle.week2.mvc.Tutorials.controllers;

import codingShuttle.week2.mvc.Tutorials.dto.DepartmentDTO;
import codingShuttle.week2.mvc.Tutorials.entities.DepartmentEntity;
import codingShuttle.week2.mvc.Tutorials.services.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path="/departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.ok(this.departmentService.getAllDepartment());
    }

    @PostMapping(path="/departments")
    public ResponseEntity<DepartmentDTO> postDepartment(@Valid @RequestBody DepartmentDTO departmentData){
        return new ResponseEntity<>(this.departmentService.postDepartment(departmentData), HttpStatus.CREATED);
    }

    @GetMapping(path = "/departments/{DeptId}")
    public ResponseEntity<DepartmentDTO>getADepartment(@PathVariable(name="DeptId") @NotNull(message = "DeptId can not be null") Long id){
        return new ResponseEntity<>(this.departmentService.getADepartment(id),HttpStatus.OK) ;
    }

    @DeleteMapping(path ="/department/delete/{id}")
    public ResponseEntity<Boolean>deleteDepartment(@PathVariable @NotNull(message = "id can not be null") Long id){
        Boolean isDeleted=this.departmentService.deleteDepartment(id);
        if(!isDeleted){
//            return (ResponseEntity<Boolean>) ResponseEntity.notFound();
            return new ResponseEntity<>(isDeleted,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(isDeleted,HttpStatus.OK);

    }

    @PutMapping(path = "/departments/update/dept/{id}")
    public ResponseEntity<DepartmentDTO>updateDepartment(@PathVariable Long id,@RequestBody DepartmentDTO departmentDTO){
      DepartmentDTO departmentDTO1=this.departmentService.updateDepartment(id,departmentDTO);
         if(departmentDTO1==null){
             return new ResponseEntity<>(departmentDTO1,HttpStatus.NOT_FOUND);

        };
         return new ResponseEntity<>(departmentDTO1,HttpStatus.OK);
    }
















}
