package task03;

import java.util.Random;

public class Environment {
	private static final double DIRT_RATE = 0.2;
    private static final double WALL_RATE = 0.1;
    private LocationState[][] grid; // m x n environment
    private int agentRow, agentCol; // agent location
    private int rows, cols; // size of grid
    private Random random;

	public enum LocationState {
		CLEAN, DIRTY, WALL
	}

	private EnvironmentState model;
	private Agent agent = null;
	private int score = 0; // agent score

	 public Environment(int m, int n) {
	        this.rows = m;
	        this.cols = n;
	        this.grid = new LocationState[m][n];
	        this.random = new Random();
	        this.score = 0;
	        initializeEnvironment();
	 }
	 
	 private void initializeEnvironment() {
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                if (random.nextDouble() < DIRT_RATE) {
	                    grid[i][j] = LocationState.DIRTY;
	                } else if (random.nextDouble() < WALL_RATE) {
	                    grid[i][j] = LocationState.WALL;
	                } else {
	                    grid[i][j] = LocationState.CLEAN;
	                }
	            }
	        }
	 }
	 public void addAgent(Agent agent, int row, int col) {
	        if (grid[row][col] != LocationState.WALL) { // check to update 
	            this.agentRow = row;
	            this.agentCol = col;
	        }
	    }
	 public void executeAction(String action) {
	        if ("SUCK".equals(action)) {
	            if (grid[agentRow][agentCol] == LocationState.DIRTY) {
	                grid[agentRow][agentCol] = LocationState.CLEAN;
	                score += 500;
	            }
	        } else {
	            moveAgent(action); 
	        }
	    }

	    private void moveAgent(String direction) {
	        int newRow = agentRow;
	        int newCol = agentCol;

	        switch (direction) {
	            case "UP":
	                newRow = agentRow - 1;
	                break;
	            case "DOWN":
	                newRow = agentRow + 1;
	                break;
	            case "LEFT":
	                newCol = agentCol - 1;
	                break;
	            case "RIGHT":
	                newCol = agentCol + 1;
	                break;
	        }

	        if (isValidMove(newRow, newCol)) {
	            agentRow = newRow;
	            agentCol = newCol;
	            score -= 10;
	        } else {
	            score -= 100;
	        }
	    }
	    private boolean isValidMove(int row, int col) {
	        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] != LocationState.WALL;
	    }

	    public void printState() {
	        System.out.println("Agent Loc.: (" + agentRow + ", " + agentCol + ")");
	        System.out.println("Score: " + score);
	        System.out.println("Environment state:");
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                System.out.print(grid[i][j] + " ");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }

	    public void run(Agent agent) {
	        for (int i = 0; i < 100; i++) { 
	            Percept percept = getPerceptSeenByAgent();
	            String action = agent.program.execute(percept); 
	            executeAction(action); 
	            printState();
	        }
	    }

	    public Percept getPerceptSeenByAgent() {
	        LocationState currentState = grid[agentRow][agentCol];
	        return new Percept(agentRow, agentCol, currentState);
	    }
}