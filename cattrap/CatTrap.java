/* Title: Dylex Suan
 * Name: Cat Trap
 * Date: January 18, 2018
 * Project Description: This program will be based on a familiar children's game informally known as "Cat and Mouse" where
 * the program will assume the role as a mouse whereas the user will assume the role as the cat, searching for the mouse.
 * There are three difficulties for the game provided: "EASY", "MEDIUM", "HARD".
 * The mouse will generate a random location from which the user has to guess based on the clues that the program provides.
 * The program is also expected to generate a grid after each guess as well as the other guesses that the user has guessed
 * in order to provide a better understanding of his or her position. If the user guesses correctly in the preset amount of guesses
 * then he or she will be asked whether to play again, or whether to return to the main menu to choose a different difficulty.
 * If not, the program will continually ask the user for his/her guess until the amount of preset guesses are exhausted through.
 * Furthermore, the program also provides further functionalities such as the ability to play a one versus one game with another player
 * who will then set the settings accordingly (for instance, if the user wants to work with a 7x7 grid, instead of a 5x5 grid for instance).
 * There are also exit functions if the user wants to exit the game, and opportunities to return back to the main menu.
 */

package cattrap;

// For this program, we want to import the Random class so that we are able to eventually generate Random numbers that
// will be casted into a character variable.
import java.util.Random;

// Furthermore, the program should import the Scanner class so that the user is able to input his or her guess on which
// the randomized generated location may be
import java.util.Scanner;

// As well, the ArrayList class will be imported for the purpose of creating the grid; this will be discussed further
import java.util.ArrayList;

// Lastly, the Collections class will be imported for the sake of creating the grid, which will be discussed further into the code.
import java.util.Collections;

public class CatTrap {

	public static void main(String[] args) {
		// We want to create a keyboard object to which the user can have access in the console when he or she is
		// navigating through the program. Please note that this variable will be passed as an argument and each method
		// will most likely accept a Scanner variable since most of the methods that will be used utilize in some way or form
		// the keyboard function.
		Scanner keyboard = new Scanner(System.in);
		
		// We have to then create a string for the name of the user
		String name = " ";
		
		// Has the user played this game before, or not? If the user has played this game before, we don't want to give the same
		// introduction each time, evidently. This will be passed as an argument for most of the methods that involve the game itself.
		boolean playedBefore = false;
		
		// The program should introduce the user into the program by providing him or her with a list of selections.
		System.out.print("Hello there! Before we start, can I get your name? ");
		name = keyboard.nextLine(); 
			
		// We just want somebody to enter their name so that we could use it constantly throughout the program.
		while (name.length() == 0) {
			System.out.print("Oops! You didn't enter a name! Please enter your name here: ");
			name = keyboard.nextLine();
		}
					
		// I want the name to be upper case, regardless if the user has entered his or her name in lowercase
		name = name.toUpperCase();
		// Then, the program will then call the menu function as a point of entry for the user.
		menu(keyboard, playedBefore, name);
	}
	
	// This serves as the point of entry for the user to navigate throughout the game.
	public static void menu(Scanner keyboard, boolean playedGameBefore, String name) {
		// We declare a variable called choice, which allows the user to input his selection
		String choice;
					
		// We also declare a variable called firstCharacterChoice to verify the selection.
		char firstCharacterChoice;
		
		// If the user has not entered this game before, then we should print the first type of introduction.
		if (playedGameBefore == false) {
			
			System.out.println("Welcome to CatTrap, " + name + "! Here is the current selection!");
			System.out.println("EASY\tMEDIUM\t  HARD\t  OTHER");
			System.out.print("Please enter your selection here by entering a choice of difficulty above: ");
			
		// Or else, if the user has played the game before, then we present a different introduction.
		} else {
			System.out.println("Hey! Welcome back to CatTrap, " + name + "!"
					+ "\nI feel that you want to play another game, right? Here is the selection: ");
			System.out.println("EASY\tMEDIUM\t  HARD\t  OTHER");
			System.out.print("Please enter your selection here by entering a choice of difficulty above: ");
		}
		

		// We allow the user to enter his or her selection based on what she enters.
		choice = keyboard.nextLine();
		
		// If the user enters the difficulties in lower case, then we need to account for that evidently in our program.
		choice = choice.toUpperCase();
		
		// We check his or her selection by the first character, so if h/she enters "EASY", then we would know that the user entered EASY.
		// This is mainly for the sake of the program, but we can verify that the user entered EASY or MEDIUM through equalsToIgnoreCase() method.
		firstCharacterChoice = choice.charAt(0);
		
		// If the first character of the choice was not 'E' or 'M' or 'H' or 'O', because those are the main selections, then we ask the user
		// to enter her selection again. We do this by saying that if the firstCharacterChoice is NOT equal to 'E', 'M', 'H', or 'O', then
		// we must say that the user needs to enter a valid selection that is stated above.
		// I also checked if the user enters his choice that is longer than the length of the selection words. If it is longer, then
		// we have to also ask the user to input again.
		while (!(firstCharacterChoice == 'E' || firstCharacterChoice == 'M' || firstCharacterChoice == 'H' || firstCharacterChoice == 'O') 
				|| (choice.length() < 4 || choice.length() > 6)) {
			System.out.print("Oops! That is not a valid choice! Please enter one of the selections above: ");
			choice = keyboard.nextLine();
			choice = choice.toUpperCase();
			firstCharacterChoice = choice.charAt(0);
		}
		
		// If the person enters anything else but the user hasn't played the game, go straight to the tutorial.
		if (playedGameBefore == false && firstCharacterChoice != 'O') {
			tutorial(firstCharacterChoice, playedGameBefore, keyboard, name);
			
		// Or else, if the person enters "OTHER" as his/her input, but she hasn't played the game before, we need to lead the user to the OTHER menu first
		} else if (playedGameBefore == false && firstCharacterChoice == 'O') {
			other(keyboard, name, playedGameBefore);
			
		} else {
			// If the user enters "EASY" then we apply the difficulty settings to easy, and if the user enters "MEDIUM", then we
			// set the lengths so that it corresponds to the medium difficulty, and so on and so forth.
			if (firstCharacterChoice == 'E' || firstCharacterChoice == 'M' || firstCharacterChoice == 'H') {
				int length = lengthOfGrid(firstCharacterChoice);
				
				// We run the gameDifficulty method so that the user can play the game.
				gameDifficulty(keyboard, playedGameBefore, firstCharacterChoice, length, name);
			
				// Otherwise if the user selected the other method, then we want the user to go to the OTHER selection.
			} else {
				other(keyboard, name, playedGameBefore);
			}
		}
		
		
		
	}
	
