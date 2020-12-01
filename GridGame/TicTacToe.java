public class TicTacToe {
	private boolean gameOver; // flag to record if the game is over
	private boolean bigGameOver;
	private int playerTurn; // whose turn it is
	private int winner; // who the winner is (0 if no winner)
	private int cols, rows; // # of rows and cols in game
	private int[][] grid; // the grid that stores the pieces
	int preR;
	int preC;
	int prePlayerTurn;
	int whichBigSquare;
	int[][] overallGrid = new int[3][3];
	int firstMove = 0;

	// The constructor initializes the game
	public TicTacToe(int r, int c) {
		// Create the board
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];

		playerTurn = 1;
		// Set that the game is not over
		gameOver = false;
		bigGameOver = false;
	}

	public boolean isValidMove(int r, int c) {
		if (isInGrid(r, c) == false) // if outside grid, not valid
			return false;

		// reg not pre game its supposed to be in is whichBigSquare but its
		// currently in the inequalities

		if (firstMove == 0) {
			firstMove = 1;
			return true;
		} else if (r < 3 && c < 3 && whichBigSquare == 0) {
			return true;
		} else if (r < 3 && c > 2 && c < 6 && whichBigSquare == 1) {
			return true;
		} else if (r < 3 && c > 5 && c < 9 && whichBigSquare == 2) {
			return true;
		} else if (r > 2 && r < 6 && c < 3 && whichBigSquare == 3) {
			return true;
		} else if (r > 2 && r < 6 && c > 2 && c < 6 && whichBigSquare == 4) {
			return true;
		} else if (r > 2 && r < 6 && c > 5 && whichBigSquare == 5) {
			return true;
		} else if (r > 5 && c < 3 && whichBigSquare == 6) {
			return true;
		} else if (r > 5 && c > 2 && c < 6 && whichBigSquare == 7) {
			return true;
		} else if (r > 5 && c > 5 && whichBigSquare == 8) {
			return true;
		} else {
			System.out.println("Not a valid move");
			return false;
		}
	}

	public boolean overallDetermination(int r, int c) {
		return true;
	}

	public int relativeRow(int r) {

		if (r == 0 || r == 3 || r == 6) {
			return 0;
		}

		if (r == 1 || r == 4 || r == 7) {
			return 1;
		}

		if (r == 2 || r == 5 || r == 8) {
			return 2;
		}
		return 100;
	}

	public int relativeCol(int c) {

		if (c == 0 || c == 3 || c == 6) {
			return 0;
		}

		if (c == 1 || c == 4 || c == 7) {
			return 1;
		}

		if (c == 2 || c == 5 || c == 8) {
			return 2;
		}
		return 100;
	}

	// tells us what game are you in
	public int currentGame(int r, int c) {
		if (r < 3 && c < 3) {
			return 0;
		} else if (r < 3 && c > 2 && c < 6) {
			return 1;
		} else if (r < 3 && c > 5 && c < 9) {
			return 2;
		} else if (r > 2 && r < 6 && c > 3) {
			return 3;
		} else if (r > 2 && r < 6 && c > 2 && c < 6) {
			return 4;
		} else if (r > 2 && r < 6 && c > 5) {
			return 5;
		} else if (r > 5 && c < 3) {
			return 6;
		} else if (r > 5 && c > 2 && c < 6) {
			return 7;
		} else if (r > 5 && c > 5) {
			return 8;
		}
		return 10;
	}

	public boolean checkForWinner(int[][] grid, int sr, int sc) {
		// horizontal
		for (int rows = sr; rows < (3 + sr); rows++) {
			int col = sc;
			if (grid[rows][col] == grid[rows][col + 1] && grid[rows][col] == grid[rows][col + 2]
					&& grid[rows][col] != 0) {
				return true;
			}
		}

		// vertical
		for (int col = sc; col < (3 + sc); col++) {
			int rows = sr;
			if (grid[rows][col] == grid[rows + 1][col] && grid[rows][col] == grid[rows + 2][col]
					&& grid[rows][col] != 0) {
				return true;
			}
		}

		// diagonal (negative slope)
		if (grid[sr][sc] == grid[sr + 1][sc + 1] && grid[sr][sc] == grid[sr + 2][sc + 2] && grid[sr][sc] != 0) {
			return true;
		}

		// diagonal (positive slope
		if (grid[sr][sc + 2] == grid[sr + 1][sc + 1] && grid[sr][sc + 2] == grid[sr + 2][sc] && grid[sr][sc + 2] != 0) {
			return true;
		}
		return false;
	}

	public boolean bigCheckForWinner(int[][] overallGrid) {
		// horizontal
		for (int rows = 0; rows < 3; rows++) {
			int col = 0;
			if (overallGrid[rows][col] == overallGrid[rows][col + 1]
					&& overallGrid[rows][col] == overallGrid[rows][col + 2] && overallGrid[rows][col] != 0) {
				return true;
			}
		}

		// vertical
		for (int col = 0; col < 3; col++) {
			int rows = 0;
			if (overallGrid[rows][col] == overallGrid[rows + 1][col]
					&& overallGrid[rows][col] == overallGrid[rows + 2][col] && overallGrid[rows][col] != 0) {
				return true;
			}
		}

		// diagonal (negative slope)
		if (overallGrid[0][0] == overallGrid[1][1] && overallGrid[0][0] == overallGrid[2][2]
				&& overallGrid[0][0] != 0) {
			return true;
		}

		// diagonal (positive slope
		if (overallGrid[0][2] == overallGrid[1][1] && overallGrid[0][2] == overallGrid[2][0]
				&& overallGrid[0][2] != 0) {
			return true;
		}
		return false;
	}

	public boolean isInGrid(int row, int col) {
		/* you create this method */

		if (col > 9) {
			System.out.println("out of bounds");
			return false;
		}

		if (col < 0) {
			System.out.println("out of bounds");
			return false;
		}

		if (row > 9) {
			System.out.println("out of bounds");
			return false;
		}

		if (row < 0) {
			System.out.println("out of bounds");
			return false;
		}

		return true;
	}

	public boolean isInGrid(Location1 l) {
		return isInGrid(l.getRow(), l.getCol());
	}

	// makes the move
	// returns false if no move was made, true if the move was successful.
	public boolean move(int r, int c) {
		if (isValidMove(r, c) == false)
			return false; // if not valid, exit
		if (gameOver == true)
			return false; // if game is over, exit

		/* Make the move by changing what you need to change in grid */
		piecePlaced(grid, r, c, playerTurn);

		if (playerTurn == 1) {
			System.out.println("It is now Player 2's turn ");
			playerTurn = 2;
			prePlayerTurn = 1;
		} else {
			playerTurn = 1;
			prePlayerTurn = 2;
			System.out.println("It is now Player 1's turn");
		}

		// reg not pre
		if (firstMove == 0) {
			// whichBigSquare = -1;
			return true;
		}

		if (relativeRow(r) == 0 && relativeCol(c) == 0) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 0!");
			whichBigSquare = 0;
		} else if (relativeRow(r) == 0 && relativeCol(c) == 1) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 1!");
			whichBigSquare = 1;
		} else if (relativeRow(r) == 0 && relativeCol(c) == 2) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 2!");
			whichBigSquare = 2;
		} else if (relativeRow(r) == 1 && relativeCol(c) == 0) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 3!");
			whichBigSquare = 3;
		} else if (relativeRow(r) == 1 && relativeCol(c) == 1) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 4!");
			whichBigSquare = 4;
		} else if (relativeRow(r) == 1 && relativeCol(c) == 2) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 5!");
			whichBigSquare = 5;
		} else if (relativeRow(r) == 2 && relativeCol(c) == 0) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 6!");
			whichBigSquare = 6;
		} else if (relativeRow(r) == 2 && relativeCol(c) == 1) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 7!");
			whichBigSquare = 7;
		} else if (relativeRow(r) == 2 && relativeCol(c) == 2) {
			piecePlaced(grid, r, c, playerTurn);
			System.out.println("Next move should be in Game 8!");
			whichBigSquare = 8;
		}
		gameOver = false;
		gameOver = checkForWinner(grid, 0, 0);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 0");
			overallGrid[0][0] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 0, 1);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 1");
			overallGrid[0][1] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 0, 2);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 2");
			overallGrid[0][2] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 1, 0);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 3");
			overallGrid[1][0] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 1, 1);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a a small game 4");
			overallGrid[1][1] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 1, 2);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 5 ");
			overallGrid[1][2] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 2, 0);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 6 ");
			overallGrid[2][0] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 2, 1);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 7 ");
			overallGrid[2][1] = prePlayerTurn;
		}
		gameOver = checkForWinner(grid, 2, 2);
		if (gameOver == true) {
			System.out.println("YAY! player " + prePlayerTurn + " won a small game 8 ");
			overallGrid[2][2] = prePlayerTurn;
		}

		bigGameOver = bigCheckForWinner(overallGrid);
		if (bigGameOver == true) {
			System.out.println("Hurray!! player " + prePlayerTurn + " won the match!!! ");
			return false;
		}
		preC = c;
		preR = r;
		firstMove = firstMove + 1;
		return true; // this means the move was successfully made
	}

	public void piecePlaced(int[][] grid, int r, int c, int playerTurn) {
		if (grid[r][c] == 0) {
			grid[r][c] = playerTurn;
			System.out.println("Player " + prePlayerTurn + " has placed a piece @ " + "row: " + r + ", col: " + c);
		}
		return;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int[][] getGrid() {
		return grid;
	}
}