//File IO imports
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

//Util import
import java.util.ArrayList;

/**
 * Generates a list of students and writes it to a file.
 * @author Bryan Zhang and Glen Wang
 * @version 1.0
 **/

class TestCaseCreator {
    private ArrayList<String> firstNames;
    private ArrayList<String> lastNames;
    private ArrayList<String> names;
    private ArrayList<String> possibleDietaryRestrictions;
    private ArrayList<String> dietaryRestrictions;
    private ArrayList<String> studentNumbers;
    private ArrayList<ArrayList> friends;
    private int numStudents;

    /**
     * generateStudents
     * This method reads from the name and dietary restrictions files and randomly generates students. Then, it will write this data onto a file named students.
     * @param numStudents An integer that specifies the number of students to generate.
     */
    public void generateStudents (int numStudents) throws IOException {
        firstNames = new ArrayList<String>();
        lastNames = new ArrayList<String>();
        names = new ArrayList<String>();
        possibleDietaryRestrictions = new ArrayList<String>();
        dietaryRestrictions = new ArrayList<String>();
        studentNumbers = new ArrayList<String>();
        friends = new ArrayList<ArrayList>();
        for (int i = 0; i < numStudents; i++) {
            friends.add(new ArrayList<String>());
        }
        this.numStudents = numStudents;
        
        lastNames = readFile("lastNames.txt");
        firstNames = readFile("firstNames.txt");
        possibleDietaryRestrictions = readFile("dietaryRestrictions.txt");
        
        generateNames();
        generateStudentNumbers();
        generateDietaryRestrictions();
        generateFriends();
        
        writeFile();
        System.out.println("Done");
    }
    
    /**
     * readFile
     * This method reads each word in a file and adds them to an ArrayList of Strings
     * @param A String which is the name of the file to read from
     * @return An ArrayList of Strings that contains all the words
     */
    private ArrayList<String> readFile (String fileName) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            list.add(fileReader.next());
            System.out.println(list.get(list.size() - 1));
        }
        fileReader.close();
        return list;
    }
    
    /**
     * generateNames
     * This method randomly generates a full name
     */
    private void generateNames () {
        for (int i = 0; i < numStudents; i++) {
            int random1 = (int)(Math.random() * firstNames.size());
            int random2 = (int)(Math.random() * lastNames.size());
            names.add(firstNames.get(random1) + " " + lastNames.get(random2));
        }
    }
    
    /**
     * generateStudentNumbers
     * This method randomly generates unique student numbers and adds to an ArrayList
     */
    private void generateStudentNumbers () {
        for (int i = 0; i < numStudents; i++) {
            String studentNumber = "";
            do {
                for (int j = 0; j < 9; j++) {
                    int random = (int)(Math.random() * 9);
                    studentNumber += Integer.toString(random);
                }
                //System.out.println(studentNumber);
            } while (studentNumbers.contains(studentNumber));
            studentNumbers.add(studentNumber);
        }
    }
    
    /**
     * generateDietaryRestrictions
     * This method randomly generates a random number of dietary restrictions, comma separated, and adds them to an ArrayList
     */
    private void generateDietaryRestrictions () {
        for (int j = 0; j < numStudents; j++) {
            String dietaryRestriction = "";
            int random = (int)(Math.random() * 100 + 1);
            
            //80% chance to have no dietary restrictions
            if (random < 80) {
                dietaryRestriction = "None";
                
                //19% chance to have mone dietary restriction
            } else if (random < 99) {
                random = (int)(Math.random() * possibleDietaryRestrictions.size());
                dietaryRestriction += possibleDietaryRestrictions.get(random);
                
                //19% chance to have more than one dietary restriction
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.addAll(possibleDietaryRestrictions);
                int randomNumDietary = (int)(Math.random() * 3 + 1);
                
                for (int i = 0; i < randomNumDietary - 1; i++) {
                    do {
                        random = (int)(Math.random() * possibleDietaryRestrictions.size());
                    } while (!temp.contains(possibleDietaryRestrictions.get(random)));
                    temp.remove(possibleDietaryRestrictions.get(random));
                    dietaryRestriction += possibleDietaryRestrictions.get(random) + ", ";
                }
                
                do {
                    random = (int)(Math.random() * possibleDietaryRestrictions.size());
                } while (!temp.contains(possibleDietaryRestrictions.get(random)));
                temp.remove(possibleDietaryRestrictions.get(random));
                dietaryRestriction += possibleDietaryRestrictions.get(random);
            }
            dietaryRestrictions.add(dietaryRestriction);
        }
    }
    
    /**
     * generateFriends
     * This method generates cliques and friends for the students using their student numbers, comma separated, and adds them to an ArrayList
     */
    private void generateFriends () {
        //Create cliques
        ArrayList<String> temp = new ArrayList<String>();
        temp.addAll(studentNumbers);
        while (temp.size() > 0) {
            int random = 0;
            if (temp.size() > 8) {
                random = (int)(Math.random() * 8 + 1);
            } else {
                random = (int)(Math.random() * temp.size() + 1);
            }
            int initialIndex = studentNumbers.indexOf(temp.get(0));
            for (int i = initialIndex; i < initialIndex + random - 1; i++) {
                for (int j = initialIndex; j < initialIndex + random - 1; j++) {
                    //System.out.println(i + " " + j);
                    if (temp.get(i - initialIndex) != studentNumbers.get(j)) {
                        friends.get(i).add(studentNumbers.get(j));
                    }
                }
            }
            for (int i = 0; i < random; i++) {
                temp.remove(0);
            }
        }
        
        //Add random friends
        for (int i = 0; i < studentNumbers.size(); i++) {
            int random = (int)(Math.random() * 80);
            int numFriend = 0;
            if (random > 20) {
                numFriend = 0;
            } else if (random > 5) {
                numFriend = 1;
            } else {
                numFriend = (int)(Math.random() * 2 + 2);
            }
            for (int j = 0; j < numFriend; j++) {
                int random2 = (int)(Math.random() * studentNumbers.size());
                if (!studentNumbers.get(random2).equals(studentNumbers.get(i)) && !friends.get(i).contains(studentNumbers.get(random2))) {
                    friends.get(i).add(studentNumbers.get(random2));
                }
            }
        }
    }
    
    /**
     * writeFile
     * This method writes to file information about each student stored in the ArrayLists
     */
    private void writeFile () throws IOException {
        File studentFile = new File ("students.txt");
        PrintWriter printStuff = new PrintWriter(studentFile);
        for (int i  = 0; i < numStudents; i++) {
            //Generate and print name
            printStuff.println(names.get(i));
            
            //Print number
            printStuff.println(studentNumbers.get(i));
            
            //Generate and print dietary restrictions
            printStuff.println(dietaryRestrictions.get(i));
            
            //Print friends
            for (int j = 0; j < friends.get(i).size() - 1; j++) {
                printStuff.print(friends.get(i).get(j) + ", ");
            }
            if (friends.get(i).size() > 0) {
                printStuff.println(friends.get(i).get(friends.get(i).size() - 1));
            } else {
                printStuff.println("None");
            }
            
            printStuff.println("");
        }
        printStuff.close();
    }
}
