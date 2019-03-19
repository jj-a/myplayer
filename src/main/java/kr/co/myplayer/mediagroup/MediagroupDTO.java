package kr.co.myplayer.mediagroup;

public class MediagroupDTO {
	
	private int mediagroupno;
	private String title;

	// Constructor
	
	public MediagroupDTO() {
		System.out.println("Start MediagroupDTO");
	}
	
	
	// Getter&Setter
	
	public int getMediagroupno() {
		return mediagroupno;
	}

	public void setMediagroupno(int mediagroupno) {
		this.mediagroupno = mediagroupno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
