import java.util.Scanner;

public class PlayFair_Cipher {

    private static char[][] keyMatrix = new char[5][5];

    private static void generateKeyMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        boolean visited [] = new boolean[26];
        int index=0;
        for(char c:key.toCharArray())
        {
            if(!visited[c-'A'] && c!=' ')
            {
                visited[c-'A']=true;
                keyMatrix[index/5][index%5]=c;
                index++;
            }
        }

        for(char c='A'; c<='Z'; c++)
        {
            if(!visited[c-'A'] && c!='J')
            {
                visited[c-'A']=true;
                keyMatrix[index/5][index%5]=c;
                index++;
            }
        }
    }

    // Encrypt the plaintext
    private static String encrypt(String plaintext, String key) {
        generateKeyMatrix(key);
        plaintext = preprocess(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                ciphertext.append(keyMatrix[posA[0]][(posA[1] + 1) % 5]);
                ciphertext.append(keyMatrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                ciphertext.append(keyMatrix[(posA[0] + 1) % 5][posA[1]]);
                ciphertext.append(keyMatrix[(posB[0] + 1) % 5][posB[1]]);
            } else { // Rectangle
                ciphertext.append(keyMatrix[posA[0]][posB[1]]);
                ciphertext.append(keyMatrix[posB[0]][posA[1]]);
            }
        }

        return ciphertext.toString();
    }

    // Decrypt the ciphertext
    private static String decrypt(String ciphertext, String key) {
        generateKeyMatrix(key);
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                plaintext.append(keyMatrix[posA[0]][(posA[1] + 4) % 5]);
                plaintext.append(keyMatrix[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                plaintext.append(keyMatrix[(posA[0] + 4) % 5][posA[1]]);
                plaintext.append(keyMatrix[(posB[0] + 4) % 5][posB[1]]);
            } else { // Rectangle
                plaintext.append(keyMatrix[posA[0]][posB[1]]);
                plaintext.append(keyMatrix[posB[0]][posA[1]]);
            }
        }

        return plaintext.toString();
    }

    private static String preprocess(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("\\s+", "");
        StringBuilder processed = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
           char ch = text.charAt(i);
           processed.append(ch);
           if(i+1<text.length() && text.charAt(i+1)==ch)
           {
               processed.append('X');
           }
        }

        if ((processed.length()&1)==1) {
            processed.append('X'); 
        }

        return processed.toString();
    }

    private static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        System.out.println("Choose: 1-Encrypt, 2-Decrypt");
        int choice = scanner.nextInt();

        if (choice == 1) {
            String encrypted = encrypt(message, keyword);
            System.out.println("Encrypted Message: " + encrypted);
        } else if (choice == 2) {
            String decrypted = decrypt(message, keyword);
            System.out.println("Decrypted Message: " + decrypted);
        } else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
