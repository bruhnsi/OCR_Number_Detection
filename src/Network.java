import java.util.List;


public class Network {
	
	private Layer inputLayer;
	private Layer outputLayer;
	private List<Layer> hiddenLayer;
	
	// getter and setter section
	public Layer getInputLayer() 
	{
		return inputLayer;
	}
	
	public void setInputLayer(Layer inputLayer) 
	{
		this.inputLayer = inputLayer;
	}
	
	public Layer getOutputLayer() 
	{
		return outputLayer;
	}
	
	public void setOutputLayer(Layer outputLayer) 
	{
		this.outputLayer = outputLayer;
	}
	
	public List<Layer> getHiddenLayer() 
	{
		return hiddenLayer;
	}
	
	public void setHiddenLayer(List<Layer> hiddenLayer) 
	{
		this.hiddenLayer = hiddenLayer;
	}
	
	
	
	
}
