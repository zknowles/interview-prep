import java.lang.*;
import java.util.*;

/*
* Read string from standard input and determine whether or not it is a
* palindrome. Print whether or not the string is a palindrome.
*/
public class Palindrome
{
	/*
	* Determine whether string is a palindrome or not and print result.
	*/
	static void isPalindrome(String str) {
        // start at the ends of the string
		int i = 0;
		int j = str.length() - 1;

        // initialize string list of punctuation
        String punctuation = ".!?,:;'\"()- ";

		// keep going until the two indexes cross/meet
        while (i < j)
		{
			char a = str.charAt(i);
			char b = str.charAt(j);

			// skip over punctuation characters while parsing from left
            if (punctuation.contains(Character.toString(a))) {
				i++;
				continue;
			}
			// skip over punctuation characters while parsing from right
            if (punctuation.contains(Character.toString(b))) {
				j--;
				continue;
			}

			// if characters not equal (ignore case), str is not palindrome
			if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                System.out.println("false");
				return;
            }

			// move to next characters
			i++;
			j--;
		}

		// string is palindrome if has made it to this point
		System.out.println("true");
    }

    /*
    * Read string from standard input and pass to palindrome detection method.
    */
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String s = sc.nextLine();
    	isPalindrome(s);
    }
}