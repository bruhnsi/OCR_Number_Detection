package NeuralNetwork;

public class Network {

	private Layer[] layers;
	
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
	
	public void iShouldNotExist(){
		//With Comment
	}
	
	public void passforeward()
	{
		for(int i = 1; i < layers.length; i++)
			layers[i].calcNodeValues(layers[i-1]);
	}	
}
