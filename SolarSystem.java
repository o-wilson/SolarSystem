import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class provides a graphical user interface to a model of the solar system
 * 
 * @author Joe Finney
 */
public class SolarSystem extends JFrame implements MouseWheelListener, MouseListener, KeyListener {
	private int width = 300;
	private int height = 300;
	private boolean exiting = false;
	private Map<RenderingHints.Key, Object> renderingHints;

	private ArrayList<SolarObject> things = new ArrayList<SolarObject>();

	// added by Oliver
	private double scale;
	private double timeScale;
	private boolean rmbPressed, lmbPressed;
	private double xOff, yOff;
	private boolean[] arrowKeys; //Clockwise from left - L,U,R,D

	/**
	 * Create a view of the Solar System. Once an instance of the SolarSystem class
	 * is created, a window of the appropriate size is displayed, and objects can be
	 * displayed in the solar system
	 *
	 * @param width  the width of the window in pixels.
	 * @param height the height of the window in pixels.
	 */
	public SolarSystem(int width, int height) {
		this.width = width;
		this.height = height;

		this.setTitle("The Solar System");
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// added by Oliver
		this.setLocationRelativeTo(null);

		renderingHints = new HashMap<>();
		renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		// added by Oliver
		scale = .4;
		timeScale = 100;
		addMouseWheelListener(this);
		addMouseListener(this);
		addKeyListener(this);
		rmbPressed = false;
		lmbPressed = false;
		xOff = 0;
		yOff = 0;
		arrowKeys = new boolean[] {false, false, false, false};
	}

	/**
	 * A method called by the operating system to draw onto the screen -
	 * <p>
	 * <B>YOU DO NOT (AND SHOULD NOT) NEED TO CALL THIS METHOD.</b>
	 * </p>
	 */
	public void paint(Graphics gr) {
		BufferedImage i = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = i.createGraphics();
		Graphics2D window = (Graphics2D) gr;
		g.setRenderingHints(renderingHints);
		window.setRenderingHints(renderingHints);

		synchronized (this) {
			if (!this.exiting) {
				g.clearRect(0, 0, width, height);
				for (SolarObject t : things) {
					g.setColor(t.col);
					g.fillOval(t.x, t.y, t.diameter, t.diameter);

					// try{ Thread.sleep(0); } catch (Exception e) {}
				}
			}

			window.drawImage(i, 0, 0, this);
		}
	}

	//
	// Shouldn't really handle colour this way, but the student's haven't been
	// introduced
	// to constants properly yet, and Color.getColor() doesn't seem to work...
	// hmmm....
	//
	private Color getColourFromString(String col) {
		Color color;

		if (col.charAt(0) == '#') {
			color = new Color(Integer.valueOf(col.substring(1, 3), 16), Integer.valueOf(col.substring(3, 5), 16),
					Integer.valueOf(col.substring(5, 7), 16));
		} else {
			try {
				java.lang.reflect.Field field = Color.class.getField(col);
				color = (Color) field.get(null);
			} catch (Exception e) {
				color = Color.WHITE;
			}
		}
		return color;
	}

	/**
	 * Draws a round shape in the window at the given co-ordinates that represents
	 * an object in the solar system. The SolarSystem class uses <i>Polar
	 * Co-ordinates</i> to represent the location of objects in the solar system.
	 *
	 * @param distance the distance from the sun to the object.
	 * @param angle    the angle (in degrees) that represents how far the planet is
	 *                 around its orbit of the sun.
	 * @param diameter the size of the object.
	 * @param col      the colour of this object, as a string. Case insentive.
	 *                 <p>
	 *                 One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN,
	 *                 LIGHT_GRAY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW.
	 *                 Alternatively, a 24 bit hexadecimal string representation of
	 *                 an RGB colour is also accepted, e.g. "#FF0000"
	 *                 </p>
	 */
	public void drawSolarObject(double distance, double angle, double diameter, String col) {
		Color colour = this.getColourFromString(col);
		//xOff, yOff added by Oliver
		double centreOfRotationX = (((double) width) / 2.0) + xOff;
		double centreOfRotationY = (((double) height) / 2.0) + yOff;

		double rads = Math.toRadians(angle);
		double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
		double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

		synchronized (this) {
			if (things.size() > 1000) {
				System.out.println("\n\n");
				System.out.println(" ********************************************************* ");
				System.out.println(" ***** Only 1000 Entities Supported per Solar System ***** ");
				System.out.println(" ********************************************************* ");
				System.out.println("\n\n");
				System.out.println("If you are't trying to add this many things");
				System.out.println("to your SolarSystem, then you have probably");
				System.out.println("forgotten to call the finishedDrawing() method");
				System.out.println("See the JavaDOC documentation for more information");
				System.out.println("\n-- Joe");
				System.out.println("\n\n");

				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} else {
				SolarObject t = new SolarObject((int) x, (int) y, (int) diameter, colour);
				things.add(t);
			}
		}
	}

	/**
	 * Draws a Body relative to its default centre of orbit
	 * 
	 * @param child The Body to be drawn
	 */
	public void drawSolarObjectAbout(CelestialBody child) {
		drawSolarObjectAbout(child, child.getCenterOfOrbit());
	}

