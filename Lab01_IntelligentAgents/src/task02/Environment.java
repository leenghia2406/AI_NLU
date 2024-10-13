package task02;

public class Environment {
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState model;
	private Agent agent = null;// single, for multi-agent using List<Agent>
	private int score = 0; // agent score

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		model = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	public void addAgent(Agent agent, String location) {
		// TODO
		// add an agent into the environment
		this.agent = agent;
		this.model.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.model;
	}

	public EnvironmentState executeAction(Action action) {
		// TODO
		// Update environment state when agent do an action
		String agentLocation = model.getAgentLocation();
		switch (action.toString()) {
		case "SUCK":
			this.model.setLocationState(agentLocation, LocationState.CLEAN);
			score += 500;
			break;
		case "LEFT":
			if (canMoveLeft(agentLocation)) {
				moveAgentLeft();
				score -= 10;
			}else {
				score -= 100;
			}
			break;
		case "RIGHT":
			if (canMoveRight(agentLocation)) {
				moveAgentRight();
				score -= 10;
			}else {
				score -= 100;
			}
			break;
		case "UP":
			if (canMoveUp(agentLocation)) {
				moveAgentUp();
				score -= 10;
			}else {
				score -= 100;
			}
			break;
		case "DOWN":
			if (canMoveDown(agentLocation)) {
				moveAgentDown();
				score -= 10;
			}else {
				score -= 100;
			}
			break;
		}
		return model;
	}
	
	private boolean canMoveLeft(String location) {
	    return !location.equals("A") && !location.equals("C");
	}

	private boolean canMoveRight(String location) {
	    return !location.equals("B") && !location.equals("D"); 
	}

	private boolean canMoveUp(String location) {
	    return !location.equals("A") && !location.equals("B"); 
	}

	private boolean canMoveDown(String location) {
	    return !location.equals("C") && !location.equals("D"); 
	}
	private void moveAgentLeft() {
	    if (model.getAgentLocation().equals(LOCATION_B)) {
	        model.setAgentLocation(LOCATION_A); 
	    } else if (model.getAgentLocation().equals(LOCATION_D)) {
	        model.setAgentLocation(LOCATION_C); 
	    }
	}

	private void moveAgentRight() {
	    if (model.getAgentLocation().equals(LOCATION_A)) {
	    	model.setAgentLocation(LOCATION_B); 
	    } else if (model.getAgentLocation().equals(LOCATION_C)) {
	        model.setAgentLocation(LOCATION_D); 
	    }
	}

	private void moveAgentUp() {
	    if (model.getAgentLocation().equals(LOCATION_C)) {
	        model.setAgentLocation(LOCATION_A); 
	    } else if (model.getAgentLocation().equals(LOCATION_D)) {
	        model.setAgentLocation(LOCATION_B); 
	    }
	}

	private void moveAgentDown() {
	    if (model.getAgentLocation().equals(LOCATION_A)) {
	        model.setAgentLocation(LOCATION_C); 
	    } else if (model.getAgentLocation().equals(LOCATION_B)) {
	        model.setAgentLocation(LOCATION_D); 
	    }
	}
	// get the percept<AgentLocation, LocationState> at the current location where
	// agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		String argentLocation = model.getAgentLocation();
		LocationState status = model.getLocationState(argentLocation);
		return new Percept(argentLocation, status);
	}

	public void run() {
		// TODO
		while(!model.isClean()) {
			Percept percept = getPerceptSeenBy();
			Action action = agent.execute(percept);
			executeAction(action);
			System.out.println("Agent Loc.: " + model.getAgentLocation() + " Action: " + action);
			System.out.println("Score: " + score);
			model.display();
		}
	}
}