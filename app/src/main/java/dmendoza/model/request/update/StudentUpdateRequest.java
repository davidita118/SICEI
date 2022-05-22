package dmendoza.model.request.update;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class StudentUpdateRequest {
   
   @NotNull
   private String nombres;

   private String apellidos;

   @Range(min = 0, max = 100, message = "El promedio debe ser entre 0 y 100")
   private Double promedio;

   private String matricula;

   public String getNombres() {
      return nombres;
   }

   public void setNombres(String name) {
      this.nombres = name;
   }

   public String getApellidos() {
      return apellidos;
   }

   public void setApellidos(String lastname) {
      this.apellidos = lastname;
   }

   public Double getPromedio() {
      return promedio;
   }

   public void setPromedio(Double average) {
      this.promedio = average;
   }

   public String getMatricula() {
      return matricula;
   }

   public void setMatricula(String matricula) {
      this.matricula = matricula;
   }
}
