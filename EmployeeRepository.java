/**
* Employee Repository encompasses functionality to retrieve, create, update and delete
* Employees from a data store. It is
*/
import java.util.List;

public class EmployeeRepository implements Repository {
  private RandomFile randomAccessFile;

  public EmployeeRepository(final RandomFile randomFile) {
      this.randomAccessFile = randomFile;
  }

  public void add(Employee employee) {
      randomAccessFile.addRecords(employee);
  }

  public void update(Employee employee) {
      randomAccessFile.addRecords(employee);
  }

  public List<Employee> findAll() {
      List<Employee> employees =  new ArrayList<>();
      Employee cur = randomAccessFile.readRecords(randomAccessFile.getCurrentBytePos());
      while(cur != null) {
          employees.add(cur);
          randomAccessFile.readRecords(randomAccessFile.getCurrentBytePos());
      }
      return employees;
  }


  // check if any of records in file is active - ID is not 0
  public boolean recordsExist() {
      boolean someoneToDisplay = true;
      // open file for reading
      randomAccessFile.openReadFile(randomAccessFile.getFile().getAbsolutePath());
      // check if any of records in file is active - ID is not 0
      someoneToDisplay = randomAccessFile.isSomeoneToDisplay();
      randomAccessFile.closeReadFile();// close file for reading
      // if no records found clear all text fields and display message
      if (!someoneToDisplay) {
          return false;
      }
      return someoneToDisplay;
  }// end isSomeoneToDisplay

  // find byte start in file for next active record
  public Employee nextRecord() {
      long  currentByteStart = randomAccessFile.getCurrentBytePos();
      Employee currentEmployee = null;
      randomAccessFile.setCurrentBytePos(currentByteStart);
      // if any active record in file look for first record
      if (recordsExist()) {
          // open file for reading
          randomAccessFile.openReadFile(randomAccessFile.getFile().getAbsolutePath());
          // get byte start in file for next record
          currentByteStart = randomAccessFile.getNext(currentByteStart);
          // assign current Employee to record in file
          currentEmployee = randomAccessFile.readRecords(currentByteStart);
          // loop to previous next until Employee is active - ID is not 0
          while (currentEmployee.getEmployeeId() == 0) {
              // get byte start in file for next record
              currentByteStart = randomAccessFile.getNext(currentByteStart);
              // assign current Employee to next record in file
              currentEmployee = randomAccessFile.readRecords(currentByteStart);
          } // end while
      } // end if

      return currentEmployee;
  }// end nextRecord
}