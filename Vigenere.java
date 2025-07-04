/** 
* <p>Takes an unencrypted/encrypted string/character as input along with a mapping string, and encrypts/decrypts it using the Vigenere cipher.
* <p>Will complain if too many or too few inputs are given.
* 
* @param args    <p>1. "encrypt"/"decrypt", to indicate which direction you want to translate. <p>2. The string defining the translation aka. the mapping for the encryption/decryption. <p>3. The text to encrypt/decrypt.
* @author        Didrik Dai
* @version       1.0, 14/03/2025
*/
public class Vigenere extends Substitution {
    //Signature to detect lazy use of AI
    private final char comp122252201841145 = 'X';

    //Initializion of some stuff
    public String plainString = "";
    public String shiftString = ""; //Will contain the shifts for the characters
    public int shiftOrder = 0; //How far into the shift string we are (0 = first char in shiftString 1 = second osv...)


    public static void main(String[] args) {
        String cryptoType = "";
        String testShift = ""; //Used for testing the existance of shiftString
        String plainTest = ""; //USed for testing the existance of plainString

        //Define and check if valid inputs
        try {
            cryptoType = args[0];
            testShift = args[1];
            plainTest = args[2];

            //Check if input is either encrypt or decrypt and complain if not
            if (cryptoType.equals("encrypt") || cryptoType.equals("decrypt")) {
                //Do nothing and just continue running if args[0] is encrypt or decrypt
            }
            else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
                return;
            }
        }
        catch (Exception e) { //Complains if too few inputs
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }
        //Stops code if too many paramaters
        try {
            String tooMany = args[3]; //Check if a arg for index 3 exists -> Too many inputs
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }
        //Code will continue if no args[3] is found
        catch (Exception e) {
        }
        finally {
        }
     

        //BEGIN CALLING METHODS AND OTHER STUFF HERE//
        //Create object using constructor -> Will update the mapping and set variables
        Vigenere vigenere = new Vigenere(args[0]);
        vigenere.shiftString = args[1];
        vigenere.plainString = args[2];

        //Decide to encrypt or decrypt to call
        String moddedString = ""; //The either encrypted or decrypted string

        if (cryptoType.equals("encrypt")) {
            //Call encrypt methods -> Remember that its overloaded
            moddedString = vigenere.encrypt(args[2]);
            System.out.println(moddedString);
        }
        else {
            //Call decrypt methods -> Remember that its overloaded
            moddedString = vigenere.decrypt(args[2]);
            System.out.println(moddedString);
        }
    }

    /**
     * Default constructor.
     * Will simply map every letter to itself.
     */
    public Vigenere() {
        //Doesn't need to do anything else
        //The rest of the code will make sure it maps to itself if no map is detected
        shiftString = "";
    }
    /**
     * Will take a keyword input from user, and interpet it as a mapping for the encryption/decryption
     * 
     * @param keyWord keyword to be used as cipher inputted by user
     */
    public Vigenere(String keyWord) {
        shiftString = keyWord; //Get the keyword from constructor
    }
        

    @Override
    public String encrypt(String plainText) {
        String cryptoText = ""; //The encrypted string
        if (shiftString.length() != 0) { //Check if user actually has inputted any shift string

            //Loop through the whole string to be encrypted
            for (int i = 0; i < plainText.length(); i++) {
                //Actual encryption stuff
                char encryptedChar = encrypt(plainText.charAt(i)); //Will take a single character and encrypt it
                cryptoText = cryptoText + encryptedChar; //Append decrypted string to final string

                //Oppdate the shift order as we go
                shiftOrder++;
                //Will reset shift order if we exceed the its length
                if (shiftString.length() == shiftOrder) {
                    shiftOrder = 0; //Reset the shift order if it exeeds its own length
                }
            }
        }

        else { //Just maps every letter to itself (returns itself) if no shift string is given
            cryptoText = plainText;
        }

        return cryptoText;
    }
    @Override
    public char encrypt(char plainChar) {
        char encryptedChar = 'A'; //Contains the encrypted character using the assiciated shift

        if (shiftString.length() != 0) { //Check if user actually has inputted any shift string

            if (plainChar > 64 && plainChar < 123) { //Uses ascii code to check if A-z
                if (Character.isUpperCase(plainChar)) { //Encrypts uppercase chars
                    int shift = (int)shiftString.charAt(shiftOrder) - 65;
                    int shiftedInt = (plainChar - 'A' + shift + 26) % 26 + 'A'; //Shift char and wrap around if needed
                    encryptedChar = (char)shiftedInt;
                }
                else { //Encrypts lowercase chars
                    int shift = (int)shiftString.charAt(shiftOrder) - 65;
                    int shiftedInt = (plainChar - 'a' + shift + 26) % 26 + 'a'; //Shift char and wrap around if needed
                    encryptedChar = (char)shiftedInt;
                }
            }
            else { //If not A-z just return untouched
                encryptedChar = plainChar;
            }
        }

        else { //Just maps every letter to itself (returns itself) if no shift string is given
            encryptedChar = plainChar;
        }

        return encryptedChar;
    }


    @Override
    public String decrypt(String cryptoText) {
        String plainText = ""; //The encrypted string

        if (shiftString.length() != 0) { //Check if user actually has inputted any shift string

            //Loop through the whole string to be encrypted
            for (int i = 0; i < cryptoText.length(); i++) {
                //Actual encryption stuff
                char decryptedChar = decrypt(cryptoText.charAt(i)); //Will take a single character and encrypt it
                plainText = plainText + decryptedChar; //Append decrypted string to final string

                //Oppdate the shift order as we go
                shiftOrder++;
                //Will reset shift order if we exceed the its length
                if (shiftString.length() == shiftOrder) {
                    shiftOrder = 0; //Reset the shift order if it exeeds its own length
                }
            }
        }
        
        else { //Just maps every letter to itself (returns itself) if no shift string is given
            plainText = cryptoText;
        }

        return plainText;
    }
    @Override
    public char decrypt(char cryptoChar) {
        char decryptedChar = 'A'; //Contains the encrypted character using the assiciated shift

        if (shiftString.length() != 0) { //Check if user actually has inputted any shift string

            if (cryptoChar > 64 && cryptoChar < 123) { //Uses ascii code to check if A-z
                if (Character.isUpperCase(cryptoChar)) { //Encrypts uppercase chars
                    int shift = (int)shiftString.charAt(shiftOrder) - 65;
                    int shiftedInt = (cryptoChar - 'A' - shift + 26) % 26 + 'A'; //Shift char and wrap around if needed
                    decryptedChar = (char)shiftedInt;
                }
                else { //Encrypts lowercase chars
                    int shift = (int)shiftString.charAt(shiftOrder) - 65;
                    int shiftedInt = (cryptoChar - 'a' - shift + 26) % 26 + 'a'; //Shift char and wrap around if needed
                    decryptedChar = (char)shiftedInt;
                }
            }
            else { //If not A-z just return untouched
                decryptedChar = cryptoChar;
            }

        } 
        else { //Just maps every letter to itself (returns itself) if no shift string is given
            decryptedChar = cryptoChar; 
        }

        return decryptedChar;
    }
}