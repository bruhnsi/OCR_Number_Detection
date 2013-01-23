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
	
	public void learn(float[] input, float[] desiredOutput){
		for (int i=0; i< numberInputNodes; i++)
			getInputLayer().getNodes()[i].setValue(input[i]); 
		passforward();
		
		//Compute Deltas (OutputLayer)
		float temp = 0.0f;
		float[] deltaK = new float[numberOutputNodes];
		float[] deltaJ = new float[numberOutputNodes*2];
		for (int k=0; k< numberOutputNodes; k++){
			temp = getOuputLayer().getNodes()[k].getValue();
			deltaK[k] = (desiredOutput[k] - temp) * temp * (1- temp);
			for (int j=0; j< numberHiddenNodes; j++){
				deltaJ[j] += getHiddenLayer().getNodes()[j].getWeight(k)* deltaK[k];
			}
		}

		Node tempNode = null;
		//Update Weights connected to OutputLayer
		for (int j=0; j< numberHiddenNodes; j++){
				for (int k=0; k< numberOutputNodes; k++){
				tempNode = getHiddenLayer().getNodes()[j];
				tempNode.setWeight(k, tempNode.getWeight(k) - learningRate * deltaK[k] * tempNode.getValue());
				deltaJ[j] += tempNode.getWeight(k)* deltaK[k]; //Compute DeltaJ (HiddenLayer) Part1 (Efficiency) 
			}
		}
		
		//Compute DeltaJ (HiddenLayer) Part 2
		for (int j=0; j< numberHiddenNodes; j++){
			deltaJ[j] *= getHiddenLayer().getNodes()[j].getValue() * (1 - getHiddenLayer().getNodes()[j].getValue());
		}
		//Update Weights connected to HiddenLayer
		for (int i=0; i< numberInputNodes; i++){
				for (int j=0; j< numberHiddenNodes; j++){
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
