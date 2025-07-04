/** 
* <p>Takes an unencrypted/encrypted string/character as input along with a mapping string, and encrypts/decrypts it using the MonoAlphaSubstitution cipher.
* <p>Will complain if too many or too few inputs are given.
* 
* @param args    <p>1. "encrypt"/"decrypt", to indicate which direction you want to translate. <p>2. The string defining the translation aka. the mapping for the encryption/decryption. <p>3. The text to encrypt/decrypt.
* @author        Didrik Dai
* @version       1.0, 14/03/2025
*/
public class MonoAlphaSubstitution extends Substitution {
    //Signature to detect lazy use of AI
    private final char comp122252201841145 = 'X';

    //Initialize some stuff
    public String mappingString = ""; //The mapping string inputted by user
    public String plainString = ""; //The string to be encrypted/decrypted
    
    public String oldMapping = ""; //Every odd char in the map
    public String newMapping = ""; //The char in front of the odd char

    /**
     * Main method.
     * Will accept exactly three command line arguments: 
     * 1. "encrypt" or "decrypt", to indicate which direction you want to translate
     * 2. The string defining the translation aka. the mapping for the encryption
     * 3. The text to en/decrypt.
     * 
     * It will then either encrypt/decrypt a given string using the givemn mapping.
     * 
     * @param args inputs from user
     */
    public static void main(String[] args) {
        String cryptoType = "";
        String mappingTest = "";
        String plainTest = "";
        

        //Define and check if valid inputs
        try {
            cryptoType = args[0];
            mappingTest = args[1];
            plainTest = args[2];

            //Check if input is either encrypt or decrypt and complain if not
            if (cryptoType.equals("encrypt") || cryptoType.equals("decrypt")) {
                //Do nothing and just continue running if args[0] is encrypt or decrypt
            }
            else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
                return;
            }
        }
        catch (Exception e) { //Complains if too few inputs
            System.out.println("Too few parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }
        //Stops code if too many paramaters
        try {
            String tooMany = args[3]; //Check if a arg for index 3 exists -> Too many inputs
            System.out.println("Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }
        //Code will continue if no args[3] is found
        catch (Exception e) {
        }
        finally {
        }
     

        //BEGIN CALLING METHODS AND OTHER STUFF HERE//
        //Create object using constructor -> Will update the mapping and set variables
        MonoAlphaSubstitution mono = new MonoAlphaSubstitution(args[1]);
        mono.mappingString = args[1];
        mono.plainString = args[2];

        //Decide to encrypt or decrypt
        String moddedString = ""; //The either encrypted or decrypted string

        if (cryptoType.equals("encrypt")) {
            //Call encrypt methods -> Remember that its overloaded
            moddedString = mono.encrypt(args[2]);
            System.out.println(moddedString);
        }
        else {
            //Call decrypt methods -> Remember that its overloaded
            moddedString = mono.decrypt(args[2]);
            System.out.println(moddedString);
        }
    }

    /**
     * Default constructor.
     * Will simply map every letter to itself if no input is given.
     */
    public MonoAlphaSubstitution() {
        //Set the mapping to nothing
        oldMapping = "";
        newMapping = "";
    }
    /**
     * Mapping constructor.
     * Will interpet the mapping as; every character at an odd position is the encoding of the one directly before it.
     *
     * @param mapping the mapping inputted by the user
     */
     public MonoAlphaSubstitution(String mappingString) {
        //Loop through the mapping string, and set the mapping        
        for (int i = 0; i < mappingString.length(); i++) {
            if (i % 2 != 0) { //Will detect odd numbers using modulus
                oldMapping = oldMapping + mappingString.charAt(i - 1); //Take note of every odd character
                newMapping = newMapping + mappingString.charAt(i); //Character its supposed to map to
            }
        }

        //Make sure that if the user inputs "", the code will still map to itself
        if (mappingString.length() == 0) {
            oldMapping = "";
            newMapping = "";
        }
    }


    /**
     * Will take a whole string and call encrypt(char plainChar) to encrypt each induvidual character.
     * 
     * @param plainText the string to be encrypted
     * @return encrypted string using mapping
     */
    @Override
    public String encrypt(String plainText) {
        String cryptoText = ""; //Encrypted text

        //Will continuously call abstract function to encrypt one character at a time
        for (int i = 0; i < plainText.length(); i++) {
            char encryptedChar = encrypt(plainText.charAt(i)); //Will take a single character and encrypt it
            cryptoText = cryptoText + encryptedChar; //Append encrypted string to final string
        }
        
        return cryptoText;
    }
    /**
     * Will take a whole string and call decrypt(char plainChar) to decrypt each induvidual character.
     * 
     * @param cryptoText the string to be decrypted
     * @return encrypted string using mapping
     */
    @Override
    public String decrypt(String cryptoText) {
        String decryptoText = "";

        //Will continuously call abstract function to decrypt one character at a time
        for (int i = 0; i < cryptoText.length(); i++) {
            char encryptedChar = decrypt(cryptoText.charAt(i)); //Will take a single character and decrypt it
            decryptoText = decryptoText + encryptedChar; //Append decrypted string to final string
        }

        return decryptoText;
    }



    //Override the abstract methods (that both take a char and return a char)
    //These will also be used in the abstract substituion method
    /**
     * Will use the mapping to encrypt each induvidual character in the given string
     * 
     * @param plainChar the char to be encrypted
     * @return encrypted char using the mapping
     */
    @Override 
    public char encrypt(char plainChar) {
        char encryptedChar = 'A'; //Initialize the encrypted char

        for (int i = 0; i < oldMapping.length(); i++) { //Takes one char and loops thorugh mapping (i is order in the mapping)
            if (oldMapping.charAt(i) == plainChar) { //Checks if the current char is in the mapping
                encryptedChar = newMapping.charAt(i); //Encrypt the char based on mapping
                return encryptedChar; //Return the decrypted char
            }
        }

        //Exits loop if char is not found in mapping
        return plainChar; //Do not encrypt, just return char as it is -> it is not the mapping
    }
    /**
     * Will use the mapping to decrypt each induvidual character in the given string
     * 
     * @param plainChar the char to be decrypted
     * @return decrypted char using the mapping
     */
    @Override
    public char decrypt(char plainChar) {
        char decryptedChar = 'A'; //Initialize the decrypted char

        for (int i = 0; i < oldMapping.length(); i++) { //Takes one char and loops thorugh mapping (i2 is order in the mapping)
            if (newMapping.charAt(i) == plainChar) { //Checks if the current char is in the mapping
                decryptedChar = oldMapping.charAt(i);
                return decryptedChar; //Return the decrypted char
            }
        }
        
        //Exits loop if char is not found in mapping
        return plainChar; //Do not decrypt, just return char as it is
    }
}