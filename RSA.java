import java.util.Scanner;
import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter the value of p and q where both are prime and not equal: ");
        int p = inp.nextInt();
        int q = inp.nextInt();
        while (!BigInteger.valueOf(p).isProbablePrime(10) || !BigInteger.valueOf(q).isProbablePrime(10) || p == q) {
            System.out.print("Enter the value of p and q where both are prime and not equal: ");
            p = inp.nextInt();
            q = inp.nextInt();
        }

        int n = p * q;
        int Φ = (p - 1) * (q - 1);

        System.out.print("Enter the value of e where 1 < e < Φ and gcd(e, Φ) = 1: ");
        int e = inp.nextInt();
        while (e <= 1 || e >= Φ || BigInteger.valueOf(e).gcd(BigInteger.valueOf(Φ)).intValue() != 1) {
            System.out.print("Enter the value of e where 1 < e < Φ and gcd(e, Φ) = 1: ");
            e = inp.nextInt();
        }

        BigInteger d = BigInteger.valueOf(e).modInverse(BigInteger.valueOf(Φ));

        int[] publicKey = {e, n};
        BigInteger[] privateKey = {d, BigInteger.valueOf(n)};

        System.out.print("Enter the message to be encrypted (0 <= M < n): ");
        int M = inp.nextInt();
        while (M < 0 || M >= n) {
            System.out.print("Enter the message to be encrypted (0 <= M < n): ");
            M = inp.nextInt();
        }

        // Encrypt the message: C = (M^e) mod n
        BigInteger C = BigInteger.valueOf(M).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));

        // Decrypt the message: M = (C^d) mod n
        BigInteger decryptedMessage = C.modPow(d, BigInteger.valueOf(n));

        System.out.println("------------------------");
        System.out.println("Public key: (" + publicKey[0] + ", " + publicKey[1] + ")");
        System.out.println("Private key: (" + privateKey[0] + ", " + privateKey[1] + ")");
        System.out.println("Encrypted message: " + C);
        System.out.println("Decrypted message: " + decryptedMessage);
        inp.close();
    }
}
