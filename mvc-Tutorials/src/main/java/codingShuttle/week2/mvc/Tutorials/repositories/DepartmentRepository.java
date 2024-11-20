package codingShuttle.week2.mvc.Tutorials.repositories;

import codingShuttle.week2.mvc.Tutorials.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
