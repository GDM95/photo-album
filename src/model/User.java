package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**The User class that keeps a list of albums.
 * @author Eric S Kim
 * @author Greg Melillo
 *
 */
public class User implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 8534L;
	/**
	 * User's username
	 */
	private String username;
	/**
	 * User's list of albums
	 */
	private List<Album> albums;

	/**Constructs a User with a specified username
	 * @param username		the username
	 */
	public User(String username) {
		this.username = username;
		this.albums = new ArrayList<Album>();
	}
	
	/**Gets the username field
	 * @return the User's username
	 */
	public String getUsername() {
		return username;
	}
	
	/**Sets the username field
	 * @param username the desired username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return username;
	}
	
	/**Adds a new album to the User's album list. Does not add albums with duplicate titles
	 * @param title	the desired album title
	 * @param boolean value confirming whether the album was successfully added
	 */
	public boolean addAlbum(String title) {
		if(albumTitleExists(title)) return false;
		this.albums.add(new Album(title));
		return true;
	}
	
	/**Removes the desired album from the album list
	 * @param album		the album to be removed
	 * @return boolean value confirming whether the album was successfully removed
	 */
	public boolean removeAlbum(Album album) {
		try {
			this.albums.remove(album);
			return true;
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	/**Renames an Album with a new String title at the given index
	 * @param title		the desired new Album title
	 * @param index		the index of the Album
	 * @return boolean value confirming whether the album was successfully renamed
	 */
	public boolean renameAlbum(String title, int index) {
		if(albumTitleExists(title)) return false;
		try {
			this.albums.get(index).setAlbumTitle(title);
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**Helper method that checks whether an album title already exists in the list of Albums
	 * @param title		the title being checked
	 * @return boolean value determining whether an album by the given title already exists
	 */
	public boolean albumTitleExists(String title) {
		try {
			for(Album album : this.albums) {
				if(album.getAlbumTitle().equalsIgnoreCase(title)) return false;
			}
			return true;
		} catch(NullPointerException e){
			throw e;
		}
	}
}
