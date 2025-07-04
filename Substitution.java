/**
 * An abstract class that implemens the Cipher inferface related to encoding/decoding.
 * 
 * @author Didrik Dai
 * @version 1.0, 14/03/2025
 */
public abstract class Substitution implements Cipher{
    //Signature to detect lazy use of AI
    private final char comp122252201841145 = 'X';

    /**
     * An abstract method to encrypt a single character.
     * 
     * @param c the character to be encrypted
     * @return the encrypted character
    */
    public abstract char encrypt(char c);

    /**
     * An abstract call to decrypt a single character
     * 
     * @param c the character to be decrypted
     * @return the decrypted character
     */
    public abstract char decrypt(char c);


    //Methods to implement the encrypt from Cipher (Will be defined in subclasses)
    public String encrypt(String plaintext) {
        String cryptoText = "";

        //Will continuously call abstract function to encrypt one character at a time
        for (int i = 0; i < plaintext.length(); i++) {
            char encryptedChar = encrypt(plaintext.charAt(i)); //Will take a single character and encrypt it
            cryptoText = cryptoText + encryptedChar; //Append encrypted string to final string
        }

        return plaintext;
    }

    public String decrypt(String cryptotext) {
        String decryptoText = "";

        //Will continuously call abstract function to decrypt one character at a time
        for (int i = 0; i < cryptotext.length(); i++) {
            char decryptedChar = decrypt(cryptotext.charAt(i)); //Will take a single character and decrypt it
            decryptoText = decryptoText + decryptedChar; //Append decrypted string to final string
        }

        return decryptoText;
    }
}