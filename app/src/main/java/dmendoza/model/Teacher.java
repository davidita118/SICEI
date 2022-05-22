package dmendoza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "teacher")
public class Teacher {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   
   @Column(name = "name")
   private String name;

   @Column(name = "lastname")
   private String lastname;

   @Column(name = "num_employee")
   private Integer numEmployee;

   @Column(name = "class_hours")
   private Double classHours;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @JsonProperty("nombres")
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("apellidos")
   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   @JsonProperty("numeroEmpleado")
   public Integer getNumEmployee() {
      return numEmployee;
   }

   public void setNumEmployee(Integer numEmployee) {
      this.numEmployee = numEmployee;
   }

   @JsonProperty("horasClase")
   public Double getClassHours() {
      return classHours;
   }

   public void setClassHours(Double classHours) {
      this.classHours = classHours;
   }
}
