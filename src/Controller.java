import java.io.IOException;

/**
 * This file is to be completed by you.
 *
 * @author <S2106632>
 */
public final class Controller {
	private final Model model;
	private final TextView view;
	private final SaveAndLoad saveAndLoad;

	public Controller(Model model, TextView view, SaveAndLoad saveAndLoad) {
		this.model = model;
		this.view = view;
		this.saveAndLoad = saveAndLoad;
	}

	public Boolean game = true;
	public void startSession() throws IOException {
		model.round = 1;
		model.player = "Player1";
		if (view.askForMode() == 2) {
			if (view.askForLoad() == 1) {
				String[] loadedGame = saveAndLoad.loadGame(view.askForFileName());
				model.setSize(Integer.parseInt(loadedGame[0]), Integer.parseInt(loadedGame[1]));
				model.loadBoard(Integer.parseInt(loadedGame[0]), Integer.parseInt(loadedGame[1]), loadedGame);
				model.setRule(Integer.parseInt(loadedGame[2]));
				model.setRound(model.getBoard());
			}
			else {
				model.setSize(view.askForRowSize(), view.askForColmSize());
				model.setRule(view.askForRule(model));
				view.displayNewGameMessage();
			}
			view.displayBoard(model);
			while (game) {
				if (model.round % 2 == 0) {
					model.makeMove(view.askForMove(model));
					if (view.askForConcede() == 1) {
						view.player1WinMessage();
						game = false;
					}
					if (view.askForSave() == 1) {
						int row = model.getNrRows();
						int col = model.getNrCols();
						String[][] board = model.getBoard();
						int rule = model.getRule();
						saveAndLoad.saveGame(view.askForFileName(),
								row,
								col,
								board,
								rule);
						view.endMessage();
						game = false;
					}
					view.displayBoard(model);
				}
				else if (model.round % 2 == 1) {
					model.makeMove(view.askForMove(model));
					if (view.askForConcede() == 1) {
						view.player2WinMessage();
						game = false;
					}
					if (view.askForSave() == 1) {
						int row = model.getNrRows();
						int col = model.getNrCols();
						String[][] board = model.getBoard();
						int rule = model.getRule();
						saveAndLoad.saveGame(view.askForFileName(),
								row,
								col,
								board,
								rule);
						view.endMessage();
						game = false;
					}
					view.displayBoard(model);
				}
				if (model.isWin(model.getBoard())) {
					if (model.getPlayer().equals("Player1")) {
						view.player2WinMessage();
					}
					if (model.getPlayer().equals("Player2")) {
						view.player1WinMessage();
					}
					game = false;
				}
				if (model.isFull(model.getBoard())) {
					view.tieMessage();
					game = false;
				}
				if (!game) {
					if (view.askForRestart() == 1) {
						model.round = 0;
						game = true;
						model.player = "Player1";
						startSession();
					}
					else {
						view.endMessage();
					}
				}
			}
		}
		else {
			model.setSize(view.askForRowSize(), view.askForColmSize());
			model.setRule(view.askForRule(model));
			view.displayAIGameMessage();
			view.displayBoard(model);
			while (game) {
				if (model.round % 2 == 1) {
					model.makeMove(view.askForMove(model));
					if (view.askForConcede() == 1) {
						view.player2WinMessage();
						game = false;
					}
					if (view.askForSave() == 1) {
						int row = model.getNrRows();
						int col = model.getNrCols();
						String[][] board = model.getBoard();
						int rule = model.getRule();
						saveAndLoad.saveGame(view.askForFileName(),
								row,
								col,
								board,
								rule);
						view.endMessage();
						game = false;
					}
					view.displayBoard(model);
				}
				else if (model.round % 2 == 0) {
					model.aiMove();
					view.displayBoard(model);
				}
				if (model.isWin(model.getBoard())) {
					if (model.getPlayer().equals("Player1")) {
						view.aiWinMessage();
					}
					if (model.getPlayer().equals("Player2")) {
						view.playerWinMessage();
					}
					game = false;
				}
				if (model.isFull(model.getBoard())) {
					view.tieMessage();
					game = false;
				}
				if (!game) {
					if (view.askForRestart() == 1) {
						model.round = 0;
						game = true;
						model.player = "Player1";
						startSession();
					}
					else {
						view.endMessage();
					}
				}
			}
		}

	}
}
