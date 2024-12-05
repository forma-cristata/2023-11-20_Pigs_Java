/*
 * Name: Kaci Craycraft
 * South Hills Username: kcraycraft45
 */
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static Scanner input = new Scanner(System.in);//input scanner
    public static Random rand = new Random();//randomizer

    public static final int WINNING_SCORE = 100;//Initialize global constant variables
    public static final int ONE_SECOND = 1000;
    public static final int HALF_A_SECOND = 500;
    public static final int DICE_FACE_ONE = 0;
    public static final int DICE_FACE_TWO = 1;


    public static void main(String[] args)
    {
        int player1TotalScore;//Initialize local variables
        int player2TotalScore;
        String[] playersUsernames;
        int winner;

        final int NOTHIN = 0;
        final int ARRAY_POSITION_ONE = 0;//Initialize local constant variables to eliminate magic numbers
        final int ARRAY_POSITION_TWO = 1;

        do
        {
            player1TotalScore = NOTHIN;//Players start each game without any points
            player2TotalScore = NOTHIN;
            playersUsernames = createUsernames();//Each player gets to pick their usernames
            //Their usernames are returned to an array

            while(!hasAnybodyWon(player1TotalScore, player2TotalScore))//While nobody has earned more than 100 total points, keep playing
            {
                player1TotalScore = scoringAndLoopingEachPlayersTurn(playersUsernames[ARRAY_POSITION_ONE], playersUsernames[ARRAY_POSITION_TWO], player1TotalScore);//This is each players turn until they choose to or must pass
                if(player1TotalScore < WINNING_SCORE)//if player1 has not won after their turn, then player 2 gets to play
                {
                    player2TotalScore = scoringAndLoopingEachPlayersTurn(playersUsernames[ARRAY_POSITION_TWO], playersUsernames[ARRAY_POSITION_ONE], player2TotalScore);//player2's turn
                }
            }
            winner = hasPlayer1WonOrPlayer2(player1TotalScore, player2TotalScore);//Someone has won... who?
            wait(ONE_SECOND);
            switch(winner)//Outputs to announce the winner
            {
                case 1 -> rollOutThisString("\n" + playersUsernames[ARRAY_POSITION_ONE] + " has won with a total of " + player1TotalScore + " points.\nCongratulations!!\n");
                case 2 -> rollOutThisString("\n" + playersUsernames[ARRAY_POSITION_TWO] + " has won with a total of " + player2TotalScore + " points.\nCongratulations!!\n");
            }
        }
        while(doYouWantToPlayAgain());//Keep 'do'ing while they wish to play again
        input.close();
    }
    public static String[] createUsernames()
    {
        String players;//Initialize local variables
        String[] ivInputs = {"x",
                "one",
                "two",
                "Invalid Input, please choose one or two only... ",
                "\nHow many players are there? "};
        String player1Username = null;
        String player2Username = null;

        final String CHEATER_PUMPKIN_EATER = "cheat";//Initialize local constants
        final int THREE_SECONDS = 3000;
        final String NO_PLAYER_TWO_ASSIGNMENT = "The Computer";

        rollOutThisString("How many players are there? One or two? ");
        players = input.nextLine();//How many players are there?
        System.out.println();//Because of rollOutThisString, I need an \n after inputs
        ivInputs[0] = players;
        players = inputValidation(ivInputs);//Input validation
        switch(players.toLowerCase())
        {
            case "one" ->//If there is only one player, they play against the computer
            {
                rollOutThisString("\n");
                player1Username = heWhoShallBeNamed();
                if(player1Username.equalsIgnoreCase(CHEATER_PUMPKIN_EATER))//If they name themselves 'cheat', it is a cheat-code to increase the likelihood of winning
                {
                    rollOutThisString("\n(´。＿。｀)    \nYou cracked the code, dummy.\nThere's no way of screwing this up now...");
                    wait(THREE_SECONDS);
                }
                player2Username = NO_PLAYER_TWO_ASSIGNMENT;//player2Username is 'The Computer'
            }
            case "two" ->//If there are two players, they both get to choose a username
            {
                rollOutThisString("\nPlayer 1...\n");
                player1Username = heWhoShallBeNamedTwoPlayers();
                rollOutThisString("\nPlayer 2...\n");
                player2Username = heWhoShallBeNamedTwoPlayers();
            }
        }
        return new String[]{player1Username, player2Username};//Return the usernames as an array
    }
    public static void rollOutThisString(String msg)
    {
        String[] msgA;//Initialize local variables

        final int ONE_ONE_HUNDREDTH_OF_A_SECOND = 10;//Initialize local constant variables

        msgA = msg.split("");//This is a way to make the code look as though it is being actively typed
        for(String character : msgA)//It adds life to the computer
        {
            System.out.print(character);
            wait(ONE_ONE_HUNDREDTH_OF_A_SECOND);
        }
    }
    public static void wait(int ms)
    {//pause function for quality of life
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    public static String inputValidation(String[] prompts)
    {//Makes input validation portable
        while(!prompts[0].equalsIgnoreCase(prompts[1]) && !prompts[0].equalsIgnoreCase(prompts[2]))
        {
            rollOutThisString(prompts[3] + "\n");
            wait(HALF_A_SECOND);
            rollOutThisString(prompts[4]);
            prompts[0] =  input.nextLine();
            System.out.println();
        }
        return prompts[0];//returns input if it fits the parameters
    }
    public static String heWhoShallBeNamed()
    {//Only called when there is only one player
        String username;//Initialize local variables

        final String CANT_NAME_ONE_PLAYER_THAT = "The Computer";//Initialize local constant variables

        wait(ONE_SECOND);
        rollOutThisString("What would you like your username to be? ");
        username = input.nextLine();
        System.out.println();
        while(username.equalsIgnoreCase(CANT_NAME_ONE_PLAYER_THAT))//player1 cannot be named "The Computer" for functionality in other methods
        {
            wait(ONE_SECOND);
            rollOutThisString("Unfortunately, that username is taken, please choose something else: ");
            username = input.nextLine();
            System.out.println();
        }
        return username;//Returns the chosen username
    }
    public static String heWhoShallBeNamedTwoPlayers()
    {//Only called when there are two players
        String usernameTwoPlayers;//Initialize local variables

        final String FORBIDDEN_USERNAME_IF_2_PLAYERS_X = "The Computer";//Initialize local constant variables
        final String FORBIDDEN_USERNAME_IF_2_PLAYERS_Y = "cheat";

        wait(ONE_SECOND);
        rollOutThisString("What would you like your username to be? ");
        usernameTwoPlayers = input.nextLine();
        System.out.println();
        while(usernameTwoPlayers.equalsIgnoreCase(FORBIDDEN_USERNAME_IF_2_PLAYERS_X) || usernameTwoPlayers.equalsIgnoreCase(FORBIDDEN_USERNAME_IF_2_PLAYERS_Y))
        {//Usernames for two players are allowed to be anything except 'cheat' and 'computer' for functionality
            wait(ONE_SECOND);
            rollOutThisString("Unfortunately, that username is forbidden; please choose something else: ");
            usernameTwoPlayers = input.nextLine();
            System.out.println();
        }
        return usernameTwoPlayers;//returns the player's username
    }
    public static boolean hasAnybodyWon(int p1TotalScore, int p2TotalScore)
    {//returns true if either player has 100 points or more
        return p1TotalScore >= WINNING_SCORE || p2TotalScore >= WINNING_SCORE;
    }
    public static int scoringAndLoopingEachPlayersTurn(String usernameOfCurrentPlayer, String usernameOfOtherPlayer, int usersScoreTotal)
    {//The actual turn for each player, returns their score per their turn
        int playersCurrentTurnScore = 0;//Initialize local variables
        int i = 0;
        int rollAgainBonus;
        int[] playerRoll;

        final int FIRST_ROLL_PER_TURN = 0;//Initialize local constant variables
        final int BONUS_FOR_ROLLING_AGAIN = 2;
        final int NO_POINTS_FOR_YOU = 0;

        while(!hasTheCurrentPlayerSurpassed100Points(playersCurrentTurnScore, usersScoreTotal))//If during their turn, the player has surpassed 100 points, the game ends
        {
            if(i>FIRST_ROLL_PER_TURN)//only asks player if they want to roll again after their first roll
            {
                rollAgainBonus = doesPlayerChooseToRollAgain(usernameOfCurrentPlayer, usernameOfOtherPlayer);
                if(rollAgainBonus == BONUS_FOR_ROLLING_AGAIN)//If they choose to roll again, they receive a bonus to their points
                {
                    playersCurrentTurnScore += rollAgainBonus;
                }
                else
                {
                    break;//players turn ends
                }
            }
            playerRoll = rollTwoDice(usernameOfCurrentPlayer);//player rolls the dice
            playersCurrentTurnScore += turnScorePerRoll(usernameOfCurrentPlayer, playerRoll);//adds their roll to their point total for their turn
            if(!doesRollHaveA1(playerRoll))//If their roll does not have a 1
            {
                playersCurrentTurnScore += doublesBonus(usernameOfCurrentPlayer, playerRoll);//We check if they rolled doubles, and add the respective bonus if they did
                wait(HALF_A_SECOND);
                rollOutThisString("\n" + usernameOfCurrentPlayer + " has " + playersCurrentTurnScore + " points for this turn so far.\n");//Tell the user their current turn score
            }
            else//Their roll had a one
            {
                wait(ONE_SECOND);
                if(areThereDoubleOnes(playerRoll))//If they rolled double ones
                {
                    rollOutThisString("However, since " + usernameOfCurrentPlayer + " rolled double ones, they lose all of their points and must pass.\n");
                    return NO_POINTS_FOR_YOU;//They lose all points for their game so far
                }
                else//They did not roll double ones, but did roll a one
                {
                    playersCurrentTurnScore = 0;//They lose all their points for their turn and must pass
                    rollOutThisString("However, since " + usernameOfCurrentPlayer + " rolled a one, they lose all of their points for this turn and must pass.\n\n");
                    rollOutThisString("\n" + usernameOfCurrentPlayer + "'s score so far is " + usersScoreTotal + "\n");
                    break;//Next player's turn
                }
            }
            i++;//Increment
        }
        usersScoreTotal += playersCurrentTurnScore;//After each turn, their turn score is added to their total score
        rollOutThisString("\n" + usernameOfCurrentPlayer + "'s total score is " + usersScoreTotal + "\n");
        return usersScoreTotal;//return their total score
    }
    public static int[] rollTwoDice(String player)
    {//rolls two randomly generated dice with values between 1 and 6 inclusive, and returns them as an array
        int[] roll = new int[2];//Initialize local variables

        final int LOWEST_DICE_VAL = 1;//Initialize local constant variables
        final int HIGHEST_DICE_VAL_PLUS_ONE = 7;

        rollOutThisString("\n\n" + player + "! Roll Those Dice!!\n...\n");
        wait(ONE_SECOND);
        rollOutThisString(player + "'s roll was as follows:\t");
        for(int i=0;i<roll.length;i++)//creates two dice values and outputs them to the user
        {
            roll[i] = rand.nextInt(LOWEST_DICE_VAL,HIGHEST_DICE_VAL_PLUS_ONE);
            wait(HALF_A_SECOND);
            if(i==0)
            {
                rollOutThisString(roll[i] + " ... ");
            }
            else
            {
                System.out.println(roll[i]);
            }
        }
        System.out.println();
        return roll;
    }
    public static int turnScorePerRoll(String theirUsername, int[] theirRollOfTheDice)
    {//This scores a player's turn only according to their dice values
        int theirRollsScore = 0;//Initialize local variables

        for(int diceFace : theirRollOfTheDice)//enhanced for; j will loop through every element in array theirRollOfTheDice
        {
            theirRollsScore += diceFace;//And will add the dice face values to their score
        }
        wait(ONE_SECOND);
        rollOutThisString(theirUsername + "'s roll is worth " + theirRollsScore + " points.\n" );
        return theirRollsScore;
    }
    public static boolean doesRollHaveA1(int[] roll)
    {//Returns true if roll has a 1
        final int DICE_FACE_OF_ONE = 1;//Initialize local constants

        for(int dicesFace : roll)//Iterates through every element of array roll
        {
            if(dicesFace == DICE_FACE_OF_ONE)
            {return true;}
        }
        return false;
    }
    public static int doublesBonus(String person, int[] theirRoll)
    {//Returns their doubles bonus points if applicable, returns zero if no doubles, or if the doubles are ones
        final int DICE_FACE_IS_A_ONE = 1;//Initialize local constant variables
        final int DICE_FACE_IS_A_SIX = 6;
        final int DOUBLES_BONUS_SIXES_ONLY = 12;
        final int DOUBLES_BONUS_TWO_TO_FIVE_INCLUSIVE = 10;
        final int NO_DOUBLES_BONUS = 0;

        wait(ONE_SECOND);
        if(theirRoll[DICE_FACE_ONE] == theirRoll[DICE_FACE_TWO] && theirRoll[DICE_FACE_ONE]!=DICE_FACE_IS_A_ONE)
        {//If they have equal die, and they are not one
            if(theirRoll[DICE_FACE_ONE] == DICE_FACE_IS_A_SIX)//If their die are sixes
            {
                rollOutThisString("\nSince " + person + " rolled double 6's, they receive a 12 point bonus!\n");
                return DOUBLES_BONUS_SIXES_ONLY;//They receive a 12 point bonus
            }
            rollOutThisString("\nSince " + person + " rolled doubles, they receive a 10 point bonus!\n");
            return DOUBLES_BONUS_TWO_TO_FIVE_INCLUSIVE;//If they rolled doubles that aren't ones and aren't sixes, they receive a ten point bonus
        }
        return NO_DOUBLES_BONUS;
    }
    public static boolean areThereDoubleOnes(int[] dice)
    {//Returns true if the dice faces are equal (i.e. double ones), this method is only accessed when there is a one in the roll, so we do not need to check the value of the die
        return (dice[DICE_FACE_ONE] == dice[DICE_FACE_TWO]);
    }
    public static boolean hasTheCurrentPlayerSurpassed100Points(int userTurnScore, int userTotalScore)
    {//This just checks if the user has won the game while their turn is still active
        return userTurnScore + userTotalScore >= WINNING_SCORE;
    }//returns true if they have won during their turn
    public static int doesPlayerChooseToRollAgain(String playerName, String otherPlayerName)
    {//returns true if the player wants to roll again
        String rollAgain;//Initialize local variables
        String[] inputsForValidation = {"x",
                "yes",
                "no",
                "\nInvalid input!! Please only enter yes or no!",
                "\nWould you like to roll again? "};

        final int ROLL_BONUS_POINTS = 2;//Initialize local constant variables
        final int NO_BONUS_POINTS = 0;

        if(!playerName.equals("The Computer") && !playerName.equals("Cheat"))//If this is player 1 in a one player game, and they did not use the cheat code
        {
            wait(ONE_SECOND);
            rollOutThisString("\n\n" + playerName + ", would you like to roll again? ");//We ask if they want to roll again
            rollAgain = input.nextLine();
            System.out.println();
            inputsForValidation[0] = rollAgain;
            rollAgain = inputValidation(inputsForValidation);//Input validation
            wait(HALF_A_SECOND);
            if(rollAgain.equalsIgnoreCase(inputsForValidation[1]))
            {
                rollOutThisString(playerName + " receives a two point bonus for choosing to roll again!\n");
                return ROLL_BONUS_POINTS;//If they choose to roll again, they receive a bonus and we return it
            }
            else
            {
                rollOutThisString(playerName + " has chosen to pass, and it is now " + otherPlayerName + "'s turn.\n");
                return NO_BONUS_POINTS;//Else, they chose to pass and do not receive a bonus
            }
        }
        else//If this is the computer or the cheated game, we need to check other things...
        {
            return cheatingHack(playerName);
        }
    }
    public static int cheatingHack(String playName)
    {//I just wrote this for fun :D
        int choice;//Initialize local variables
        int toCheatOrNotToCheat;

        final int LOWER_BOUND = 1;//Initialize local constant variables
        final int UPPER_BOUND = 101;
        final String CHEAT_CODE = "cheat";
        final int CHEATING_MODULUS = 5;
        final int NOT_CHEATING_MODULUS = 2;
        final int NO_REMAINDER = 0;
        final int TO_PASS = 0;
        final int OR_NOT_TO_PASS = 2;

        choice = rand.nextInt(LOWER_BOUND,UPPER_BOUND);//Returns an integer from 1 to 100
        if (playName.equalsIgnoreCase(CHEAT_CODE))//If they found the cheat code
        {
            toCheatOrNotToCheat = CHEATING_MODULUS;//5
        }
        else//if they did not use the cheat code
        {
            toCheatOrNotToCheat = NOT_CHEATING_MODULUS;//2
        }
        wait(ONE_SECOND);
        //After playing this game a lot, I found that the more often you pass, the more likely you are to win the game
        //When cheating, the computer is less likely to pass and thus, more likely to lose, if you play strategically
        //There is a 50% chance of a number between 1 and 100 being divisible by two
        //There is a 20% chance of a number between 1 and 100 being divisible by 5
        if(choice%toCheatOrNotToCheat == NO_REMAINDER)//The remainder (modulus) is more likely to occur as not zero, if the player chose to cheat
        {//resulting in the computer choosing to keep playing more often, and subsequently rolling a 1 more often
            rollOutThisString("\nThe Computer decided to pass.\n\n");
            return TO_PASS;
        }
        else
        {
            rollOutThisString("The Computer chose to keep playing.\n");
            return OR_NOT_TO_PASS;
        }
    }
    public static int hasPlayer1WonOrPlayer2(int p1CumulativeScore, int p2CumulativeScore)
    {//Determines the winner
        final int PLAYER_ONE_WON = 1;//Initialize local constant variables
        final int PLAYER_TWO_WON = 2;

        if(p1CumulativeScore>p2CumulativeScore)//Whoever has the highest score when the game ends wins
        {
            return PLAYER_ONE_WON;
        }
        else
        {
            return PLAYER_TWO_WON;
        }
    }
    public static boolean doYouWantToPlayAgain()
    {//Returns true if the user wants to play the whole game again
        String answer;//Initialize local variables
        String[] thoseInputs = {"x",
                "yes",
                "no",
                "Invalid input! Please only enter yes or no.",
                "\nWould you like to play another game? " };

        rollOutThisString("Would you like to play again? ");
        answer = input.nextLine();
        System.out.println();
        thoseInputs[0] = answer;
        answer = inputValidation(thoseInputs);
        return answer.equalsIgnoreCase(thoseInputs[1]);
    }
}