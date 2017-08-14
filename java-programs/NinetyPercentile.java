import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NinetyPercentile
{
	/*
	 * The 90th percentile approximation is made by updating an array named freq that represents
	 * time intervals and keeps track of how many request times are in each interval. I chose
	 * to have 150 intervals (one for each second), so:
	 * freq[0] = number of request times in the interval [0.0, 1.0)
	 * freq[1] = number of request times in the interval [1.0, 2.0)
	 * ...
	 * freq[149] = number of request times in the interval [149.0, 150.0]
	 * Given this array and the number of requests for a certain minute, we can determine which
	 * interval the 90 percent marker lies in and return an approximation that is the average of
	 * the upper and lower bound for that interval.
	 *
	 * NOTE: I chose to have 150 intervals for this program because it yielded the best scores
	 * with the test cases, including a score of 100 on the first test case. However, given the
	 * extremely high number of requests, users of this program may want to have less intervals
	 * in order to improve performance (while increasing error). However, the number of intervals
	 * must be a factor of 150.
	 */

	private static final int NUM_INTERVALS = 150000;

	public static void main(String args[]) {
		// get first request and calculate first minute boundary
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] request = s.split(" ");
		long timestamp = Long.parseLong(request[0]);
		long secondsOverMinute = timestamp % 60;
		long minuteBoundary = timestamp - secondsOverMinute;

		// initialize freq arrays for current and next minute and update freqCurrent
		int[] freqCurrent = new int[NUM_INTERVALS];
		int[] freqNext = new int[NUM_INTERVALS];
		int index = indexToIncrement(request[1]);
		freqCurrent[index]++;

		// number of requests for current and next minute
		int currentMinuteCount = 1;
		int nextMinuteCount = 0;

		while (sc.hasNextLine()) {
			s = sc.nextLine();
			request = s.split(" ");
			timestamp = Long.parseLong(request[0]);
			index = indexToIncrement(request[1]);
			if (timestamp >= minuteBoundary + 120) {
				// calculate and print 90th percentile for current minute
				printNinetyPercentile(minuteBoundary, freqCurrent, currentMinuteCount);
				minuteBoundary += 60;
				if (timestamp >= minuteBoundary + 120) {
					// calculate and print 90th percentile for next minute
					// write 0.0 for all minutes up until minute prior to request's minute
					printNinetyPercentile(minuteBoundary, freqNext, nextMinuteCount);
					Arrays.fill(freqNext, 0);
					while (minuteBoundary < timestamp - 120) {
						minuteBoundary += 60;
						printNinetyPercentile(minuteBoundary, freqNext, 0);
					}
					currentMinuteCount = 0;
				}
				else {
					// put next minute's info into current minute and zero out next minute
					freqCurrent = freqNext;
					currentMinuteCount = nextMinuteCount;
					Arrays.fill(freqNext, 0);
				}
				// update variables for next minute
				freqNext[index]++;
				nextMinuteCount = 1;
			}
			else if (timestamp >= minuteBoundary + 60) {
				// add request to calculation for next minute
				freqNext[index]++;
				nextMinuteCount++;
			}
			else if (timestamp >= minuteBoundary) { // if timestamp < minuteBoundary, irrelevant
				// add request to calculation for current minute
				freqCurrent[index]++;
				currentMinuteCount++;
			}
		}

		// calculate and print 90th percentile for final two minutes
		printNinetyPercentile(minuteBoundary, freqCurrent, currentMinuteCount);
		printNinetyPercentile(minuteBoundary + 60, freqNext, nextMinuteCount);
	}

	/*
	 * Given request processing time (as a String) for a request, return the index in freq
	 * whose count needs to be incremented.
	 */
	private static int indexToIncrement(String requestTimeAsString) {
		double requestTime = Double.parseDouble(requestTimeAsString);
		double tableHasher = 150.0/NUM_INTERVALS;
		int indexToIncrement = (int) Math.floor(requestTime/tableHasher);
		if (indexToIncrement == NUM_INTERVALS)
			return NUM_INTERVALS - 1;
		return indexToIncrement;
	}

	/*
	 * Given the minute boundary, freq array and number of requests for a certain minute,
	 * calculate and print the 90th percentile request processing time for that minute (next to
	 * the proper timestamp).
	 */
	private static void printNinetyPercentile(long minuteBoundary, int[] freq, int count) {
		// calculate 90th percentile
		double ninetyPercentile = 0.0;
		if (count != 0) {
			int tenPercentMarker = (int) Math.ceil(count*0.1);
			int i = NUM_INTERVALS - 1;
			for (; i >= 0; i--) {
				tenPercentMarker -= freq[i];
				if (tenPercentMarker <= 0)
					break;
			}
			double lowerBound = i * (150.0/NUM_INTERVALS);
			double upperBound = (i+1) * (150.0/NUM_INTERVALS);
			ninetyPercentile = (lowerBound + upperBound) / 2.0;
		}

		// print to STDOUT
		Date d = new Date(minuteBoundary*1000);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		date.setTimeZone(TimeZone.getTimeZone("UTC"));
		String s = date.format(d);
		System.out.println(s + " " + ninetyPercentile);
	}
}