import java.util.Scanner;
public class HillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message to be encrypted:");
        String message = sc.nextLine();
        int length = message.length();
        System.out.println("The length of the message is:"+length);
        int n=0,m=0;
        if((int)(Math.sqrt(length)*Math.sqrt(length))==length)
        {
            n = (int)Math.sqrt(length);
            m = n+1;
        }
        else if(new java.math.BigInteger(String.valueOf(length)).isProbablePrime(1)==false)
        {
            n = (int)Math.sqrt(length);
            m = n+1;
        }
        else{
            n = (int)Math.sqrt(length);
            m = n+2;
        }
        System.out.println("The size of the message matrix is:"+n+"x"+m);
        int [][] p = new int[n][m];
        int index=0;
        for(char ch:message.toCharArray())
        {
            p[index/m][index%m] = (int)ch;
            index++;
        }
        // insert the remaining indexes in p with remaining alphabet letter 'z'
        for(int i=index;i<n*m;i++)
        {
            p[i/m][i%m] = (int)'z';
        }   
        System.out.println("Enter the sqaure key matrix of size:"+m);
        int [][] key = new int[m+1][m+1];
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
            {
                key[i][j] = sc.nextInt();
            }
        }
        int [][] c = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                c[i][j] = 0;
                for(int k=0;k<m;k++)
                {
                    c[i][j] += key[i][k]*p[k][j];
                }
                c[i][j] = c[i][j]%26;
            }
        }
        System.out.println("The encrypted message is:");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.print((char)(c[i][j]+'a')+"");
            }
        }
        //find the inverse of the key matrix using elementary row operations

        int [][] keyInverse = new int[m][m];
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i==j)
                {
                    keyInverse[i][j] = 1;
                }
                else{
                    keyInverse[i][j] = 0;
                }
            }
        }
        for(int i=0;i<m;i++)
        {
            int temp = key[i][i];
            for(int j=0;j<m;j++)
            {
                key[i][j] = key[i][j]/temp;
                keyInverse[i][j] = keyInverse[i][j]/temp;
            }
            for(int j=0;j<m;j++)
            {
                if(i!=j)
                {
                    temp = key[j][i];
                    for(int k=0;k<m;k++)
                    {
                        key[j][k] = key[j][k] - temp*key[i][k];
                        keyInverse[j][k] = keyInverse[j][k] - temp*keyInverse[i][k];
                    }
                }
            }
        }
        System.out.println("The inverse key matrix is:");
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.print(keyInverse[i][j]+" ");
            }
            System.out.println();
        }

        //decrypt the message using the inverse key matrix
        int [][] c1 = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                c1[i][j] = 0;
                for(int k=0;k<m;k++)
                {
                    c1[i][j] += keyInverse[i][k]*c[k][j];
                }
                c1[i][j] = c1[i][j]%26;
            }
        }
        System.out.println("The decrypted message is:");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.print((char)(c1[i][j]+'a')+"");
            }
        }




        sc.close();
    }
}

