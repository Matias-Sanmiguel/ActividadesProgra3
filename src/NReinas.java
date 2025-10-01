import java.util.*;

public class NQueensBacktracking {
    private int n;
    private int[] queens; // queens[i] = column position of queen in row i
    private List<List<String>> solutions;
    
    public NQueensBacktracking(int n) {
        this.n = n;
        this.queens = new int[n];
        this.solutions = new ArrayList<>();
    }
    
    public List<List<String>> solveNQueens() {
        backtrack(0);
        return solutions;
    }
    
    private void backtrack(int row) {
        // Base case: all queens placed
        if (row == n) {
            solutions.add(createBoard());
            return;
        }
        
        // Try each column in current row (variable number of recursive calls)
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                queens[row] = col; // Place queen
                backtrack(row + 1); // Recursive call
                // No need to explicitly remove queen (will be overwritten)
            }
        }
    }
    
    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // Check column conflict
            if (queens[i] == col) {
                return false;
            }
            // Check diagonal conflicts
            if (Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }
    
    private List<String> createBoard() {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    row.append('Q');
                } else {
                    row.append('.');
                }
            }
            board.add(row.toString());
        }
        return board;
    }
    
    // Alternative method to just count solutions
    public int countSolutions() {
        return countBacktrack(0);
    }
    
    private int countBacktrack(int row) {
        if (row == n) {
            return 1; // Found a solution
        }
        
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                queens[row] = col;
                count += countBacktrack(row + 1);
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        NQueensBacktracking nQueens = new NQueensBacktracking(8);
        
        // Count solutions
        int solutionCount = nQueens.countSolutions();
        System.out.println("Number of solutions for 8-Queens: " + solutionCount);
        
        // Get all solutions for smaller board
        NQueensBacktracking smallBoard = new NQueensBacktracking(4);
        List<List<String>> solutions = smallBoard.solveNQueens();
        
        System.out.println("\nSolutions for 4-Queens:");
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            for (String row : solutions.get(i)) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
