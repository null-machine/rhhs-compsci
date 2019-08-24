import java.util.ArrayList;

class TicketingSystem { 
	
	public static void main(String[] args) throws Exception {
		
		// arr for testing
		ArrayList<Table> tables = new ArrayList<Table>();
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("hello there"));
		students.add(new Student("there helonsteihnoteuhistnlo"));
		students.add(new Student("hhere tellaoeuaoeuo"));
		Table tabel = new Table(8);
		tabel.setStudents(students);
		TestCaseCreator tcc = new TestCaseCreator();
		tcc.generateStudents(200);
		for(int i = 0; i < 15; ++i) tables.add(tabel);
		
		FloorPlan fp = new FloorPlan();
		//fp.generateFloorPlan(tables);
		//fp.displayFloorPlan();
	}
}
