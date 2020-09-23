package tictac;

import java.util.Scanner;

import tictac.board.Board;

public class App {
    public static void main(String[] args) {
        Board board = new Board();

        gameLoop(board);
    }
    
    private static void gameLoop(Board board) {
        Boolean complete = false;
        Scanner scanner = new Scanner(System.in);
        Boolean player = true;

        while (!complete) {
            board.Print();
            
            System.out.print("What's your move [1-9]: ");
            int move = scanner.nextInt();

            if (!board.Insert(move, player)) {
                System.out.println("Invalid Move");
                continue;
            }


            if (board.CheckWin()) {
                board.Print();
                System.out.println(player ? "X wins!" : "O wins!");
                break;
            }

            player = !player;
        }

        scanner.close();
    }
}
