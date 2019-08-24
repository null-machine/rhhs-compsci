import java.util.ArrayList;

/**
 * Represents a table.
 * 
 * @author sunny
 */
public class Table {

  private int size;
  private ArrayList<Student> students;

  /**
   * Creates a new table.
   * 
   * @param size the # of people at table
   */
  public Table(int size) {
    this.size = size;
    this.students = new ArrayList<Student>();
  }

  /**
   * gets the list of students.
   * 
   * @return the list of students
   */
  public ArrayList<Student> getStudents() {
    return students;
  }

  /**
   * sets the list of students.
   * 
   * @param students the new list of students
   */
  public void setStudents(ArrayList<Student> students) {
    this.students = students;
  }

  /**
   * gets the size of the table.
   * 
   * @return the size of the table
   */
  public int getSize() {
    return size;
  }

}
