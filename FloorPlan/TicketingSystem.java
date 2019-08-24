import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

class TicketingSystem { 
    
    public static void main(String[] args) throws Exception {
        
        // arr for testing
        ArrayList<Table> tables = new ArrayList<Table>();
        for(int i = 0; i < 20; ++i) tables.add(new Table(8));
        ArrayList<Student> students = new ArrayList<Student>();
        students = readFile();
        
        FloorPlan fp = new FloorPlan();
        fp.generateFloorPlan(new ArrayList<Table>());
        fp.displayFloorPlan();
    }
    
    public static ArrayList<Student> readFile () throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        File student = new File("students.txt");
        Scanner fileReader = new Scanner(student);
        while(fileReader.hasNext()) {
            String name = fileReader.nextLine();
            String number = fileReader.nextLine();
            String dietaryRestrictions = fileReader.nextLine();
            String friends = fileReader.nextLine();
            fileReader.nextLine();
            //System.out.println(name + " " + number + " " + dietaryRestrictions + " " + friends);
            ArrayList<String> dietaryRestrictionList = new ArrayList<String>();
            ArrayList<String> friendList = new ArrayList<String>();
            while (dietaryRestrictions.contains(",")) {
                //System.out.println(dietaryRestrictions);
                int indexOfComma = dietaryRestrictions.indexOf(",");
                dietaryRestrictionList.add(dietaryRestrictions.substring(0, indexOfComma));
                dietaryRestrictions = dietaryRestrictions.substring(indexOfComma + 2);                 
            }
            dietaryRestrictionList.add(dietaryRestrictions);
            
            while (friends.contains(",")) {
                int indexOfComma = friends.indexOf(",");
                friendList.add(friends.substring(0, indexOfComma));
                friends = friends.substring(indexOfComma + 2);                 
            }
            students.add(new Student(name, number, dietaryRestrictionList, friendList));
        }
        fileReader.close();
        return students;
    }
    
    public static ArrayList<Table> sortingHat (ArrayList<Student> students) {
        ArrayList<Table> tables = new ArrayList<Table>();
        while (students.size() != 0) {
            int random = (int)(Math.random() * 3 + 6);
            if (students.size() < random) {
                random = students.size();
            }
            Table table = new Table(random);
            ArrayList<Student> clique = new ArrayList<Student>();
            for (int i = 0; i < random; i++) {
                clique.add(students.get(i));
                students.remove(0);
            }
            table.setStudents(clique);
            tables.add(table);
        }
        return tables;
        
    }
}
