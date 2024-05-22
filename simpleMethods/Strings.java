package cp213;

/**
 * @author Vicky Sekhon 169024498
 * @version 2023-09-20
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here
	String str = string;
    str = str.toLowerCase();
    for (int j = 0; j <= string.length()-1; j++) {
    	if (! Character.isAlphabetic(string.charAt(j))){
    		str = str.replace(String.valueOf(string.charAt(j)), "");
    	}
    }
    Boolean palindrome = false;
    String backwards = "";
    int length = str.length() - 1;
    for (int i = length; i >= 0; i--){
         backwards += str.charAt(i); 
    }
    palindrome = str.equals(backwards);

    return palindrome;

   }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here
    Boolean valid = true;
    int length = name.length();
    if (length == 1){
         if (name.charAt(0) == '_' || Character.isDigit(name.charAt(0))){
              valid = false;
         } 
    } else{ // the variable name isnt just one character
         if (Character.isDigit(name.charAt(0))){
              valid = false;
         } else {
              for (int i = 0; i < length; i++){
                   if (Character.isWhitespace(name.charAt(i)) || name.charAt(i) == ':'){ //addresses the case for a white space in a variable to still be a valid variable name
                            valid = false;
                  }
             }    
        }
    }
    
    return valid;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// your code here
    String consonants = "";
    String indexesCapitalized = "";

    // keep track of indexes capitalized
    for (int i = 0; i<=word.length() - 1; i++){
         if(Character.isUpperCase(word.charAt(i))){
              indexesCapitalized += i;
         }
    }    
    word = word.toLowerCase();
    String vowels = "aeiou"; // create vowels to compare with
    char firstLetter = word.charAt(0); // look at the first letter of the word 
    if (vowels.contains(String.valueOf(firstLetter))){     // check if the letter is in vowels
         word += "way"; // ending portion of final string 
    } else{
         int count = 0; // doesnt start with vowel
         while (count < word.length() && ! vowels.contains(String.valueOf(word.charAt(count)))){ // while the current character is not a vowel
              if (count == 1){ // after the first iteration, y is considered a vowel
                   vowels += "y";
              }
              consonants += word.charAt(count);
              count++;
         }
         word = word.substring(count) + consonants + "ay";
    }

    // make indexes that need to be capitalized, capitalized
    for (int k = 0; k <= indexesCapitalized.length()-1; k++){ // looking through the indexes to be capitalized eg "02"
         int indexToCapitalize = Character.getNumericValue(indexesCapitalized.charAt(k));  // convert one by one the character at index "k" to an indexable integer eg = 0
         char upperChar = Character.toUpperCase(word.charAt(indexToCapitalize)); // get uppercase character at that specific index eg = Character.toUpperCase(word.charAt(0)) = A
         word = word.substring(0, indexToCapitalize) + upperChar + word.substring(indexToCapitalize + 1, word.length()); //
         //        substring to replace ""              A                  remaining substring = "vidday"
    }
    // dAvid
    // indexes = "02" integer.parseint(string.valueof(indexesCapitlaized.charAt(k)))
    return word; 
    }
}
