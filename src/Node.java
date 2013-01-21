

public class Node {
	
	// object properties
	private Layer layer;
	private float value;
	private float[] weights;
	
	// getter and setter section 
	public Layer getLayer() {
		return layer;
	}
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float[] getWeights() {
		return this.weights;
	}
	public void setWeights(float[] weights) {
		this.weights = weights;
	}
	
	
	
	

}
