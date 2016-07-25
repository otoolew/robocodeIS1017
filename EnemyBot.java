package robocodeIS1017;
import robocode.*;
public class EnemyBot {
	private String name;
	private double bearing;
	private double distance;
	private double energy;
	private double heading;
	private double velocity;
	public EnemyBot(){
		reset();
	}

	public void update(ScannedRobotEvent enemy){
		name = enemy.getName();
		bearing = enemy.getBearing();
		distance = enemy.getDistance();
		energy = enemy.getEnergy();
		heading = enemy.getHeading();
		velocity = enemy.getVelocity();
	}
	
	public void reset(){
		name = "";
		bearing = 0.0;
		distance = 0.0;
		energy = 0.0;
		heading = 0.0;
		velocity = 0.0;
	}
	
	public boolean none(){
		return name.equals("");
	}
	
	public String getName() {
		return name;
	}
	public double getBearing() {
		return bearing;
	}
	public double getDistance() {
		return distance;
	}
	public double getEnergy() {
		return energy;
	}
	public double getHeading() {
		return heading;
	}
	public double getVelocity() {
		return velocity;
	}
}
