import java.util.Scanner;


// flipper

public class Main {

    private static int[] flipV(int[] grid) {
		int tl = grid[0];
		int bl = grid[2];
		grid[0] = grid[1];
		grid[2] = grid[3];
		grid[1] = tl;
		grid[3] = bl;
		return grid;
	}
	
	private static  int[] flipH(int[] grid) {
		int tl = grid[0];
		int tr = grid[1];
		grid[0] = grid[2];
		grid[1] = grid[3];
		grid[2] = tl;
		grid[3] = tr;
		return grid;
	}

    public static void main(String[] args) {
        int[] grid = {1, 2, 3, 4};
        Scanner input = new Scanner(System.in);
        String sequence = input.next();
        for(int i = 0; i < sequence.length(); ++i) {
			if(sequence.charAt(i) == 'V') grid = flipV(grid);
			else grid = flipH(grid);
		}
        
        System.out.println(grid[0] +  " " + grid[1] + "\n" + grid[2] + " " + grid[3]);
    }
}
