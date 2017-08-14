import java.lang.*;

public class OneAway
{
	public static boolean checkOneAway(String x, String y) {
		int xLength = x.length();
		int yLength = y.length();
		if (xLength - yLength > 1 || yLength - xLength > 1)
			return false;

		boolean oneChangeMade = false;
		boolean xLonger = false;
		boolean yLonger = false;
		boolean sameSize = false;
		if (xLength - yLength == 1)
			xLonger = true;
		else if (yLength - xLength == 1)
			yLonger = true;
		else
			sameSize = true;

		int minLength = Math.min(xLength, yLength);
		int i = 0;
		int j = 0;
		while (i < minLength && j < minLength) {
			int xLetter = x.charAt(i);
			int yLetter = y.charAt(j);
			if (xLetter == yLetter) {
				i++;
				j++;
				continue;
			}

			if (xLonger && !oneChangeMade) {
				i++;
				oneChangeMade = true;
			}
			else if (yLonger && !oneChangeMade) {
				j++;
				oneChangeMade = true;
			}
			else if (sameSize && !oneChangeMade) {
				oneChangeMade = true;
				i++;
				j++;
			}
			else
				return false;
		}

		return true;
	}
	public static void main(String args[]) {
		System.out.println(checkOneAway("pale", "ple"));
		System.out.println(checkOneAway("pales", "pale"));
		System.out.println(checkOneAway("pale", "bale"));
		System.out.println(checkOneAway("pale", "bake"));
	}
}