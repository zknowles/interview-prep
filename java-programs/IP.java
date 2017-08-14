import java.lang.*;
import java.util.*;

/*
* Read file from standard input, parse each line and print whether the line
* is an IPv4 address, an IPv6 address, or neither.
*/
public class IP
{
	/*
	* Validate each line by checking if it is an IPv4 address, an IPv6 address,
	* or neither, and print the result.
	*/
	static void IPAddressValidation(String s) {
		// initialize both IPv4 and IPv6 possibilities to false
		boolean v4 = false;
		boolean v6 = false;
		
		// split string with period as delimiter (must double-escape)
        String[] v4Strings = s.split("\\.");

		// check for IPv4 only if there are 4 substrings
        if (v4Strings.length == 4) {
			int j;
            // check each substring to see if it is an int between 0 and 255, inclusive
			for (j = 0; j < 4; j++) {
				int number = -1;
				try { number = Integer.parseInt(v4Strings[j]); }
				catch (NumberFormatException e) { break; }

				if (number < 0 || number > 255) break;
			}
			if (j == 4) v4 = true; // string must be IPv4 if all 4 substrings check out
		}

		// check for IPv6 only if not IPv4
        if (!v4) {
			// split string with colon as delimiter; must have -8 to account
			// for empty strings between colons or trailing/leading empty
			// strings
            String[] v6Strings = s.split(":", -8);

			// investigate only if there are 8 substrings
            if (v6Strings.length == 8) {
				int j;
				// check each string to see that it is either empty or is a
				// 4-digit hex number
                for (j = 0; j < 8; j++) {
                    if (v6Strings[j].length() > 4) break; // if more than 4 hex digits, cannot be IPv6
					// check each substring to see if it is a hex number
                    int number = -1;
					try { number = Integer.parseInt(v6Strings[j], 16); }
					catch (NumberFormatException e) { break; }
				}
				if (j == 8) v6 = true; // string must be IPv6 if all 8 substrings check out
			}
		}

		// print the appropriate output
        if (v4) System.out.println("IPv4");
		else if (v6) System.out.println("IPv6");
		else System.out.println("Neither");
    }

    /*
    * Read in each line from standard input file and pass to validation method.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			IPAddressValidation(s);
		}
    }
}