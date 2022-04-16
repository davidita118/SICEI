package dmendoza.model.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class StudentCreateRequest {
   
   @NotEmpty
   @NotNull
   private String nombres;

   @NotEmpty
   @NotNull
   private String apellidos;

   @NotEmpty
   @NotNull
   private String matricula;

   @NotNull
   @Range(min = 0, max = 100, message = "El promedio debe ser entre 0 y 100")
   private double promedio;

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

   public String getMatricula() {
      return matricula;
   }

   public void setMatricula(String code) {
      this.matricula = code;
   }

   public double getPromedio() {
      return promedio;
   }

   public void setPromedio(double average) {
      this.promedio = average;
   }
}
