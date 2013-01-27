import Data.DataProvider;
import Data.ImageData;
import NeuralNetwork.Network;


public class Main {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// init Data
		DataProvider dataProvider;
		int numberNetworks = 6;
		
		if(args.length >= 1)
		{
			dataProvider = new DataProvider(args[0]);
			System.out.println("\n  data path: " + args[0]);
		}
		else
			dataProvider = new DataProvider("./bin/test.h5");
		ImageData[][] testData = dataProvider.getData(numberNetworks);
		
		
		
		// init Networks
		Network learningNets[] = new Network[numberNetworks];
		if (args.length >= 3)
		{
			// create 6 Networks with different learning data
			for(int i = 0;i < 6; i++)
			{
				learningNets[i] = new Network(Float.parseFloat(args[1]),Integer.parseInt(args[2]),dataProvider.getData(6)[i]);
			}
			System.out.println("  learning rate: " + args[1]);
			System.out.println("  number hidden nodes: " + args[2]);
		}
		else
		{
			for(int i = 0;i < 6; i++)
			{
				learningNets[i] = new Network(0.02f,20,dataProvider.getData(6)[i]);
			}
		}
		
		
		float error = 1; 
		//TODO: rotating lerningDatasets 
		//TODO: determine best learningrate
		//TODO: compare normalisation and no normalisation
		while(error > 0.01)
		{
			// creating Threads
			Thread threads[] = new Thread[learningNets.length];
			for(int i= 0; i < learningNets.length; i++)
			{
				threads[i] = new Thread(learningNets[i]);
				threads[i].start();
			}
			for(Thread t :threads)
				t.join();
			
			// switch learning data
			
			
			
			// middel all nets
			Network net = middelNets(learningNets);
			
			// testing the net
			int sumTrue = 0;
			for(ImageData img :testData)
			{
				net.setInput(img);
				net.passforward();
				if((int) img.getLabel() == net.getOutput())
					sumTrue++;
			}
		    error = (1.0f - ((float)sumTrue/ (float)testData.length));
		    System.out.println("Error: "+error);
		}
	}
		
	public static Network middelNets(Network[] nets)
	{
		int netCount = nets.length;
		Network newNet =  new Network(0.02f, nets[0].getNumberHiddenNodes(), null);
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
