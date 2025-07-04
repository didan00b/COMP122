/** 
* <p>Takes an unencrypted/encrypted string or character as input and encrypts/decrypts it using a the monoalphasubstituion cipher.
* <p>Will complain if too many or too few inputs are given.
* 
* @param args    <p>1. "encrypt"/"decrypt", to indicate which direction you want to translate. <p>2. The shift for the encryption/decryption. <p>3. The text to encrypt/decrypt.
* @author        Didrik Dai
* @version       1.0, 14/03/2025
*/
public class Caesar extends MonoAlphaSubstitution {
    //Signature to detect lazy use of AI
    private final char comp122252201841145 = 'X';

    //Initialize some variables
    private int shift = 0; //Shift user inputs
    public String mappingBase = "AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZaabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz"; //Tranlation table to be used for encryption
    public String plainText = ""; //String to be encrypted/decrypted
    public String mapping = "";

    public static void main(String[] args) {
        String moddedString = ""; //Encrypted/decrypted string
        String cryptoType = ""; //If the user wants to encrypt or decrypt
        int testShift = 0; //Used to test if a shift exist
        String plainTest = ""; //Used to test if a plainText exist

        //Define and check if valid inputs
        try {
            cryptoType = args[0];
            testShift = Integer.parseInt(args[1]);
            plainTest = args[2];

            //Check if input is either encrypt or decrypt and complain if not
            if (cryptoType.equals("encrypt") || cryptoType.equals("decrypt")) {
                //Do nothing and just continue running if args[0] is encrypt or decrypt
            }
            else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java Caesar encrypt key \"cipher text\"");
                return;
            }
        }
        catch (Exception e) { //Complains if too few inputs
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }
        //Stops code if too many paramaters
        try {
            String tooMany = args[3]; //Check if a arg for index 3 exists -> Too many inputs
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }
        //Code will continue if no args[3] is found
        catch (Exception e) {
        }
        finally {
        }

        //Actual translation part//
        //Create a new Caesar object to instansiate the shift and translation tables
        Caesar caesar = new Caesar(Integer.parseInt(args[1]));

        //Decide to either encrypt or decrypt
        if (cryptoType.equals("encrypt")) {
            //Call encrypt methods -> Remember that its overloaded and inherited from MonoAlpha
            moddedString = caesar.encrypt(args[2]);
            System.out.println(moddedString);
        }
        else {
            //Call decryption methods -> Remember that its overloaded and inherited from MonoAlpha
            moddedString = caesar.decrypt(args[2]);
            System.out.println(moddedString);
        }
    }

    /**
     * Default constructor.
     * Will simply map every single char to themself.
     */
    public Caesar() {
    }
    /**
     * <p>Constructor.
     * <p>Will take a shift value, change it to a modififed shift value. 
     * Then translates the shift into a translation table MonoAlphaSubstituion can use.
     * 
     * @param plainShift the shift user inputs
     */
    public Caesar(int plainShift) {
        shift = plainShift + 12225; //Convert shift to desired shift?
        mapping = translate(); //Translate the table to be used in MonoAlphaSub
    }

    public String encrypt(String plainText) {
        //Create MonoAlpha object to instansiate the translation table in MonoAlpha
        MonoAlphaSubstitution monosub = new MonoAlphaSubstitution(mapping);
        String cryptoText = monosub.encrypt(plainText);
        
        return cryptoText;
    }
    public String decrypt(String cryptoText) {
        //Create MonoAlpha object to instansiate the translation table in MonoAlpha
        MonoAlphaSubstitution monosub = new MonoAlphaSubstitution(mapping);
        String plainText = monosub.decrypt(cryptoText);
        
        return plainText;
    }

    /**
     * A method to translate a shift to a mapping MonoAlphaSubstitution can use.
     * 
     * @return the translated mapping
     */
    public String translate() { 
        String translatedMapping = ""; //The translated mapping

        //Loop thorugh the mapping base and translate to given shift
        for (int i = 0; i < mappingBase.length(); i++) {
            if (i % 2 != 0) { //Will detect odd numbers using modulus
                if (Character.isUpperCase(mappingBase.charAt(i))) { //Checks if letter is uppercase
                    int shiftedInt = (mappingBase.charAt(i-1) - 'A' + shift + 26) % 26 + 'A'; //Shift char and wrap around if needed
                    translatedMapping = translatedMapping + (char)shiftedInt; //Append the translated char to the mapping
                }
                else { //If not, it gotta be lowercase
                    int shiftedInt = (mappingBase.charAt(i-1) - 'a' + shift + 26) % 26 + 'a'; //Shift char and wrap around if needed
                    translatedMapping = translatedMapping + (char)shiftedInt; //Append the translated char to the mapping
                }
            }
            else { //Non odd chars are unchanged and just directly added to the map
                translatedMapping = translatedMapping + mappingBase.charAt(i);
            }

        }

        return translatedMapping;
    }


}