import java.util.Scanner;
public class RabinMillerPrimality {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter a number n: ");
        int n = inp.nextInt();
        int base = 2; 
        System.out.println("The number "+n+" is "+test(n,base));
        inp.close();
    }
    private static String test(int n , int a)
    {
        int k = 0;
        int m = n-1;
        while(m%2==0)
        {
            m = m/2;
            k++;
        }
        System.out.println("k = "+k+" m = "+m);
        int T = (int) Math.pow(a, m)%n;
        if(T==1 || T==-1)
        {
            return "prime";
        }
        for(int i = 1 ; i < k ; i++)
        {
            T = T*T%n;
            if(T==-1)
            {
                return "prime";
            }
            else if(T==1)
            {
                return "composite";
            }
        }
        return "composite";
    }
}
