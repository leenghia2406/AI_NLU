package task03;

import java.util.Random;

public class AgentProgram {
	private Random random = new Random();
//return an appropriate action based on the given percept
	public String execute(Percept percept) {
        if (percept.getLocationState() == Environment.LocationState.DIRTY) {
            return "SUCK";
        } else {
            return getRandomMove();
        }
    }
	// random action
    private String getRandomMove() {
        String[] actions = {"UP", "DOWN", "LEFT", "RIGHT"};
        return actions[random.nextInt(actions.length)];
    }
}