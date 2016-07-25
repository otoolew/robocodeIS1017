//REFRENCE #1
// I Needed Get with the Math so I referenced the following
// It was also a Gold Mine of Information
// http://mark.random-article.com/weber/java/robocode/lesson4.html
package robocodeIS1017;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import robocode.*;

/**
 * Final Milestone 2
 * class MyRobot2
 * @author William O'Toole
 */

// BOT BEHAVIOR
// DODGE BULLET: Bot should Sense when an enemy bot has spent energy to fire a bullet and move before the bullet hits
// PREDICTIVE TARGETING: Bot uses Predictive Targeting to fire ahead of a moving target increasing accuracy.
// EFFICIENT BULLETS: Bot Selects bullet power based on distance of enemy. Closer Enemies will get a Full Power Shot. 

public class MyRobot2 extends AdvancedRobot{
	private static double bulletEnergy; // amount of power put in a bullet
	private static int stepDistance; // distance to make in each step
	private static int rightRotationAngle; // right angle for rotating the tank (e.g. 30, 45, 60, 90)
	private static int leftRotationAngle; // left angle for rotating the tank

	private byte scanDegree = 1;
	private int count = 0; // track turn duration
	private int moveDirection = 1;
	private double prevEnergy=100;
	
	String trackName; // Name of the robot we're currently tracking

	private AdvancedEnemyBot enemy = new AdvancedEnemyBot();
	

