package model;

import java.io.Serializable;

/**Class providing tags for each image in the Pic class
 * @author Eric S Kim
 * @author Greg Melillo
 *
 */
public class Tag implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 749L;
	/**
	 * type is the type of tag (e.g., "location" or "person"), and name is the string associated with the type (e.g. "New Brunswick" or "Susan")
	 */
	private String type, name;
	
	/**Constructs a tag with a specified type and name associated with the type
	 * @param type		the type of tag
	 * @param name		a name of the specified tag type
	 */
	public Tag(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	/**Gets the tag's type
	 * @return Tag's type
	 */
	public String getType() {
		return this.type;
	}
	
	/**Gets Tag's name
	 * @return Tag's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**Sets the Tag's type
	 * @param type	Tag's type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**Sets the Tag's name
	 * @param name	Tag's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Type: " +type +", Name: " +name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object tag) {
		if(tag == null || !(tag instanceof Tag)) return false;
		Tag t = (Tag) tag;
		return t.getType().equals(type) && t.getType().equals(name);
	}
}
