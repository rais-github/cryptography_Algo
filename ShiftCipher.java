import java.util.Scanner;
class ShiftCipher{
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter Plaintext: ");
        String plainText = inp.nextLine();
        while(!plainText.chars().allMatch(Character::isLetter)){
            System.out.println("Please enter a valid plaintext: ");
            plainText = inp.nextLine();
        }
        plainText = plainText.toLowerCase();
        System.out.println("Enter Key: ");
        int key = inp.nextInt();
        while(key < 1 || key > 25){
            System.out.println("Please enter a valid key: ");
            key = inp.nextInt();
        }
        String cipherText = encrypt(plainText, key);
        System.out.println("Encrypted Text: "+cipherText.toUpperCase());
        System.out.println("Decrypted Text: "+decrypt(cipherText, key));
        System.out.println("------------Brute Force Decryption------------");
        System.out.println("Key for "+cipherText+" is -> "+bruteForce(cipherText, plainText));
        inp.close();
    }
    private static String encrypt(String plainText , int key)
    {
        StringBuilder cipherText = new StringBuilder();
        for(char ch:plainText.toCharArray())
        {
            char temp = (char)(((int)ch + key - 97) % 26 + 97);
            cipherText.append(temp);
        }
        return cipherText.toString();
    }
    private static String decrypt(String cipherText,int key)
    {
        StringBuffer plainText = new StringBuffer();
        for(char ch:cipherText.toCharArray())
        {
            char temp = (char)((((int)ch - key - 97) + 26) % 26 + 97);
            plainText.append(temp);
        }
        return plainText.toString();
    }
    private static Integer  bruteForce(String cipher,String plainText) {
        for (int i = 1; i < 26; i=i+1) {
            if (decrypt(cipher, i).equals(plainText)) {
                return i;
            }
        }
        return null;
    } 
}
