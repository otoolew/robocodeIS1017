package robocodeIS1017;
import robocopackage edu.pitt.is17.finalproject.robots;de.*;

public class AdvancedEnemyBot extends EnemyBot{
	private double x,y;
	public AdvancedEnemyBot(){
		reset();
	}
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public void update(ScannedRobotEvent e, Robot robo){
		super.update(e);
		double enemyBearing = (e.getBearing()+robo.getHeading());
		if (enemyBearing < 0 ){
			enemyBearing += 360;
		}
		x = robo.getX() + Math.sin(Math.toRadians(enemyBearing)) * e.getDistance();
		y = robo.getY() + Math.cos(Math.toRadians(enemyBearing)) * e.getDistance();
	}
	
	public double predictX(long e){
		return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * e;
	}
	
	public double predictY(long e){
		return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * e;
	}
	
	@Override
	public void reset(){
		super.reset();
	}
}
