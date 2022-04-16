package dmendoza.repository;

import java.util.List;
import java.util.Optional;

import dmendoza.config.SiceiDB;
import dmendoza.model.Student;

public class StudentRepository {
   
   public List<Student> getAllStudents() {
      List<Student> students = SiceiDB.getStudents();
      return students;
   }

   public Optional<Student> getStudentById(Integer studentId) {
      Optional<Student> sOptional = Optional.empty();

      for(Student student: SiceiDB.getStudents()) {
         if(student.getId() == studentId) {
            sOptional = Optional.of(student);
            break;
         }
      }

      return sOptional;
   }

   public Optional<Student> getStudentByCode(String studentCode) {
      Optional<Student> sOptional = Optional.empty();

      for(Student student: SiceiDB.getStudents()) {
         if(student.getCode().equalsIgnoreCase(studentCode)) {
            sOptional = Optional.of(student);
            break;
         }
      }

      return sOptional;
   }

   public void delete(Student oldStudent) {
      
      for(Student student: SiceiDB.getStudents()) {
         if(student.getId() == oldStudent.getId()) {
            SiceiDB.getStudents().remove(student);
            break;
         }
      }
   }

   public Student save(Student student) {

      if(student.getId() == null) {
         return newEntity(student);
      }
      
      return updateEntity(student);
   }

   
   private Student newEntity(Student student) {
      int id = SiceiDB.getIdStudentCount();
      student.setId(id);

      SiceiDB.getStudents().add(student);

      return student;
   }

   private Student updateEntity(Student updatedStudent) {

      for(int i=0; i < SiceiDB.getStudents().size(); i++) {
         Student student = SiceiDB.getStudents().get(i);

         if(student.getId() == updatedStudent.getId()) {
            SiceiDB.getStudents().set(i, updatedStudent);
            break;
         }
      }

      return updatedStudent;
   }
}
