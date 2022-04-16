package dmendoza.model.request.update;

import javax.validation.constraints.PositiveOrZero;

public class TeacherUpdateRequest {
   
   private String nombres;

   private String apellidos;

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

   public Double getHorasClase() {
      return horasClase;
   }

   public void setHorasClase(Double horasClase) {
      this.horasClase = horasClase;
   }
}
