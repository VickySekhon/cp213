package cp213;

/**
 * @author Vicky Sekhon 169024498
 * @version 2023-09-20
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

	// your code here
	double closest = v1;
	double diff1 = target - v1;
	double diff2 = target - v2;
	if (diff1 > diff2) {
	    closest = v2;
	} else {
	    closest = v1;
	}

	return closest;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {

	// your code here
	Boolean prime = false; 

    //check if number is greater than 1, if not then the number is not prime
    if (n>1){
         int i = 2;
         while (n%i != 0 && i<n){
              i++;
         }
         if (i == n){ // completed while loop so no factors were found 
              prime = true;
         } 
    }
    return prime;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {

	// your code here
    double harmonicSum = 0; 
        
    for (double i = 1; i <= n; i++){
    	harmonicSum += 1/i;
    	}
    
    return harmonicSum;

    }

    
}