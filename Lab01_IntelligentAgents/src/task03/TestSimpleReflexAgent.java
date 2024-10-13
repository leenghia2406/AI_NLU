package task03;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        Environment env = new Environment(4, 4); 
        Agent agent = new Agent(new AgentProgram());
        env.addAgent(agent, 0, 0); 
        env.run(agent); 
    }
}

