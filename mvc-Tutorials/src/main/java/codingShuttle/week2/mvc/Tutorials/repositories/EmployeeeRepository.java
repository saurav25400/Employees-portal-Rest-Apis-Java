package codingShuttle.week2.mvc.Tutorials.repositories;

import codingShuttle.week2.mvc.Tutorials.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeeRepository extends JpaRepository<EmployeeEntity,Long> {


}
