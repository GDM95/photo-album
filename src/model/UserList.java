package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**UserList class contains a static arraylist of users that will be serialized/deserialized. Also contains a static reference to the current user
 * that all controllers will have access to.
 * @author Eric S Kim
 * @author Greg Melillo
 */
public class UserList {
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final String directory = "data";
	private static final String file = "users";
	/**
	 * The user list
	 */
	private static ArrayList<User> users;
	
	private static User current_user;
	
	
	public static User getCurrentUser() {
		return UserList.current_user;
	}
	
	public static void setCurrentUser(User user) {
		UserList.current_user = user;
	}
	
	public static List<User> getUserList() {
		return UserList.users;
	}
	
	public static boolean addNewUser(String name) {
		if(usernameExists(name)) return false;
		UserList.users.add(new User(name));
		return true;
	}
	
	public static boolean removeUser(User user) {
		if(!usernameExists(user.getUsername())) return false;
		try {
			UserList.users.remove(user);
			return true;
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public static boolean usernameExists(String name) {
		if(UserList.users == null || users.isEmpty()) return false;
		for(User user : UserList.users) {
			if(user.getUsername().equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	/**
	 * Searches the username list for the given name
	 * @param key the username to be looked up
	 * @return the user if one is found
	 */
	public static User getUser(String name) { 
		try {
			for (User user : UserList.users) {
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
	public static void deserializeUsers() {
		try {
			FileInputStream fileIn = new FileInputStream(directory +File.separator +file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			UserList.users = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException | ClassNotFoundException e) {
			
		}finally {
			System.out.println("Deserializing Users...");
			System.out.println(Arrays.toString(UserList.users.toArray()));
		}
	}
	
	/**
	 * Writes user data to file
	 */
	public static void serializeUsers() {
		try {
			FileOutputStream fileOut = new FileOutputStream(directory +File.separator +file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(UserList.users);
			out.close();
			fileOut.close();
		}catch(IOException e) {
			
		}	
	}
}
