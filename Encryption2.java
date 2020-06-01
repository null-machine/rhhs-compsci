import java.lang.Math;
import java.math.BigInteger;

public class Encryption2 {
  
  public static void main (String[] args) {
    //BigInteger[] pq = {new BigInteger("3"), new BigInteger("11")}; //generatePrimes();
    
    for (int k = 0; k<20;k++) {
      BigInteger[] pq = generatePrimes();
      BigInteger m = (pq[0].subtract(BigInteger.ONE)).multiply((pq[1].subtract(BigInteger.ONE)));
      BigInteger n = pq[0].multiply(pq[1]);
      BigInteger[] keys = generateKeyData(m);
      
      //System.out.println(pq[0]+" "+pq[1]+" "+m+" "+n);
      /*
      System.out.println(keys[0]+" "+keys[1]+" "+m);
      System.out.println((keys[0].multiply(keys[1])));
      System.out.println((keys[0].multiply(keys[1])).mod(m)+"\n");
      */
      BigInteger[] publicKey = {n, keys[0]};
      BigInteger[] privateKey = {n, keys[1]};
      
      BigInteger[] text = {new BigInteger("5345"), new BigInteger("342"), new BigInteger("34"), new BigInteger("0"), new BigInteger("1"), new BigInteger("13")};
      BigInteger[] cipher;
      BigInteger[] solved;
      
      cipher = encrypt(text, publicKey);
      solved = decrypt(cipher, privateKey);
      
      System.out.print("\ncipher: ");
      for (int i = 0; i<cipher.length; i++) {
        System.out.print(cipher[i]+" ");
      }
      System.out.print("\nsolved: ");
      for (int i = 0; i<solved.length; i++) {
        System.out.print(solved[i]+" ");
      }
      System.out.println();
    }
  }
  
  /** generatePrimes
  * Selects two different prime numbers.
  * @return BigInteger[], size 2, one prime number each
  */
  
  // TODO reimplement this
  // https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/math/BigInteger.java
  
  public static BigInteger[] generatePrimes() {
    BigInteger[] primes = {BigInteger.ZERO, BigInteger.ZERO};
    
    for (int i=0;i<2;i++) {
      primes[i] = new BigInteger(Integer.toString((int)(Math.random()*(1000)+3)));
      do {
        primes[i] = primes[i].nextProbablePrime();
      } while (primes[0].compareTo(primes[1]) == 0);
    }
    
    return primes;
  }
  
  /** generateKeyData
  * Generates two numerical values used for the keys which are returned in an array of size 2.
  * @return BigInteger[], size 2, e and d to make part of the public and private keys
  */
  public static BigInteger[] generateKeyData(BigInteger m) {
    BigInteger[] keys = new BigInteger[2];
    keys[0] = new BigInteger("3"); // keys[0] is e, key[1] is d
    
    while ((keys[0].gcd(m).compareTo(BigInteger.ONE) != 0) && (keys[0].compareTo(m) == -1)) {
      keys[0] = keys[0].nextProbablePrime();
      //System.out.println(keys[0]+" "+m);
    }
    
    keys[1] = keys[0].modInverse(m);
    // System.out.println(keys[0]+" "+keys[1]+" "+m);
    
    return keys;
  }
  
  /** encrypt
  * Encrypts data in number form.
  * @param msg, 1D BigInteger array for data
  * @param publicKey, 1D BigInteger array with n and e
  * @return BigInteger[], encrypted data
  */
  public static BigInteger[] encrypt(BigInteger[] msg, BigInteger[] publicKey) {
    BigInteger[] cipher = new BigInteger[msg.length];
    for (int i=0; i<msg.length; i++) {
      cipher[i] = msg[i].pow(publicKey[1].intValue()).mod(publicKey[0]);
    }
    return cipher;
  }
  
  /** decrypt
  * Decrypts data in number form.
  * @param cipher, 1D BigInteger array for the encrypted data
  * @param privateKey, 1D BigInteger array with n and d
  * @return BigInteger[], the decrypted data
  */
  public static BigInteger[] decrypt(BigInteger[] cipher, BigInteger[] privateKey) {
    BigInteger[] solved = new BigInteger[cipher.length];
    for (int i=0; i<solved.length; i++) {
      solved[i] = cipher[i].pow(privateKey[1].intValue()).mod(privateKey[0]);
    }
    return solved;
  }
}
