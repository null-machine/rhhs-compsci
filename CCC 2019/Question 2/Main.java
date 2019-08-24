import java.util.Scanner;

public class Main {

    
    
    
    
    
    
    
    
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // grab some primes
        int primeCheck = 20000019;
        boolean[] prime = new boolean[primeCheck];
		
        for(int i = 2; i < primeCheck; ++i) prime[i] = true;
            
        for(int factor = 2; factor * factor < primeCheck; ++factor) {
            if(prime[factor]) {
                for (int i = factor; factor * i < primeCheck; i++)
                    prime[factor * i] = false;
            }
        }
        int primeCount = 0;
        for(boolean boo : prime) {
			if(boo) ++primeCount;
		}
		int[] primes = new int[primeCount];
		int c = 0;
		for(int i = 2; i < primeCheck; ++i) {
			if(prime[i]) { 
				primes[c] = i;
				c++;
			}
		}
		//for(int i : primes) System.out.print(i + " ");
		
		
		int t = input.nextInt();
		for(int i = 0; i < t; ++i) {
			int avg = input.nextInt();
			//for(int i = 3; i < avg; i += 2) {
			for(int prome : primes) {
				if(prime[2 * avg - prome]) {
					System.out.println(prome + " " + (2 * avg - prome));
					break;
				}
			}
		} 
    }
}
