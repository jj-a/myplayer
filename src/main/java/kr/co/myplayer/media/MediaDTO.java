package kr.co.myplayer.media;

import org.springframework.web.multipart.MultipartFile;

public class MediaDTO {
	
	private int mediano;
	private String title, poster, filename;
	private long filesize;
	private String mview, rdate;
	private int mediagroupno;
	
	// Spring File Object (MultipartFile)
	private MultipartFile posterMF, filenameMF;
	
	// Constructor
	
	public MediaDTO() {
		System.out.println("Start MediaDTO");
	}

	
	// Getter&Setter
	
	public int getMediano() {
		return mediano;
	}

	public void setMediano(int mediano) {
		this.mediano = mediano;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getMview() {
		return mview;
	}

	public void setMview(String mview) {
		this.mview = mview;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getMediagroupno() {
		return mediagroupno;
	}

	public void setMediagroupno(int mediagroupno) {
		this.mediagroupno = mediagroupno;
	}


	public MultipartFile getPosterMF() {
		return posterMF;
	}


	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}


	public MultipartFile getFilenameMF() {
		return filenameMF;
	}


	public void setFilenameMF(MultipartFile filenameMF) {
		this.filenameMF = filenameMF;
	}
	
	
	
	
}
