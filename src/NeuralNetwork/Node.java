package NeuralNetwork;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Node implements Savable{
	
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
	public float getWeight(Integer i) {
		return this.weights[i];
	}
	public void setWeight(Integer i, float weights) {
		this.weights[i] = weights;
	}
	

	
	public void calcValue(Layer layerBefore)
	{
		Node[] nodesBefore = layerBefore.getNodes();
		float sum = 0;
		for(int i = 0; i < nodesBefore.length ;i++)
		{
			sum += nodesBefore[i].getValue() * weights[i];
		}
		this.value = (float) ( 1 / (1+ Math.pow(Math.E,-sum)));
	}
	@Override
	public void save(DataOutputStream dS) {
		try {
			for (int i = 0; i < weights.length; i++) {
				dS.writeFloat(weights[i]);
			}
        }
        catch (Exception e){
        	e.printStackTrace();
        }
		
	}
	@Override
	public void load(DataInputStream dS) {
		try {
			for (int i = 0; i < weights.length; i++) {
				weights[i] = dS.readFloat();
			}
        }
        catch (Exception e){
        	e.printStackTrace();
        }
	}
	
}
