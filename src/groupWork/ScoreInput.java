package groupWork;

import java.sql.*;
import java.util.Scanner;

/**
 * @author anthonymcdonald
 *
 */
public class ScoreInput {

	DBConnect db = new DBConnect();

	/**
	 * @param args
	 */
	public void enterScores() {

		// Creating Connection variable
		Connection con;
		// Calling getConnection method within connection variable
		con = db.getConnection();
		// Importing the scanner class to input user values
		Scanner scanner = new Scanner(System.in);
		// Importing the scores class so we can set user input values to
		Score m = new Score();

		// Instantiating the variables and objects to be used to store data
		String updateScoresSQL = null;
		String teamHomeName = null, teamAwayName = null;
		int userScores = 0;
		int userMatch = 0;
		int round = 0;

		// Setting up a boolean so that the user can loop through inputting
		// match scores until they specify to stop
		boolean cont = true;
		boolean conti = true;
		boolean continueloop = true;

		try {
			// Setting up the query needed to update the scores for a match
			PreparedStatement ps = con.prepareStatement("SELECT match_id, team_home_id, team_away_id FROM matches");
			ResultSet rs = ps.executeQuery();

			// Setting up a second statement so that the system can output team
			// names to the screen rather than just IDs
			PreparedStatement ps1 = con.prepareStatement("SELECT team_name FROM teams WHERE team_id = ? ");

			// Creating a while loop to generate the current matches and teams
			// so the user can select which match they want to update
			while (rs.next()) {

				// Setting local variables to inherit the data values in the
				// database
				m.setMatch_id(rs.getInt("match_id"));
				m.setTeam_home_id(rs.getInt("team_home_id"));
				m.setTeam_away_id(rs.getInt("team_away_id"));

				// Setting the variable in the select statement for the team
				// name
				// to be the ID taken from the match table above
				ps1.setInt(1, rs.getInt("team_home_id"));
				ResultSet rs1 = ps1.executeQuery();

				// While the result set has values in it this loop runs
				while (rs1.next()) {
					// Setting the variable for the team home name
					teamHomeName = (rs1.getString(1));
				}

				// Setting the variable in the select statement for the team
				// name
				// to be the ID taken from the match table above
				ps1.setInt(1, rs.getInt("team_away_id"));
				rs1 = ps1.executeQuery();

				// While the result set has values in it this loop runs
				while (rs1.next()) {
					// Setting the variable for the team home name
					teamAwayName = (rs1.getString(1));
				}

				// Printing the current matches with team IDs and team names in
				// each match
				System.out.printf("Match ID: %d \t- Team %d: " + teamHomeName + "  \tVS\tTeam %d: " + teamAwayName + "\n",
						m.getMatch_id(), m.getTeam_home_id(), m.getTeam_away_id());
			}

			// Start of the do while so that the user is continually asked for
			// input until they specify to stop
			do {
				System.out.println();

				System.out.println(
						"Would you like to manually update a score for a single match, a full round or none? (Please enter either 'M', 'R' or 'N')");

				String contin;
				contin = scanner.next();

				// If statement for when the user wants to end the update
				// process and types n
				if (contin.equalsIgnoreCase("n")) {

					System.out.println("Finished updating scores, thank you");
					System.out.println();

					// Variable cont set to false to break the do while
					cont = false;
					scanner.nextLine();
					// else if for if the user inputs m when prompted to update
					// scores match by match
				} else if (contin.equalsIgnoreCase("m")) {
					// Setting boolean conti to true to be used in the next do
					// while loop
					conti = true;

					// Opening of the do while loop to be used for updating each
					// match
					do {
						System.out.println(
								"Which match would you like to update scores in (Please enter one of the match IDs from above)?");

						// Prompting the user to now select the match they want
						// to
						// update
						if (scanner.hasNextInt()) {
							userMatch = scanner.nextInt();
							System.out.println();
							if (userMatch <= m.getMatch_id() && userMatch > 0) {

								// Query used to insert values into the
								// match
								// table
								// to
								// store
								// each
								// score attribute
								updateScoresSQL = "UPDATE matches SET home_scores_tries = ?, home_penalties = ?, home_conversion = ?, away_scores_tries = ?, away_penalties = ?, away_conversion = ? WHERE match_id ="
										+ userMatch + ";";

								// Launching the SQL Query
								PreparedStatement stmt = con.prepareStatement(updateScoresSQL);

								// Opening of the do while loop used for the
								// user inputting the number of tries for a
								// specific match for the home team
								do {
									System.out.println("Please enter the number of tries scored by the home team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										// If statement checking the user isn't
										// inputting negative scores
										if (userScores >= 0) {

											// Setting home tries to the user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(1, userScores);
											m.setHome_scores_tries(userScores);

											// Setting variable conti to be
											// false to break do while loop for
											// inputting home teams tries
											conti = false;

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the home teams number of
										// tries
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of tries by the
									// home team
								} while (conti);

								// Opening of the do while loop used for the
								// user inputting the number of penalties for a
								// specific match for the home team (as drop
								// goals are also worth 3 they are included
								// here)
								do {

									System.out.println(
											"Please enter the number of penalties/drop goals scored by the home team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										if (userScores >= 0) {
											// Setting home penalties to the
											// user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(2, userScores);
											m.setHome_penalties(userScores);

											// Setting variable conti to be
											// false to break do while loop for
											// inputting home teams tries
											conti = false;

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the home teams number of
										// penalties/drop goals
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of penalties/drop
									// goals by the home team
								} while (conti);

								// Opening of the do while loop used for the
								// user inputting the number of conversions for
								// a specific match for the home team
								do {

									System.out
											.println("Please enter the number of conversions scored by the home team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										// If statement checking the user isn't
										// inputting any negative numbers
										if (userScores >= 0) {

											// Check to make sure the user
											// hasn't input more conversions
											// than tries
											if (userScores > m.getHome_scores_tries()) {
												System.out.println(
														"Sorry, can't score more conversions than tries, check your numbers please");
												conti = true;
											} else {

												// Setting home conversions to
												// the
												// user
												// input
												// and
												// also
												// storing
												// into
												// database
												stmt.setInt(3, userScores);
												m.setHome_conversion(userScores);

												// Setting variable conti to be
												// false to break do while loop
												// for
												// inputting home teams tries
												conti = false;
											}

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the home teams number of
										// conversions
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of conversions
									// by the home team
								} while (conti);

								// Opening of the do while loop used for the
								// user inputting the number of tries for
								// a specific match for the away team
								do {

									System.out.println("Please enter the number of tries scored by the away team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										// If statement checking the user isn't
										// inputting any negative numbers
										if (userScores >= 0) {

											// Setting away tries to the user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(4, userScores);
											m.setAway_scores_tries(userScores);

											// Setting variable conti to be
											// false to break do while loop
											// for
											// inputting home teams tries
											conti = false;

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the away teams number of
										// tries
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of tries
									// by the away team
								} while (conti);

								// Opening of the do while loop used for the
								// user inputting the number of penalties/drop
								// goals
								// for a specific match for the away team
								do {

									System.out.println(
											"Please enter the number of penalties/drop goals scored by the away team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										// If statement checking the user isn't
										// inputting any negative numbers
										if (userScores >= 0) {
											// Setting away penalties to the
											// user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(5, userScores);
											m.setAway_penalties(userScores);

											// Setting variable conti to be
											// false to break do while loop
											// for
											// inputting home teams tries
											conti = false;

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the away teams number of
										// penalties/drop goals
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of penalties/drop
									// goals
									// by the away team
								} while (conti);

								// Opening of the do while loop used for the
								// user inputting the number of conversions
								// for a specific match for the away team
								do {
									System.out
											.println("Please enter the number of conversions scored by the away team");

									// If statement checking the user isn't
									// inputting anything other than an int
									if (scanner.hasNextInt()) {
										userScores = scanner.nextInt();

										// If statement checking the user isn't
										// inputting any negative numbers
										if (userScores >= 0) {

											// Check to make sure the number of
											// conversions scored isn't greater
											// than
											// the number of tries
											if (userScores > m.getAway_scores_tries()) {
												System.out.println(
														"Sorry, can't score more conversions than tries, check your numbers please");
												System.out.println();

												conti = true;
											} else {
												// Setting home conversions to
												// the
												// user
												// input
												// and
												// also
												// storing
												// into
												// database
												stmt.setInt(6, userScores);
												m.setAway_conversion(userScores);

												// Setting variable conti to be
												// false to break do while loop
												// for
												// inputting home teams tries
												conti = false;

												// Executing Query
												stmt.executeUpdate();

												System.out.println("Scores updated");
												System.out.println();


											}

											// Else statement letting the user
											// know that they have input a
											// negative number
										} else {
											System.out.println("Values 0 and above only for scores please");
											System.out.println();

											conti = true;
										}

										// Else statement letting the user know
										// they have input a value other than an
										// int for the away teams number of
										// conversions
									} else {
										System.out.println("Integers only for scores please");
										System.out.println();

										scanner.next();
										conti = true;
									}

									// Closing of the do while loop for the
									// users input for number of conversions
									// by the away team
								} while (conti);

								// Setting the boolean for the loop of setting
								// match ids to false
								// to break the loop and return to selecting
								// either by match or round
								continueloop = false;

								// Else statement for if the user doesn't input
								// a valid match ID
							} else {
								System.out.println("Not a valid match ID");
								System.out.println();

							}

							// Else statement for if the user doesn't input an
							// integer as a match ID
						} else {
							System.out.println("Positive integers only for IDs please");
							System.out.println();

							scanner.next();
						}

						// Closing of the do while loop for setting scores match
						// by match
					} while (continueloop);

					// else if for if the user inputs r when prompted to update
					// scores round by round
				} else if (contin.equalsIgnoreCase("r")) {
					System.out.println();

					System.out.println("Please enter the round you would like to enter scores for (1-5)");

					// If statement to check that the user enters an integer for
					// the round number
					if (scanner.hasNextInt()) {

						round = scanner.nextInt();
						System.out.println();

						// If statements checking the round input and setting
						// the starting value for the match IDs correspondingly
						// (Round 1 starts at match 1, round 2 at match 4, round
						// 3 at match 7 etc.)
						if (round == 1) {

							userMatch = 1;

						} else if (round == 2) {

							userMatch = 4;

						} else if (round == 3) {

							userMatch = 7;

						} else if (round == 4) {

							userMatch = 10;

						} else if (round == 5) {

							userMatch = 13;

						}

						// For loop to run the update matches code 3 times per
						// round, i.e. to run the code for each match within a
						// round
						for (int i = 0; i < 3; i++) {

							// Query used to insert values into the match table
							// to
							// store
							// each
							// score attribute
							updateScoresSQL = "UPDATE matches SET home_scores_tries = ?, home_penalties = ?, home_conversion = ?, away_scores_tries = ?, away_penalties = ?, away_conversion = ? WHERE match_id ="
									+ userMatch + ";";
							// Launching the SQL Query
							PreparedStatement stmt = con.prepareStatement(updateScoresSQL);

							// Start of the do while loop for entering the home
							// team's number of tries per match for a round
							do {
								System.out.println("Please enter the number of tries scored by the home team for match "
										+ userMatch);

								// Checking the user inputs an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user doesn't input negative
									// values
									if (userScores >= 0) {

										// Setting home tries to the user input
										// and
										// also
										// storing
										// into
										// database
										stmt.setInt(1, userScores);
										m.setHome_scores_tries(userScores);

										// Setting boolean to false to break the
										// do while
										conti = false;

										// Letting user know they have input a
										// negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// Closing of the do while loop for inputting
								// the number of tries scored by the home team
								// in a match for a round
							} while (conti);

							// Start of the do while loop for entering the home
							// team's number of penalties/drop goals per match
							// for a round
							do {

								System.out.println(
										"Please enter the number of penalties/drop goals scored by the home team for match "
												+ userMatch);

								// Checking the user has input an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user has input a positive
									// value
									if (userScores >= 0) {

										// Setting home penalties to the user
										// input
										// and
										// also
										// storing
										// into
										// database
										stmt.setInt(2, userScores);
										m.setHome_penalties(userScores);

										// Setting boolean false to break the do
										// while loop
										conti = false;

										// Letting the user know they have input
										// a negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// Closing of the do while loop for entering the
								// number of penalties/drop goals scored by the
								// home team in a match per round
							} while (conti);

							// Start of the do while loop for entering the home
							// team's number of conversions per match for a
							// round
							do {

								System.out.println(
										"Please enter the number of conversions scored by the home team for match "
												+ userMatch);

								// Checking the user has input an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user hasn't input a negative
									// value
									if (userScores >= 0) {

										// Checking the user hasn't input more
										// conversions than tries
										if (userScores > m.getHome_scores_tries()) {
											System.out.println(
													"Sorry, can't score more conversions than tries, check your numbers please");
											System.out.println();
											conti = true;
										} else {
											// Setting home conversions to the
											// user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(3, userScores);
											m.setHome_conversion(userScores);

											// Setting boolean false to break do
											// while loop
											conti = false;

										}

										// Letting the user know they have input
										// a negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// Closing of do while loop for entering the
								// number of conversions scored by the home team
								// in a match for a round
							} while (conti);

							// Start of the do while loop for entering the away
							// team's number of tries per match for a round
							do {

								System.out.println("Please enter the number of tries scored by the away team for match "
										+ userMatch);

								// Checking the user has input an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user hasn't input a negative
									// number
									if (userScores >= 0) {
										// Setting away tries to the user input
										// and
										// also
										// storing
										// into
										// database
										stmt.setInt(4, userScores);
										m.setAway_scores_tries(userScores);

										// Setting boolean false to break the do
										// while loop
										conti = false;

										// Letting the user know they have input
										// a negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// End of the do while loop for entering the
								// number of tries scored by the away team in a
								// match for a round
							} while (conti);

							// Start of the do while loop for entering the away
							// team's number of penalties/drop goals per match
							// for a round
							do {

								System.out.println(
										"Please enter the number of penalties/drop goals scored by the away team for match "
												+ userMatch);

								// Checking the user has input an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user hasn't input a negative
									// value
									if (userScores >= 0) {
										// Setting away penalties to the user
										// input
										// and
										// also
										// storing
										// into
										// database
										stmt.setInt(5, userScores);
										m.setAway_penalties(userScores);

										// Setting boolean false to break the do
										// while loop
										conti = false;

										// Letting the user know they have input
										// a negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// End of the do while for inputting the number
								// of penalties/drop goals scored by the away
								// team in a match for a round
							} while (conti);

							// Start of the do while loop for entering the away
							// team's number of conversions per match for a
							// round
							do {
								System.out.println(
										"Please enter the number of conversions scored by the away team for match "
												+ userMatch);

								// Checking the user has input an integer
								if (scanner.hasNextInt()) {
									userScores = scanner.nextInt();

									// Checking the user hasn't input a negative
									// value
									if (userScores >= 0) {

										// Checking the number of conversions
										// isn't greater than the number of
										// tries
										if (userScores > m.getAway_scores_tries()) {
											System.out.println(
													"Sorry, can't score more conversions than tries, check your numbers please");
											System.out.println();
											conti = true;
										} else {
											// Setting home conversions to the
											// user
											// input
											// and
											// also
											// storing
											// into
											// database
											stmt.setInt(6, userScores);
											m.setAway_conversion(userScores);

											// Setting boolean false to break
											// the do while loop
											conti = false;

											// Executing Query
											stmt.executeUpdate();

											System.out.println("Scores updated");
											System.out.println();

										}

										// Letting the user know they have input
										// a negative value
									} else {
										System.out.println("Values 0 and above only for scores please");
										System.out.println();

										conti = true;
									}

									// Letting the user know they have input a
									// value that isn't an integer
								} else {
									System.out.println("Integers only for scores please");
									System.out.println();

									scanner.next();
									conti = true;
								}

								// End of the do while loop for inputting the
								// number of conversions scored by the away team
								// in a match for a round
							} while (conti);

							// Incrementing match ID so that the next iteration
							// in the for loop will do the second match in the
							// selected round and the 3rd and final iteration
							// will do the 3rd match
							userMatch++;

						} // End of the for loop to input for 3 matches i.e. 1
							// round

						// Letting the user know that when selecting the round
						// number to enter scores for they have input a value
						// that isn't an integer
					} else {
						System.out.println("Integers only for round numbers please");
						System.out.println();

						scanner.next();
					}

					// Letting the user know that they haven't entered one of
					// the three (M,R or N) values specified at the start of the
					// class to select which method they want to use to input
					// score values
				} else {
					System.out.println("Please check your input matches the suggestions");
					System.out.println();

				}

				// Closing of the do while loop used to continually iterate
				// through the entire entering scores process unless the user
				// chooses to exit by typing n
			} while (cont);

			System.out.println();


			// Automatically generated catch for the try statement that must
			// surround SQL statements in java
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}
}
