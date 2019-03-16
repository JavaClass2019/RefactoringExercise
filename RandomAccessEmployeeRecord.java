
/*
 * 
 * This is a Random Access Employee record definition
 * 
 * */

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Objects;

public class RandomAccessEmployeeRecord {
   public static final int SIZE = 175; // Size of each RandomAccessEmployeeRecord object

   private final Employee employee;

   // Create empty record
   public RandomAccessEmployeeRecord() {
      this.employee = Employee.NULL_EMPLOYEE;
   } // end RandomAccessEmployeeRecord

   // Initialize record with details
   public RandomAccessEmployeeRecord(Employee employee) {
      this.employee = Objects.requireNonNull(employee, "Employee cannot be null");
   } // end RandomAccessEmployeeRecord

   public Employee getEmployee() {
      return employee;
   }

   // Read a record from specified RandomAccessFile
   public void read(RandomAccessFile file) throws IOException {
      employee.setEmployeeId(file.readInt());
      employee.setPps(readName(file));
      employee.setSurname(readName(file));
      employee.setFirstName(readName(file));
      employee.setGender(file.readChar());
      employee.setDepartment(readName(file));
      employee.setSalary(file.readDouble());
      employee.setFullTime(file.readBoolean());
   } // end read

   // Ensure that string is correct length
   private String readName(RandomAccessFile file) throws IOException {
      char name[] = new char[20], temp;

      for (int count = 0; count < name.length; count++) {
         temp = file.readChar();
         name[count] = temp;
      } // end for

      return new String(name).replace('\0', ' ');
   } // end readName

   // Write a record to specified RandomAccessFile
   public void write(RandomAccessFile file) throws IOException {
      file.writeInt(employee.getEmployeeId());
      writeName(file, employee.getPps().toUpperCase());
      writeName(file, employee.getSurname().toUpperCase());
      writeName(file, employee.getFirstName().toUpperCase());
      file.writeChar(employee.getGender());
      writeName(file, employee.getDepartment());
      file.writeDouble(employee.getSalary());
      file.writeBoolean(employee.getFullTime());
   } // end write

   // Ensure that string is correct length
   private void writeName(RandomAccessFile file, String name) throws IOException {
      StringBuffer buffer = null;

      if (name != null)
         buffer = new StringBuffer(name);
      else
         buffer = new StringBuffer(20);

      buffer.setLength(20);
      file.writeChars(buffer.toString());
   } // end writeName
} // end class RandomAccessEmployeeRecord