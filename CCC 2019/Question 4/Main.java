import java.util.Scanner;

public class Main {

    
    
    
    
    
    
    
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] att = new int[n];
        for(int i = 0; i < n; ++i) att[i] = input.nextInt();
        
        int days = (int)Math.ceil(n / k) + 1;
        int choice = days * k - n;
        System.out.println(days + " " + choice);
        int[] restLocs = new int[days];
        int finVal = 0;
        
        for(int day = 0; day < days; ++day) {
			int maxLoc = -1;
			int maxVal = -1;
			for(int i = restLocs[Math.max(0, day-1)]; i < k + restLocs[Math.max(0, day-1)]; ++i) {
				if(att[Math.min(i, att.length-1)] > maxVal) {
					maxVal = att[Math.min(i, att.length-1)];
					maxLoc = Math.min(i, att.length-1);
				}
			}
			finVal += maxVal;
			if(k + restLocs[Math.max(0, day-1)] - maxLoc < choice) {
				choice -= k + restLocs[Math.max(0, day-1)] - maxLoc;
				restLocs[day] = maxLoc;
			} else {
				if(day == 0) restLocs[day] = k;
				else restLocs[day] = restLocs[day-1] + k;
			}
		}
		System.out.println(finVal);
    }
}
