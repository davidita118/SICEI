package dmendoza.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dmendoza.model.Teacher;
import dmendoza.model.request.create.TeacherCreateRequest;
import dmendoza.model.request.update.TeacherUpdateRequest;
import dmendoza.service.TeacherService;

@RestController
@RequestMapping("/api/v1")
public class TeacherRest {
   
   @Autowired
   private TeacherService teacherService;

   @GetMapping("/profesores")
   public ResponseEntity<List<Teacher>> getTeachers() {
      List<Teacher> teachers = teacherService.getAll();
      return ResponseEntity.ok().body(teachers);
   }

   @GetMapping("/profesores/{id}")
   public ResponseEntity<Teacher> getTeacher(@PathVariable("id") Integer teacherId) {
      Teacher teacher = teacherService.getById(teacherId);
      return ResponseEntity.ok().body(teacher);
   }

   @PostMapping("/profesores")
   public ResponseEntity<Teacher> postTeacher(@RequestBody @Valid TeacherCreateRequest createRequest) {
      Teacher teacher = teacherService.post(createRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
   }

   @PutMapping("/profesores/{id}")
   public ResponseEntity<Teacher> putTeacher(@PathVariable("id") Integer teacherId, @RequestBody @Valid TeacherUpdateRequest updateRequest) {
      Teacher teacher = teacherService.put(teacherId, updateRequest);
      return ResponseEntity.ok().body(teacher);
   }

   @DeleteMapping("/profesores/{id}")
   public ResponseEntity<Void> deleteTeacher(@PathVariable("id") Integer teacherId) {
      teacherService.delete(teacherId);
      return ResponseEntity.ok().build();
   }
}
