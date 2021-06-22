package project1;

/**
 * Filename:    ShapeDrawer.java
 * Author:      Bedemariam Degef
 * Date:        01/26/2021
 * Description: JPanel that holds animated images.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ShapeDrawer extends JPanel {

	static class TransformHolder {
		static int translateX = 0;
		static int translateY = 0;
		static double rotation = 0.0;
		static double scaleX = 1.0;
		static double scaleY = 1.0;

		static void reset() {
			translateX = 0;
			translateY = 0;
			rotation = 0.0;
			scaleX = 1.0;
			scaleY = 1.0;
		}
	}

	private static final long serialVersionUID = 1L;
	// A counter that increases by one in each frame.
	private int frameNumber = 1;
	// The time, in milliseconds, since the animation started.
	private long elapsedTimeMillis;
	// This is the measure of a pixel in the coordinate system
	// set up by calling the applyLimits method. It can be used
	// for setting line widths, for example.
	private float pixelSize;

	// Constant created to store the desired number of frames in the animation
	private static final int FRAMES = 5;

	// Variable for storing the maximum image size after all transforms
	private final static int IMAGE_SIZE = 50;

	// Variable for storing the offset used by applyWindowToViewportTransformation
	private final static int OFFSET = IMAGE_SIZE * 4;

	// Variable for storing number of images
	private final static int NUM_IMAGES = 3;

	// Variable for storing how many images have been drawn per loop
	private static int imageNum = 1;

	ImageTemplate myImages = new ImageTemplate();
	BufferedImage tImage = myImages.getImage(ImageTemplate.letterT);
	BufferedImage oImage = myImages.getImage(ImageTemplate.letterO);
	BufferedImage cImage = myImages.getImage(ImageTemplate.letterC);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		JFrame window;
		window = new JFrame("Java Animation"); // The parameter shows in the window title bar.
		final ShapeDrawer panel = new ShapeDrawer(); // The drawing area.
		window.setContentPane(panel); // Show the panel in the window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
		window.pack(); // Set window size based on the preferred sizes of its contents.
		window.setResizable(false); // Don't let user resize window.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( // Center window on screen.
				(screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
		Timer animationTimer; // A Timer that will emit events to drive the animation.
		final long startTime = System.currentTimeMillis();
		// Taken from AnimationStarter
		// Modified to change timing and allow for recycling
		animationTimer = new Timer(1600, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (panel.frameNumber >= FRAMES) {
					panel.frameNumber = 1;
				} else {
					panel.frameNumber++;
				}
				panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
				panel.repaint();
			}
		});
		window.setVisible(true); // Open the window, making it visible on the screen.
		animationTimer.start(); // Start the animation running.
	}

	public ShapeDrawer() {
		// Size of Frame
		setPreferredSize(new Dimension(800, 600));
	}

	// This is where all of the action takes place and modified to add the specific
	// Images
	// Also added looping structure for Different transformations
	protected void paintComponent(Graphics g) {

		/*
		 * First, create a Graphics2D drawing context for drawing on the panel.
		 * (g.create() makes a copy of g, which will draw to the same place as g, but
		 * changes to the returned copy will not affect the original.)
		 */
		Graphics2D g2 = (Graphics2D) g.create();

		/*
		 * Turn on antialiasing in this graphics context, for better drawing.
		 */
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/*
		 * Fill in the entire drawing area with white.
		 */
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight()); // From the old graphics API!

		/*
		 * Here, I set up a new coordinate system on the drawing area, by calling the
		 * applyLimits() method that is defined below. Without this call, I would be
		 * using regular pixel coordinates. This function sets the value of the global
		 * variable pixelSize, which I need for stroke widths in the transformed
		 * coordinate system.
		 */
		// Controls your zoom and area you are looking at
		applyWindowToViewportTransformation(g2, -OFFSET, 0, 0, OFFSET, true);

		AffineTransform savedTransform = g2.getTransform();
		System.out.println("Frame is " + frameNumber);
		switch (frameNumber) {
		case 1: // First frame is unmodified.
			TransformHolder.reset();
			break;
		case 2: // Second frame translates each image by (-5, 7).
			TransformHolder.translateX = -5;
			TransformHolder.translateY = 7;
			break;
		// Can add more cases as needed
		case 3: // Third frame rotates each image by 45 degrees counterclockwise.
			TransformHolder.rotation = 45 * Math.PI / 180.0;
			break;
		case 4: // Fourth frame rotates each image by 90 degrees clockwise.
			TransformHolder.rotation -= (90 * Math.PI / 180.0);
			break;
		case 5: // Fifth frame scales 2x, .5y.
			TransformHolder.scaleX = 2.0;
			TransformHolder.scaleY = 0.5;
			break;
		default:
			break;
		} // End switch

		// Add a T image.
		drawImage(tImage, g2, savedTransform);

		// Add an O image
		drawImage(oImage, g2, savedTransform);

		// Add a C image
		drawImage(cImage, g2, savedTransform);

		// Reset image count
		imageNum = 1;
	}

	// Contains logic to draw the image according to current parameters.
	private void drawImage(BufferedImage image, Graphics2D graphic, AffineTransform transform) {
		int imageOffset = imageNum * IMAGE_SIZE;

		graphic.translate(TransformHolder.translateX, TransformHolder.translateY);
		graphic.translate(-(imageOffset), imageOffset);
		graphic.rotate(TransformHolder.rotation);
		graphic.scale(TransformHolder.scaleX, TransformHolder.scaleY);
		graphic.drawImage(image, 0, 0, this);
		graphic.setTransform(transform);

		imageNum++;
	}

	// Method for window to viewport transformation
	private void applyWindowToViewportTransformation(Graphics2D g2, double left, double right, double bottom,
			double top, boolean preserveAspect) {
		int width = getWidth(); // The width of this drawing area, in pixels.
		int height = getHeight(); // The height of this drawing area, in pixels.
		if (preserveAspect) {
			// Adjust the limits to match the aspect ratio of the drawing area.
			double displayAspect = Math.abs((double) height / width);
			double requestedAspect = Math.abs((bottom - top) / (right - left));
			if (displayAspect > requestedAspect) {
				// Expand the viewport vertically.
				double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
				bottom += excess / 2;
				top -= excess / 2;
			} else if (displayAspect < requestedAspect) {
				// Expand the viewport vertically.
				double excess = (right - left) * (requestedAspect / displayAspect - 1);
				right += excess / 2;
				left -= excess / 2;
			}
		}
		g2.scale(width / (right - left), height / (bottom - top));
		g2.translate(-left, -top);
		double pixelWidth = Math.abs((right - left) / width);
		double pixelHeight = Math.abs((bottom - top) / height);
		pixelSize = (float) Math.max(pixelWidth, pixelHeight);
	}
}