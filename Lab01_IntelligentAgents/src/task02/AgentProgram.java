package task02;


public class AgentProgram {
//return an appropriate action based on the given percept
	public Action execute(Percept p) {// location, status
		// TODO
		// Implement the method named execute() in AgentProgram class for an agent working in the 4 squares [A, B, C, D] environment:
		if (p.getLocationState().equals(Environment.LocationState.DIRTY) ) {
			return Action.SUCK;
		}else {
			Action action[] = {Action.LEFT, Action.RIGHT, Action.UP, Action.DOWN};
			Action randomAction = action[new java.util.Random().nextInt(action.length)];
			return randomAction;
		}
		
	}
}