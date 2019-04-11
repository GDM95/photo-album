package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**The list of users. This is the primary structure that will be serialized, allowing for data saving at any point in the program.
 * @author User
 *
 */
public class UserList implements Serializable {
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 85341157L;
	private static final String directory = "data";
	private static final String file = "users.data";
	/**
	 * The user list
	 */
	private List<User> users;
	
	public UserList() {
		this.users = new ArrayList<User>();
	}
	
	public List<User> getUserList() {
		return this.users;
	}
	
	public boolean addNewUser(String name) {
		if(usernameExists(name)) return false;
		User user = new User(name);
		users.add(user);
		return true;
	}
	
	public boolean removeUser(User user) {
		if(!usernameExists(user.getUsername())) return false;
		try {
			users.remove(user);
			return true;
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public boolean usernameExists(String name) {
		try {
			for(User user : this.users) {
				if(user.getUsername().equalsIgnoreCase(name)) return false;
			}
			return true;
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Searches the username list for the given name
	 * @param key the username to be looked up
	 * @return the user if one is found
	 */
	public User getUser(String name) { 
		try {
			for (User user : this.users) {
			     if (user.getUsername().equals(name)) {
			        return user;
			     }
			  }
			 return null; 
		} catch(NullPointerException e){
			return null;
		}
	}
	
	/**
	 * Grabs the serialized user data from file
	 */
	public void deserializeUsers() {
		try {
			FileInputStream fileIn = new FileInputStream(directory +File.separator +file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.users = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException | ClassNotFoundException e) {
			
		}finally {
			System.out.println("Deserializing Users...");
			System.out.println(Arrays.toString(this.users.toArray()));
		}
	}
	
	/**
	 * Writes user data to file
	 */
	public void serializeUsers() {
		try {
			FileOutputStream fileOut = new FileOutputStream(directory +File.separator +file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.users);
			out.close();
			fileOut.close();
			System.out.println("Serializing Users...");
		}catch(IOException e) {
			
		}	
	}
}
