package game;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
 
public
class tictac
{
 
    static ArrayList<Integer> ourpositions = new ArrayList<Integer>();
    static ArrayList<Integer> comppositions = new ArrayList<Integer>();
 
public
    static void main(String[] args)
    {
 
        Scanner IN = new Scanner(System.in);
        System.out.print("PRESS 1 FOR SINGLE PLAYER \n PRESS 2 FOR DOUBLE PLAYER.");
        int a = IN.nextInt();
        if (a == 1)
        { // making game boardd of 2D character array
            char[][] boardd = {{' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '}};
            Scanner SC = new Scanner(System.in);
            System.out.print("PLAYER, WHAT IS YOUR  NAME: ");
            String p = SC.nextLine();
 
            printboardd(boardd); // printing the boardd
 
            while (true)
            { // Running an infinite loop until someone wins the game;
                Scanner scan = new Scanner(System.in);
                System.out.println("Input a Number from 1 TO 9, " + p);
                int mypos = scan.nextInt();
                while (ourpositions.contains(mypos) || comppositions.contains(mypos))
                {
                    System.out.println("This Position is already taken !! ");
                    mypos = scan.nextInt(); // after a wrong position we will again ask our user to re enter a new correct position.
                }
 
                putchar(boardd, mypos, "player");
 
                String ans = checkifwin(); // checking if we won after we made a move .
                if (ans.length() > 0)
                { // checking if we haven't returned the empty string.
                    printboardd(boardd);
                    System.out.println(ans);
                    break;
                }
 
                Random random = new Random();
                int comppos = random.nextInt(9) + 1; // giving computer a random position from 1 to 9 ;
                while (ourpositions.contains(comppos) || comppositions.contains(comppos))
                {
                    comppos = random.nextInt(9) + 1;
                }
                putchar(boardd, comppos, "comp");
 
                printboardd(boardd);
 
                ans = checkifwin(); // checking winner if computer wins after it's move.
                if (ans.length() > 0)
                { // checking if we haven't returned the empty string.
                    printboardd(boardd);
                    System.out.println(ans);
                    break;
                }
            }
        }
        else if (a == 2)
        {
            char[][] board = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
 
            //Create a Scanner and ask the players for their names
            Scanner in = new Scanner(System.in);
            System.out.println("Let's play Tic Tac Toe!");
            System.out.print("Player 1, what is your name? ");
            String p1 = in.nextLine();
            System.out.print("Player 2, what is your name? ");
            String p2 = in.nextLine();
 
            //Create a player1 boolean that is true if it is player 1's turn and false if it is player 2's turn
            boolean player1 = true;
 
            //Create a gameEnded boolean and use it as the condition in the while loop
            boolean gameEnded = false;
            while (!gameEnded)
            {
 
                //Draw the board
                drawBoard(board);
 
                //Print whose turn it is
                if (player1)
                {
                    System.out.println(p1 + "'s Turn (x):");
                }
                else
                {
                    System.out.println(p2 + "'s Turn (o):");
                }
 
                //Create a char variable that stores either 'x' or 'o' based on what player's turn it is
                char c = ' ';
                if (player1)
                {
                    c = 'x';
                }
                else
                {
                    c = 'o';
                }
 
                //Create row and col variables which represent indexes that correspond to a position on our board
                int row = 0;
                int col = 0;
 
                //Only break out of the while loop once the user enters a valid position
                while (true)
                {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("ENTER YOUR PLACEMENT (1-9)");
                    int pos = sc.nextInt();
                    switch (pos)
                    {
                    case 1:
                        row = 0;
                        col = 0;
                        break;
                    case 2:
                        row = 0;
                        col = 2;
                        break;
                    case 3:
                        row = 0;
                        col = 4;
                        break;
                    case 4:
                        row = 2;
                        col = 0;
                        break;
                    case 5:
                        row = 2;
                        col = 2;
                        break;
                    case 6:
                        row = 2;
                        col = 4;
                        break;
                    case 7:
                        row = 4;
                        col = 0;
                        break;
                    case 8:
                        row = 4;
                        col = 2;
                        break;
                    case 9:
                        row = 4;
                        col = 4;
                        break;
                    default:
                        break;
                    }
 
                    //Check if the row and col are in bounds
                    if (row < 0 || col < 0 || row > 5 || col > 5)
                    {
                        System.out.println("This position is off the bounds of the board! Try again.");
                    }
                    //Check if the position on the board the user entered is empty (has a sapce) or not
                    else if (board[row][col] != ' ')
                    {
                        System.out.println("Someone has already made a move at this position! Try again.");
 
                        //Otherwise, the position is valid so break out of the while loop
                    }
                    else
                    {
                        break;
                    }
                }
 
                //Set the position on the board at row, col to c
                board[row][col] = c;
 
                //Check to see if either player has won
                if (playerHasWon(board) == 'x')
                {
                    System.out.println(p1 + " has won!");
                    gameEnded = true;
                }
                else if (playerHasWon(board) == 'o')
                {
                    System.out.println(p2 + " has won!");
                    gameEnded = true;
                }
                else
                {
 
                    //If neither player has won, check to see if there has been a tie (if the board is full)
                    if (boardIsFull(board))
                    {
                        System.out.println("It's a tie!");
                        gameEnded = true;
                    }
                    else
                    {
                        //If player1 is true, make it false, and vice versa; this way, the players alternate each turn
                        player1 = !player1;
                    }
                }
            }
 
            //Draw the board at the end of the game
            drawBoard(board);
        }
 
        else
        {
            System.out.print("INVALID INPUT");
        }
    }
public
    static void printboardd(char[][] boardd)
    {
        for (char[] rows : boardd)
        { // iterating over a row
            for (char c : rows)
            { // iterating over a column of a row
                System.out.print(c);
            }
            System.out.println();
        }
    }
 
public
    static void putchar(char[][] boardd, int mypos, String user)
    {
 
        char tochange = ' ';
        if (user.equals("player"))
        {
            tochange = 'X'; // if it's player turn then we will change the changing char to X
            ourpositions.add(mypos);
        }
        else if (user.equals("comp"))
        {
            tochange = 'O'; // Otherwise we will change it to the O
            comppositions.add(mypos);
        }
 
        switch (mypos)
        { // changing the position of our's according to the blocks in the array ;
        case 1:
            boardd[0][0] = tochange;
            break;
        case 2:
            boardd[0][2] = tochange;
            break;
        case 3:
            boardd[0][4] = tochange;
            break;
        case 4:
            boardd[2][0] = tochange;
            break;
        case 5:
            boardd[2][2] = tochange;
            break;
        case 6:
            boardd[2][4] = tochange;
            break;
        case 7:
            boardd[4][0] = tochange;
            break;
        case 8:
            boardd[4][2] = tochange;
            break;
        case 9:
            boardd[4][4] = tochange;
            break;
        }
    }
 
public
    static String checkifwin()
    {
 
        // we will check the winner if it's positions contains any one of the following lists .
 
        List rows1 = Arrays.asList(1, 2, 3);
        List rows2 = Arrays.asList(4, 5, 6);
        List rows3 = Arrays.asList(7, 8, 9);
        List col1 = Arrays.asList(1, 4, 7);
        List col2 = Arrays.asList(2, 5, 8);
        List col3 = Arrays.asList(3, 6, 9);
        List dig1 = Arrays.asList(1, 5, 9);
        List dig2 = Arrays.asList(3, 5, 7);
 
        // now adding them all together in an another list so that we dont have to check individually for each list and we can iterate over all by just using for loop .
 
        List<List> winning = new ArrayList<List>();
        winning.add(rows1);
        winning.add(rows2);
        winning.add(rows3);
        winning.add(col1);
        winning.add(col2);
        winning.add(col3);
        winning.add(dig1);
        winning.add(dig2);
 
        for (List l : winning)
        {
            if (ourpositions.containsAll(l))
            {
                return "Congrats YOU  Won ";
            }
            else if (comppositions.containsAll(l))
            {
                return "Computer Wins !! ";
            }
            else if (ourpositions.size() + comppositions.size() == 9)
            {
                return "It's a DRAW !! ";
            }
        }
        return "";
    }
    //Make a function to draw the tic tac toe board
public
    static void drawBoard(char[][] board)
    {
 
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
 
    //Make a function to see if someone has won and return the winning char
public
    static char playerHasWon(char[][] board)
    {
 
        //Check each row
        for (int i = 0; i < 5; i = i + 2)
        {
            if (board[i][0] == board[i][2] && board[i][2] == board[i][4] && board[i][0] != ' ')
            {
                return board[i][0];
            }
        }
 
        //Check each column
        for (int j = 0; j < 5; j = j + 2)
        {
            if (board[0][j] == board[2][j] && board[2][j] == board[4][j] && board[0][j] != ' ')
            {
                return board[0][j];
            }
        }
 
        //Check the diagonals
        if (board[0][0] == board[2][2] && board[2][2] == board[4][4] && board[0][0] != ' ')
        {
            return board[0][0];
        }
        if (board[4][0] == board[2][2] && board[2][2] == board[0][4] && board[4][0] != ' ')
        {
            return board[4][0];
        }
 
        //Otherwise nobody has not won yet
        return ' ';
    }
 
    //Make a function to check if all of the positions on the board have been filled
public
    static boolean boardIsFull(char[][] board)
    {
        for (int i = 0; i < 5; i = i + 2)
        {
            for (int j = 0; j < 5; j = j + 2)
            {
                if (board[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}
