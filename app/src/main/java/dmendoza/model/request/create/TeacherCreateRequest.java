package dmendoza.model.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class TeacherCreateRequest {
   
   @NotNull
   @NotEmpty
   private String nombres;

   @NotNull
   @NotEmpty
   private String apellidos;

   @NotNull
   @PositiveOrZero
   private Integer numeroEmpleado;

   @NotNull
   @PositiveOrZero
   private Double horasClase;

   public String getNombres() {
      return nombres;
   }

   public void setNombres(String nombres) {
      this.nombres = nombres;
   }

   public String getApellidos() {
      return apellidos;
   }

   public void setApellidos(String apellidos) {
      this.apellidos = apellidos;
   }

   public Integer getNumeroEmpleado() {
      return numeroEmpleado;
   }

   public void setNumeroEmpleado(Integer numeroEmpleado) {
      this.numeroEmpleado = numeroEmpleado;
   }

   public Double getHorasClase() {
      return horasClase;
   }

   public void setHorasClase(Double horasClase) {
      this.horasClase = horasClase;
   }
}
