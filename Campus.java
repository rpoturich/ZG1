
public class Campus {
	private int campusID;
	private String campusName;
	private String shortName;
	
	public Campus() {
		// TODO Auto-generated constructor stub
	}
	
	public Campus(int campusID){
		this.campusID = campusID;
		
	}

	public Campus(int campusID, String campusName, String shortName) {
		this.campusID = campusID;
		this.campusName = campusName;
		this.shortName = shortName;
	}

	public int getCampusID() {
		return campusID;
	}

	public void setCampusID(int campusID) {
		this.campusID = campusID;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
