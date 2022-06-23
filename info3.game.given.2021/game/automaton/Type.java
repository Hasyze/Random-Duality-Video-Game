package automaton;

public enum Type {
	
	ADVERSAIRE("A"), CLUE("C"), DANGER("D"), GATE("G"), JUMPABLE("J"), MISSILE("M"), OBSTACLE("O"), PRENDRE("P"),
	TEAM("T"), VOID("V"), PLAYER("@"), NIMPORTE("_");

	private String value;

	private Type(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
