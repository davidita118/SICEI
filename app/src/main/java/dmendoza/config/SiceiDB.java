package dmendoza.config;


import java.util.ArrayList;
import java.util.List;

import dmendoza.model.Student;
import dmendoza.model.Teacher;

public class SiceiDB {

   private static List<Student> students;
   private static int idStudentCount;

   private static List<Teacher> teachers;
   private static int idTeacherCount;

   static {
      students = new ArrayList<>();
      teachers = new ArrayList<>();
   }

   public static int getIdStudentCount() {
      return ++idStudentCount;
   }

   public static List<Student> getStudents() {
      return students;
   }

   public static int getIdTeacherCount() {
      return ++idTeacherCount;
   }

   public static List<Teacher> getTeachers() {
      return teachers;
   }
}
