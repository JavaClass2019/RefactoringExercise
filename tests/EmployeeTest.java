package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmployeeTest {

  @Test
  public void testNullEmployeeValues() {
    Employee nullEmployee = Employee.NULL_EMPLOYEE;

    assertEquals(0, nullEmployee.getEmployeeId());
    assertEquals("", nullEmployee.getPps());
    assertEquals("", nullEmployee.getSurname());
    assertEquals("", nullEmployee.getFirstName());
    assertEquals('\0', nullEmployee.getGender());
    assertEquals("", nullEmployee.getDepartment());
    assertEquals(0.0, nullEmployee.getSalary(), 0.0);
    assertFalse(nullEmployee.getFullTime());
  }

  @Test
  public void testToStringMethod() {
    Employee john = new Employee(1, "1234", "Banda", "John", 'M', "Administration", 50_000.00, false);
    String expected1 = "Employee ID: 1\nPPS Number: 1234\nSurname: Banda\nFirst Name: John\nGender: M\nDepartment: Administration\nSalary: 50000.0\nFull Time: No";
    assertEquals(expected1, john.toString());

    Employee mary = new Employee(2, "5678", "Phiri", "Mary", 'F', "Administration", 150_000.00, true);
    String expected2 = "Employee ID: 2\nPPS Number: 5678\nSurname: Phiri\nFirst Name: Mary\nGender: F\nDepartment: Administration\nSalary: 150000.0\nFull Time: Yes";
    assertEquals(expected2, mary.toString());
  }
}