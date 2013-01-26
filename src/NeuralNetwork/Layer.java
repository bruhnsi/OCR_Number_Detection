package NeuralNetwork;

import java.util.Random;



public class Layer {
	

	private Node[] nodes;
	

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
	
	public float[][] getWeights()
	{
		float[][] weights = new float[nodes.length][];
		for(int i = 0; i < nodes.length; i++)
			weights[i] = nodes[i].getWeights();
		return weights;
	}
	
	public void setWeights(float[][] weights)
	{
		for(int i = 0; i < nodes.length; i++)
			nodes[i].setWeights(weights[i]);
	}
	
	
	
	// Constructor
	Layer(int numberOfNodes,int numberOfNodesLayerBefore, boolean random)
	{
		this.initLayer(numberOfNodes,numberOfNodesLayerBefore,random);
	}
	
	// init function
	private void initLayer(int numberOfNodes,int numberOfNodesBeforeLayer,boolean random)
	{
		nodes = new Node[numberOfNodes];
		for( int i = 0; i < numberOfNodes; i++)
		{
			nodes[i] = new Node();
			if(random)
				nodes[i].setWeights(getRandomWeights(nodes[i], -0.1f , 0.1f , numberOfNodesBeforeLayer));
			else
			{
				float[] weights = new float[numberOfNodesBeforeLayer];
				for (int j = 0; j < weights.length; j++) 
				{
					weights[j] = 1;
				}
				nodes[i].setWeights(weights);
			}
		}
	}
	
	private float[] getRandomWeights(Node node,float min, float max, int numberOfNodesBeforeLayer)
	{
		float[] weights = new float[numberOfNodesBeforeLayer];
		Random randomGenerator = new Random();
		for(int i =  0; i < weights.length; i++)
		{
			weights[i] = randomGenerator.nextFloat()*(max-min) + min;
		}
		return weights;
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
