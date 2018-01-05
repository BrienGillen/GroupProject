package groupWork;

import java.io.BufferedReader;
import java.sql.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class used to generate the fixtures given an input of the number of teams for
 * the tournament and then a manual user input for each team name
 * 
 * @author briengillen
 *
 */
public class MakeFixtures {

	/**
	 * Setting up the connection to the database
	 */
	DBConnect db = new DBConnect();

	/**
	 * Empty main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * Method used to take the input of number of teams and generate random
	 * fixtures
	 * 
	 * @param numberOfTeams
	 */
	public void makeFixtures(int numberOfTeams) {

		// Instantiation of a scanner object to allow for user input
		Scanner scan = new Scanner(System.in);

		// String variables used to input SQL queries
		String team = "", updateTeamsSql, clearTeamsSql, clearRounds, selectTeams;

		// Integer variables used to set the number of teams in the tournament,
		// to get the team IDs and to get the match IDs
		int numberTeams, numberMatches;

		// Integer variables used to set the start and end point for the
		// generation of round IDs given the match IDs
		int startPoint, endPoint;

		// A prepared statement and result set used to run a SQL query and get a
		// set of results from that query
		PreparedStatement state;
		ResultSet rs;

		// Instantiation of a connection, with an initial value of null
		Connection con;

		// Setting the variables for the SQL queries so that they can be called
		// in multiple occasions later
		updateTeamsSql = "INSERT INTO teams (team_id, team_name) VALUES (?, ?);";
		clearTeamsSql = "DELETE FROM teams";
		clearRounds = "DELETE FROM rounds";
		selectTeams = "SELECT * FROM teams;";

		// Creating an object from the class fixtures
		Fixtures fix = new Fixtures();

		// Creating a get fixtures object using the GetFixIDs class
		GetFixIDs getFix = new GetFixIDs();

		// Creating a set rounds object from the SetRounds class
		SetRounds round = new SetRounds();

		// Try used to surround both the SQL code and the writing to and reading
		// from a text file code
		try {

			// Setting the local variable for the number of teams to be the same
			// as the variable input into the method
			numberTeams = numberOfTeams;

			// Establishing the database connection
			con = db.getConnection();

			// Setting up a statement to run the selecting from teams statement
			PreparedStatement ps = con.prepareStatement(selectTeams);

			// Setting up two ArrayLists of type String to take in the team
			// names to be written to the file and then to read the team names
			// individually back from the file
			ArrayList<String> teams = new ArrayList<String>();
			ArrayList<String> fixtures = new ArrayList<String>();

			// Setting up an array of Strings to be populated with the user
			// input for team names
			String[] team2 = new String[6];

			// Setting up the classes used to read from and write to the
			// included text file fixtures
			File file = new File("fixtures.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			System.out
					.println("Please enter the " + numberTeams + " team names as requested in any order, in English :");

			// Preparing and running the query to empty the teams table in
			// preparation for an insert
			state = con.prepareStatement(clearTeamsSql);
			state.executeUpdate();

			// Running a for loop to count up to the number of teams
			for (int i = 0; i < numberTeams; i++) {
				System.out.println("Team " + (i + 1) + ":");

				// Setting the string team to be the name input by the user
				team = scan.next();

				// If statement to check if the user inputs one of the suggested
				// team names (for future iterations of this code where a
				// different tournament may be used this if statement would need
				// completely overhauled)
				if (team.equalsIgnoreCase("Ireland") || team.equalsIgnoreCase("Wales")
						|| team.equalsIgnoreCase("Scotland") || team.equalsIgnoreCase("England")
						|| team.equalsIgnoreCase("France") || team.equalsIgnoreCase("Italy")) {

					// If to check if the name the user has input has already
					// been entered
					if (Arrays.asList(team2).contains(team)) {
						System.out.println("Make sure no teams are entered twice");

						System.out.println();

						// Decrementing i so that if the user enters the same
						// team name twice it asks for that number of team name
						// again
						i--;

						// Else to continue as normal if the user doesn't input
						// the same name twice
					} else {
						// Adding the user input to an Array of strings so that
						// we can make sure they don't input the same name twice
						team2[i] = team;

						// Preparing the insert statement for the teams table
						// with the integers of team_id and team_name set
						// accordingly
						state = con.prepareStatement(updateTeamsSql);
						state.setInt(1, i + 1);
						state.setString(2, team);
						state.executeUpdate();
					}
					// Else to let the user know that their input doesn't match
					// the suggestions and to decrement i so that the user still
					// enters the name for that team number rather than the loop
					// carrying on
				} else {
					System.out.println(
							"Please check for your input, teams for this tournament are : \nIreland, England, Scotland, France, Italy or Wales");
					System.out.println();
					i--;
				}

			}

			System.out.println("Generating Fixtures from teams input, please wait...");
			System.out.println();


			// Running the query for selecting the data just added to the teams
			// table
			rs = ps.executeQuery();

			// Checking that the result set has data
			while (rs.next()) {
				// Adding the team name for each team to an ArrayList
				teams.add(rs.getString(2));
			}

			// Using the collections class to shuffle the teams ArrayList so
			// that the 6 names are randomly shuffled making the fixtures random
			// each time
			Collections.shuffle(teams);

			// Checking that the text file exits and if not creating it
			if (!file.exists()) {
				file.createNewFile();
			}

			// Setting the number of Fixtures from the teams ArrayList
			int numberOfFixtures = ((teams.size() - 1) * (teams.size() / 2)) * 2;

			// Populating another ArrayList of type String with the fixtures
			// generated by inputting the teams ArrayList into the fixtures
			// class
			ArrayList<String> fixy = fix.getFixtures(teams);

			// Running a for loop to write the team names to the text file as
			// team_home (new line) team_away, rather than having both team
			// names on the same line
			for (int i = 0, j = 1; i <= numberOfFixtures && j <= numberOfFixtures; i += 2, j += 2) {
				bw.write(fixy.get(i) + "\n");
				bw.write(fixy.get(j) + "\n");
			}
			// Closing the writing to file resource
			bw.close();

			// Running a nested for loop to add each line of the fixtures text
			// file to the fixtures ArrayList
			for (int outer = 0; outer < 10; outer++) {
				for (int inner = 0; inner < 3; inner++) {
					fixtures.add(br.readLine());
				}
			}

			// Generating the fixture_id, team_home_id and team_away_id for the
			// matches table given the fixtures ArrayList
			getFix.getFixIDs(fixtures);
			
			System.out.println("Writing Fixture IDs to database please wait...");
			System.out.println();


			// Preparing and running the clearRounds statement to empty the
			// rounds table before population
			ps = con.prepareStatement(clearRounds);
			ps.executeUpdate();

			// Initialising the start and end points of the generating rounds
			// class and 0 and 3 respectively
			startPoint = 0;
			endPoint = 3;

			// Getting the number of matches from the fixtures ArrayList
			numberMatches = fixtures.size() / 2;

			
			System.out.println("Randomizing Fixtures please wait...");
			System.out.println();

			// Running the setRounds method of the round class to update the
			// round table given the start and end points of the for loop inside
			// the class and the number of matches that IDs need to be generated
			// for
			round.setRounds(startPoint, endPoint, numberMatches);
			// Incrementing the start and end points by three to generate the ID
			// for the next 3 matches
			startPoint += 3;
			endPoint += 3;
			round.setRounds(startPoint, endPoint, numberMatches);
			startPoint += 3;
			endPoint += 3;
			round.setRounds(startPoint, endPoint, numberMatches);
			startPoint += 3;
			endPoint += 3;
			round.setRounds(startPoint, endPoint, numberMatches);
			startPoint += 3;
			endPoint += 3;
			round.setRounds(startPoint, endPoint, numberMatches);

			System.out.println("Fixtures Generated");
			System.out.println();


			// Closing the reading from file resource
			br.close();
			// Closing the connection resource to the database
			con.close();

			// Catch block created for the reading and writing from a file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// Catch block created for the running of SQL
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