	/**
	 * Draws the child object relative to its parent (centre of orbit)
	 * 
	 * @param child  The Body to be drawn
	 * @param parent The Body it's orbiting
	 */
	public void drawSolarObjectAbout(CelestialBody child, CelestialBody parent) {
		double distance = child.getDistanceToCentre();
		double angle = child.getAngleToOrigin();
		double diameter = child.getDiameter();
		String col = child.getColor();
		double cORD = parent.getDistanceToCentre();
		double cORA = parent.getAngleToOrigin();

		drawSolarObjectAbout(distance, angle, diameter, col, cORD, cORA);
	}

	/**
	 * Draws a round shape in the window at the given co-ordinates. The SolarSystem
	 * class uses <i>Polar Co-ordinates</i> to represent the location of objects in
	 * the solar system. This method operates in the same fashion as
	 * drawSolarObject, but provides additional co-ordinates to allow the programmer
	 * to use an arbitrary point about which the object orbits (e.g. a planet rather
	 * than the sun).
	 *
	 * @param distance                 the distance from this object to the point
	 *                                 about which it is orbiting.
	 * @param angle                    the angle (in degrees) that represents how
	 *                                 far the object is around its orbit.
	 * @param diameter                 the size of the object.
	 * @param col                      the colour of this object, as a string. Case
	 *                                 insentive.
	 *                                 <p>
	 *                                 One of: BLACK, BLUE, CYAN, DARK_GRAY, GRAY,
	 *                                 GREEN, LIGHT_GRAY, MAGENTA, ORANGE, PINK,
	 *                                 RED, WHITE, YELLOW
	 *                                 </p>
	 * @param centreOfRotationDistance the distance part of the polar co-ordinate
	 *                                 about which this object orbits.
	 * @param centreOfRotationAngle    the angular part of the polar co-ordinate
	 *                                 about which this object orbits.
	 */
	public void drawSolarObjectAbout(double distance, double angle, double diameter, String col,
			double centreOfRotationDistance, double centreOfRotationAngle) {
		Color colour = this.getColourFromString(col);
		double centrerads = Math.toRadians(centreOfRotationAngle);
		//xOff, yOff added by Oliver
		double centreOfRotationX = (((double) width) / 2.0) + centreOfRotationDistance * Math.sin(centrerads) + xOff;
		double centreOfRotationY = (((double) height) / 2.0) + centreOfRotationDistance * Math.cos(centrerads) + yOff;

		double rads = Math.toRadians(angle);
		double x = (int) (centreOfRotationX + distance * Math.sin(rads)) - diameter / 2;
		double y = (int) (centreOfRotationY + distance * Math.cos(rads)) - diameter / 2;

		synchronized (this) {
			if (things.size() > 10000) {
				System.out.println("\n\n");
				System.out.println(" ********************************************************* ");
				System.out.println(" ***** Only 10000 Entities Supported per Solar System ***** ");
				System.out.println(" ********************************************************* ");
				System.out.println("\n\n");
				System.out.println("If you are't trying to add this many things");
				System.out.println("to your SolarSystem, then you have probably");
				System.out.println("forgotten to call the finishedDrawing() method");
				System.out.println("See the JavaDOC documentation for more information");
				System.out.println("\n-- Joe");
				System.out.println("\n\n");

				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} else {
				SolarObject t = new SolarObject((int) x, (int) y, (int) diameter, colour);
				things.add(t);
			}
		}
	}

	/**
	 * Updates the window to show all objects that have recently been drawn using
	 * drawSolarObject() or drawSolarObjectAbout().
	 * 
	 * The method also waits for 20 milliseconds (1/50th of one second) and then
	 * clears the screen.
	 */
	public void finishedDrawing() {
		try {
			this.repaint();
			Thread.sleep(20);
			synchronized (this) {
				things.clear();
			}
		} catch (Exception e) {
		}
	}

	// added by Oliver
	public void update() {
		double panSpeed = 5;		
		if (arrowKeys[0])
			xOff += panSpeed;
		else if (arrowKeys[2])
			xOff -= panSpeed;

		if (arrowKeys[1])
			yOff += panSpeed;
		else if (arrowKeys[3])
			yOff -= panSpeed;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double zoomSpeed = .01;
		double timeSpeed = 50;
		if (rmbPressed)
			timeScale -= e.getWheelRotation() * timeSpeed;
		else
			scale -= e.getWheelRotation() * zoomSpeed;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			lmbPressed = true;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rmbPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			lmbPressed = false;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rmbPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= KeyEvent.VK_LEFT && e.getKeyCode() <= KeyEvent.VK_DOWN)
			arrowKeys[e.getKeyCode() - KeyEvent.VK_LEFT] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= KeyEvent.VK_LEFT && e.getKeyCode() <= KeyEvent.VK_DOWN)
			arrowKeys[e.getKeyCode() - KeyEvent.VK_LEFT] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Get the current zoom level of the solar system
	 * 
	 * @return current scale
	 */
	public double getScale() {
		return scale;
	}

	/**
	 * Get the current timeScale of the solar system
	 * 
	 * @return current timeScale
	 */
	public double getTimeScale() {
		return timeScale;
	}

	private class SolarObject {
		public int x;
		public int y;
		public int diameter;
		public Color col;

		public SolarObject(int x, int y, int diameter, Color col) {
			this.x = x;
			this.y = y;
			this.diameter = diameter;
			this.col = col;
		}
	}
}
