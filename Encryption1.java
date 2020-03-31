

public class Encryption1 {
  
  // fuck it
  private static int[] PRIMES = new int[] {
    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
    43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
    101, 103, 107, 109, 113, 127, 131, 137, 139, 149,
    151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199
  };
  
  public static void main(String[] args) {
    int[] primes = genPrimes();
    int m = (primes[0] - 1) * (primes[1] - 1);
    int n = primes[0] * primes[1];
    int[] keys = genKeys(m);
    int[] publicKey = new int[] {n, keys[0]};
    int[] privateKey = new int[] {n, keys[1]};
    
    int msg = 14;
    int cipher = encrypt(msg, publicKey);
    int decryptedMsg = decrypt(cipher, privateKey);
    int hackedMsg = hack(cipher, publicKey);
    
    System.out.println(m + " mn " + n);
    System.out.println(keys[0] + " keys " + keys[1]);
    System.out.println(cipher + " messages " + msg + " " + decryptedMsg + " " + hackedMsg);
  }
  
  private static int encrypt(int msg, int[] key) {
    return (int)Math.pow(msg, key[1]) % key[0];
  }
  
  private static int decrypt(int cipher, int[] key) {
    return (int)Math.pow(cipher, key[1]) % key[0];
  }
  
  private static int hack(int cipher, int[] key) {
    // for each prime combo in existence
    // if product = key[0]
    // use primes to find m
    for (int i = 0; i < PRIMES.length; i++) {
      for (int j = i; j < PRIMES.length; j++) {
        int n = PRIMES[i] * PRIMES[j];
        if (n == key[0]) {
          // :hacker voice: I'm in.
          int e = PRIMES[i];
          int m = (e - 1) * (PRIMES[j] - 1);
          for (int d = e + 1; d < m; d++) {
            if ((gcd(e, d) == 1) && (e * d % m == 1)) {
              return decrypt(cipher, new int[] {key[0], d});
            }
          }
        }
      }
    }
    return -1;
  }
  
  private static int[] genKeys(int m) {
    int e = 3;
    for (int d = e + 1; d < m; d++) {
      if ((gcd(e, d) == 1) && (e * d % m == 1)) {
        return new int[] {e, d};
      }
    }
    System.out.println("no key pairs");
    return new int[] {-1, -1};
  }
  
  private static int[] genPrimes() {
    return new int[] {3, 11};
  }
  
  private static boolean isPrime(int number) {
    int sqrt = (int)Math.ceil(Math.sqrt(number));
    for (int i = 2; i < sqrt; i++) {
      if (number % i == 0) return false;
    }
    return true;
  }
  
  private static int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }
}
