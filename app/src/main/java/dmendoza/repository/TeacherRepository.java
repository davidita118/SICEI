package dmendoza.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dmendoza.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
   Optional<Teacher> findByNumEmployee(Integer numEmployee);
}
