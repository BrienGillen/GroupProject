package groupWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class to generate and shuffle fixtures given an input of an ArrayList of type
 * String
 * 
 * @author briengillen
 *
 */
public class Fixtures {

	// Instantiation of a random
	Random rand = new Random();

	/**
	 * Empty main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * Method to get the fixtures given the input of an ArrayList with type
	 * parameter String
	 * 
	 * @param teams
	 * @return rounds
	 */
	public ArrayList<String> getFixtures(ArrayList<String> teams) {

		// Setting the number of teams variable to be the size of the entered
		// ArrayList
		int numberOfTeams = teams.size();

		// Generate the fixtures using the cyclic algorithm.
		// Get the values for the number of rounds and number of matches from
		// the numberOfTeams integer
		int totalRounds = numberOfTeams - 1;
		int matchesPerRound = numberOfTeams / 2;

		// Instantiate two integers for use when selecting home and away teams
		int home = 0;
		int away = 1;

		// Making a new ArrayList to store the final fixtures and to be returned
		// at the end of the method
		ArrayList<String> rounds = new ArrayList<String>();

		// Running a for loop for the number of rounds (in this case 5)
		for (int round = 0; round < totalRounds; round++) {

			// Setting up an ArrayList to initially store the fixture values
			// before randomising
			ArrayList<String> fixtures = new ArrayList<String>();

			// For loop to run through the number of matches in a round (in this
			// case 3)
			for (int match = 0; match < matchesPerRound; match++) {

				// Setting the home integer to be the result of the sum of the
				// values from the
				// two for loops (i.e. what match and what round we are
				// currently on) divided by one less than the number of teams
				home = (round + match) % (numberOfTeams - 1);

				// Setting the away integer to be the result of one less than
				// the number of teams minus the sum of the current match and
				// current round all divided by one less than the number of
				// teams
				away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

				// Last team stays in the same place while the others
				// rotate around it.
				if (match == 0) {
					away = numberOfTeams - 1;
				}

				// Adding the home team first to the ArrayList using the home
				// integer as an indexer to flip through the input 'teams'
				// ArrayList as the for loop runs
				fixtures.add(teams.get(home));

				// Adding the away team second to the ArrayList using the away
				// integer as an indexer to flip through the input 'teams'
				// ArrayList as the for loop runs
				fixtures.add(teams.get(away));

				// Setting up an integer to be used to randomly switch which
				// team is home and which is away
				int swap;

				// Randomly generating the integer swap between 0 and 1
				swap = rand.nextInt(2);

				// Using the swap arguement of the Collections class to switch
				// the elements of the fixture array, as swap is randomly
				// generated whether or not the teams swap or not is random
				Collections.swap(fixtures, 0, swap);
			}

			// Adding all the fixtures in the fixtures ArrayList to a second
			// ArrayList so that they are all together and the home and away
			// teams are randomly generated
			rounds.addAll(fixtures);

		}

		// Returning the ArrayList rounds
		return rounds;

	}
}