	// If the user has never played this game before or has run the code for the first time, then we have to introduce
	// him or her with the tutorial method.
	public static void tutorial(char choice, boolean playedGameBefore, Scanner keyboard, String nameOfPerson) {
		// We want to use an arrayList to store the coordinate that we are asking of the user.
		ArrayList<String> storeTutorialCoordinates = new ArrayList<String>();
		
		// This is the length of the grid. For the easy method, this is effectively a 5x5 grid.
		final byte LENGTH = (byte)lengthOfGrid(choice);
		
		// This will be in the input so that the user can enter whatever the program tells the user to do.
		String tutorialInput;
		
		// Difficulty of the program will be mentioned throughout the tutorial, so I want to be able to use this multiple times.
		String difficulty;
		
		// This will be used as the main coordinates for this level. We want to retain the letter character (the first character)
		// and the numerical character (the second character). Note that in the main difficulties, we are going to be using arrays
		// to sort out the letter coordinates from the numerical coordinates.
		char letterCoordinate, numCoordinate;
		
		// When we introduce this to the user, we want the user to know what difficulty he or she selected.
		// After the tutorial, we need the user to be led to his or her chosen difficulty.
		if (choice == 'E') {
			difficulty = "EASY";
		} else if (choice == 'M') {
			difficulty = "MEDIUM";
		} else {
			difficulty = "HARD";
		}
		
		System.out.println("\n\nWelcome to the " + difficulty + " difficulty! I assume that you are here for the very first time"
				+ "\nso let me show you how to play.");
		System.out.println("Here is the grid that you will be looking at: ");
				
		// The grid is simply generated as such. We want to generate this according to the ASCII characters that we have:	
		// Printing out the LETTERS
		System.out.print("  ");
		for (int i = 0; i < LENGTH; i++) {
			System.out.print((char)(i+65) + " ");
		}
		System.out.println();
			
		// Printing out the numerical characters with which the user will use to form his or her coordinate.
		for (int i = 0; i < LENGTH; i++) {
			System.out.print((char)(48+i) + " ");
				
			// Printing out the O's for each line.
			for (int j = 0; j < LENGTH; j++) {
				System.out.print("O ");
			}
			// Of course, every iteration needs to start a new line, so a new line must stated.
			System.out.println();
		}
			
		// The instructions that the user will be given
		System.out.print("\nThe program acting as the mouse is hiding in an unknown coordinate in this grid."
				+ "\nYour job is to find it and capture it by guessting correctly its coordinate within a set amount of guesses."
				+ "\nThe way in which you will guess it is very simple; enter a coordinate with the LETTER then the NUMBER afterwards."
				+ "\nWe will try one example first; please type in A2. ");
				
		// We want the user to enter his or her input being asked, which is "A2"
		tutorialInput = keyboard.nextLine();
		
		// As long as the user does not enter in A2, this loop will keep running.
		while (!(tutorialInput.equals("A2"))) {
			System.out.print("I do not think that is A2. Please enter A2 into the program please. ");
			tutorialInput = keyboard.nextLine();
		}
		
		// We want to be able to add the coordinates to the array list to pass it on to the method, which will generate the grid.
		storeTutorialCoordinates.add(tutorialInput);		
		
		// Generate the grid that we need.
		GenerationOfGrid(storeTutorialCoordinates, LENGTH, 0);
		
		System.out.println("\nGreat! On the grid, we see that you marked A2. \nIn a random spot on the grid, the program will "
				+ "generate a random coordinate so that you can guess where it is.");
		
		// We affirm that the user has played the game, and as such, the user can proceed on whatever difficulty he or she selected.
		playedGameBefore = true;
		System.out.println("That is really it for the tutorial! Now that you have chosen the " + difficulty + " difficulty, it's time to get "
				+ "playing now!\n");
		
		// Before the user goes onto the difficulty that he or she picked, we want to establish the length of the grid.
		int length = lengthOfGrid(choice);
		// The program then proceeds onwards with the gameDifficulty of the user's choice.
		gameDifficulty(keyboard, playedGameBefore, choice, length, nameOfPerson);
		
	}
	
