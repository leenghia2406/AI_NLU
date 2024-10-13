package task01;

public class AgentProgram {
//return an appropriate action based on the given percept
	public Action execute(Percept p) {// location, status
		// TODO
		//Implement the method named execute() in AgentProgram class for an agent working in the 2 squares [A, B] environment:
		if (p.getLocationState().equals(Environment.LocationState.DIRTY) ) {
			return Action.SUCK;
		}else if (p.getAgentLocation().equals(Environment.LOCATION_A)) {
			return Action.RIGHT;
		}else { 
			return Action.LEFT;
		}
	}
}