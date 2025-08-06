import java.util.Arrays;
import java.util.ArrayList;
// 1=right, 2=up, 3=diagonal

public class Main {
    private static int n = 3;
    private static int[][] board = new int[n][n];

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {board[i][j] = (int)(101*Math.random());}}
    }

    private static int[] getPosition(ArrayList<Integer> moves) {
        int right = 0; int up = 0;
        for (int move : moves) {
            if (move==1) {right+=1;}
            else if (move==2) {up+=1;}
            else {right+=1; up+=1;}
        }
        int[] position = {right, up};
        return position;
    }

    private static void TraverseBoard(ArrayList<Integer> previousMoves) {
        int[] currentPosition = getPosition(previousMoves);
        if (currentPosition[0]==n && currentPosition[1]==n) {
            System.out.println(previousMoves);
        }
        else if (currentPosition[0]==n) {

        ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone();
        moves.add(1);
        TraverseBoard(moves);

        moves = (ArrayList<Integer>) previousMoves.clone();
        moves.add(2);
        TraverseBoard(moves);

        moves = (ArrayList<Integer>) previousMoves.clone();
        moves.add(3);
        TraverseBoard(moves);

    }

    public static void main(String[] args) {
        FillBoard();

    }
}