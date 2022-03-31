/**
 * This file is to be completed by you.
 *
 * @author <S2106632>
 */
public final class Model {

	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 6 rows and 7 columns.
	public static final int DEFAULT_NR_ROWS = 6;
	public static final int DEFAULT_NR_COLS = 7;
	public static final int DEFAULT_RULE = 4;

	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int rule;
	public int round;
	public String player = "Player1";
	// Here we make the variable player public to make it easy to reset the game
	// More details in report
	private String[][] board = new String[DEFAULT_NR_ROWS][DEFAULT_NR_COLS];
	private int counterUpperRow;
	private int counterDownRow;

	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model() {
		// Initialise the board size to its default values.
		nrRows = DEFAULT_NR_ROWS;
		nrCols = DEFAULT_NR_COLS;
		rule = DEFAULT_RULE;
		round = 1;
		// Initialise the board in case it equals null.
		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				board[i][j] = " ";
			}
		}
	}


	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================
	public void setSize (int r, int c){
		// Ask for the height and length of the board.
		nrRows = r;
		nrCols = c;
		createBoard(nrRows, nrCols);
	}

	public void setRule (int p) {
		// Ask people how many pieces they want to win
			rule = p;
		}

	public void createBoard(int row, int col) {
		// Use a 2D array to represent a grid
		String[][] newBoard = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				newBoard[i][j] = " ";
			}
		}
		board = newBoard;
	}

	public void loadBoard(int row, int col, String[] savedBoard) {
		//load the board saved
		board = new String[row][col];
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j ++) {
				board[i][j] = savedBoard[3 + col * i + j];
			}
		}
	}

	public void setRound(String[][] board) {
		//according to the pieces in the board to decide which round is it
		int count = 1;
		for (int i = 0; i < nrRows; i ++) {
			for (int j = 0; j < nrCols; j ++) {
				if (board[i][j].equals("A") || board[i][j].equals("B")) {
					count ++;
				}
			}
		}
		round += count;
 	}

	public boolean isMoveValid(int move) {
		// Check if the move is doable.
		int count = 0;
		if (move > nrCols || move < 1) {
			return false;
		}
		for (int i = nrRows - 1; i >= 0; i --) {
			if (!board[i][move - 1].equals(" ")) {
				count ++;
			}
			if (count == nrRows) {
				return false;
			}
		}
		return true;
	}

	public void makeMove(int move) {
		if (isMoveValid(move)) {
			if (round % 2 == 1) {
				for (int i = nrRows - 1; i >= 0; i--) {
					if (board[i][move - 1].equals(" ")) {
						board[i][move - 1] = "A";
						round ++;
						break;
					}
				}
				player = "Player2";

			} else if (round % 2 == 0) {
				for (int i = nrRows - 1; i >= 0; i--) {
					if (board[i][move - 1].equals(" ")) {
						board[i][move - 1] = "B";
						round ++;
						break;
					}
				}
				player = "Player1";
			}
		}
	}

	public boolean isFull(String[][] board) {
		//to check if the board is full of pieces
		int size = nrRows * nrCols;
		int count = 1;
		for (int i = 0; i < nrRows; i ++) {
			for (int j = 0; j < nrCols; j ++) {
				if (!board[i][j].equals(" ")) {
					count ++;
				}
				if (count == size) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean rowConnected(String[][] board) {
		if (board[0].length < rule) {
			return false;
		}
		// Check if horizontal is connected.
		for (int i = 0; i < nrRows; i ++) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i][j + r]) && !board[i][j].equals(" ")) {
						count ++;
					}
					if (count == rule) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean colConnected(String[][] board) {
		if (board.length < rule) {
			return false;
		}
		// Check if vertical is connected.
		for (int i = nrRows - 1; i > nrRows - rule; i --) {
			for (int j = 0; j < nrCols; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i - r][j]) && !(board[i][j].equals(" "))) {
						count ++;
					}
					if (count == rule) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean upperDiagonal(String[][] board) {
		// Check if diagonal(from left bottom conner to the top right conner) is connected.
		for (int i = nrRows - 1; i > nrRows - rule; i --) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i - r][j + r]) && !board[i][j].equals(" ")) {
						count ++;
					}
					if (count == rule) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean downDiagonal(String[][] board) {
		// Check if the other diagonal(top left to bottom right) is connected.
		for (int i = 0; i < nrRows - rule + 1; i ++) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i + r][j + r]) && !board[i][j].equals(" ")) {
						count ++;
					}
				}
				if (count == rule) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isWin(String[][] board) {
		// if any player fulfills any of the condition,
		// they win
		return rowConnected(board) || colConnected(board) || upperDiagonal(board) || downDiagonal(board);
	}


	///Counter Move Booleans
	/// Row check player's rule - 1 pieces in a row
	public boolean counterRowNext() {
		if (board[0].length < rule) {
			return false;
		}
		// Check if horizontal is going to be connected.
		for (int i = 0; i < nrRows; i ++) {
			int count = 0;
			for (int j = 0; j < nrCols - rule; j ++) {
				if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals("A")) {
					count ++;
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}

	/// Column check player's rule - 1 pieces in a row
	public boolean counterColNext() {
		if (board.length < rule) {
			return false;
		}
		// Check if vertical is going to be connected.
		for (int j = 0; j < nrCols; j ++) {
			int count = 1;
			for (int i = nrRows - 1; i >= nrRows - rule + 1; i --) {
				if (board[i][j].equals(board[i - 1][j]) && board[i][j].equals("A")) {
					count ++;
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}

	/// Upper diagonal player's rule - 1 pieces in a row
	public boolean counterUpperNext() {
		// Check if diagonal(from left bottom conner to the top right conner) is going to be connected.
		for (int i = nrRows - 1; i > nrRows - rule; i --) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i - r][j + r]) && board[i][j].equals("A")) {
						count ++;
					}
					if (count == rule - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/// Down diagonal player's rule - 1 pieces in a row
	public boolean counterDownNext() {
		// Check if the other diagonal(top left to bottom right) is going to be connected.
		for (int i = 0; i < nrRows - rule + 1; i ++) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i + r][j + r]) && board[i][j].equals("A")) {
						count ++;
					}
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}



	///Wining Move Booleans
	/// Row check AI's rule - 1 pieces in a row
	public boolean winningRowNext() {
		if (board[0].length < rule) {
			return false;
		}
		// Check if horizontal is going to be connected.
		for (int i = 0; i < nrRows; i ++) {
			int count = 0;
			for (int j = 0; j < nrCols - rule; j ++) {
				if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals("B")) {
					count ++;
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}

	/// Column check AI's rule - 1 pieces in a row
	public boolean winningColNext() {
		if (board.length < rule) {
			return false;
		}
		// Check if vertical is going to be connected.
		for (int j = 0; j < nrCols; j ++) {
			int count = 1;
			for (int i = nrRows - 1; i >= nrRows - rule + 1; i --) {
				if (board[i][j].equals(board[i - 1][j]) && board[i][j].equals("B")) {
					count ++;
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}

	/// Upper diagonal AI's rule - 1 pieces in a row
	public boolean winningUpperNext() {
		// Check if diagonal(from left bottom conner to the top right conner) is going to be connected.
		for (int i = nrRows - 1; i > nrRows - rule; i --) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i - r][j + r]) && board[i][j].equals("B")) {
						count ++;
					}
					if (count == rule - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/// Down diagonal AI's rule - 1 pieces in a row
	public boolean winningDownNext() {
		// Check if the other diagonal(top left to bottom right) is going to be connected.
		for (int i = 0; i < nrRows - rule + 1; i ++) {
			for (int j = 0; j < nrCols - rule + 1; j ++) {
				int count = 1;
				for (int r = 1; r < rule; r ++) {
					if (board[i][j].equals(board[i + r][j + r]) && board[i][j].equals("B")) {
						count ++;
					}
				}
				if (count == rule - 1) {
					return true;
				}
			}
		}
		return false;
	}




	//
	//
	//for row counter
	public int getRowRow() {
		if (counterRowNext()) {
			for (int i = 0; i < nrRows; i ++) {
				int count = 0;
				for (int j = 0; j < nrCols - rule; j ++) {
					if (board[i][j].equals(board[i][j + 1]) && !(board[i][j].equals(" "))) {
						count ++;
					}
					if (count == rule - 1) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int getColRow() {
		if (counterRowNext()) {
			for (int i = 0; i < nrRows; i ++) {
				int count = 0;
				for (int j = 0; j < nrCols - rule; j ++) {
					if (board[i][j].equals(board[i][j + 1]) && !(board[i][j].equals(" "))) {
						count ++;
					}
					if (count == rule - 1) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	public int getCounterRow(int a, int b) {
		for (int r = 0; r < rule; r ++) {
			if (board[a][b + r].equals(" ")) {
				return b + r + 1;
			}
		}
		return -1;
	}
	///
	///
	///for column counter
	public int getRowCol() {
		if (counterColNext()) {
			for (int j = 0; j < nrCols; j ++) {
				int count = 1;
				for (int i = nrRows - 1; i >= nrRows - rule + 1; i --) {
					if (board[i][j].equals(board[i - 1][j]) && !(board[i][j].equals(" "))) {
						count ++;
					}
					if (count == rule - 1) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int getColCol() {
		if (counterColNext()) {
			for (int j = 0; j < nrCols; j ++) {
				int count = 1;
				for (int i = nrRows - 1; i >= nrRows - rule + 1; i --) {
					if (board[i][j].equals(board[i - 1][j]) && !(board[i][j].equals(" "))) {
						count ++;
					}
					if (count == rule - 1) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	public int getCounterCol(int a, int b) {
		for (int r = 0; r < rule; r ++) {
			if (board[a - r][b].equals(" ")) {
				return b + 1;
			}
		}
		return -1;
	}

	///
	///
	///for upper diagonal counter
	public int getRowUpper() {
		if (counterUpperNext()) {
			for (int i = nrRows - 1; i > nrRows - rule; i --) {
				for (int j = 0; j < nrCols - rule + 1; j ++) {
					int count = 1;
					for (int r = 1; r < rule; r ++) {
						if (board[i][j].equals(board[i - r][j + r]) && !board[i][j].equals(" ")) {
							count ++;
						}
						if (count == rule - 1) {
							return i;
						}
					}
				}
			}
		}
		return -1;
	}

	public int getColUpper() {
		if (counterUpperNext()) {
			for (int i = nrRows - 1; i > nrRows - rule; i --) {
				for (int j = 0; j < nrCols - rule + 1; j ++) {
					int count = 1;
					for (int r = 1; r < rule; r ++) {
						if (board[i][j].equals(board[i - r][j + r]) && !board[i][j].equals(" ")) {
							count ++;
						}
						if (count == rule - 1) {
							return j;
						}
					}
				}
			}
		}
		return -1;
	}

	public int getCounterUpper(int a, int b) {
		for (int r = 0; r < rule; r ++) {
			if (board[a - r][b + r].equals(" ")) {
				counterUpperRow = a - r;
				return b + r + 1;
			}
		}
		return -1;
	}

	///
	///
	///for down diagonal counter
	public int getRowDown() {
		if (counterDownNext()) {
			for (int i = 0; i < nrRows - rule + 1; i ++) {
				for (int j = 0; j < nrCols - rule + 1; j ++) {
					int count = 1;
					for (int r = 1; r < rule; r ++) {
						if (board[i][j].equals(board[i + r][j + r]) && !board[i][j].equals(" ")) {
							count ++;
						}
					}
					if (count == rule - 1) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int getColDown() {
		if (counterDownNext()) {
			for (int i = 0; i < nrRows - rule + 1; i ++) {
				for (int j = 0; j < nrCols - rule + 1; j ++) {
					int count = 1;
					for (int r = 1; r < rule; r ++) {
						if (board[i][j].equals(board[i + r][j + r]) && !board[i][j].equals(" ")) {
							count ++;
						}
					}
					if (count == rule - 1) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	public int getCounterDown(int a, int b) {
		for (int r = 0; r < rule; r ++) {
			if (board[a + r][b + r].equals(" ")) {
				counterDownRow = a + r;
				return b + r + 1;
			}
		}
		return -1;
	}

	public int getCounterUpperRow() {
		return counterUpperRow;
	}

	public int getCounterDownRow() {
		return counterDownRow;
	}

	public void aiMove() {
		if (counterRowNext() && getRowRow() % 2 == 0) {
			if (getCounterRow(getRowRow(), getColRow()) != -1) {
				makeMove(getCounterRow(getRowRow(), getColRow()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (counterColNext()) {
			if (getCounterCol(getRowCol(), getColCol()) != -1) {
				makeMove(getCounterCol(getRowCol(), getColCol()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (counterUpperNext() && getCounterUpperRow() % 2 == 0) {
			if (getCounterUpper(getRowUpper(), getColUpper()) != -1) {
				makeMove(getCounterUpper(getRowUpper(), getColUpper()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (counterDownNext() && getCounterDownRow() % 2 == 0) {
			if (getCounterDown(getRowDown(), getColDown()) != -1) {
				makeMove(getCounterDown(getRowDown(), getColDown()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (winningRowNext() && getRowRow() % 2 == 0) {
			if (getCounterRow(getRowRow(), getColRow()) != -1) {
				makeMove(getCounterRow(getRowRow(), getColRow()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (winningColNext()) {
			if (getCounterCol(getRowCol(), getColCol()) != -1) {
				makeMove(getCounterCol(getRowCol(), getColCol()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (winningUpperNext() && getCounterUpperRow() % 2 == 0) {
			if (getCounterUpper(getRowUpper(), getColUpper()) != -1) {
				makeMove(getCounterUpper(getRowUpper(), getColUpper()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else if (winningDownNext() && getCounterDownRow() % 2 == 0) {
			if (getCounterDown(getRowDown(), getColDown()) != -1) {
				makeMove(getCounterDown(getRowDown(), getColDown()));
			}
			else {
				makeMove((int) (Math.random() * getNrCols()));
			}
		}
		else {
			makeMove((int) (Math.random() * getNrCols()));
		}
	}

	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================
	public int getNrRows() {
		return nrRows;
	}

	public int getNrCols() {
		return nrCols;
	}

	public String[][] getBoard() {
		return board;
	}

	public String getPlayer() {
		return player;
	}

	public int getRound() {
		return round;
	}

	public int getRule() {
		return rule;
	}
}

