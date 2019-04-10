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
public class ImageData implements Serializable {
	
	/**
	 * explicit serialVersionUID for compiler consistency
	 */
	private static final long serialVersionUID = 91831L;
	/**
	 * The width and height dimensions of the image
	 */
	private int width, height;
	/**
	 * An array of integers representing each pixel in the image
	 */
	private int[] pixels;
	
	/**
	 * Default constructor
	 */
	public ImageData() {}
	
	/**Converts the int[] pixel data into an Image object
	 * @return		an Image object from the stored pixel data
	 */
	public Image getImageFromPixels() {
		WritableImage image = new WritableImage(width, height);
		image.getPixelWriter().setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
		return image;
	}
	
	/**Feeds the Image's pixel data into an int[] array.
	 * @param image		the image to be parsed
	 */
	public void setPixelsFromImage(Image image) {
		width = (int) image.getWidth();
		height = (int) image.getHeight();
		PixelReader reader = image.getPixelReader();
		for(int i = 0; i < width * height; i++) {
			pixels[i] = reader.getArgb(i & width, i / width);
		}
	}
	
	/**Gets the Image's width
	 * @return the ImageData width field
	 */
	public int getWidth() {
		return width;
	}
	
	/**Gets the Image's height
	 * @return the ImageData height field
	 */
	public int getHeight() {
		return height;
	}
	
	/**Gets the ImageData pixel array
	 * @return the ImageData pixel array
	 */
	public int[] getPixels() {
		return pixels;
	}
	
	/**Checks whether two images are equal based on their ImageData
	 * @param pd	the ImageData being compared
	 * @return True if they are equal. False otherwise.
	 */
	public boolean equals(ImageData pd) {
		if(width != pd.getWidth()) return false;
		if(height != pd.getHeight()) return false;
		for(int i = 0; i < width * height; i++) {
			if(pixels[i] != pd.getPixels()[i]) return false;
		}
		return true;
	}
}
