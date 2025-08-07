import java.util.ArrayList;
import java.util.Arrays;

/* Citation: I used the Google AI Search Overview occasionally for syntax. - Isaac Wu, August 6th, 2025 */
// Note: 1=right, 2=down, 3=diagonal
public class Main {
    private static int n = 4;
    private static int[][] board = new int[n][n];
    private static ArrayList<Integer> cheapestTraversion = new ArrayList<Integer>();
    private static int minCost;

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {board[i][j] = (int)(101*Math.random());}}
    }

    private static void TraverseBoard(int cost, int right, int down, ArrayList<Integer> previousMoves) {
        if (cost < minCost) {
            if (right==n-1 && down==n-1) {
                cheapestTraversion = (ArrayList<Integer>) previousMoves.clone();
                minCost = cost;
            } else if (right == n-1) {
                ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2);
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
            } else if (down == n-1) {
                ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(1);
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
            } else {
                ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // Doing diagonal first, which will be closer to the average cheapest path
                TraverseBoard(cost+board[down+1][right+1], right+1, down+1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2);
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(1);
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
            }
        }
    }

    public static void main(String[] args) {
        FillBoard();
        for (int[] row : board) {System.out.println(Arrays.toString(row));}
        // Initializing minCost and cheapestTraversion to be that of the diagonal (shortest path)
        minCost = board[0][0];
        for (int i=1; i<n; i++) {
            minCost += board[i][i];
            cheapestTraversion.add(3);
        }

        TraverseBoard(board[0][0], 0, 0, new ArrayList<Integer>());
        System.out.println("\nMin Cost: " + minCost);
        System.out.println("Cheapest Traversion: " + cheapestTraversion);
    }
}