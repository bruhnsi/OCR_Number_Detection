package NeuralNetwork;

public class Network {
	//test tes
	private Layer[] layers = new Layer[3];
	private float learningRate;
	private int numberOutputNodes = 10;
	private int numberInputNodes = 784; 
	
	// getter and setter section
	
	public Layer getHiddenLayer() 
	{
		return this.layers[1];
	}
	
	public void setHiddenLayer(Layer[] hiddenLayer) 
	{
		this.layers = hiddenLayer;
	}
	
	public Layer getOuputLayer()
	{
		return this.layers[layers.length -1];
		
	}
	
	public Layer getInputLayer() 
	{
		return this.layers[0];
	}
	
	public void setInputLayer(Layer inputLayer)
	{
		this.layers[0] = inputLayer;
	}
	

	public Network(float learningRate)
	{
		this.learningRate = learningRate;
		layers[2] = new Layer(numberOutputNodes, numberHiddenNodes, true);
		layers[1] = new Layer(numberHiddenNodes, 784, true);
		layers[0] = new Layer(numberInputNodes, 1, false);
	}
	
	public void learn(Float[] input, Float[] desiredOutput){
		Float[] deltak = new Float[numberOutputNodes];
	}
	
	public void passforward()
	{
		for(int i = 1; i < layers.length; i++)
			layers[i].calcNodeValues(layers[i-1]);
	}	
	
	
	
}
