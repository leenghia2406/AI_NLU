package task01;

public abstract class Action {

	public static final Action LEFT = new DynamicAction("LEFT");
	public static final Action RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK = new DynamicAction("SUCK");

	public abstract boolean isNoOp();
}