	// This method serves to generate the grid for the three difficulties that are listed below.
	public static void GenerationOfGrid(ArrayList<String> userCoordinates, int length, int numberOfGuesses) {
		
		// We want to generate the grid according to the difficulty and thus, the length of the sides.
		char[][] gridFormat = new char[length][length];
		
		// We want to store the alpha coordinates in their own array so that we can make reference to them.
		char[] letters = new char[userCoordinates.size()];
		
		// We also want to make reference to the numeric coordinates that we have 
		char[] numbers = new char[userCoordinates.size()];
		
		// We want to sort out the Array list that we established because we want to go through each coordinate to
		// mark an X on each coordinate. If we did not have this, then if the user had entered coordinates randomly
		// then it is possible that some of the coordinates would not have been accounted for.
		Collections.sort(userCoordinates);
		
		// First of all, we need to get each coordinate, and assign the letter Coordinate and the numeric Coordinate to their respective arrays.
		for (int i = 0; i < userCoordinates.size(); i++) {
			String currentCoordinate = userCoordinates.get(i);
			letters[i] = currentCoordinate.charAt(0);
			numbers[i] = currentCoordinate.charAt(1);
		}

		// Then, we assign each element in the array making the grid with an "O", representing an empty spot.
		for (int i = 0; i < gridFormat.length; i++) {
			for (int j = 0; j < gridFormat[i].length; j++) {
				gridFormat[i][j] = 'O';
			}
		}
		
		// If the arrayList contains a guess or more than one guess, then we have to check, which coordinate did the user enter>
		if (userCoordinates.size() > 0) {
			// Or else, if the arrayList is not empty, we then check each coordinate that is stored in the array list
			// Then, we check that each element of the array is there.
			for (int h = 0; h < userCoordinates.size(); h++) {
				
				// Then, we iterate through each element of the gridFormat 2d array.
				for (int i = 0; i < gridFormat.length; i++) {
					
					// We first check if the current element in numbers matches the number that we have stored (for instance, is this the correct row)
					if ((char)(i + 48) == numbers[h]) {
						
						// If so, we then check if the current element in the letters array matches the letter that we have stored (for instance, is this the correct column)
						for (int j = 0; j < gridFormat[i].length; j++) {
							
							// We check if the letter selected that is affixed on the grid is the same letter that the user's coordinate has.
							if ((char)(j + 65) == letters[h]) {
								
								// If this is true, then essentially we mark this element with a capital X.
								gridFormat[i][j] = 'X';
							}
						}
					} 
				}
			}
		}
	
		// Afterwards, we print out the grid, printing out the letters first or the columns
		System.out.print("  ");
		for (int let = 65; let < (65 + length); let++) {
			System.out.print((char)(let) + " ");
		}
		
		// Then, the program prints out the actual grid, by first printing the letters associated
		// with each row, and the elements of the gridFormat 2D array.
		System.out.println();
		for (int i = 0; i < gridFormat.length; i++) {
			// This will print out the numbers of each row starting from 0
			System.out.print((char)(i + 48) + " ");
			// We are printing out whatever is in gridFormat.
			for (int j = 0; j < gridFormat[i].length; j++) {
				System.out.print(gridFormat[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
	
	// This is simply a method that will return the length of the grid when the user wants to play.
	// The lengths are written according to their difficulties.
	public static int lengthOfGrid(char choice) {
		
		// I want to verify the length based on the user's difficulty.
		int length;
		// We check the character parameter variable choice.
		switch (choice) {
		// If the character parameter variable choice is 'E', then the program will return a length of 5 units.
		case 'E':
			length = 5;
			break;
		// Otherwise, if the character parameter variable choice is 'M', then the program will return a length of 8 units.
		case 'M':
			length = 8;
			break;
		// Otherwise, if the character parameter variable choice is 'H' , then the program will return a length of 10 units.
		default:
			length = 10;
			break;
		}
		// We then return the length of the grid.
		return length;
	}
	
	// This will be called when the user has either finished his or her tutorial, or the user selects a difficulty from the selection
	// in the main menu.
	public static void gameDifficulty(Scanner keyboard, boolean playedGameBefore, char choice, int length, String nameOfPerson) {
		// Amount of points that the user has won. We will add 
		int amountOfPoints = 0;
		
		// Round number. We always start at 1 because, evidently, the first round is always ROUND 1 and not ROUND 0, for instance.
		byte round = 1;
		
		// This will be established at the end of the difficulty, and will ask if the user want to play again.
		String choiceToLeave;
		
		// I understand that I could have simply used the length variable 
		final int LENGTH = length;
		
		// We establish the boolean found to check that the user has found the location of the mouse.
		boolean found;
		
		do 
		{
			// We want to check if the user has found the coordinate yet. At the start of the game, the user has NOT found the coordinates yet.
			found = false;
			
			int allotedPoints = 90;
			// The amount of guesses that is allotted for this difficulty
			final byte amountOfGuesses = 10;
					
			// The guess number that the user is on currently.
			int guessNumber = 0;
					
			// This is the computer's location in the grid.
			String officialCoordinate, userGuess;
					
			// We are going to store the user's coordinates into here.
			ArrayList<String> storedCoordinates = new ArrayList<String>();
		
			// We create a Random object from the Random class in order to generate random numbers
			Random genNumbers = new Random();
			
			// WE store a random number between 65-70 and cast it into a character data type in order to place it in the letterCoordinate
			// char variable. As well, we store a random number between 48-53 and cast it into a character data type in order to place it
			// in the numberCoordinate. We are trying to get the unicodes of these numbers and display as a character.
			char letterCoordinate = (char)(genNumbers.nextInt(LENGTH) + 65);
			char numberCoordinate = (char)(genNumbers.nextInt(LENGTH) + 48);
			
			// We want to get the user's current coordinate and store them into their respective arrays.
			char userLetter, userNumber;
			
			// Coordinate proximity, if the user is x units away, we need to tell the user if he/she is somewhat close, far
			
			// I want to combine the letterCoordinate and the numberCooridnate in order to present to the user in case
			// he/she is unable to guess it at the end.
			officialCoordinate = Character.toString(letterCoordinate) + Character.toString(numberCoordinate);
			
			System.out.print("ROUND " + round);
			System.out.println("\nThe computer has now decided a particular coordinate. Here is your grid: ");
			
			// We want to show the user what does the grid look like before he or she starts to guess.
			GenerationOfGrid(storedCoordinates, length, amountOfGuesses);
			
			// We want the user to continue guessing until either the amount of available guesses have been exhausted through
			// or the user has in fact found the grid.
			while (guessNumber < amountOfGuesses && !found) {
				// First of all, we ask the user to enter the user's guess as to where the mouse is.
				System.out.print("Please input your guess as stated before in the tutorial: ");
				
				// We use the nextLine() method because of the fact that we are asking the user to enter a string.
				userGuess = keyboard.nextLine();
				
				// This was implemented to prevent an Index Out Of Bounds exception, because if the user only enters the letter coordinate,
				// or the numeric coordinate, then it is certain that an exception will occur. The program will retrieve the first and second
				// characters after the program has checked that the user's guess is two characters long.
				while (userGuess.length() != 2) {
					System.out.print("Whoops! That is not a valid coordinate. Please enter a valid coordinate and try again: ");
					userGuess = keyboard.nextLine();
				}
				
				// We need to grab the coordinate of the user's numeric and letter coordinate
				userLetter = userGuess.charAt(0);
				userNumber = userGuess.charAt(1);
				
				// If the user's coordinate is outside the boundaries, for instance if the user enters A7, the letter coordinate is in the limit of the grid
				// but the 7 is considered invalid. As such, we need to ask the user to enter his or her guess again.
				while (userGuess.length() != 2 || userLetter < 65 || userLetter > (65 + LENGTH) || userNumber < 48 || userNumber >= (48 + LENGTH)) {
					System.out.print("Whoops! That is not a valid coordinate. Please enter a valid coordinate and try again: ");
					userGuess = keyboard.nextLine();
					userLetter = userGuess.charAt(0);
					userNumber = userGuess.charAt(1);
				}
				
				// I want to store the user's coordinates into the array list that I have created in the program
				// so that I am able to pass it on to the grid. The main objective with this array is to display
				// the grid with the coordinates that the user has entered.
				storedCoordinates.add(userGuess);
				
				// Then, we generate the grid by calling the method called GenerationOfGrid.
				// The parameter variables require a String ArrayList, an integer variable denoting the length of the 
				// grid perceived as well as the amount of guesses that the user is on right now.
				// As such, the program will pass these values onto the method so that the user can see it.
				GenerationOfGrid(storedCoordinates, LENGTH, amountOfGuesses);
				// We want to print out the amount of points that the user currently has.
				System.out.println(amountOfPoints + allotedPoints + " POINTS\n");
				
				// If the coordinate equals the officialCoordinate that we have created above (the location of the mouse)
				// then we have to say that the user has found it. Otherwise, we tell the user that the mouse is hiding
				// somewhere else.
				if (userGuess.equals(officialCoordinate)) {
					found = true;
					
					// We also want to add the amount of allotted points to the user.
					amountOfPoints += allotedPoints;
					
				} else if ((guessNumber+1) != amountOfGuesses) {
					// We want to check how far is the user's guess from the location of the mouse.
					proximityOfPoint(letterCoordinate, numberCoordinate, userLetter, userNumber);
					
					// If the user has not found the location of the mouse, then we have to decrease the amount of points
					// that the user would have gotten if he or she had guessed correctly.
					System.out.println("The mouse is hiding somewhere else! Please try again!");
					allotedPoints -= 10;
				}
				
				// Of course, the amount of guesses gets incremented by 1 every single time this is run
				guessNumber++;
				
			}
			
			// If the user has found it before the set amount of guesses is exhausted through, then we must
			// say that the user has found it in "amountOfGuesses" guesses, and we ask the user whether he or she wants to
			// try again.
			if (found == true) {
				System.out.print("Hey, you found it! Great job! You found the mouse's location in " + guessNumber + " guesses!\n"
						+ "You now have " + amountOfPoints + " points! Onto round " + (round+1) + "!\n\n\n\n");
				round++;
			} else {
				System.out.println("Aw, you didn't find it! Better luck next time!\n\n\n");
			}
			
		} while (found); // This game will continue as long as the user keeps saying winning.
		
		// Extra line to seperate the menu and the information given from the game
		menu(keyboard, playedGameBefore, nameOfPerson);
	}
	
	// This will be called when we want to check the proximity of the user's guess to the program's location or the mouse's location
	public static void proximityOfPoint(char programLet, char programNum, char userLetter, char userNumber) {
		
		// We determine the proximity of the point by deciphering the distance between the user's letter coordinate
		// and the computer's letter coordinate. This goes the same way for the numeric coordinate.
		// Remember that distance is always positive, so there are certain cases where the answer could be -1 or 1
		// due to the position of the user's guess in relation to the computer's location.
		// As a result, we must use the absolute value to return a distance that is positive.
		int proximityLetter = Math.abs(((int)programLet - (int)userLetter));
		int proximityNumber = Math.abs(((int)programNum - (int)userNumber));
		
		// I do not want to write the statements over and over again in terms of whether
		// the user is close to where he/she has guessed. Thus, I will simply write out the strings
		// and call them that way.
		String close = "The location is close to where you guessed.";
		String somewhatClose = "The location is somewhat close to where you guessed.";
		String far = "The location is far from where you guessed.";
		
		// Before this conditional structure is explained, it is important to note that I did not use the switch function, simply because
		// I know that according to the difficulty, there may be possibilities where the user is 5 units away or 6 units away (especially in the Hard difficulty).
		// As a result, we want to be able to satisfy all of the conditions that might occur during this method.
		// I found that there were many possibilities that could happen especially because of the fact that this occurred on a 2D grid.
		// If the difference between the number coordinates is 0, then we proceed onwards
		// with the letter coordinates.
		if (proximityNumber == 0) {
			// Then, we check, if the distance between the letter coordinates is 1, then we consider that close.
			if (proximityLetter == 1) {
				System.out.println(close);
			// Or else, we check that if the distance between the letter coordinates is 2, then we consider that somewhat close.
			} else if (proximityLetter == 2) {
				System.out.println(somewhatClose);
			// Or else, we check that if the distance betwen the letter coordinates is 3, then we consider that far.
			} else if (proximityLetter > 2){
				System.out.println(far);
			} // I did not add an else statement because if none of these conditions are met, then we say that the user has found it.
		// Or else, we check if the distance between the numbers are equal to 1, if so, we run this nested for loop.
		} else if (proximityNumber == 1) {
			if (proximityLetter == 0 || proximityLetter == 1) {
				System.out.println(close);
			} else if (proximityLetter == 2) {
				System.out.println(somewhatClose);
			} else {
				System.out.println(far);
			}
		// Otherwise we check if the distance between the numbers are equal to 2. If so, we run this nested conditional, and the program
		// follows the format of the first nested conditional as such.
		} else if (proximityNumber == 2) {
			if (proximityLetter >= 0 && proximityLetter <= 2) {
				System.out.println(somewhatClose);
			} else {
				System.out.println(far);
			}
		// Finally, we say that if none of the conditions are satisfied, then we want to say simply that 
		} else {
			System.out.println(far);
		}
	}
	
	// The program serves to run the other selections that are available here. There are currently the EXIT and the GO BACK selection.
	public static void other(Scanner keyboard, String nameOfPerson, boolean playedGameBefore) {
		// We simply want the user to enter his or her selection, so we create a String reference variable called userInput
		// and ask the user what he or she wants to do in this selection.
		String userInput;
		System.out.println("\nWelcome to OTHER, " + nameOfPerson + "! Here are your options:\n ");
		System.out.println("EXIT\t\tGO BACK\t\t1V1\n");
		
		// We want the user to enter his or her selection.
		System.out.print("Please input your selection by entering a choice of difficulty above: ");
		userInput = keyboard.nextLine();
		
		// Always upper case for the selections just to make it a little bit easier.
		userInput = userInput.toUpperCase();
		
		// If the user does not enter in the words "EXIT" or "GO BACK", then we should allow the user to enter his or her selection again.
		while (!(userInput.equals("EXIT") || userInput.equals("GO BACK") || userInput.equals("1V1"))) {
			System.out.println("Oops! Please enter a valid choice from the selection above: ");
			userInput = keyboard.nextLine();
		}
		
		// If the user wants to GO BACK, we bring him or her to the menu.
		if (userInput.equals("GO BACK")) {
			// This System.out.println is intended solely to separate the menu and the other method.
			System.out.println();
			menu(keyboard, true, nameOfPerson);
		// Otherwise, if the user enters 1V1, then we bring the user to the option to play 1V1.
		} else if (userInput.equals("1V1")){
			oneVOne(keyboard, playedGameBefore, nameOfPerson);
		// Or else, the user will exit the game, and the program will terminate at this point.
		} else {
			System.out.println("Thanks for playing, " + nameOfPerson + "! Bye bye now!");
		}
	}
	
	// This method will be run if the user enters in his selection of 1V1. The method will run the game where,
	// essentially, two users would play the game and guess each other's coordinates.
	public static void oneVOne(Scanner keyboard, boolean playedGameBefore, String nameOfPerson) {
		// The String reference variable "choice" refers to whether the user wants to play the game or not, near the end.
		// The String reference variable "playGame" refers to whether the user has played the game.
		// I know that creating another tutorial for this would be somewhat onerous, so I simply place a disclaimer
		// asking that the user understand that if the user does not know how to input the coordinates, for instance
		// then it is the user's responsibility at this point.
		String choice, playGame = " ";
		// We also want btoh users
		String characterOne, characterTwo;
		
		// If the user has not played the game before, then we tell the user that it is advised to learn how to play the game first before
		// proceeding with this type of selection.
		if (playedGameBefore == false) {
			System.out.print("Just a reminder, there is no tutorial associated with this.\nIt is thus recommended that you are "
					+ "comfortable with the game's format\nby playing some of the difficulties before proceeding with this.\n"
					+ "You must also need two people.\nAre you ready? Please type in \"YES\" or \"NO\" now: ");
			// We ask the user to enter in YES or NO
			playGame = keyboard.nextLine();
			
			// Input validation loop; if the user does not enter YES or NO but something else instead,
			// then we ask the user to input again.
			while (!(playGame.equalsIgnoreCase("YES") || playGame.equalsIgnoreCase("NO"))) {
				System.out.print("Oops! That is not a valid answer. Please enter in \"YES\" or \"NO\": ");
				playGame = keyboard.nextLine();
			}
		} 
		
		// If the user does not enter in YES, then we direct him or her to the menu to play the game.
		if (playGame.equalsIgnoreCase("NO")) {
			System.out.println("I will direct you to the next menu.");
			menu(keyboard, playedGameBefore, nameOfPerson);
		// Otherwise, we allow the user to play the game.
		} else {
			do 
			{
				// Create two array lists to display the grids to each of the user.
				ArrayList<String> gridPlayer1 = new ArrayList<String>();
				ArrayList<String> gridPlayer2 = new ArrayList<String>();
				
				// Create the random object where they can decide who goes first.
				Random generationNum = new Random();
				
				// We want to get the names of the two players, which explains the characterOne and characterTwo reference variables.
				// The lengthString variable is used to store the length of the grid that the user desires to play in.
				// The playerOneCoordinates and the playerTwoCoordinates are mainly used to store the first player and the second player's coordinates
				// (essentially what does the person want the other user to guess with?)
				String lengthString, playerOneCoordinates, playerTwoCoordinates;
				// At the end, we want to specify who has won. We will change this based on who has actually won.
				String whoHasWon = "";
				// The first four variables (userLetter1, userLetter2, userNumber1, userNumber2) refer to both of the user's coordinates
				// and storing the letter and numeric coordinates, since they will mainly be used to check whether it is valid or not.
				// The last four variables (guessedLet1, guessedNum1, guessedLet2, guessedNum2) refer to both of the user's coordinates
				// and using them in order to generate their own grids with the GenerationOfGrid method.
				char userLetter1, userLetter2, userNumber1, userNumber2, guessedLet1, guessedNum1, guessedLet2, guessedNum2;
				
				// Create a byte variable to establish the length of the code.
				// We also want to check if the numberOfGuesses has surpassed, and by that, we first set the first user's amount of guesses made and
				// and the second amount of guesses made.
				byte length, numberOfGuesses = 10, guessesTaken1 = 0, guessesTaken2 = 0;
				
				// We check whether either user has been able to find it.
				boolean found = false;
				
				// Generate the number of the player; this number decides who goes first.
				byte randomPlayer = (byte)(generationNum.nextInt(1) + 1);
				
				// Every single game will have 10 guesses assigned to each player.
				System.out.println("10 guesses are assigned to each player.");
				
				// Again, we ask the players to enter their names
				System.out.print("Enter name of player 1: ");
				characterOne = keyboard.nextLine();
				
				// The second player enters his name
				System.out.print("Enter name of player 2: ");
				characterTwo = keyboard.nextLine();
				
				// Allow the user to enter the desired length of the grid.
				System.out.print("Please enter the desired length of the grid that you want to input (between 0-10 units only): ");
				
				// Enter the length of the grid.
				length = keyboard.nextByte();
				
				while (length < 0 || length > 10) {
					System.out.println("Oops! That is not a valid length of the grid. Please enter again: ");
					length = keyboard.nextByte();
				}
				
				// This sole keyboard.nextLine() is here to prevent the possibility of skipping over a parti 
				keyboard.nextLine();
				
				// We want both the user's to establish their own coordinates first
				System.out.println(characterTwo + ", please do not look at the screen.");
				System.out.print(characterOne + ", please enter your selected coordinates: ");
				
				// We ask the player 1 to enter his or her coordinates.
				playerOneCoordinates = keyboard.nextLine();
				// We take the first coordinate as well as the second coordinate and store them
				// in char variables called userLetter1 and userNumber1 respectively.
				userLetter1 = playerOneCoordinates.charAt(0);
				userNumber1 = playerOneCoordinates.charAt(1);
				
				// Then, we check if the first user meets these conditions:
				/*
				 * -- If the user's coordinates are not 2 characters in length
				 * -- If the user's letter coordinate is not under A (in terms of the unicode) or over the nth letter after A
				 * -- If the user's numeric coordinate is not under 0 (again, in terms of the unicode) or over the nth number after 0
				 * where n is the length in this case.
				 * If the first player doesn't meet this condition, we ask the player again.
				 */
				while (playerOneCoordinates.length() != 2 || (userLetter1 < 65 || userLetter1 > (65 + length) || userNumber1 < 48 || userNumber1 > (48 + length))) {
					System.out.print("That is not a valid coordinate, please try again. ");
					playerOneCoordinates = keyboard.nextLine();
					userLetter1 = playerOneCoordinates.charAt(0);
					userNumber1 = playerOneCoordinates.charAt(1);
				}
				
				// We don't want characterTwo to be peeking at his or her screen, so we seperate it with a large space in between.
				for (int i = 0; i < 200; i++) {
					System.out.println();
				}
				
				// We ask the CharacterOne not to look at the screen and ask the second user to enter his or her coordinates
				// that the first user will have to guess.
				System.out.println(characterOne + ", please do not look at the screen.");
				System.out.print(characterTwo + ", please enter your selected coordinates: ");
				
				// We ask the player 1 to enter his or her coordinates.
				playerTwoCoordinates = keyboard.nextLine();
				// We take the first coordinate as well as the second coordinate and store them
				// in char variables called userLetter1 and userNumber1 respectively.
				userLetter2 = playerTwoCoordinates.charAt(0);
				userNumber2 = playerTwoCoordinates.charAt(1);
				
				// Then, we check if the second user meets these conditions:
				/*
				 * -- If the user's coordinates are not 2 characters in length
				 * -- If the user's letter coordinate is not under A (in terms of the unicode) or over the nth letter after A
				 * -- If the user's numeric coordinate is not under 0 (again, in terms of the unicode) or over the nth number after 0
				 * where n is the length in this case.
				 * If the second player doesn't meet this condition, we ask the player again.
				 */
				while (playerTwoCoordinates.length() != 2 || (userLetter1 < 65 || userLetter1 > (65 + length) || userNumber1 < 48 || userNumber1 >= (48 + length))) {
					System.out.print("That is not a valid coordinate, please try again. ");
					playerTwoCoordinates = keyboard.nextLine();
					userLetter2 = playerOneCoordinates.charAt(0);
					userNumber2 = playerOneCoordinates.charAt(1);
				}
				
				// We check, who is going first? If the randomNumber is 1 then obviously the first user will go first.
				// If the randomNumber is 2 then the second user will go first.
				if (randomPlayer == 1) {
					System.out.println(characterOne + " GOES FIRST!");
				} else {
					System.out.println(characterTwo + " GOES FIRST!");
				}
				
				// This simply makes sure that we are alternating between each player, so we assign another variable
				// that will increment and decrement based on the person playing.
				byte orderTurn = randomPlayer;
				
				// This game, in this case, will continue to run as long as the first user's amount of guesses
				// and the second user's amount of guesses are under the numberOfGuesses assigned.
				// The loop will also continue until either one of them has found the other's user coordinate
				// or until both of them have exhausted through the numberOfGuesses given.
				while ((guessesTaken1 < numberOfGuesses || guessesTaken2 < numberOfGuesses) && !found) {
					// The coordinates variable will be somewhat of an temporary input variable, which will hold
					// the user's coordinates in place.
					String coordinates;
					if (orderTurn == 1) {
						// We ask the user to input his or her guessed coordinates. We call this to another method
						// called Enter1V1 Coordinates because I simply don't want to repeat the same thing with the other user.
						coordinates = Enter1V1Coordinates(keyboard, characterOne);
						gridPlayer1.add(coordinates);
						
						// We get the numeric coordinates and letter coordinates of the first user's guess
						guessedLet1 = coordinates.charAt(0);
						guessedNum1 = coordinates.charAt(1);
						
						// We ask, like above, is this a valid coordinate? This uses the same conditions as what we had last time. One could put this in a method if need be
						// but for the sake of time, I want to simply have the while loop whenever possible.
						while (playerOneCoordinates.length() != 2 || (guessedLet1 < 65 || guessedLet1 > (65 + length) || guessedNum1 < 48 || guessedNum1 > (48 + length))) {
							System.out.print("That is not a valid coordinate, please try again. ");
							playerOneCoordinates = keyboard.nextLine();
							userLetter1 = playerOneCoordinates.charAt(0);
							userNumber1 = playerOneCoordinates.charAt(1);
						}
						
						// The amount of guessTaken for the first user should also be added as well.
						guessesTaken1++;
						// We then generate the grid based on the length, and output the user's guess.
						GenerationOfGrid(gridPlayer1, length, numberOfGuesses);
						
						// We then check, if the user's coordinates is the same with the second user's coordinates,
						// then that implies that player 1 has found the coordinates.
						if (coordinates.equals(playerTwoCoordinates)) {
							// We set found to true, so that when we iterate again, the game does not run anymore.
							found = true;
							// The order turn is set to 0, because we are not going to the other person to ask him/her to
							// guess another coordinate -- in other words, it is redundant.
							orderTurn = 0;
							// We tell the user that he or she has won with this amount of guesses! The conditional is only there
							// for proper grammar ideally.
							if (guessesTaken1 == 1) {
								whoHasWon = characterOne + " has won with " + guessesTaken1 + " guess!";
							} else {
								whoHasWon = characterOne + " has won with " + guessesTaken1 + " guesses!";
							}
						// Otherwise, if the first user has not guessed the coordinates correctly,
						// then we run this block of code.
						} else {
							// We generate the proximity of the point of the first user's guess to the second user's coordinates.
							proximityOfPoint(userLetter2, userNumber2, guessedLet1, guessedNum1);
							// We then increment orderTurn to 2, so that the second user can enter his or her coordinates.
							orderTurn++;
						}
					// This block of code will run after the first user has taken his or her turn. This block of code runs 
					// the second user's turn.
					} else if (orderTurn == 2) {
						// We ask the second user to input his or her guessed coordinates. We call this to another method
						// called Enter1V1 Coordinates because I simply don't want to repeat the same thing with the other user.
						coordinates = Enter1V1Coordinates(keyboard, characterTwo);
						// We add the coordinates to the arrayList to pass onto the GenerationOfGrid method.
						gridPlayer2.add(coordinates);
						
						// We get the numeric coordinates and letter coordinates of the first user's guess
						guessedLet2 = coordinates.charAt(0);
						guessedNum2 = coordinates.charAt(1);
						
						// We check, like above, is this a valid coordinate? This uses the same conditions as what we had last time. One could put this in a method if need be
						// but for the sake of time, I want to simply have the while loop whenever possible.
						while (playerOneCoordinates.length() != 2 || (guessedLet2 < 65 || guessedLet2 > (65 + length) || guessedNum2 < 48 || guessedNum2 >= (48 + length))) {
							System.out.print("That is not a valid coordinate, please try again. ");
							playerOneCoordinates = keyboard.nextLine();
							userLetter1 = playerOneCoordinates.charAt(0);
							userNumber1 = playerOneCoordinates.charAt(1);
						}
						
						// We then generate the grid based on the length, and output the user's guess.
						GenerationOfGrid(gridPlayer2, length, numberOfGuesses);
						// The amount of guessTaken for the second user should also be added as well.
						guessesTaken2++;
						// We then check, if the user's coordinates is the same with the first user's coordinates,
						// then that implies that player 2 has found the coordinates.
						if (coordinates.equals(playerOneCoordinates)) {
							// We set found to true, so that when we iterate again, the game does not run anymore.
							found = true;
							// The order turn is set to 0, because we are not going to the other person to ask him/her to
							// guess another coordinate -- in other words, it is redundant.
							orderTurn = 0;
							// We tell the user that he or she has won with this amount of guesses! The conditional is only there
							// for proper grammar ideally.
							if (guessesTaken2 == 1) {
								whoHasWon = characterTwo + " has won with " + guessesTaken2 + " guess!";
							} else {
								whoHasWon = characterTwo + " has won with " + guessesTaken2 + " guesses!";
							}
						// Otherwise, if the first user has not guessed the coordinates correctly,
						// then we run this block of code.
						} else {
							// We generate the proximity of the point of the first user's guess to the second user's coordinates.
							proximityOfPoint(userLetter1, userLetter2, guessedLet2, guessedNum2);
							// We then increment orderTurn to 2, so that the second user can enter his or her coordinates.
							orderTurn--;
						}
					} 
				}
				// We say who has won based on the String reference variable whoHasWon.
				System.out.println(whoHasWon);
				// We ask the players if they both want to play again.
				System.out.print("Would both players like to play again? Type \"YES\" or \"NO\" now: ");
				choice = keyboard.nextLine();
				
				// If the choice or the selection is YES, then the game will repeat.
			} while (choice.equals("YES"));
			
		}
		
		// Otherwise, if the user's print out NO, then we simply lead the user to the main menu.
		menu(keyboard, true, nameOfPerson);
	}
	
	// This method serves to essentially replace the idea of copying the same code in both areas, since
	// we are asking both users to enter their coordinates. As such, we simply call this method in the 
	// oneVone method only.
	public static String Enter1V1Coordinates(Scanner keyboard, String characterName) {
		String chosenCoordinates;
		// The previous line states to enter the coordinates of whatever character:
		System.out.print(characterName + ", please enter your coordinates: ");
		// We simply allow the user to enter his or her coordinate in the console.
		chosenCoordinates = keyboard.nextLine();
		// We return whatever the user has entered.
		return chosenCoordinates;
	}

}
