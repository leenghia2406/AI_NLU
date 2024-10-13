package task01;

public class DynamicAction extends Action {
	private String name;

	public DynamicAction(String name) {//SUCK, RIGHT, LEFT
		this.name = name;
	}

	@Override
	public boolean isNoOp() {
		return false;
	}
	@Override
	public String toString() {
		return this.name;
	}
}
