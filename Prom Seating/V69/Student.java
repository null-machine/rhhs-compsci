import java.util.ArrayList;

class Student {
	
	private String name;
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	private String studentNumber;
	public String getStudentNumber() { return this.studentNumber; }
	public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
	
	private ArrayList<String> dietaryRestrictions;
	public ArrayList<String> getDietaryRestrictions() { return this.dietaryRestrictions; }
	public void setDietaryRestrictions(ArrayList<String> dietaryRestrictions) { this.dietaryRestrictions = dietaryRestrictions; }
	
	private ArrayList<String> friendStudentNumbers;
	public ArrayList<String> getFriendStudentNumbers() { return this.friendStudentNumbers; }
	public void setFriendStudentNumbers(ArrayList<String> friendStudentNumbers) { this.friendStudentNumbers = friendStudentNumbers; }
	
	Student(String name, String studentNumber, ArrayList<String> dietaryRestrictions, ArrayList<String> friendStudentNumbers) {
		this.name = name;
		this.studentNumber = studentNumber;
		this.dietaryRestrictions = dietaryRestrictions;
		this.friendStudentNumbers = friendStudentNumbers;
	}
	
	Student(String name){
		this.name = name;}
}
