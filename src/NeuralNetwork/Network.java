package NeuralNetwork;

public class Network {
	//test
	private Layer[] layers = new Layer[3];
	private float learningRate;
	private int numberOutputNodes = 10;
	private int numberInputNodes = 784; 
	
	// getter and setter section
	
	public Layer[] getHiddenLayer() 
	{
		return layers;
	}
	
	public void setHiddenLayer(Layer[] hiddenLayer) 
	{
		this.layers = hiddenLayer;
	}
	
	public Layer getOuputLayer()
	{
		return this.layers[layers.length -1];
		
	}
	
	public void setInputLayer(Layer inputLayer)
	{
		this.layers[0] = inputLayer;
	}
	
	public Network(float learningRate)
	{
		this.learningRate = learningRate;
	}
	
	public void passforeward()
	{
		for(int i = 1; i < layers.length; i++)
			layers[i].calcNodeValues(layers[i-1]);
	}	
}