import java.util.Scanner;

class AffineCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        byte k1 = sc.nextByte();
        byte k2 = sc.nextByte();

        while (k1 % 2 == 0 || k1 == 13) {
            System.out.print("Enter a valid key for k1 (odd and not 13): ");
            k1 = sc.nextByte();
        }
        while (k2 < 0 || k2 > 25) {
            System.out.print("Enter a valid key for k2 (0-25): ");
            k2 = sc.nextByte();
        }

        String cipherText = encryptMessage(str.toCharArray(), k1, k2);
        System.out.println("Encrypted Message is: " + cipherText);
        System.out.println("Decrypted Message is: " + decryptCipher(cipherText, k1, k2));
        bruteForce(cipherText);
        sc.close();
    }

    private static String encryptMessage(char[] msg, byte k1, byte k2) {
        StringBuilder cipher = new StringBuilder();
        for (char c : msg) {
           
                cipher.append((char) ((((k1 * (c - 'a'+1))%26 + k2) % 26) + 'a'-1));
            
        }
        return cipher.toString();
    }

    private static String decryptCipher(String cipher, byte k1, byte k2) {
        StringBuilder msg = new StringBuilder();
        int k1_inv = 0;
        for (int i = 0; i < 26; i++) {
            if ((k1 * i) % 26 == 1) { 
                k1_inv = i;
                break;
            }
        }
        
        for (int i = 0; i < cipher.length(); i++) {
            char c = cipher.charAt(i);
            
                msg.append((char) (((k1_inv * ((c - 'a'+1 - k2 + 26)) % 26) + 'A'-1)));
             
        }
        return msg.toString();
    }

    private static void bruteForce(String cipher) {
        for (int i = 1; i < 26; i=i+2) {
            if(i==13) continue;
            for (int j = 1; j < 26; j++) {
                System.out.println("k1: " + i + " k2: " + j + " Message: " + decryptCipher(cipher, (byte) i, (byte) j));
            }
        }
    }
}
