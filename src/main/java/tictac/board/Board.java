package tictac.board;

public class Board {
	public static final int SIZE = 3;
	private int[][] board;

	public Board() {
		board = CreateBoard();
	}
	
	private int[][] CreateBoard() {
		int[][] matrix = new int[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int f = 0; f < SIZE; f++) {
				matrix[i][f] = 0;
			}
		}
		
		return matrix;
	}

	public void Print() {
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (c != 0) {
					System.out.print(" |");
				}

				System.out.print(" ");

				if (board[r][c] == 0) {
					System.out.print(" ");
				} else {
					System.out.print((board[r][c] == 1) ? 'x' : 'o');
				}
			}
			if (r != 2) {
				System.out.println("");
				System.out.print(" ");
				System.out.println("-".repeat(9));
			}
		}
		System.out.println("");
	}

	public Boolean Insert(int pos, Boolean player) {
		if (pos > (SIZE * SIZE)) return false;

		pos--;

		int r = pos / SIZE;
		int c = pos % SIZE;

		if (board[r][c] != 0) return false;

		board[r][c] = player ? 1 : 2;
		return true;
	}

	public Boolean CheckWin() {
		return this.CheckRow() || this.CheckCol() || this.CheckDiag();
	}

	private Boolean CheckRow() {
		Boolean winner = false;

		for (int r = 0; r < SIZE; r++) {
			int curr = board[r][0];
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] != curr || board[r][c] == 0) break;
				if (c == SIZE-1) winner = true;
			}
		}

		return winner;
	}

	private Boolean CheckCol() {
		Boolean winner = false;

		for (int c = 0; c < SIZE; c++) {
			int curr = board[0][c];
			for (int r = 0; r < SIZE; r++) {
				if (board[r][c] != curr || board[r][c] == 0) break;
				if (r == SIZE-1) winner = true;
			}
		}

		return winner;
	}

	private Boolean CheckDiag() {
		Boolean winner = false;

		int curr = board[0][0];
		for (int i = 0; i < SIZE; i++) {
			if (board[i][i] != curr || board[i][i] == 0) break;
			if (i == SIZE-1) winner = true;
		}

		if (winner) return winner;

		curr = board[0][SIZE-1];

		for (int i = 0; i < SIZE; i++) {
			if (board[i][SIZE-i-1] != curr || board[i][SIZE-i-1] == 0) break;
			if (i == SIZE-1) winner = true;
		}

		return winner;
	}
}