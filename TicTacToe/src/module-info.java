import java.util.Scanner;
public
class Main
{
public
    static void main(String[] args)
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