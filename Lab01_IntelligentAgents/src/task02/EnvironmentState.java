package task02;

import java.util.HashMap;
import java.util.Map;

import task02.Environment.LocationState;

public class EnvironmentState {
	private Map<String, Environment.LocationState> state = new HashMap<String, Environment.LocationState>();
	private String agentLocation = null;//

	public EnvironmentState(Environment.LocationState locAState, Environment.LocationState locBState, 
			Environment.LocationState locCState, Environment.LocationState locDState) {
		this.state.put(Environment.LOCATION_A, locAState);
		this.state.put(Environment.LOCATION_B, locBState);
		this.state.put(Environment.LOCATION_C, locCState);
		this.state.put(Environment.LOCATION_D, locDState);
	}

	public void setAgentLocation(String location) {
		this.agentLocation = location;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(String location) {
		return this.state.get(location);
	}

	public void setLocationState(String location, LocationState locationState) {
		this.state.put(location, locationState);
	}

	public boolean isClean() {
		// check whether all locations are clean
		// TODO
		return this.state.get(Environment.LOCATION_A) == LocationState.CLEAN &&
				this.state.get(Environment.LOCATION_B) == LocationState.CLEAN && 
						this.state.get(Environment.LOCATION_C) == LocationState.CLEAN &&
								this.state.get(Environment.LOCATION_D) == LocationState.CLEAN;
	}

	public void display() {
		System.out.println("Environment state: \n\t" + this.state);
	}
}