package dmendoza.repository;

import java.util.List;
import java.util.Optional;

import dmendoza.config.SiceiDB;
import dmendoza.model.Teacher;

public class TeacherRepository {
   public Optional<List<Teacher>> getAllTeachers() {
      Optional<List<Teacher>> sOptional = Optional.empty();

      if(!SiceiDB.getTeachers().isEmpty()) {
         sOptional = Optional.of(SiceiDB.getTeachers());
      }

      return sOptional;
   }

   public Optional<Teacher> getTeacherById(Integer teacherId) {
      Optional<Teacher> sOptional = Optional.empty();

      for(Teacher teacher: SiceiDB.getTeachers()) {
         if(teacher.getId() == teacherId) {
            sOptional = Optional.of(teacher);
            break;
         }
      }

      return sOptional;
   }

   public Optional<Teacher> getTeacherByNumEmployee(Integer teacherNumEmployee) {
      Optional<Teacher> sOptional = Optional.empty();

      for(Teacher teacher: SiceiDB.getTeachers()) {
         if(teacher.getNumEmployee() == teacherNumEmployee) {
            sOptional = Optional.of(teacher);
            break;
         }
      }

      return sOptional;
   }

   public void delete(Teacher oldTeacher) {
      
      for(Teacher teacher: SiceiDB.getTeachers()) {
         if(teacher.getId() == oldTeacher.getId()) {
            SiceiDB.getTeachers().remove(teacher);
            break;
         }
      }
   }

   public Teacher save(Teacher teacher) {

      if(teacher.getId() == null) {
         return newEntity(teacher);
      }
      
      return updateEntity(teacher);
   }

   
   private Teacher newEntity(Teacher teacher) {
      int id = SiceiDB.getIdTeacherCount();
      teacher.setId(id);

      SiceiDB.getTeachers().add(teacher);

      return teacher;
   }

   private Teacher updateEntity(Teacher updatedTeacher) {

      for(int i=0; i < SiceiDB.getStudents().size(); i++) {
         Teacher teacher = SiceiDB.getTeachers().get(i);

         if(teacher.getId() == updatedTeacher.getId()) {
            SiceiDB.getTeachers().set(i, updatedTeacher);
            break;
         }
      }

      return updatedTeacher;
   }
}
