package dmendoza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

   @Column(name = "code")
   private String code;

   @Column(name = "average")
   private Double average;

   @Column(name = "profile_picture")
   private String profilePicture;

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

   public String getProfilePicture() {
      return profilePicture;
   }

   public void setProfilePicture(String profilePicture) {
      this.profilePicture = profilePicture;
   }
}
