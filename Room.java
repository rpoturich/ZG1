
public class Room {
	private int roomID;
	private int campusID;
	private String roomName;
	private int capacity;
	private boolean videolink;
	
	
	public Room() {
	
	}
	
	public Room(int roomID){
		this.roomID = roomID;
		
		
	}
	
	public Room (int roomID, int campusID, String roomName, int capacity, boolean videolink){
		this.roomID = roomID;
		this.campusID = campusID;
		this.roomName = roomName;
		this.capacity = capacity;
		this.videolink = videolink;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getCampusID() {
		return campusID;
	}

	public void setCampusID(int campusID) {
		this.campusID = campusID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isVideolink() {
		return videolink;
	}

	public void setVideolink(boolean videolink) {
		this.videolink = videolink;
	}

	
	
	
}
