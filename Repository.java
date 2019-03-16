
import java.util.List;

/**
 * A Repository for retrieving, creating, updating and deleting Employee
 * records. Provides functionality to get first, last, previous and next
 * records and as such relies on a cursor-based database. Which basically means
 * that the implementing class MUST be able to keep track of the current position
 * we are in the database.
 * 
 */

public interface Repository {

    /**
     * Whether a records exist or not in the current database
     * @return
     */
    boolean recordsExist();

    /**
     * Add an employee to the database
     * @param employee
     */
    void add(Employee employee);

    /**
     * Update an employee
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * Retrieve all employees
     *
     * @return
     */
    List<Employee> findAll();

    /**
     * Gets the previous record from the current cursor on the database
     * @return
     */
    Employee previousRecord();

    /**
     * Gets the next record from the current cursor on the database
     * @return
     */
    Employee nextRecord();
}