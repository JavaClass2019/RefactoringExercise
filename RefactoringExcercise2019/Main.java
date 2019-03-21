/**
 * Main class
 *
 * Entry point for the application
 */
public class Main {
  // main method
  public static void main(String args[]) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              EmployeeDetails employeeDetails = new EmployeeDetails();
              employeeDetails.createAndShowGUI();
          }
      });
  }// end main
}