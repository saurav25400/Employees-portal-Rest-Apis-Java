package codingShuttle.week2.mvc.Tutorials.services;

import codingShuttle.week2.mvc.Tutorials.dto.EmployeeDTO;
import codingShuttle.week2.mvc.Tutorials.entities.EmployeeEntity;
import codingShuttle.week2.mvc.Tutorials.repositories.EmployeeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeeRepository employeeeRepository;
   private final ModelMapper mapper;


    public EmployeeService(EmployeeeRepository employeeeRepository,ModelMapper mapper) {
        this.employeeeRepository = employeeeRepository;
        this.mapper=mapper;
    }

    public Optional<EmployeeDTO> getEmployee(Long id) {

        Optional<EmployeeEntity> empEntity= employeeeRepository.findById(id);
        return empEntity.map(empEntity1->mapper.map(empEntity1,EmployeeDTO.class));

    }

    public EmployeeDTO postEmployee(EmployeeDTO employee) {
        EmployeeEntity emp= mapper.map(employee,EmployeeEntity.class);
        EmployeeEntity empEntity=employeeeRepository.save(emp);
        return mapper.map(empEntity,EmployeeDTO.class );
    }

    public String updateData(String age, String email) {
        return "my age is "+age+" and my email is "+email;
    }

    public String deleteData(Long employeeId) {
        employeeeRepository.deleteById(employeeId);
        return "yes the employee with given id "+employeeId+" is deleted";
    }

    // now repeat above for all the other classes.


    public List<EmployeeDTO>getAllEmployee(){
       List<EmployeeEntity>employeeEntities=employeeeRepository.findAll();
       //1st way
       return employeeEntities.stream()
               .map(empEntity ->mapper.map(empEntity,EmployeeDTO.class)).collect(Collectors.toList());

        //2nd way --------> manually using plain java but always use first way for cleaner code;
        //List<EmployeeDTO>newList=new ArrayList<>();
//        for(int i=0;i<employeeEntities.size();i++){
//            //EmployeeEntity empObj=employeeEntities.get(i);
//          // EmployeeDTO empDto =mapper.map(employeeEntities.get(i),EmployeeDTO.class);
//           newList.add(mapper.map(employeeEntities.get(i),EmployeeDTO.class));
//        }
//        return newList;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long employeeId) {
        EmployeeEntity employeeEntity=mapper.map(employeeDTO,EmployeeEntity.class);
            employeeEntity.setId(employeeId);
            EmployeeEntity empObj=employeeeRepository.save(employeeEntity);
            return mapper.map(empObj,EmployeeDTO.class);

    }

    public boolean deleteEmployee(Long employeeId) {
        boolean isExist=employeeeRepository.existsById(employeeId);
        if(!isExist){
            return false;
        }
        employeeeRepository.deleteById(employeeId);
        return true;
    }


    public EmployeeDTO partialUpdate(Map<String, Object> updateField, Long employeeId) {
        EmployeeEntity empEntity=employeeeRepository.findById(employeeId).orElse(null);
        if(empEntity==null){
            return null;
        }
        updateField.forEach((field,value)->{
            Field foundField=ReflectionUtils.findField(EmployeeEntity.class,field);
            foundField.setAccessible(true);
            ReflectionUtils.setField(foundField,empEntity,value);
        });
        return mapper.map(employeeeRepository.save(empEntity),EmployeeDTO.class);

    }
}
