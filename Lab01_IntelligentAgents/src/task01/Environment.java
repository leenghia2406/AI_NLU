package task01;

public class Environment {
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState model;
	private Agent agent = null;// single, for multi-agent using List<Agent>

	public Environment(LocationState locAState, LocationState locBState) {
		model = new EnvironmentState(locAState, locBState);
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
	    if (action == Action.SUCK) {
	        model.setLocationState(agentLocation, LocationState.CLEAN);
	    } else if (action == Action.LEFT) {
	            model.setAgentLocation(LOCATION_A);
	    } else {
	            model.setAgentLocation(LOCATION_B);
	    }
	    return model;
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
			model.display();
		}
	}
}