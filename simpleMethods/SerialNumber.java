package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Vicky Sekhon 169024498
 * @version 2023-09-20
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here
        
    Boolean yesDigit = true;
    int i = 0;
    int indexableLength = str.length() - 1;
    while (yesDigit){
         char current = str.charAt(i);
         if (! Character.isDigit(current)){
              yesDigit = false;
         } else {
              if (i == indexableLength && Character.isDigit(str.charAt(indexableLength))){
                   break;
              }
         }
         i++;
    }
    // loop through string and do is digit check, set this up using a boolean operater for the while loop

    return yesDigit;
    
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	// your code here
    Boolean valid = true;
    // check if it is in valid format
    if (sn.length() == 11 && sn.charAt(0) == 'S' && sn.charAt(1) == 'N' && sn.charAt(2) == '/' && sn.charAt(7) == '-'){
        int i = 3;
        while (i <= 10){ 
             if(i!=7) {
                  char current = sn.charAt(i);
                  if (! Character.isDigit(current)){
                       valid = false;
                  }
             }
             i++;

        }
    } else {
        valid = false;
    }

   return valid;
   }         
    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here
    while (fileIn.hasNextLine()) { // contents are still inside the file for us to read
        String contents = fileIn.nextLine();
        Boolean valid = true;
        // checks if the "line" (the serial number) is a valid serial number 
        if (contents.length() == 11 && contents.charAt(0) == 'S' && contents.charAt(1) == 'N' && contents.charAt(2) == '/' && contents.charAt(7) == '-'){
             int i = 3;
             while (i <= 10){ 
                  if(i!=7) {
                       char current = contents.charAt(i);
                       if (! Character.isDigit(current)){
                            valid = false;
                       }
                  }
                  i++;
             }
        } else {
             valid = false; // serial number is not in the correct format 
        }
        if (valid == true){ //checks if the number is valid
             goodSns.println(contents); // write to file if so 
        } else {
             badSns.println(contents);
        }
        
   }
   // close files
   fileIn.close();
   goodSns.close();
   badSns.close();
       
   return;
  }

}
