package codingShuttle.week2.mvc.Tutorials.services;

import codingShuttle.week2.mvc.Tutorials.dto.DepartmentDTO;
import codingShuttle.week2.mvc.Tutorials.entities.DepartmentEntity;
import codingShuttle.week2.mvc.Tutorials.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;


    //constructor injection
    DepartmentService(DepartmentRepository departmentRepository,ModelMapper mapper){
        this.departmentRepository=departmentRepository;
        this.mapper=mapper;
    }


    public List<DepartmentDTO> getAllDepartment(){
        List<DepartmentEntity> departmentEntity=departmentRepository.findAll();
        return departmentEntity.stream().map(departmentEntity1 ->
            mapper.map(departmentEntity1,DepartmentDTO.class)).collect(Collectors.toList());

    }


    public DepartmentDTO postDepartment(DepartmentDTO departmentData) {
        DepartmentEntity departmentEntity=mapper.map(departmentData, DepartmentEntity.class);
        return mapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }

    public DepartmentDTO getADepartment(Long id) {
        DepartmentEntity departmentEntity=this.departmentRepository.findById(id).orElse(null);
        return mapper.map(departmentEntity, DepartmentDTO.class);

    }

    public Boolean deleteDepartment(Long id) {
        DepartmentEntity departmentEntity=this.departmentRepository.findById(id).orElse(null);
        if(departmentEntity==null){
            return false;
        }
         this.departmentRepository.deleteById(id);
        return true;


    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity=this.departmentRepository.findById(id).orElse(null);
        if(departmentEntity==null){
            return null;
        }
        DepartmentEntity deptEntity=mapper.map(departmentDTO,DepartmentEntity.class);
        deptEntity.setId(id);
        return mapper.map(deptEntity,DepartmentDTO.class);
    }
}
