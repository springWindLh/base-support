package model;

public enum Role {
	ADMIN("ADMIN"), USER("USER");
	private String name;

	private Role(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return String.valueOf(this.name);
	}	
	
}
