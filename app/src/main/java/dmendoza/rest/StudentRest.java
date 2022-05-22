package dmendoza.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dmendoza.model.Student;
import dmendoza.model.request.create.StudentCreateRequest;
import dmendoza.model.request.update.StudentUpdateRequest;
import dmendoza.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentRest {
   
   @Autowired
   private StudentService studentService;

   @GetMapping("/alumnos")
   public ResponseEntity<List<Student>> getStudents() {
      List<Student> students = studentService.getAll();
      return ResponseEntity.ok().body(students);
   }

   @GetMapping("/alumnos/{id}")
   public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer studentId) {
      Student student = studentService.getById(studentId);
      return ResponseEntity.ok().body(student);
   }

   @PostMapping("/alumnos")
   public ResponseEntity<Student> postStudent(@RequestBody @Valid StudentCreateRequest request) {
      Student student = studentService.post(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(student);
   }

   @PutMapping("/alumnos/{id}")
   public ResponseEntity<Student> putStudent(@PathVariable("id") Integer studentId, @RequestBody @Valid StudentUpdateRequest request) {
      Student student = studentService.put(studentId, request);
      return ResponseEntity.ok().body(student);
   }

   @DeleteMapping("/alumnos/{id}")
   public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer studentId) {
      studentService.delete(studentId);
      return ResponseEntity.ok().build();
   }

   @RequestMapping(
      path = "/alumnos/{id}/fotoPerfil",
      method = RequestMethod.POST,
      consumes = {
         MediaType.MULTIPART_FORM_DATA_VALUE
      }
   )
   public ResponseEntity<Student> uploadProfilePicture(@PathVariable("id") Integer studentId, @RequestPart MultipartFile multipartFile) {
      Student student = studentService.uploadProfilePicture(studentId, multipartFile);
      return ResponseEntity.ok().body(student);
   }
}
