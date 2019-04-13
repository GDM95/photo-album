package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**The Album class that keeps a list of images.
 * @author Eric S Kim
 * @author Greg Melillo
 *
 */
public class Album implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 41687L;
	/**
	 * The Album title
	 */
	private String title;
	/**
	 * The List of images as Pic objects
	 */
	private List<Pic> images;
	
	/**Constructs an album with a specified title
	 * @param title		the album title
	 */
	public Album(String title) {
		this.title = title;
		this.images = new ArrayList<Pic>();
	}
	
	/**Gets the Album title
	 * @return the album title
	 */
	public String getAlbumTitle() {
		return this.title;
	}
	
	/**Gets the Album size
	 * @return the album's size
	 */
	public int getAlbumSize() {
		try {
			return this.images.size();
		} catch(NullPointerException e) {
			return 0;
		}
	}
	
	public List<Pic> getImageList() {
		return this.images;
	}
	
	/**Helper method for getDateRange. Gets the oldest Calendar in the album.
	 * @return	the oldest Pic's Calendar
	 */
	public Calendar getOldestCalendar() {
		if(this.getAlbumSize() == 0) return null;
		
		Pic temp = this.images.get(0);
		for(Pic pic : this.images) {
			if(pic.getCalendar().compareTo(temp.getCalendar()) < 0) temp = pic;
		}
		
		return temp.getCalendar();
	}
	
	/**Helper method for getDateRange. Gets the newest Calendar in the album.
	 * @return	the newest Pic's Calendar
	 */
	public Calendar getNewestCalendar() {
		if(this.getAlbumSize() == 0) return null;
		
		Pic temp = this.images.get(0);
		for(Pic pic : this.images) {
			if(pic.getCalendar().compareTo(temp.getCalendar()) > 0) temp = pic;
		}
		
		return temp.getCalendar();
	}
	
	/**Gets the date range of all pics in the album.
	 * @return	the Album's date range
	 */
	public String getDateRange() {
		if(this.getAlbumSize() == 0) return "";
		return getOldestCalendar().getTime().toString() +" - " +getNewestCalendar().getTime().toString();
	}
	
	/**Sets the album title
	 * @param title		the desired album title
	 */
	public void setAlbumTitle(String title) {
		this.title = title;
	}
	
	/**Adds an image to the Album
	 * @param image		the image being added to the album
	 */
	public void addImage(Pic image) {
		if(image == null) return;
		this.images.add(image);
	}
	
	public boolean removeImage(int index) {
		try {
			this.images.remove(index);
			return true;
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Pic getImage(int index) {
		try {
			return this.images.get(index);
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Pic getFirstImage() {
		try {
			return this.images.get(0);
		} catch(NullPointerException e) {
			return null;
		}
	}
}
