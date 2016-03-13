package game;

public class BaseStats {
	private int hitPoints;
	private double defense;
	private String name;
	
	public BaseStats(int hitPoints, double defense, String name){
		this.hitPoints = hitPoints;
		this.defense = defense;
		this.name = name;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	
	public void removeHitPoints(int dmg){
		this.hitPoints = (int)(this.hitPoints - (dmg -(dmg * this.defense)));
		if(this.hitPoints<0){
			this.hitPoints = 0;
		}
	}
	
	public boolean isAlive(){
		return this.hitPoints>0;
	}
}