package dmendoza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

   @Column(name = "num_employee")
   private Integer numEmployee;

   @Column(name = "class_hours")
   private Double classHours;

   public Integer getNumEmployee() {
      return numEmployee;
   }

   public void setNumEmployee(Integer numEmployee) {
      this.numEmployee = numEmployee;
   }

   public Double getClassHours() {
      return classHours;
   }

   public void setClassHours(Double classHours) {
      this.classHours = classHours;
   }
}
