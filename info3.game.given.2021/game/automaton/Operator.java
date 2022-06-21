package automaton;

public enum Operator {
	AND("&"), OR("/"), NOT("!");

	private String value;

	private Operator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
