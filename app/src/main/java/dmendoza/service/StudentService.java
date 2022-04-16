package dmendoza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dmendoza.exception.model.NotFoundException;
import dmendoza.exception.model.UnprocessableEntityException;
import dmendoza.model.Student;
import dmendoza.model.request.create.StudentCreateRequest;
import dmendoza.model.request.update.StudentUpdateRequest;
import dmendoza.repository.StudentRepository;

@Service
public class StudentService {
   
   private StudentRepository studentRepository = new StudentRepository();

   public List<Student> getAll() {
      List<Student> students = new ArrayList<>();
      studentRepository.getAllStudents().iterator().forEachRemaining(students::add);

      return students;
   }

   public Student getById(Integer studentId) {
      Optional<Student> sOptional = studentRepository.getStudentById(studentId);

      if(!sOptional.isPresent()) {
         throw new NotFoundException("El estudiante solicitado no existe");  
      }

      return sOptional.get();
   }

   public Student getByCode(String code) {
      Optional<Student> sOptional = studentRepository.getStudentByCode(code);

      if(!sOptional.isPresent()) {
         throw new NotFoundException("El estudiante solicitado no existe"); 
      }

      return sOptional.get();
   }

   public Student post(StudentCreateRequest createRequest) {

      validateCode(createRequest.getMatricula());

      Student student = new Student();
      student.setName(createRequest.getNombres());
      student.setLastname(createRequest.getApellidos());
      student.setCode(createRequest.getMatricula());
      student.setAverage(createRequest.getPromedio());

      student = studentRepository.save(student);
      return student;
   }

   private void validateCode(String code) {
      Optional<Student> sOptional = studentRepository.getStudentByCode(code);

      if(sOptional.isPresent()) {
         throw new UnprocessableEntityException("Ya existe un estudiante con la matrícula " + code); 
      }
   }

   public Student put(Integer studentId, StudentUpdateRequest updateRequest) {
      Student student = getById(studentId);

      boolean isPresent = updateRequest.getNombres() != null;
      student.setName(isPresent? updateRequest.getNombres() : student.getName());

      isPresent = updateRequest.getApellidos() != null;
      student.setLastname(isPresent? updateRequest.getApellidos() : student.getLastname());

      isPresent = updateRequest.getPromedio() != null;
      student.setAverage(isPresent? updateRequest.getPromedio() : student.getAverage());

      student = studentRepository.save(student);
      return student;
   }

   public void delete(Integer studentId) {
      Student student = getById(studentId);

      studentRepository.delete(student);
   }
}
