package entity;

public class point {

	private String name;
	private float[] value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float[] getValue() {
		return value;
	}
	public void setValue(float[] value) {
		this.value = value;
	}
	
	public int getDimension() {
		return value.length;
	}
	
}
