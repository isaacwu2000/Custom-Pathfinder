import java.util.ArrayList;
import java.util.Arrays;

/* Citation: I used the Google AI Search Overview occasionally for syntax. - Isaac Wu, August 6th, 2025 */

// Note: 1=right, 2=down, 3=diagonal
public class Main {
    private static int n = 3;
    private static int[][] board = new int[n][n];
    private static ArrayList<ArrayList<Integer>> traversions = new ArrayList<ArrayList<Integer>>();

    private static void FillBoard() {
        for (int i=0; i<n; i++) {for (int j=0; j<n; j++) {board[i][j] = (int)(101*Math.random());}}
    }

    private static int[] getPosition(ArrayList<Integer> moves) {
        int right = 0; int down = 0;
        for (int move : moves) {
            if (move==1) {right+=1;}
            else if (move==2) {down+=1;}
            else {right+=1; down+=1;}
        }
        int[] position = {right, down};
        return position;
    }

    private static void TraverseBoard(ArrayList<Integer> previousMoves) {
        int[] currentPosition = getPosition(previousMoves);
        ArrayList<Integer> moves = (ArrayList<Integer>) previousMoves.clone(); // Making a clone prevents the list from getting moves from other calls

        if (currentPosition[0]==n-1 && currentPosition[1]==n-1) { // Adding the moves when we get to the end
            traversions.add(moves);
        }
        else if (currentPosition[0]==n-1) { // When we are at the right edge, we only go down
            for (int i=0; i<n-1-currentPosition[1]; i++) {
                moves.add(2);
            }
            traversions.add(moves);
        }
        else if (currentPosition[1]==n-1) { // When we are at the bottom edge, we only go right
            for (int i=0; i<n-1-currentPosition[0]; i++) {
                moves.add(1);
            }
            traversions.add(moves);
        }
        else { // Doing all valid moves (right, down, diagonal)
            moves.add(1);
            TraverseBoard(moves);
            moves = (ArrayList<Integer>) previousMoves.clone();
            moves.add(2);
            TraverseBoard(moves);
            moves = (ArrayList<Integer>) previousMoves.clone();
            moves.add(3);
            TraverseBoard(moves);
        }
    }

    private static int getCost(ArrayList<Integer> traversion) {
        int cost = board[0][0];
        for (int i=0; i<traversion.size(); i++) {
            // Cutting the portion of the traversion to get the square
            ArrayList<Integer> cutTraversion = new ArrayList<Integer>();
            for (int m=0; m<i+1; m++) {cutTraversion.add(traversion.get(m));}
            int[] position = getPosition(cutTraversion);
            cost += board[position[1]][position[0]];
        }
        return cost;
    }

    public static void main(String[] args) {
        FillBoard();
        for (int[] row : board) {System.out.println(Arrays.toString(row));}

        TraverseBoard(new ArrayList<Integer>());

        int minCost = getCost(traversions.get(0));
        ArrayList<Integer> minCostPath = traversions.get(0);
        for (ArrayList<Integer> t : traversions) {
            int cost = getCost(t);
            if (cost < minCost) {minCost = cost; minCostPath=t;}
        }
        System.out.println("Minimum Cost: " + minCost);
        System.out.println("Minimum Cost Path: " + minCostPath);
    }
}