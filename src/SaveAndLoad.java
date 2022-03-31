import java.io.*;
import java.util.Arrays;

public class SaveAndLoad {
    public SaveAndLoad(){
    }


    public void saveGame(String name, int rows, int cols, String[][] board, int rule) {
        try (FileWriter savedGame = new FileWriter(name + ".txt")) {
            savedGame.write(String.valueOf(rows));
            savedGame.write("\n");
            savedGame.write(String.valueOf(cols));
            savedGame.write("\n");
            savedGame.write(String.valueOf(rule));
            savedGame.write("\n");
            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < cols; j ++) {
                    savedGame.write(board[i][j]);
                    savedGame.write("\n");
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] loadGame(String name) throws IOException {
        try (BufferedReader loadedGame = new BufferedReader(new FileReader(name + ".txt"))) {
            String data;
            int row = Integer.parseInt(loadedGame.readLine());
            int col = Integer.parseInt(loadedGame.readLine());
            int rule = Integer.parseInt(loadedGame.readLine());
            String[] dataArray = new String[row * col + 3];
            dataArray[0] = String.valueOf(row);
            dataArray[1] = String.valueOf(col);
            dataArray[2] = String.valueOf(rule);
            for (int i = 3; i < row * col + 3; i ++) {
                data = loadedGame.readLine();
                dataArray[i] = data;
            }
            return dataArray;
        }
    }
}
