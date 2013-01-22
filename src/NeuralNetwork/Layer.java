package NeuralNetwork;

import java.util.Random;



public class Layer {
	

	private Node[] nodes;
	// First weights[X] = Array of weights for Node nodes[1]
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
	Layer(int numberOfNodes,int numberOfNodesLayerBefore)
	{
		this.initLayer(numberOfNodes,numberOfNodesLayerBefore);
	}
	
	// Object methods section
	
	private void initRandomWeights(float min, float max, int numberOfNodesBeforeLayer)
	{
		weights = new float[nodes.length][numberOfNodesBeforeLayer];
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
	private void initLayer(int numberOfNodes,int numberOfNodesBeforeLayer)
	{
		nodes = new Node[numberOfNodes];
		for( int i = 0; i < numberOfNodes; i++)
		{
			initRandomWeights(-1, 1,numberOfNodesBeforeLayer);
			nodes[i] = new Node();
			nodes[i].setWeights(weights[i]);
		}
	}
	
	public void calcNodeValues(Layer layerBefore)
	{
		for(int i = 0; i < nodes.length; i++)
			nodes[i].calcValue(layerBefore);
			
	}
	
	// function for updating all weights on this Layer
	public void updateWeights()
	{
	}
	
	
	
}