	/**
	 * run: E733tBot's default behavior
	 */
	// The Main run loop
	public void run() {
		// Debug to make sure the properties loaded correctly
		//JOptionPane.showMessageDialog(null, bulletEnergy+" "+stepDistance+" "+rightRotationAngle+" "+leftRotationAngle);
		loadProperties();
		
		// Set colors
		setBodyColor(new Color(85,107,47)); // Olive Green
		setGunColor(new Color(0,0,0)); // Black
		setRadarColor(new Color(127,255,0));
		setBulletColor(new Color(178,34,34 ));
		setScanColor(new Color(173,255,47));
		
		// Separate Gun, Radar and Body to turn Independently
		setAdjustGunForRobotTurn(true); 
		setAdjustRadarForRobotTurn(true);
		
		// Reseting EnemyBot values
		enemy.reset();
		
		//Scans for a Robot
		scan();
		
		// Prepare gun
		trackName = null; // Initialize to not tracking anyone				
			
		// Robot main loop
		while(true) {
			setTurnRadarRight(360);
			execute();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		// Radar Lock on Target Then Search for Others that maybe Closer
		if (enemy.none() || e.getDistance() < enemy.getDistance()-30 || e.getName().equals(enemy.getName())) {			
			enemy.update(e, this);
		}
		
		// Turn Body 90 or 30 Degrees away from target
		setTurnRight(e.getBearing()+90-30*moveDirection);
		// Check for a change in Energy Soent by the Target
		// If there was a change, Move to the side DODGE the Bullet
		double enemySpentEnergy = prevEnergy-e.getEnergy();
		if(enemySpentEnergy>0&&enemySpentEnergy<=3){
			moveDirection =-moveDirection;
			setAhead((e.getDistance()/4+25)*moveDirection);			
		}

		// Calculate Enemy Coordinates
		double firePower = Math.min(500 / enemy.getDistance(), 3); // REFRENCE #1
		double bulletSpeed = 20 - firePower * 3; // REFRENCE #1
		long time = (long)(enemy.getDistance() / bulletSpeed);// REFRENCE #1

		// calculate gun turn to predicted x,y location
		double xFuture = enemy.predictX(time);// REFRENCE #1
		double yFuture = enemy.predictX(time);// REFRENCE #1

		
		// Scan Area with a Wobble
		scanDegree *= -1;
		setTurnRadarRight(efficientRotation(getHeading() - getRadarHeading() + e.getBearing()));
		setTurnRadarRight(360 * scanDegree);	


		// Turn Turret to Target
		double targetDeg = bearingBetweenTwo(enemy.getX(), enemy.getY(), xFuture, yFuture);
		// turn the gun to the predicted x,y location
		//setTurnGunRight(efficientRotation((getHeading() - getGunHeading() + e.getBearing())-bearingDeg));
		// Moving Gun to predicted Target
		setTurnGunRight(efficientRotation(getHeading() - getGunHeading() + e.getBearing()) - targetDeg);
		// Fire Gun when Ready
		if(getGunHeat()==0 && Math.abs(getGunTurnRemaining()) < 10){
			setFire(firePower);
		}else{
			setFire(firePower);
		}
		// Track the Enemies Energy
		prevEnergy = e.getEnergy();
		execute();
	}
	public void onHitWall(HitWallEvent e){
		if (e.getBearing() > -90 && e.getBearing() <= 90){
			back(100);
		}else{
			ahead(100);
		}
	}
	public void onRobotDeath(RobotDeathEvent e) {
		if (e.getName().equals(enemy.getName())) {
			enemy.reset();
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		setTurnRight(e.getBearing()+90-30*moveDirection);
		setAhead(100);
	}

	// If another Bot Runs into my Bot
	public void onHitRobot(HitRobotEvent event) {
		if (event.getBearing() > -90 && event.getBearing() <= 90) {
			setBack(100);
		} else {
			setAhead(100);			
		}
	}
	

	// Get the Opposite Bearing
	public double oppositeBearing(double e){
		double bearing = e;
		if(bearing>180){
			bearing = bearing-180;
		}else{
			bearing = bearing+180;
		}
		return bearing;		
	}

	public double efficientRotation(double angle) {
		while (angle >  180) angle -= 360;
		while (angle < -180) angle += 360;
		return angle;
	}
	// REFRENCE #1
	// I Needed Get with the Math so I referenced the following
	// It was also a Gold Mine of Information
	// http://mark.random-article.com/weber/java/robocode/lesson4.html	
	double bearingBetweenTwo(double x1, double x2,double y1, double y2) {
		double xo = x2-x1;
		double yo = y2-y1;
		double hyp = Point2D.distance(x1, y1, x2, y2);
		double arcSin = Math.toDegrees(Math.asin(xo / hyp));
		double bearing = 0;
		System.out.println(xo+"\n "+yo+"\n "+hyp+"\n "+arcSin+"\n "+bearing);
		if (xo > 0 && yo > 0) { // both pos: lower-Left
			bearing = arcSin;
		} else if (xo < 0 && yo > 0) { // x neg, y pos: lower-right
			bearing = 360 + arcSin; // arcsin is negative here, actuall 360 - ang
		} else if (xo > 0 && yo < 0) { // x pos, y neg: upper-left
			bearing = 180 - arcSin;
		} else if (xo < 0 && yo < 0) { // both neg: upper-right
			bearing = 180 - arcSin; // arcsin is negative here, actually 180 + ang
		}
		System.out.println(xo+"\n "+yo+"\n "+hyp+"\n "+arcSin+"\n "+bearing);
		return bearing;
	}	

	public static void loadProperties(){
		//initializing Properties props
		Properties props = new Properties();
		// Try to load the file
		try {
			// load the file from specified path
			props.load(new FileInputStream("config/MyRobot1.properties"));	 
			// set each Robot Property to the specified KEY. The Key is the in String Quotes and functions like a Hash
			bulletEnergy = Double.parseDouble(props.getProperty("bulletenergy"));
			stepDistance = Integer.parseInt(props.getProperty("stepdistance"));
			rightRotationAngle = Integer.parseInt(props.getProperty("rightrotationangle"));
			leftRotationAngle = Integer.parseInt(props.getProperty("leftrotationangle"));
			// The Catch Clause
		} catch (IOException e) {
			System.out.println("Failied to Load Props");
		}

	}
}
