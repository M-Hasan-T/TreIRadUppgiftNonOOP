import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> firstPlayerLine = new ArrayList<Integer>();
    static ArrayList<Integer> secondPlayerLine = new ArrayList<Integer>();

    public static void main(String[] args) {

        // Create an empty gameboard
        char[][] board = {
                {' ', '|', ' ', '|', ' '},
                {'-', '-', '-', '-', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '-', '-', '-', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        initializeBoard(board);

        String player = "X";
        while (true) {
            int movePosition = checkInput();
            while (firstPlayerLine.contains(movePosition) || secondPlayerLine.contains(movePosition) || secondPlayerLine.contains(firstPlayerLine) || secondPlayerLine.contains(secondPlayerLine)) {
                System.out.println("Try an empty cell!");
                movePosition = checkInput();
            }

            fillCell(board, movePosition, player);
            player = player.equals("X") ? "O" : "X";
            System.out.print(checkResult());
            if (checkResult().length() > 0) break;
            initializeBoard(board);

        }
    }

    //Initialize the gameboard for the first time
    public static void initializeBoard(char[][] gameBoard) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static char fillCell(char[][] board, int movePosition, String player) {
        char ch;
        if (player.equals("X")) {
            ch = 'X';
            firstPlayerLine.add(movePosition);
        } else {
            ch = 'O';
            secondPlayerLine.add(movePosition);
        }

        switch (movePosition) {
            case 1:
                board[0][0] = ch;
                break;
            case 2:
                board[0][2] = ch;
                break;
            case 3:
                board[0][4] = ch;
                break;
            case 4:
                board[2][0] = ch;
                break;
            case 5:
                board[2][2] = ch;
                break;
            case 6:
                board[2][4] = ch;
                break;
            case 7:
                board[4][0] = ch;
                break;
            case 8:
                board[4][2] = ch;
                break;
            case 9:
                board[4][4] = ch;
                break;
            default:
                break;
        }
        return ch;
    }

    public static String checkResult() {
        List<Integer> firstRow = Arrays.asList(1, 2, 3);
        List<Integer> secondRow = Arrays.asList(4, 5, 6);
        List<Integer> thirdRow = Arrays.asList(7, 8, 9);
        List<Integer> firstColumn = Arrays.asList(1, 4, 7);
        List<Integer> secondColumn = Arrays.asList(2, 5, 8);
        List<Integer> thirdColumn = Arrays.asList(3, 6, 9);
        List<Integer> firstCross = Arrays.asList(3, 5, 7);
        List<Integer> secondCross = Arrays.asList(1, 5, 9);

        List<List<Integer>> listOfWinConditions = new ArrayList<List<Integer>>();
        listOfWinConditions.add(firstRow);
        listOfWinConditions.add(secondRow);
        listOfWinConditions.add(thirdRow);
        listOfWinConditions.add(firstColumn);
        listOfWinConditions.add(secondColumn);
        listOfWinConditions.add(thirdColumn);
        listOfWinConditions.add(firstCross);
        listOfWinConditions.add(secondCross);

        for (List winList : listOfWinConditions
        ) {
            if (firstPlayerLine.containsAll(winList)) {
                return "First player wins!";
            } else if (secondPlayerLine.containsAll(winList)) {
                return "Second player wins!";
            } else if (firstPlayerLine.size() + secondPlayerLine.size() == 9) {
                return "Draw!";
            }
        }
        return "";
    }

    public static int checkInput() {
        Scanner scan = new Scanner(System.in);
        int movePosition;
        do {
            System.out.print("Enter a number between 1 to 9 to make your movement: ");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number!");
                scan.next();
            }
            movePosition = scan.nextInt();
        } while (movePosition < 1 || movePosition > 9);
        return movePosition;
    }
}