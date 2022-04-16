package dmendoza.model;

public class Teacher extends Person {

   private Integer numEmployee;

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
