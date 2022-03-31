/**
 * This file is to be completed by you.
 *
 * @author <S2106632>
 */
public final class TextView
{
	public TextView()
	{
	
	}
	
	public final void displayNewGameMessage()
	{
		System.out.println("---- NEW GAME STARTED ----" + "\n" + "Welcome to connectX");
	}

	public final void displayAIGameMessage() {
		System.out.println("Welcome to AI mode" + "\n" + "---- NEW GAME Settled ----");
	}
	
	public final int askForMove(Model model)
	{
		System.out.print("Select a free column: ");
		int col = InputUtil.readIntFromUser();
		if (!model.isMoveValid(col)) {
			System.err.print("Invalid Move!" + "\n" + "Choose another");
			return askForMove(model);
		}
		return col;
	}

	public int askForSave() {
		System.out.print("Do you wanna save the game here? If yes, enter1, if no, enter 2");
		int save = InputUtil.readIntFromUser();
		if (save != 1 && save != 2) {
			System.err.print("You should choose between 1 and 2");
			return askForSave();
		}
		return save;
	}

	public int askForLoad() {
		System.out.print("Any game you wanna load? If yes, enter 1, if no, enter 2");
		int load = InputUtil.readIntFromUser();
		if (load != 1 && load != 2) {
			System.err.print("You should choose between 1 and 2");
			return askForLoad();
		}
		return load;
	}

	public String askForFileName() {
		System.out.print("What is your game name?");
		return InputUtil.readStringFromUser();
	}

	public void player2WinMessage() {
		System.out.println("Player2 wins");
	}

	public void player1WinMessage() {
		System.out.println("Player1 wins");
	}

	public void playerWinMessage() {
		System.out.print("Congrats! You beat the stupid AI!");
	}

	public void aiWinMessage() {
		System.out.print("Sorry... Try next time to beat AI!");
	}

	public int askForConcede() {
		System.out.print("Do you wanna concede? If yes, enter 1, if no, enter 2");
		int decision = InputUtil.readIntFromUser();
		if (decision != 1 && decision != 2) {
			System.err.print("You should choose between 1 and 2");
			return askForConcede();
		}
		return decision;
	}

	public int askForRestart() {
		System.out.print("Do you wanna play again? If yes, enter 1, if no, enter 2");
		int decision = InputUtil.readIntFromUser();
		if (decision != 1 && decision != 2) {
			System.err.print("You should choose between 1 and 2");
			return askForRestart();
		}
		return decision;
	}

	public void tieMessage() {
		System.out.println("Tie! No one wins!");
	}

	public void endMessage() {
		System.out.println("Thanks for your play!");
	}

	public int askForRowSize() {
		System.out.print("How long do you want your rows to be?");
		int row = InputUtil.readIntFromUser();
		if (row <= 1) {
			System.err.print("The length of row must be bigger than 1" + "\n" + "Choose another!");
			return askForRowSize();
		}
		return row;
	}

	public int askForColmSize() {
		System.out.print("How long do you want your columns to be?");
		int column = InputUtil.readIntFromUser();
		if (column <= 1) {
			System.err.print("The length of column must be bigger than 1" + "\n" + "Choose another!");
			return askForRowSize();
		}
		return column;
	}

	public int askForRule(Model model) {
		System.out.print("How many pieces do you wish to win?");
		int rule = InputUtil.readIntFromUser();
		if (rule > model.getNrRows() && rule > model.getNrCols()) {
			System.err.print("The goal must be smaller than the length of one of the sides of the board" + "\n" + "Choose another!");
			return askForRule(model);
		}
		else if (rule <= 1) {
			System.err.print("Invalid choice, please choose any number bigger than 1!");
			return askForRule(model);
		}
		return rule;
	}

	public int askForMode() {
		System.out.print("Do you wanna play against Stupid AI? If yes, enter 1. If no, enter 2.");
		int mode = InputUtil.readIntFromUser();
		if (mode != 1 && mode != 2) {
			System.err.print("Choose between 1 and 2");
			askForMode();
		}
		return mode;
	}

	
	public final void displayBoard(Model model)
	{
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		String [][] board = model.getBoard();
		// Get your board representation.
		
		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(4 * nrCols + 1);

		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();
		
		// You can add to the String using its append method.
		for (int i = 0; i < nrRows; i ++) {
			sb.append(rowDivider);
			sb.append("\n");
			for (int c = 0; c < nrCols; c ++) {
				sb.append("| ");
				sb.append(board[i][c]);
				sb.append(" ");
			}
			sb.append("|");
			sb.append("\n");
		}
		sb.append(rowDivider);
		sb.append("\n");
		if (model.getRound() % 2 == 0) {
			sb.append("Player2").append("'s turn");
		}
		else {
			sb.append("Player1").append("'s turn");
		}
		//sb.append(model.getPlayer()).append("'s turn");
		
		// Then print out the assembled String.
		System.out.println(sb);
	}
}
