package NeuralNetwork;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import Data.ImageData;

public class Network implements Runnable, Savable {

	private float[] desiredOutput= new float[10];
	private float error;
	private Layer[] layers = new Layer[3];
	private ImageData[][] learningData;
	private float learningRate = 0.02f;
	private int numberHiddenNodes = 20;
	private int numberInputNodes = 784;
    private int numberOutputNodes = 10;
    private int id;
	
	

	public Network(float learningRate, int numberHiddenLayerNodes,ImageData[][] learningData,int id)
	{
		this.learningRate = learningRate;
		this.learningData = learningData;
		this.id = id;
		this.numberHiddenNodes = numberHiddenLayerNodes;
		layers[2] = new Layer(numberOutputNodes, numberHiddenNodes, true);
		layers[1] = new Layer(numberHiddenNodes, numberInputNodes, true);
		layers[0] = new Layer(numberInputNodes, 1, false);
	}
	public Network(float learningRate, int numberHiddenLayerNodes)
	{
		this.learningRate = learningRate;
		this.numberHiddenNodes = numberHiddenLayerNodes;
		layers[2] = new Layer(numberOutputNodes, numberHiddenNodes, true);
		layers[1] = new Layer(numberHiddenNodes, numberInputNodes, true);
		layers[0] = new Layer(numberInputNodes, 1, false);
	}
	public Network(String path){
		loadFromFile(path);
	}

	public float[] getOutArray()
	{
		float[] a = new float[10];
		Node[] nodes = layers[2].getNodes();
		for (int i = 0; i < nodes.length; i++)
			a[i] = nodes[i].getValue();
		return a;
	}
	public float getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(float learningRate) {
		this.learningRate = learningRate;
	}

	public float getError() {
		return error;
	}
	
	public ImageData[][] getLearningData() {
		return learningData;
	}

	public void setLearningData(ImageData[][] learningData) {
		this.learningData = learningData;
	}

	public Layer getHiddenLayer() 
	{
		return this.layers[1];
	}
	
	public Layer getInputLayer() 
	{
		return this.layers[0];
	}
	
	// getter and setter section
	public int getNumberHiddenNodes() {
		return numberHiddenNodes;
	}
	
	public int getOutput()
	{
		int max = 0;
		Node[] outputNodes = getOutputLayer().getNodes();
		for(int i = 1; i < outputNodes.length;i++ )
		{
			if (outputNodes[i].getValue() > outputNodes[max].getValue())
				max = i;
		}
		return max;
	}
	
	public Layer getOutputLayer()
	{
		return this.layers[layers.length -1];
		
	}
	
	public void learn(float[] input, int expectedValue){
		for (int i= 0; i < desiredOutput.length; i++)
			desiredOutput[i] = 0f;
		desiredOutput[expectedValue]=1.0f;
		for (int i=0; i< numberInputNodes; i++)
			getInputLayer().getNodes()[i].setValue(input[i]); 
		passforward();
		
		//Compute Deltas (OutputLayer)
		float temp = 0.0f;
		float[] deltaK = new float[numberOutputNodes];
		float[] deltaJ = new float[numberHiddenNodes];
		for (int k=0; k< numberOutputNodes; k++){
			temp = getOutputLayer().getNodes()[k].getValue();
			deltaK[k] = (temp - desiredOutput[k]) * temp * (1- temp);
		}

		Node tempNode = null;
		//Update Weights connected to OutputLayer
		for (int k=0; k< numberOutputNodes; k++){
			tempNode = getOutputLayer().getNodes()[k];
				for (int j=0; j< numberHiddenNodes; j++){
				tempNode.setWeight(k, tempNode.getWeight(k) - learningRate * deltaK[k] * getHiddenLayer().getNodes()[j].getValue());
				deltaJ[k] += tempNode.getWeight(j)* deltaK[k]; //Compute DeltaJ (HiddenLayer) Part1 (Efficiency) 
			}
		}
		
		//Compute DeltaJ (HiddenLayer) Part 2
		for (int j=0; j< numberHiddenNodes; j++){
			deltaJ[j] *= getHiddenLayer().getNodes()[j].getValue() * (1 - getHiddenLayer().getNodes()[j].getValue());
		}
		//Update Weights connected to HiddenLayer
		for (int j=0; j< numberHiddenNodes; j++){
				for (int i=0; i< numberInputNodes; i++){
				tempNode = getHiddenLayer().getNodes()[j];
				tempNode.setWeight(i, tempNode.getWeight(i) - learningRate * deltaJ[j] * getInputLayer().getNodes()[i].getValue());
				}
			}
		desiredOutput[expectedValue]= 0f;
	}
	
	public void passforward()
	{
		for(int i = 1; i < layers.length; i++)
			layers[i].calcNodeValues(layers[i-1]);
		
	}

