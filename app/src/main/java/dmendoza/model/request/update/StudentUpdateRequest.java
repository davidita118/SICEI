package dmendoza.model.request.update;

import org.hibernate.validator.constraints.Range;

public class StudentUpdateRequest {
   
   private String nombres;

   private String apellidos;

   @Range(min = 0, max = 100, message = "El promedio debe ser entre 0 y 100")
   private Double promedio;

   public String getName() {
      return nombres;
   }

   public void setName(String name) {
      this.nombres = name;
   }

   public String getLastname() {
      return apellidos;
   }

   public void setLastname(String lastname) {
      this.apellidos = lastname;
   }

   public Double getAverage() {
      return promedio;
   }

   public void setAverage(Double average) {
      this.promedio = average;
   }
}
