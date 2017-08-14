import java.util.*;

public class FlightPath
{
	private String[] flights;
	private HashMap<String, String> sourcesToDestinations;

	public FlightPath(String[] flights) {
		this.flights = flights;
		sourcesToDestinations = new HashMap<String, String>();
		for (String flight : flights) {
			String[] places = flight.split(" -> ");
			sourcesToDestinations.put(places[0], places[1]);
		}
	}

	public String findStartingPoint() {
		HashMap<String, String> destinationsToSources = new HashMap<String, String>();
		for (String flight : flights) {
			String[] places = flight.split(" -> ");
			destinationsToSources.put(places[1], places[0]);
		}
		for (Map.Entry<String, String> entry : sourcesToDestinations.entrySet()) {
			String place = entry.getKey();
			if (!destinationsToSources.containsKey(place))
				return place;
		}

		return null;
	}

	public void printTotalTrip() {
		String from = findStartingPoint();
		String to = sourcesToDestinations.get(from);
		while (to != null) {
			System.out.println(from + " -> " + to);
			from = to;
			to = sourcesToDestinations.get(from);
		}
	}

	public static void main(String[] args) {
		String[] flights = {
			"Los Angeles, CA -> San Francisco, CA",
			"Portland, OR -> Chicago, IL",
			"New York, NY -> Los Angeles, CA",
			"New Orleans, LA -> Miami, FL",
			"San Francisco, CA -> Portland, OR",
			"Chicago, IL -> New Orleans, LA"
		};

		FlightPath f = new FlightPath(flights);
		f.printTotalTrip();
	}
}