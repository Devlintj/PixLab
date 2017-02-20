import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}
	
	/** Method to set green and red to 0 */
	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}
	
	/** Method to negate all colors in the picture */
	public void negate() {
		int red;
		int blue;
		int green;
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				red = pixelObj.getRed();
				blue = pixelObj.getBlue();
				green = pixelObj.getGreen();
				pixelObj.setRed(255 - red);
				pixelObj.setBlue(255 - blue);
				pixelObj.setGreen(255 - green);
			}
		}
	}
	
	/** Method to make a picture various scales of gray */
	public void grayscale() {
		int red;
		int blue;
		int green;
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				red = pixelObj.getRed();
				blue = pixelObj.getBlue();
				green = pixelObj.getGreen();
				int average = (red + blue + green)/ 3;
				pixelObj.setRed(average);
				pixelObj.setBlue(average);
				pixelObj.setGreen(average);
			}
		}
	}
	
	public void fixUnderwater() {
		int red;
		int blue;
		int green;
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				red = pixelObj.getRed();
				blue = pixelObj.getBlue();
				green = pixelObj.getGreen();
				pixelObj.setRed(red + 50);
				pixelObj.setBlue(blue - 50);
				pixelObj.setGreen(green - 75);
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}
	
	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from right to left
	 */
	public void mirrorVerticalRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}
	
	/**
	 * Method that mirrors the picture around a horizontal mirror in the center of
	 * the picture from top to bottom
	 */
	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel botPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height / 2; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				topPixel = pixels[row][col];
				botPixel = pixels[height - 1 - row][col];
				botPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	/**
	 * Method that mirrors the picture around a horizontal mirror in the center of
	 * the picture from bottom to top
	 */
	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel botPixel = null;
		int height = pixels.length;
		for (int row = 0; row < height / 2; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				topPixel = pixels[row][col];
				botPixel = pixels[height - 1 - row][col];
				topPixel.setColor(botPixel.getColor());
			}
		}
	}
	
	/**
	 * Method to mirror the picture diagonally from
	 * the top left to the bottom right
	 */
	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel orgPixel = null;
		Pixel flipPixel = null;
		int diagonal;
		if(pixels.length <= pixels[0].length) {
			diagonal = pixels.length;
		} else {
			diagonal = pixels[0].length;
		}
		for(int row = 0; row < diagonal; row++) {
			for(int col = 0; col < diagonal; col++) {
				orgPixel = pixels[row][col];
				flipPixel = pixels[col][row];
				orgPixel.setColor(flipPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {
				count++;
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
		System.out.println(count);
	}
	
	/** Mirror arms of snowman picture */
	public void mirrorArms() {
		//left arm start - row:159 col:105 end - row:190 col:170
		//right arm start - row:172 col:239 end - row:196 col:294
		int mirrorPointLeft = 190;
		int mirrorPointRight = 196;
		Pixel topPixel = null;
		Pixel botPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		// loop through left arm
		for (int row = 159; row < mirrorPointLeft; row++) {
			for (int col = 105; col < 170; col++) {
				topPixel = pixels[row][col];
				botPixel = pixels[mirrorPointLeft - row + mirrorPointLeft][col];
				botPixel.setColor(topPixel.getColor());
			}
		}
		// loop through right arm
		for (int row = 172; row < mirrorPointRight; row++) {
			for (int col = 239; col < 294; col++) {
				topPixel = pixels[row][col];
				botPixel = pixels[mirrorPointRight - row + mirrorPointRight][col];
				botPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	/** Mirrors a seagull so that two seagulls are next to eachother on a beach */
	public void mirrorGull() {
		//start row:235 col:238. End row: 320 col: 344
		int mirrorPoint = 344;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for(int row = 235; row < 320; row++) {
			for(int col = 238; col < mirrorPoint; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}
	
	/** Method do copy only part of the original picture */
	public void copyTwo(Picture fromPic, int startRow, int startCol, 
						int endRow, int endCol, int toStartRow, int toStartCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] fromPixels = fromPic.getPixels2D();
		Pixel[][] toPixels = this.getPixels2D();
		for(int fromRow = startRow, toRow = toStartRow; fromRow < endRow && toRow < toPixels.length; fromRow++, toRow++) {
			for(int fromCol = startCol, toCol = toStartCol; fromCol < endCol && toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}
	
	/** Method to create Tyler's very own canvas using various methods and techniques */
	public void myCollage() {
		Picture barbara = new Picture("barbaraS.jpg");
		Picture seagull = new Picture("seagull.jpg");
		Picture snowman = new Picture("snowman.jpg");
		this.copy(barbara, 0, 0);
		this.copy(barbara, 150, 0);
		this.copy(barbara, 300, 0);
		this.copyTwo(snowman, 100, 100, 300, 300, 0, 150);
		this.copyTwo(snowman, 100, 100, 300, 300, 150, 150);
		this.copyTwo(snowman, 100, 100, 300, 300, 350, 150);
		this.copyTwo(seagull, 230, 230, 350, 350, 0, 100);
		this.copyTwo(seagull, 230, 230, 350, 350, 150, 100);
		this.copyTwo(seagull, 230, 230, 350, 350, 300, 100);
		this.mirrorVertical();
		this.mirrorHorizontal();
		this.mirrorDiagonal();
		this.negate();
		this.mirrorVerticalRightToLeft();
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel botPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		Color botColor = null;
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				botPixel = pixels[row +1][col];
				botColor = botPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist || leftPixel.colorDistance(botColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}
	
	public void edgeDetection2(int edgeDist) {
		Pixel centerPixel, leftPixel, topPixel, botPixel, rightPixel, topLeft, botLeft, topRight, botRight = null;
		Pixel[][] pixels = this.getPixels2D();
		Color leftColor, botColor, rightColor, topColor = null;
		for(int row = 1; row <= pixels.length - 2; row += 3) {
			for(int col = 1; col <= pixels[0].length - 2; col += 3) {
				int black = 0;
				int topNum = 0, botNum = 0, leftNum = 0, rightNum = 0;
				centerPixel = pixels[row][col];
				leftPixel= pixels[row][col - 1];
				rightPixel = pixels[row][col + 1];
				botPixel = pixels[row + 1][col];
				topPixel = pixels[row - 1][col];
				topLeft = pixels[row - 1][col - 1];
				topRight = pixels[row - 1][col + 1];
				botLeft = pixels[row + 1][col - 1];
				botRight = pixels[row + 1][col + 1];
				leftColor = leftPixel.getColor();
				rightColor = rightPixel.getColor();
				topColor = topPixel.getColor();
				botColor = botPixel.getColor();
				if(centerPixel.colorDistance(rightColor) > edgeDist) {
					rightPixel.setColor(Color.BLACK);
					black++;
					rightNum++;
				} else {
					rightPixel.setColor(Color.WHITE);
				}
				if(centerPixel.colorDistance(botColor) > edgeDist) {
					botPixel.setColor(Color.BLACK);
					black++;
					botNum++;
				} else {
					botPixel.setColor(Color.WHITE);
				}
				if(centerPixel.colorDistance(topColor) > edgeDist) {
					topPixel.setColor(Color.BLACK);
					black++;
					topNum++;
				} else {
					topPixel.setColor(Color.WHITE);
				}
				if(centerPixel.colorDistance(leftColor) > edgeDist) {
					leftPixel.setColor(Color.BLACK);
					black++;
					leftNum++;
				} else {
					leftPixel.setColor(Color.WHITE);
				}
				if(topNum == botNum && topNum == 1) {
					leftPixel.setColor(topPixel.getColor());
					rightPixel.setColor(topPixel.getColor());
					leftNum = topNum;
					rightNum = botNum;
				}
				if(leftNum == rightNum && leftNum == 1) {
					topPixel.setColor(leftPixel.getColor());
					botPixel.setColor(leftPixel.getColor());
					topNum = leftNum;
					botNum = rightNum;
				}
				if(botNum == rightNum) {
					botRight.setColor(rightPixel.getColor());
				} else {
					botRight.setColor(Color.WHITE);
				}
				if(topNum == rightNum) {
					topRight.setColor(rightPixel.getColor());
				} else {
					topRight.setColor(Color.WHITE);
				}
				if(botNum == leftNum) {
					botLeft.setColor(leftPixel.getColor());
				} else {
					botLeft.setColor(Color.WHITE);
				}
				if(topNum == leftNum) {
					topLeft.setColor(leftPixel.getColor());
				} else {
					topLeft.setColor(Color.WHITE);
				}
				if(black > 0) {
					centerPixel.setColor(Color.BLACK);
				} else {
					centerPixel.setColor(Color.WHITE);
				}
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
