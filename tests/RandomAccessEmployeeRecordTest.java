import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.RandomAccessFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RandomAccessEmployeeRecordTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  /**
   * Testing the writing and reading of a record from a random access file.
   *
   * @throws Exception
   */
  @Test
  public void testCanWriteRecord() throws Exception {
    final RandomAccessFile randomAccessFile = new RandomAccessFile(temporaryFolder.newFile("testFile"), "rw");

    Employee employee = new Employee(1, "1234", "Banda", "John", 'M', "Administration", 50_000.00, false);
    RandomAccessEmployeeRecord record = new RandomAccessEmployeeRecord(employee);

    record.write(randomAccessFile);

    randomAccessFile.seek(0); // go to the start of the file

    Employee employee2 = Employee.NULL_EMPLOYEE;
    RandomAccessEmployeeRecord readRecord = new RandomAccessEmployeeRecord(employee2);

    // Check that we can read the record into a different employee object
    readRecord.read(randomAccessFile);

    assertEquals(1, employee2.getEmployeeId());
    assertEquals("1234", employee2.getPps().trim());
    assertEquals("BANDA", employee2.getSurname().trim());
    assertEquals("JOHN", employee2.getFirstName().trim());
    assertEquals('M', employee2.getGender());
    assertEquals("Administration", employee2.getDepartment().trim());
    assertEquals(50_000.0, employee2.getSalary(), 0.0);
    assertFalse(employee2.getFullTime());
  }
}