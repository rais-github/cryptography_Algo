import java.util.Scanner;
class multiplicativeCipher{
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
            System.out.println("Please enter a valid key(1-25): ");
            key = inp.nextInt();
        }
        String cipherText = encrypt(plainText, key);
        System.out.println("Encrypted Text: "+cipherText.toUpperCase());
        System.out.println("Decrypted Text: "+decrypt(cipherText, key));
        System.out.println("------------Brute Force Decryption------------");
        System.out.println("Key for "+cipherText+" is -> "+bruteForce(cipherText, plainText));
        inp.close();
    }
    private static String encrypt(String plainText,int key)
    {
        StringBuilder cipherText = new StringBuilder();
        for(char ch:plainText.toCharArray())
        {
            cipherText.append((char)(((ch - 'a') * key) % 26 + 'a'));;
           
        }
        return cipherText.toString();
    }
    private static String decrypt(String cipherText,int key)
    {
        StringBuffer plainText = new StringBuffer();
        int keyInverse = 0;
        for(int i=1;i<=25 ; i++)
        {
            if(key*i%26 == 1)
            {
                keyInverse = i;
                break;
            }
        }
        for(char ch:cipherText.toCharArray())
        {
            plainText.append((char)(((ch - 'a') * keyInverse) % 26 + 'a'));
        }
        return plainText.toString();
    }
    private static int bruteForce(String cipherText,String plainText)
    {
        for(int i=1;i<=25;i++)
        {
            if(plainText.equals(decrypt(cipherText, i)))
            {
                return i;
            }
        }
        return -1;
    }
}