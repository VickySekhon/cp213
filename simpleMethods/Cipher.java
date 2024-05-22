package cp213;

/**
 * @author Vicky Sekhon 169024498
 * @version 2023-09-20
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {

	// your code here
    String cipher = "";

    if (s.isEmpty()){
         cipher = "";
    } else {
         for (int j = 0; j < s.length(); j++){
              for (int i = 0; i < ALPHA_LENGTH; i++){
                   char currentAlpha = ALPHA.charAt(i);
                   if (Character.toUpperCase(currentAlpha) == Character.toUpperCase(s.charAt(j))){ // the alphabet character corresponds to the current character in the string 
                        cipher += ALPHA.charAt((i+n)%ALPHA_LENGTH);
                   }
              }
         }
    }
    cipher = cipher.toUpperCase();
    return cipher;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	// your code here
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String str = s.toUpperCase();
    String enciphered = "";
    
    if (str.length() == 0){
         enciphered = "";
    } else{
         for (int i = 0; i<str.length(); i++){ // finds current character in string
              char currentCharacter = str.charAt(i); // current string^
              for (int j = 0; j<alphabet.length();j++){ // looks at the alphabet 
                   if (currentCharacter == (alphabet.charAt(j))){ // finds the corresponding alphabet value that equals the string
                            enciphered += ciphertext.charAt(j);
                       }
                  }
   
             }
        }
        // use for loop to find characters in string
    // find appropriate position for character in alphabet
    // once index is found use it to compare to ciphertext and create new string with the indexes 

     return enciphered;
    }

}
