/**
 * 
 */
package groupWork;

import java.util.Scanner;

/**
 * 
 * Main Class to run all the other methods and to output to the screen a
 * constantly running menu for the user to navigate through and only exit when
 * they choose to
 * 
 * @author briengillen
 *
 */
public class FixturesMenu {

	/**
	 * Main method which outputs to screen the menu mentioned above
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Setting up integers to be the start and end point for the
		// PointsCode so that the code can update the results round by round
		// rather than all in one go
		// Also setting up an integer to be the indexer (input by the user) to
		// select which round they would like to upload from a text file for
		// And setting up an integer to let the user choose the number of teams
		// they would like to make fixtures for
		int startPoint, endPoint, round, numberTeams;

		// Booleans to run the do while loops for uploading the scores from a
		// text file, letting the user choose to upload from said text files
		// until they ask to stop and letting the user choose what they would
		// like to search for within the database, respectively
		boolean uploader = true;
		boolean roundUpload = true;
		boolean searching = true;

		// Strings for letting the user select whether or not they would like to
		// upload from a text file, and whether or not, and how, they would like
		// to search through the database, respectively
		String upload, searcher;

		// Initialising of a scanner and the multiple other classes to be used
		// in this method
		Scanner scan = new Scanner(System.in);
		MakeFixtures make = new MakeFixtures();
		MainUpload up = new MainUpload();
		ScoreInput si = new ScoreInput();
		PointsFinal pf = new PointsFinal();
		LeagueTable lt = new LeagueTable();
		Searching search = new Searching();

		// Setting the default value of number of teams to be six for this
		// tournament
		numberTeams = 6;

		// Setting starting values for the parameters of the PointsFinal create
		// scores code
		startPoint = 0;
		endPoint = 3;

		System.out.println("\t\t\t>>>WELCOME TO THE SIX NATIONS SYSTEM<<<");
		System.out.println("\t\t\t>>PLEASE FOLLOW ALL INSTRUCTIONS GIVEN<<");
		
		// Making the fixtures with an input to select the number of teams the
		// user would like to make fixtures for
		make.makeFixtures(numberTeams);

		// Do while loop to let the user select whether or not they want to
		// upload from a text file, and if they do which text file(s) to upload
		// from
		do {
			
			
			System.out.println(
					"Would you like to upload scores from the text files? (Please enter either 'Y' or 'N') \n(If no then the scores will be filled in as 0 to be manually"
							+ " filled in later)");

			// Set String to take in the user input for comparison
			upload = scan.next();

			// If statement for is the user chooses y, meaning they would like
			// to upload from text files
			if (upload.equalsIgnoreCase("y")) {

				// Start of the do while to let the user continue uploading from
				// text files until they request to stop
				do {
					System.out.println(
							"Which round would you like to upload scores for? (1-5, or 6 for all) \nPlease enter '0' if you are finished \n(If any of the conversion values are "
									+ "greater than the number of tries the value will be set to zero)");

					// Checking that the user has input an integer for the text
					// file they would like to upload from
					if (scan.hasNextInt()) {

						// Setting integer round to be the user input
						round = scan.nextInt();

						// If statements comparing the user input and running
						// the corresponding upload from the MainUpload class to
						// what option they chose
						if (round == 1) {
							// bulk upload round 1 values
							up.bulkUploadRound1();
						} else if (round == 2) {
							// bulk upload round2 values
							up.bulkUploadRound2();
						} else if (round == 3) {
							// bulk upload round 3 values
							up.bulkUploadRound3();
						} else if (round == 4) {
							// bulk upload round 4 values
							up.bulkUploadRound4();
						} else if (round == 5) {
							// bulk upload round 5 values
							up.bulkUploadRound5();
						} else if (round == 0) {
							// Setting both booleans for both do while loops to
							// false if the user chooses 0 to exit the upload
							uploader = false;
							roundUpload = false;
							System.out.println("Finishing upload...");
							System.out.println();
						} else if (round == 6) {

							// Uploading all the round values in one method

							// bulk upload round 1 values
							up.bulkUploadRound1();

							// bulk upload round 2 values
							up.bulkUploadRound2();

							// bulk upload round 3 values
							up.bulkUploadRound3();

							// bulk upload round 4 values
							up.bulkUploadRound4();

							// bulk upload round 5 values
							up.bulkUploadRound5();

							System.out.println("Finishing upload...");

							// Changing both booleans of both do while loops to
							// be false if the user has uploaded from all 5 text
							// files in one go
							uploader = false;
							roundUpload = false;

							// Letting the user know that their input is not one
							// of the
							// values suggested for the text file upload
						} else {
							System.out.println("Please check the input matches the suggestions");
							scan.next();
						}

						// Letter the user know that their input is not an
						// integer
					} else {
						System.out.println("Please check the input matches the suggestions");
						scan.next();
					}

					// Closing of the inner do while loop to let the user exit
					// selecting the text file to upload from
				} while (roundUpload);

				// Else for if the user inputs n to not upload from the text
				// files, which will default all the values to be 0
			} else if (upload.equalsIgnoreCase("n")) {
				up.blankUpload();

				// Setting boolean false to exit the outer do while loop and
				// continue with the system if the user chooses no to upload
				// from text files
				uploader = false;

				// Else to let the user know that their input for selecting
				// whether or not they want to upload from the text files
				// doesn't match the suggestions (y or n)
			} else {
				System.out.println("Please check the input matches the suggestions");
				uploader = true;
			}

			// End of outer do while so the user can stop uploading from files
			// and continue with the system
		} while (uploader);

		// Running of the ScoreInput class method enterScores to let the user
		// manually update scores by either round or match at their choosing
		si.enterScores();

		// PointsFinal code to update the rest of the matches table from the
		// database using the scores entered above and start and end points to
		// run the code on a round by round basis
		pf.buildScores(startPoint, endPoint);
		// Incrementing the start and end points after each iteration of the
		// buildScores method
		startPoint += 3;
		endPoint += 3;
		pf.buildScores(startPoint, endPoint);
		startPoint += 3;
		endPoint += 3;
		pf.buildScores(startPoint, endPoint);
		startPoint += 3;
		endPoint += 3;
		pf.buildScores(startPoint, endPoint);
		startPoint += 3;
		endPoint += 3;
		pf.buildScores(startPoint, endPoint);

		// Calling the method updateLeagueTable from the LeagueTable class to
		// input the results from the matches into a final tally
		lt.updateLeagueTable();

		search.displayMatches();

		// Calling the displayLeague table from the search class to print the
		// entirety of the league table to screen, including team names
		search.displayLeague();

		// Do while loop to allow the user to continue looping through the
		// search options until they choose to exit
		do {
			System.out.println(
					"Would you like to search the system for one Teams results, for one Matches results, for all matches results or for the entire league results?"
							+ " \n(Please enter 'T' for teams, 'M' for one match, 'A' for all matches, 'L' for League or 'N' for none to close the system)");

			// Setting string to be the input of the user
			searcher = scan.next();

			// If statement for if the user chooses to search for data by team,
			// calling the searchTeam method of Search class
			if (searcher.equalsIgnoreCase("t")) {

				search.searchTeam();

				// Else if statement for if the user chooses to search for data
				// by
				// match, calling the searchFixtures method of Search class
			} else if (searcher.equalsIgnoreCase("m")) {

				search.searchFixtures();

				// Else if statement to show the whole matches table if the user
				// chooses a
			} else if (searcher.equalsIgnoreCase("a")) {

				search.displayMatches();

				// Else if statement to show the whole league table if the user
				// chooses l
			} else if (searcher.equalsIgnoreCase("l")) {

				search.displayLeague();

				// Else if statement to close the do while loop if the user
				// chooses n
				// to exit the system
			} else if (searcher.equalsIgnoreCase("n")) {
				System.out.println("System Closing...");
				searching = false;

				// Else statement to let the user know their input doesn't match
				// the suggestions (t, m or n)
			} else {
				System.out.println("Please check the input matches the suggestions");
			}

			// Closing of the do while loop to let the user continue searching
			// until they choose to exit
		} while (searching);

		System.out.println("Thank you for using this system");
		
		// Closing of the scanner resource
		scan.close();

	}

}
