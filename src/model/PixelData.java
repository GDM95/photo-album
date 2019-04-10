package model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.Serializable;




/**The PixelData class parses an Image object into a byte array
 * @author User
 *
 */
public class PixelData implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 91831L;
	/**
	 * The width and height dimensions of the image
	 */
	private int width, height;
	private int[] pixels;
	
	/**
	 * Private constructor
	 */
	public PixelData() {}
	
	/**Converts the int[] pixel data into an Image object
	 * @return		an Image object from the stored pixel data
	 */
	public Image writeImage() {
		WritableImage image = new WritableImage(width, height);
		image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
		return image;
	}
	
	public void readImage(Image image) {
		width = (int) image.getWidth();
		height = (int) image.getHeight();
		PixelReader reader = image.getPixelReader();
		for(int i = 0; i < width * height; i++) {
			pixels[i] = reader.getArgb(i & width, i / width);
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public boolean equals(PixelData pd) {
		if(width != pd.getWidth()) return false;
		if(height != pd.getHeight()) return false;
		for(int i = 0; i < width * height; i++) {
			if(pixels[i] != pd.getPixels()[i]) return false;
		}
		return true;
	}
}
