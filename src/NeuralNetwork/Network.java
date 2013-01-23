package NeuralNetwork;


import Data.ImageData;

public class Network {
	//test tes
	private Layer[] layers = new Layer[3];
	private float learningRate;
	private int numberOutputNodes = 10;
	private int numberInputNodes = 784;
	private int numberHiddenNodes = numberOutputNodes*2; 
	
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
	
	public int getOutput()
	{
		int max = 0;
		Node[] outputNodes = getOuputLayer().getNodes();
		for(int i = 1; i < outputNodes.length;i++ )
		{
			if (outputNodes[i].getValue() > outputNodes[max].getValue())
				max = i;
		}
		return max;
	}
	

	public Network(float learningRate)
	{
		this.learningRate = learningRate;
		layers[2] = new Layer(numberOutputNodes, numberHiddenNodes, true);
		layers[1] = new Layer(numberHiddenNodes, 784, true);
		layers[0] = new Layer(numberInputNodes, 1, false);
	}
	
	public void learn(float[] input, Float[] desiredOutput){
		for (Integer i=0; i< numberInputNodes; i++)
			getInputLayer().getNodes()[i].setValue(input[i]); 
		passforward();
		
		//Compute Deltas (OutputLayer)
		Float temp = 0.0f;
		Float[] deltaK = new Float[numberOutputNodes];
		Float[] deltaJ = new Float[numberOutputNodes*2];
		for (Integer k=0; k< numberOutputNodes; k++){
			temp = getOuputLayer().getNodes()[k].getValue();
			deltaK[k] = (desiredOutput[k] - temp) * temp * (1- temp);
			for (Integer j=0; j< numberHiddenNodes; j++){
				deltaJ[j] += getHiddenLayer().getNodes()[j].getWeights()[k]* deltaK[k];
			}
		}
		//Compute Deltas (HiddenLayer)
		for (Integer j=0; j< numberHiddenNodes; j++){
			deltaJ[j] *= getHiddenLayer().getNodes()[j].getValue() * (1 - getHiddenLayer().getNodes()[j].getValue());
		}

		Node tempNode = null;
		//Update Weights connected to OutputLayer
		for (Integer j=0; j< numberHiddenNodes; j++){
				for (Integer k=0; k< numberOutputNodes; k++){
				tempNode = getHiddenLayer().getNodes()[j];
				tempNode.setWeight(k, tempNode.getWeight(k) - learningRate * deltaK[k] * tempNode.getValue());
				}
			}
		//Update Weights connected to HiddenLayer
		for (Integer i=0; i< numberInputNodes; i++){
				for (Integer j=0; j< numberHiddenNodes; j++){
				tempNode = getInputLayer().getNodes()[i];
				tempNode.setWeight(j, tempNode.getWeight(j) - learningRate * deltaJ[j] * tempNode.getValue());
				}
			}
	
	
	}
	
	public void setInput(ImageData inputData)
	{
		float[] greyValues = inputData.getGrayValues();
		Node[] inputNodes = this.layers[0].getNodes();
		for(int i = 0; i < greyValues.length; i++ )
			inputNodes[i].setValue(greyValues[i]);
	}
	
	public void passforward()
	{
		for(int i = 1; i < layers.length; i++)
			layers[i].calcNodeValues(layers[i-1]);
		
	}	
	
	
	
}
