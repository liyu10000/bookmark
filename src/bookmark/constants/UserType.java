package bookmark.constants;

public enum UserType {
	USER ("user"),
	EDITOR ("editor"),
	CHIEF_EDITOR ("chiefeditor");
	
	private UserType (String name) {
		this.name = name;
	}
	
	private String name;
	public String getUserType() {
		return name;
	}
}
