package dmendoza.repository;

import org.springframework.stereotype.Repository;

import dmendoza.model.Student;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
   Optional<Student> findByCode(String code);
}
