import java.util.ArrayList;

class Table {
  private int size;
  private ArrayList<Student> students;
  Table (int size) {
    this.size = size;
  }
  
  public ArrayList<Student> getStudents () {
    return students;
  }
  
  public void setStudents (ArrayList<Student> students) {
    this.students = students;
  }
  
  public int getSize () {
    return size;
  }
}
