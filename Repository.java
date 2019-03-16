
import java.util.List;

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
     * Gets the next record from the current cursor on the database
     * @return
     */
    Employee nextRecord();
}