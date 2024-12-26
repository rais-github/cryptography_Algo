import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class primitiveRoot {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = inp.nextInt();
        int phi = n - 1;
        List<Integer> primitiveRoots = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
            if (gcd(i, n) == 1) {
                if (isPrimitiveRoot(i, n)) {
                    primitiveRoots.add(i);
                }
            }
        }
        
        if (primitiveRoots.isEmpty()) {
            System.out.println("Primitive root does not exist for " + n);
        } else {
            System.out.println("Primitive roots of " + n + " are: " + primitiveRoots);
            System.out.println("Table of primitive roots and their corresponding roots:");
            for (int root : primitiveRoots) {
                System.out.println(root + " => " + getRoots(root, n));
            }
        }
        
        inp.close();
    }
    
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
        }
        
        public static boolean isPrimitiveRoot(int a, int n) {
        int value = 1;
        int order = 0;

        for (int i = 0; i < n - 1; i++) {
            value = (value * a) % n;
            order++;
            if (value == 1) {
                return order == n - 1; 
            }
        }
        return false;
    }
        
        private static List<Integer> getRoots(int a, int n) {
        List<Integer> roots = new ArrayList<>();
        int value = 1;
        for (int i = 0; i < n - 1; i++) {
            value = (value * a) % n;
            roots.add(value);
        }
        return roots;
    }
}

