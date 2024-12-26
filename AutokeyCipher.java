import java.util.Scanner;

public class AutokeyCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your choice: \n1. Encryption \n2. Decryption \n3. Brute Force \n4. Exit");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                String msg = getInputMessage(sc, "Enter the message to encrypt (only alphabetic characters): ");
                String key = getInputKey(sc, "Enter the key (a single letter or a number between 0-25): ");
                String encrypted = autoEncryption(msg, key);
                System.out.println("Plaintext : " + msg);
                System.out.println("Encrypted : " + encrypted);

            } else if (choice.equals("2")) {
                String msg = getInputMessage(sc, "Enter the message to decrypt (only alphabetic characters): ");
                String key = getInputKey(sc, "Enter the key (a single letter or a number between 0-25): ");
                String decrypted = autoDecryption(msg, key);
                System.out.println("Encrypted : " + msg);
                System.out.println("Decrypted : " + decrypted);

            } else if (choice.equals("3")) {
                String msg = getInputMessage(sc, "Enter the message to encrypt (only alphabetic characters): ");
                String key = getInputKey(sc, "Enter the key (a single letter or a number between 0-25): ");
                String encrypted = autoEncryption(msg, key);
                bruteForce(encrypted, msg);

            } else if (choice.equals("4")) {
                System.out.println("Exiting the program...");
                break;

            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }

        sc.close();
    }

    private static String getInputMessage(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String msg = sc.nextLine().toUpperCase();
            if (msg.matches("[A-Z]+")) {
                return msg;
            } else {
                System.out.println("Invalid input. Please enter only alphabetic characters.");
            }
        }
    }

    private static String getInputKey(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String key = sc.nextLine().toUpperCase();
            if (key.matches("[A-Z]")) {
                return key;
            } else if (key.matches("\\d+") && Integer.parseInt(key) >= 0 && Integer.parseInt(key) <= 25) {
                return String.valueOf(alphabet.charAt(Integer.parseInt(key)));
            } else {
                System.out.println("Invalid input. Please enter a letter (A-Z) or a number between 0-25.");
            }
        }
    }

    private static String autoEncryption(String msg, String key) {
        int k = alphabet.indexOf(key);
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (i == 0) {
                encrypted.append(alphabet.charAt((c - 'A' + k) % 26));
            } else {
                encrypted.append(alphabet.charAt((c - 'A' + (msg.charAt(i - 1) - 'A')) % 26));
            }
        }
        return encrypted.toString();
    }

    private static String autoDecryption(String msg, String key) {
        int k = alphabet.indexOf(key);
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (i == 0) {
                decrypted.append(alphabet.charAt((c - 'A' - k + 26) % 26));
            } else {
                decrypted.append(alphabet.charAt((c - 'A' - (msg.charAt(i - 1) - 'A') + 26) % 26));
            }
        }
        return decrypted.toString();
    }

    private static void bruteForce(String cipherText, String plainText) {
        for (int i = 0; i < 26; i++) {
            String decrypted = autoDecryption(cipherText, String.valueOf(alphabet.charAt(i)));
            if (decrypted.equals(plainText)) {
                System.out.println("The key is: " + i);
                return;
            }
        }
        System.out.println("No valid key found.");
    }
}
