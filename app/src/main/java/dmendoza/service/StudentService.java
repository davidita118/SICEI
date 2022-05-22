package dmendoza.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dmendoza.exception.model.InternalServerErrorException;
import dmendoza.exception.model.NotFoundException;
import dmendoza.exception.model.UnprocessableEntityException;
import dmendoza.model.Student;
import dmendoza.model.request.create.StudentCreateRequest;
import dmendoza.model.request.update.StudentUpdateRequest;
import dmendoza.repository.StudentRepository;

@Service
public class StudentService {
   
   @Autowired
   private StudentRepository studentRepository;

   @Value("${aws.s3.profilePictureBucket")
   private String profilePictureBucket;

   @Value("${aws.accessKey}")
   private String awsAccessKey;

   @Value("${aws.secretKey}")
   private String awsSecretKey;

   @Value("${aws.sessionToken}")
   private String awsSessionToken;

   public List<Student> getAll() {
      List<Student> students = new ArrayList<>();
      studentRepository.findAll().iterator().forEachRemaining(students::add);

      return students;
   }

   public Student getById(Integer studentId) {
      Optional<Student> sOptional = studentRepository.findById(studentId);

      if(!sOptional.isPresent()) {
         throw new NotFoundException("El estudiante solicitado no existe");  
      }

      return sOptional.get();
   }

   public Student getByCode(String code) {
      Optional<Student> sOptional = studentRepository.findByCode(code);

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
      Optional<Student> sOptional = studentRepository.findByCode(code);

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

   public Student uploadProfilePicture(Integer studentId, MultipartFile file) {
      Student student = getById(studentId);

      BasicSessionCredentials awsCredentials = new BasicSessionCredentials(awsAccessKey, awsSecretKey, awsSessionToken);
      AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
         .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
         .withRegion(Regions.US_EAST_1).build();
      String key = "photos/" + student.getCode() + "_" + student.getLastname() + "_profilePicture_" + file.getOriginalFilename();
      
      PutObjectRequest objectRequest;
      try {
         objectRequest = new PutObjectRequest(profilePictureBucket, key, file.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
      } catch (IOException e) {
         throw new InternalServerErrorException("No se pudo subir la foto de perfil");
      }

      amazonS3.putObject(objectRequest);
      student.setProfilePicture("https://" + profilePictureBucket + ".s3.amazonaws.com/" + key);
      student = studentRepository.save(student);
      
      return student;
   }

   public void delete(Integer studentId) {
      Student student = getById(studentId);

      studentRepository.delete(student);
   }
}
