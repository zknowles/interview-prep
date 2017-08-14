import java.lang.*;
import java.util.*;

/* 
* Read string from standard input and print the number of distinct substrings
* that can be made using the characters in that string.
*/
public class DistinctSubstrings
{
	/*
	* Return count of distinct substrings that can be made from characters in
	* string.
	*/
	static long substringCalculator(String st) {
		// initialize array of strings to hold suffixes of st
        int length = st.length();
		String[] suffixes = new String[length];

		// populate array with the sorted suffixes of st
        for (int i = 0; i < length; i++)
			suffixes[i] = st.substring(i);
		Arrays.sort(suffixes);

		int count = suffixes[0].length(); // start count at first suffix length

		// Find length of longest common prefix of adjacent suffixes, calculate
        // difference between that and length of second suffix, then add that
        // difference to the running sum.
        for (int i = 1; i < length; i++) {
			String a = suffixes[i-1];
			String b = suffixes[i];
			int checkLength = Math.min(a.length(), b.length());

			int j; // variable for length of the longest common prefix
			for (j = 0; j < checkLength; j++) {
				if (a.charAt(j) != b.charAt(j))
					break;
			}

			count += b.length() - j;
		}

		return count;
    }

    /* 
    * Read string in from standard input and pass its lowercase version to
    * substringCalculator, provided it contains only alphabetical characters.
    */
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String s = sc.nextLine();

    	// make sure string contains only alphabetical characters
    	if (!s.matches("[a-zA-Z]+")) {
    		System.err.println("Error:
    		 Input string must contain only alphabetical characters");
    		return;
    	}

    	s = s.toLowerCase();
    	System.out.println(substringCalculator(s));
    }
}