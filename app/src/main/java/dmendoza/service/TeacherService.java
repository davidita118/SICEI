package dmendoza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dmendoza.exception.model.NotFoundException;
import dmendoza.exception.model.UnprocessableEntityException;
import dmendoza.model.Teacher;
import dmendoza.model.request.create.TeacherCreateRequest;
import dmendoza.model.request.update.TeacherUpdateRequest;
import dmendoza.repository.TeacherRepository;

@Service
public class TeacherService {
   
   @Autowired
   private TeacherRepository teacherRepository;

   public List<Teacher> getAll() {
      List<Teacher> teachers = new ArrayList<>();
      teacherRepository.findAll().iterator().forEachRemaining(teachers::add);

      return teachers;
   }

   public Teacher getById(Integer teacherId) {
      Optional<Teacher> tOptional = teacherRepository.findById(teacherId);

      if(!tOptional.isPresent()) {
         throw new NotFoundException("El profesor solicitado no existe");
      }

      return tOptional.get();
   }

   public Teacher getByNumEmployee(Integer teacherNumEmployee) {
      Optional<Teacher> tOptional = teacherRepository.findByNumEmployee(teacherNumEmployee);

      if(!tOptional.isPresent()) {
         throw new NotFoundException("El profesor solicitado no existe");
      }

      return tOptional.get();
   }

   public Teacher post(TeacherCreateRequest createRequest) {

      validateNumEmployee(createRequest.getNumeroEmpleado());

      Teacher teacher = new Teacher();
      teacher.setName(createRequest.getNombres());
      teacher.setLastname(createRequest.getApellidos());
      teacher.setNumEmployee(createRequest.getNumeroEmpleado());
      teacher.setClassHours(createRequest.getHorasClase());

      teacher = teacherRepository.save(teacher);
      return teacher;
   }

   private void validateNumEmployee(Integer teacherNumEmployee) {
      Optional<Teacher> tOptional = teacherRepository.findByNumEmployee(teacherNumEmployee);

      if(tOptional.isPresent()) {
         throw new UnprocessableEntityException("Ya existe un profesor con el número de empleado " + teacherNumEmployee);
      }
   }

   public Teacher put(Integer teacherId, TeacherUpdateRequest updateRequest) {
      Teacher teacher = getById(teacherId);

      boolean isPresent = updateRequest.getNombres() != null;
      teacher.setName(isPresent? updateRequest.getNombres() : teacher.getName());
      
      isPresent = updateRequest.getApellidos() != null;
      teacher.setLastname(isPresent? updateRequest.getApellidos() : teacher.getLastname());

      isPresent = updateRequest.getHorasClase() != null;
      teacher.setClassHours(isPresent? updateRequest.getHorasClase() : teacher.getClassHours());

      teacher = teacherRepository.save(teacher);
      return teacher;
   }

   public void delete(Integer teacherId) {
      Teacher teacher = getById(teacherId);

      teacherRepository.delete(teacher);
   }
   
}
