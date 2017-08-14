import java.util.*;

public class TwoSum
{
	public static ArrayList<String> getPairs(int[] numbers, int target) {
		HashSet<Integer> numberMap = new HashSet<Integer>();
		ArrayList<String> returnPairs = new ArrayList<String>();
		int length = numbers.length;
		for (int i = 0; i < length; i++) {
			int targetValue = target - numbers[i];
			if (numberMap.contains(targetValue)) {
				String x = Integer.toString(targetValue);
				String y = Integer.toString(numbers[i]);
				String pair = x + " , " + y;
				returnPairs.add(pair);
			}
			if (!numberMap.contains(numbers[i]))
				numberMap.add(numbers[i]);
		}

		return returnPairs;
	}
	public static void main(String args[]) {
		int[] numbers = {6, 4, 3, 2, 5, 1, 7, 6, 0, 0, 8, -1, 9, -1, 1};
		int target = 8;
		ArrayList<String> pairs = getPairs(numbers, target);
		for (int i = 0; i < pairs.size(); i++)
			System.out.println(pairs.get(i));
	}
}