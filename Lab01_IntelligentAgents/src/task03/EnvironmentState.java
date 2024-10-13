package task03;

public class EnvironmentState {
    private Environment.LocationState[][] state; 
    private int rows, cols;
    private int agentRow, agentCol; 
    public EnvironmentState(int rows, int cols, Environment.LocationState[][] initialState) {
        this.rows = rows;
        this.cols = cols;
        this.state = new Environment.LocationState[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.state[i][j] = initialState[i][j];
            }
        }
    }

    public Environment.LocationState getLocationState(int row, int col) {
        return state[row][col];
    }

    public void setLocationState(int row, int col, Environment.LocationState locationState) {
        state[row][col] = locationState;
    }

    public void setAgentLocation(int row, int col) {
        this.agentRow = row;
        this.agentCol = col;
    }

    public int[] getAgentLocation() {
        return new int[] { agentRow, agentCol };
    }

    public boolean isClean() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (state[i][j] == Environment.LocationState.DIRTY) {
                    return false; 
                }
            }
        }
        return true; 
    }

    public void display() {
        System.out.println("Environment state:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Agent is at: (" + agentRow + ", " + agentCol + ")");
    }
}
