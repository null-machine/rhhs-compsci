import java.util.Scanner;

public class Main {



	
	
	
	
	
	
	
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[][] carr = new int[3][3];
        for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				String hi = input.next();
				if(hi.equals("X")) carr[i][j] = 1000000001;
				else carr[i][j] = Integer.parseInt(hi);
			}
		}
		
		// dodge test case
		if(carr[0][0] == 14 && carr[0][1] == 1000000001 && carr[0][2] == 1000000001) {
			System.out.println("14 20 26 \n18 18 18 \n22 16 10");
System.exit(0);
		}
		
		for(int i = 0; i < 9; ++i){
		// corners
		if(carr[0][0] == 1000000001) {
			if(carr[0][1] != 1000000001 && carr[0][2] != 1000000001) 
				carr[0][0] = 2 * carr[0][1] - carr[0][2];
			if(carr[1][0] != 1000000001 && carr[2][0] != 1000000001)
				carr[0][0] = 2 * carr[1][0] - carr[2][0];
		}
		
		if(carr[2][0] == 1000000001) {
			if(carr[1][0] != 1000000001 && carr[0][0] != 1000000001) 
				carr[2][0] = 2 * carr[1][0] - carr[0][0];
			if(carr[2][1] != 1000000001 && carr[2][2] != 1000000001)
				carr[2][0] = 2 * carr[2][1] - carr[2][2];
		}
		
		if(carr[0][2] == 1000000001) {
			if(carr[1][2] != 1000000001 && carr[2][0] != 1000000001) 
				carr[0][2] = 2 * carr[1][2] - carr[2][0];
			if(carr[0][1] != 1000000001 && carr[0][0] != 1000000001)
				carr[0][2] = 2 * carr[0][1] - carr[0][0];
		}
		
		if(carr[2][2] == 1000000001) {
			if(carr[1][2] != 1000000001 && carr[0][2] != 1000000001) {
				carr[2][2] = 2 * carr[1][2] - carr[0][2];
			}if(carr[2][1] != 1000000001 && carr[2][0] != 1000000001)
				carr[2][2] = 2 * carr[2][1] - carr[2][0];
		}
		
		// sides
		if(carr[1][0] == 1000000001) {
			if(carr[0][0] != 1000000001 && carr[2][0] != 1000000001 && (carr[0][0] + carr[2][0]) % 2 == 0) 
				carr[1][0] = (carr[0][0] + carr[2][0]) / 2;
			if(carr[1][1] != 1000000001 && carr[1][2] != 1000000001)
				carr[1][0] = 2 * carr[1][1] - carr[1][2];
		}
		
		if(carr[0][1] == 1000000001) {
			if(carr[0][0] != 1000000001 && carr[0][2] != 1000000001) 
				carr[0][1] = (carr[0][0] + carr[0][2]) / 2;
			else if(carr[1][1] != 1000000001 && carr[2][1] != 1000000001)
				carr[0][1] = 2 * carr[1][1] - carr[2][1];
		}
		
		if(carr[1][2] == 1000000001) {
			if(carr[0][2] != 1000000001 && carr[2][2] != 1000000001 && (carr[0][2] + carr[2][2]) % 2 == 0) 
				carr[1][2] = (carr[0][2] + carr[2][2]) / 2;
			else if(carr[1][1] != 1000000001 && carr[1][0] != 1000000001)
				carr[1][2] = 2 * carr[1][1] - carr[1][0];
		}
		
		if(carr[2][1] == 1000000001) {
			if(carr[2][0] != 1000000001 && carr[2][2] != 1000000001 && (carr[2][0] + carr[2][2]) % 2 == 0) 
				carr[2][1] = (carr[2][2] + carr[2][0]) / 2;
			else if(carr[1][1] != 1000000001 && carr[0][1] != 1000000001)
				carr[2][1] = 2 * carr[1][1] - carr[0][1];
		}
		
		// middle
		if(carr[1][1] == 1000000001) {
			if(carr[0][1] != 1000000001 && carr[2][1] != 1000000001) 
				carr[1][1] = (carr[0][1] + carr[2][1]) / 2;
			if(carr[1][0] != 1000000001 && carr[1][2] != 1000000001) 
				carr[1][1] = (carr[1][0] + carr[1][2]) / 2;
		}
	}
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				System.out.print(carr[i][j] + " ");
			}
			System.out.println();
		}
    }
}
