import java.util.Scanner;

public class Elgamal {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the value of p which is prime: ");
        int p = inp.nextInt();
        while(!(new java.math.BigInteger(String.valueOf(p)).isProbablePrime(1)) ) {
            System.out.println("Enter the value of p which is prime: ");
            p = inp.nextInt();
        }
        System.out.println("Enter the value of e1 which is primitive root of p: ");
        int e1 = inp.nextInt();
        while(primitiveRoot.isPrimitiveRoot(e1, p) == false) {
            System.out.println("Enter the value of e1 which is primitive root of p: ");
            e1 = inp.nextInt();
        }
        System.out.println("Enter the value of d which is private key such that it lies [1,p-2]: ");
        int d = inp.nextInt();
        while(d < 1 || d > p-2) {
            System.out.println("Enter the value of d which is private key such that it lies [1,p-2]: ");
            d = inp.nextInt();
        }
        System.out.println("Enter the value of P which is message to be encrypted: ");
        int P = inp.nextInt();
        int e2 = (int) Math.pow(e1, d) % p;
        
        System.out.println("Message to be encrypted: " + P);
        int [] encrypted = encrypt(P, e1, e2, p);
        System.out.println("Encrypted Message: " + encrypted[0]);
        System.out.println("Encrypted Key: " + encrypted[1]);
        int decrypted = (int) ((encrypted[1] * (Math.pow(encrypted[0], p-1-d))) % p);
        System.out.println("Decrypted Message: " + decrypted);


        inp.close();
    }
    private static int[] encrypt(int P, int e1, int e2, int p) {
        int r = (int) (Math.random() * (p-2)) + 1;
        int a = (int) Math.pow(e1, r) % p;
        int b = (int) ((Math.pow(e2, r) * P) % p);
        return new int[] {a, b};
    }
}
