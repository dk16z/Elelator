package domain;

public class Persons {
	
	private int startFloor;
	private int endFloor;
	private int weight;
	private static int ID=0;
	private int id=0;
	public PersonState isUp;
	public Persons(int startFloor, int endFloor, int weight, PersonState isUp) {
		super();
		this.startFloor = startFloor;
		this.endFloor = endFloor;
		this.weight = weight;
		ID++;
		this.isUp = isUp;
		id=ID;
	}
	public int getStartFloor() {
		return startFloor;
	}
	public void setStartFloor(int startFloor) {
		this.startFloor = startFloor;
	}
	public int getEndFloor() {
		return endFloor;
	}
	public void setEndFloor(int endFloor) {
		this.endFloor = endFloor;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getId() {
		return id;
	}

	public PersonState getIsUp() {
		return isUp;
	}
	public void setIsUp(PersonState isUp) {
		this.isUp = isUp;
	}
	
	

}
