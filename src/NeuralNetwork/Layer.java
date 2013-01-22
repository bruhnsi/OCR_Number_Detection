package NeuralNetwork;

import java.util.Random;



public class Layer {
	

	private Node[] nodes;
	private float[][] weights;
	

	// Getter and Setter section  
	public Node[] getNodes()
	{
		return this.nodes;
	}
	
	public boolean setNodes(Node[] nodes)
	{
		if(nodes != null)
		{
			this.nodes = nodes;
			return true;
		}else
			return false;
	}
	
	public float[][] getWeights() {
		return weights;
	}

	public void setWeights(float[][] weights) {
		this.weights = weights;
	}
	
	// Constructor
	Layer(int numberOfNodes)
	{
		this.initLayer(numberOfNodes);
	}
	
	// Object methods section
	
	public void initRandomWeights(float min, float max)
	{
		Random randomGenerator = new Random();
		for(float[] weightsArray :weights)
		{
			for(@SuppressWarnings("unused") float weight :weightsArray)
			{
				weight = randomGenerator.nextFloat()*(max-min) + min;
			}
		}
	}
	
	// init function
	public void initLayer(int numberOfNodes)
	{
		for( int i = 0; i < numberOfNodes; i++)
		{
			initRandomWeights(-1, 1);
			nodes[i] = new Node();
			nodes[i].setWeights(weights[i]);
		}
	}
	
	public void calcNodeValues()
	{
		
	}
	
	// function for updating all weights on this Layer
	public void updateWeights()
	{
	}
	
	
	
}
