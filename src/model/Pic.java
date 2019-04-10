package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pic implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 810L;
	
	private PixelData image;	//Image is NOT SERIALIZABLE
	private String caption;
	private List<Tag> tags;
	private Calendar date;
	
	public Pic() {
		caption = "";
		tags = new ArrayList<Tag>();
		date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND,0);
		image = new PixelData();
	}
	
	//PROBABLY NEEDS ANOTHER CONSTRUCTOR WITH AN IMAGE PARAMETER
	
	
}
