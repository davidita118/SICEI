package dmendoza.model;

public class Student extends Person {

   private String code;

   private Double average;

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public Double getAverage() {
      return average;
   }

   public void setAverage(Double average) {
      this.average = average;
   }
}
