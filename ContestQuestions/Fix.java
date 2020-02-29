package fix;

import java.util.Scanner;

public class Fix {

    /*
    public static boolean isPrefix(String a, String b){
        for(int i = 0; i < b.length(); i++){
            if(a.substring(0, i).equals(b.substring(0, i))){
            return false;
            }
        }
        
        return true;
    }
    */
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String[] word = new String [3];
        n = input.nextInt();
        for(int j = 0; j < 3; j++){
            word[j] = input.next();
            if(word[0].startsWith (word[1])) || word[0].startsWith (word[2]) || word[1].startsWith (word[2]) || word[1].startsWith(word[0]) || word[2].startsWith(word[1]) || word[2].startsWith(word[0])){
                System.out.println("0");
            }
        }
        
        /*
            for (int i = 0; i < n; i++) {
            String a = input.nextLine();
            String b = input.nextLine();
            String c = input.nextLine();
        }
        */
    }
}
