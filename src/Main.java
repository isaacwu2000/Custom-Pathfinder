import java.util.ArrayList;
import java.util.Arrays;

/*  Citation: I used the Google AI Search Overview
    and W3 Schools occasionally for syntax.
    - Isaac Wu, August 8th, 2025 */

// Number correspondence:
// 8↖ 7↑ 5↗
// 6←    3→
// 4↙ 2↓ 1↘
// Lower numbers are done first because they are more likely to be optimal

public class Main {
    private static int n = 3;
    private static int[][] board = new int[n][n];
    private static ArrayList<Integer> cheapestTraversion = new ArrayList<Integer>();
    private static int minCost;

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {board[i][j] = (int)(101*Math.random());}}
    }
    // todo: check if the move lands on a place you've already gone to
    // todo: only do moves that are possible
    private static void TraverseBoard(int cost, int right, int down, ArrayList<Integer> previousMoves) {
        if (cost < minCost) {
            ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone();
            if (right==n-1 && down==n-1) { // Bottom right corner (path complete)
                cheapestTraversion = moves;
                minCost = cost;
            } else if (right==0 && down==0){ // Top left corner
                moves.add(1); // ↘
                TraverseBoard(cost+board[down+1][right+1], right+1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
            } else if (right==0 && down==n-1) { // Bottom left corner
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(5); // ↗
                TraverseBoard(cost+board[down-1][right+1], right+1, down-1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(7); // ↑
                TraverseBoard(cost+board[down-1][right], right, down-1, moves);
            } else if (right==n-1 && down == 0) { // Top right corner
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(4); // ↙
                TraverseBoard(cost+board[down+1][right-1], right-1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(6); // ←
                TraverseBoard(cost+board[down][right-1], right-1, down, moves);
            } else if (down==n-1) { // Bottom edge
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(5); // ↗
                TraverseBoard(cost+board[down-1][right+1], right+1, down-1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(6); // ←
                TraverseBoard(cost+board[down][right-1], right-1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(7); // ↑
                TraverseBoard(cost+board[down-1][right], right, down-1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(8); // ↖
                TraverseBoard(cost+board[down-1][right-1], right-1, down-1, moves);
            } else if (right==n-1) { // Right edge
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(4); // ↙
                TraverseBoard(cost+board[down+1][right-1], right-1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(6); // ←
                TraverseBoard(cost+board[down][right-1], right-1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(7); // ↑
                TraverseBoard(cost+board[down-1][right], right, down-1, moves);
            } else if (right==0) { // Left edge
                moves.add(1); // ↘
                TraverseBoard(cost+board[down+1][right+1], right+1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(5); // ↗
                TraverseBoard(cost+board[down-1][right+1], right+1, down-1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(7); // ↑
                TraverseBoard(cost+board[down-1][right], right, down-1, moves);
            } else if (down==0) { // Top edge
                moves.add(1); // ↘
                TraverseBoard(cost+board[down+1][right+1], right+1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(4); // ↙
                TraverseBoard(cost+board[down+1][right-1], right-1, down+1, moves);
                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(6); // ←
                TraverseBoard(cost+board[down][right-1], right-1, down, moves);
            } else { // Not along any edge
                moves.add(1); // ↘
                TraverseBoard(cost+board[down+1][right+1], right+1, down+1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(2); // ↓
                TraverseBoard(cost+board[down+1][right], right, down+1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(3); // →
                TraverseBoard(cost+board[down][right+1], right+1, down, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(4); // ↙
                TraverseBoard(cost+board[down+1][right-1], right-1, down+1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(5); // ↗
                TraverseBoard(cost+board[down-1][right+1], right+1, down-1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(6); // ←
                TraverseBoard(cost+board[down][right-1], right-1, down, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(7); // ↑
                TraverseBoard(cost+board[down-1][right], right, down-1, moves);

                moves = (ArrayList<Integer>) previousMoves.clone();
                moves.add(8); // ↖
                TraverseBoard(cost+board[down-1][right-1], right-1, down-1, moves);
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