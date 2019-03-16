
/**
* Employee Repository encompasses functionality to retrieve, create, update and delete
* Employees from a data store. It is
*/
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository {
    private RandomFile randomAccessFile;

    public EmployeeRepository(final RandomFile randomFile) {
        this.randomAccessFile = randomFile;
    }

    public void add(Employee employee) {
        // open file for writing
        randomAccessFile.openWriteFile(randomAccessFile.getFile().getAbsolutePath());
        // write into a file
        long currentByteStart = randomAccessFile.addRecords(employee);
        randomAccessFile.setCurrentBytePos(currentByteStart);
        randomAccessFile.closeWriteFile();// close file for writing
    }

    public void update(Employee employee) {
        randomAccessFile.addRecords(employee);
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        Employee cur = randomAccessFile.readRecords(randomAccessFile.getCurrentBytePos());
        while (cur != null) {
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
        long currentByteStart = randomAccessFile.getCurrentBytePos();
        Employee currentEmployee = null;
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
        randomAccessFile.setCurrentBytePos(currentByteStart);
        return currentEmployee;
    }// end nextRecord

    // find byte start in file for previous active record
    public Employee previousRecord() {
        Employee currentEmployee = null;
        long currentByteStart = randomAccessFile.getCurrentBytePos();
        // if any active record in file look for first record
        if (recordsExist()) {
            // open file for reading
            randomAccessFile.openReadFile(randomAccessFile.getFile().getAbsolutePath());
            // get byte start in file for previous record
            currentByteStart = randomAccessFile.getPrevious(currentByteStart);
            // assign current Employee to previous record in file
            currentEmployee = randomAccessFile.readRecords(currentByteStart);
            // loop to previous record until Employee is active - ID is not 0
            while (currentEmployee.getEmployeeId() == 0) {
                // get byte start in file for previous record
                currentByteStart = randomAccessFile.getPrevious(currentByteStart);
                // assign current Employee to previous record in file
                currentEmployee = randomAccessFile.readRecords(currentByteStart);
            } // end while
            randomAccessFile.closeReadFile();// close file for reading
        }
        randomAccessFile.setCurrentBytePos(currentByteStart);
        return currentEmployee;
    }// end previousRecord
}