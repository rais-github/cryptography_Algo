import java.util.Scanner;
public class DiffieHellman {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the value of prime number (p): ");
        int p = inp.nextInt();
        while(!java.math.BigInteger.valueOf(p).isProbablePrime(10)){
            System.out.println("Enter the value of prime number (p): ");
            p = inp.nextInt();
        }
        System.out.println("Enter the value of primitive root of p (α): ");
        int α = inp.nextInt();
        while(!primitiveRoot.isPrimitiveRoot(p, α))
        {
            System.out.println("Enter the value of primitive root of p (α): ");
            α = inp.nextInt();
        }
        System.out.println("Enter the value of private key of A (Xa): ");
        int Xa = inp.nextInt();
        System.out.println("Enter the value of private key of B (Xb): ");
        int Xb = inp.nextInt();

        int Ya = (int) Math.pow(α, Xa) % p;
        int Yb = (int) Math.pow(α, Xb) % p;
        System.out.println("Public key of A (Ya): " + Ya + "\nPublic key of B (Yb): " + Yb);

        int Ka = (int) Math.pow(Yb, Xa) % p;
        int Kb = (int) Math.pow(Ya, Xb) % p;
        System.out.println("Secret key of A (Ka): " + Ka + "\nSecret key of B (Kb): " + Kb);
        inp.close();
    }    
}
