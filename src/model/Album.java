package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