	@Override
	public void run() 
	{
		int sumTrue = 0;
		// learn
		for (int i = 0; i < learningData.length; i++)
		{
			if(i != this.id)
			{
				for(ImageData img :learningData[i])
				{
					this.learn(img.getGrayValues(), img.getLabel());
				}
			}
		}		
		//test
		for(ImageData img :learningData[id])
		{
			this.setInput(img);
			this.passforward();
			if (this.getOutput() == img.getLabel())
				sumTrue++;
		}
		this.error = 1.0f - ((float)sumTrue / (float)(learningData[id].length));
	}
	
	public void setHiddenLayer(Layer[] hiddenLayer) 
	{
		this.layers = hiddenLayer;
	}
	
	public void setInput(ImageData inputData)
	{
		float[] greyValues = inputData.getGrayValues();
		Node[] inputNodes = this.layers[0].getNodes();
		for(int i = 0; i < greyValues.length; i++ )
			inputNodes[i].setValue(greyValues[i]);
	}
	
	public void setInputLayer(Layer inputLayer)
	{
		this.layers[0] = inputLayer;
	}

	public void setNumberHiddenNodes(int numberHiddenNodes) {
		this.numberHiddenNodes = numberHiddenNodes;
	}
	
	public void saveToFile(String path){
        try {
            FileOutputStream fileStream = new FileOutputStream(path);
            DataOutputStream dataStream = new DataOutputStream(fileStream);
            
            save(dataStream);
            
            fileStream.close();
        }
        catch (Exception e){
        	e.printStackTrace();
        }

	}
	
	public void loadFromFile(String path){
        try {
            FileInputStream fileStream = new FileInputStream(path);
            DataInputStream dataStream = new DataInputStream(fileStream);
            
            load(dataStream);
            
            fileStream.close();
        }
        catch (Exception e){
        	e.printStackTrace();
        }

	}
	
	public void save(DataOutputStream dS){
        try {            
            dS.writeFloat(learningRate);
            dS.writeInt(numberInputNodes);
            dS.writeInt(numberHiddenNodes);
            dS.writeInt(numberOutputNodes);
            
            getInputLayer().save(dS);
            getHiddenLayer().save(dS);
            getOutputLayer().save(dS);
        }
        catch (Exception e){
        	e.printStackTrace();
        }
		
	}
	@Override
	public void load(DataInputStream dS) {
        try {            
        	learningRate = dS.readFloat();
        	numberInputNodes = dS.readInt();
        	numberHiddenNodes = dS.readInt();
        	numberOutputNodes = dS.readInt();
            
            layers[0] = new Layer(numberInputNodes, 1, false);
            layers[0].load(dS);
    		layers[1] = new Layer(numberHiddenNodes, numberInputNodes, true);
            layers[1].load(dS);
    		layers[2] = new Layer(numberOutputNodes, numberHiddenNodes, true);
            layers[2].load(dS);

    		
        }
        catch (Exception e){
        	e.printStackTrace();
        }
		
	}
	
	public static Network middelNets(Network[] nets)
	{
		int netCount = nets.length;
		Network newNet =  new Network(0.02f, nets[0].getNumberHiddenNodes());
		float[][][] outputWeights = new float[nets.length][][];
		float[][][] hiddenWeights = new float[nets.length][][];
		for(int i = 0; i < netCount; i++)
		{
			outputWeights[i] = nets[i].getOutputLayer().getWeights();
			hiddenWeights[i] = nets[i].getHiddenLayer().getWeights();
		}
			
		float[][] newOutputWeights = new float[10][newNet.getNumberHiddenNodes()];
		float[][] newhiddenWeights = new float[newNet.getNumberHiddenNodes()][784];
			
		// calc new weights for Output Layer
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < newNet.getNumberHiddenNodes();j++)
			{
				float sum = 0;
				for(int netnumber = 0; netnumber < nets.length; netnumber++)
				{
					sum += outputWeights[netnumber][i][j];
				}
				newOutputWeights[i][j] = sum/(float)netCount;
			}
		}
		
		// calc new weights for hidden Layer
		for(int i = 0; i < newNet.getNumberHiddenNodes(); i++)
		{
			for(int j = 0; j < 784;j++)
			{
				float sum = 0;
				for(int netnumber = 0; netnumber < nets.length; netnumber++)
				{
					sum += hiddenWeights[netnumber][i][j];
				}
				newhiddenWeights[i][j] = sum/(float)netCount;
			}
		}
		
		// set Weights and return new Network
		newNet.getHiddenLayer().setWeights(newhiddenWeights);
		newNet.getOutputLayer().setWeights(newOutputWeights);
		return newNet;
	}
	
}
