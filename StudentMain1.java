package student_information;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class StudentMain1 {
   final static int max = 100;
   static Student[] student = new Student[max];
   static Student[] storedStudent = new Student[max];
   static Scanner scan = new Scanner(System.in);
   static String searchId,checkIfIdExist;
   static int maximum = 0, count = 0;
   static int checkId = 0, check = 0;;
   static int updateIndex = 0;
   static int beforeExecute = 0;
   static int storedStudentLastNum = 0;

   public static void main(String[] args) throws Exception {
      int number, out = 0;
      if (count == 0) {
         callFileValue();
      }
      while (out != 1) {
         System.out.println("----------------------------");
         System.out.println("1.add");
         System.out.println("2.update");
         System.out.println("3.delete");
         System.out.println("4.view");
         System.out.println("5.finish");
         System.out.println("----------------------------");
         number = scan.nextInt();
         if (number == 1) {
            add();
         } else if (number == 2) {
            update();
         } else if (number == 3) {
            delete();
         } else if (number == 4) {
            view();
         } else if (number == 5) {
            exit();
            out = 1;
         }
      }
   }

   private static void add() throws Exception {
      System.out.println("[학생 정보 입력]");
      student[count] = new Student(null, null, null, null);

      do {
         System.out.print("id : ");
         checkIfIdExist = scan.next();
         checkId = 1;
         searchId();
         checkId = 0;
         if (check == 1) {
            System.out.println("Exist! Please enter another id!");
         }
      } while (check == 1);
      student[count].id = checkIfIdExist;
      System.out.print("name : ");
      student[count].name = scan.next();
      System.out.print("department : ");
      student[count].department = scan.next();
      System.out.print("phoneNumber : ");
      student[count].phoneNumber = scan.next();
      storedStudent[storedStudentLastNum] = student[count];
      storedStudentLastNum++;
      count++;
   }

   private static void update() throws Exception {
      System.out.println("[Change only phonenumber]");
      searchId();
      if (check == 1) {
         System.out.print("Write your update phonenumber : ");
         storedStudent[updateIndex].phoneNumber = scan.next();
         System.out.println("your phone number is updated");
      } else {
         System.out.println("There is no data");
      }
      check = 0;
   }

   private static void delete() throws ClassNotFoundException, IOException {
      searchId();
      if (check == 1) {
         int total = count + maximum - 1;

         if (updateIndex != total) {
            for (int i = updateIndex; i < total; i++) {
               storedStudent[i] = storedStudent[i + 1];
            }
         }
         storedStudentLastNum--;
         if(count!=0){
        	 count--;
         }
         System.out.println("complete");
      } else
         System.out.println("There is no data");
      check = 0;
   }

   private static void view() throws IOException, ClassNotFoundException, EOFException {
      
      searchId();

      if (check == 1) {
         System.out.println("[student information]");
         System.out.println(storedStudent[updateIndex]);         
      } else {
         System.out.println("There is no data");
      }
      check = 0;
   }

   private static void exit() throws FileNotFoundException, ClassNotFoundException, IOException, EOFException {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("information.txt"));
      oos.writeInt(storedStudentLastNum);

      for (int i = 0; i < storedStudentLastNum; i++)
         oos.writeObject(storedStudent[i]);
      oos.close();
      System.out.println("Stored in a information file");
      System.out.println("exit!");
   }

   public static void searchId() throws IOException, ClassNotFoundException {
      if (checkId == 1) {
         searchId = checkIfIdExist;
      } else {
         System.out.print("Write ID you want to search for : ");
         searchId = scan.next();
      }

      for (updateIndex = 0; updateIndex < storedStudentLastNum; updateIndex++) {
         if (storedStudent[updateIndex].id.equals(searchId)) {
            check = 1;
            break;
         } else {
            check = 0;
         }
      }
   }

   private static void callFileValue() throws IOException, ClassNotFoundException {
      File file = new File("information.txt");

      if (file.exists()) {
         FileInputStream fis = new FileInputStream(file);
         ObjectInputStream ois = new ObjectInputStream(fis);
         if (fis.available() > 0) {
            maximum = ois.readInt();
            for (int i = 0; i < maximum; i++) {
               storedStudent[i] = (Student) ois.readObject();
            }
            storedStudentLastNum = maximum;
         }
         fis.close();
         ois.close();
      }
      
   }
}