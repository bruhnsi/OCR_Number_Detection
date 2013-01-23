import Data.DataProvider;
import Data.ImageData;
import NeuralNetwork.Network;

public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// init Data
		DataProvider dataProvider;
		
		if(args.length >= 1)
		{
			dataProvider = new DataProvider(args[0]);
			System.out.println("\n  data path: " + args[0]);
		}
		else
			dataProvider = new DataProvider("./bin/test.h5");
		ImageData[] learningData = dataProvider.getLerntData();
		ImageData[] testData = dataProvider.getTestData();
		
		
		
		// init Network
		Network net;
		if (args.length >= 3)
		{
			net = new Network(Float.parseFloat(args[1]), Integer.parseInt(args[2]));
			System.out.println("  learning rate: " + args[1]);
			System.out.println("  number hidden nodes: " + args[2]);
		}
		else
		    net = new Network(0.02f, 20);
		float error = 1; 
		//TODO: determine best learningrate
		//TODO: use Crossvalidation
		//TODO: compare normalisation and no normalisation
		//TODO: dynamic learnrate
		int loopCount = 0;
		while(error > 0.01)
		{
			loopCount++;
			System.out.println("\n******** Durchlauf: "+loopCount+" ********\n");
			int sumLearn = 0;
			for(ImageData img :learningData)
			{
				net.learn(img.getGrayValues(), img.getLabel());
				if((int) img.getLabel() == net.getOutput())
					sumLearn++;
			}
			System.out.println("Error(LernDaten): "+ (1.0f - ((float)sumLearn / (float)learningData.length)));
			int sumTrue = 0;
			for(ImageData img :testData)
			{
				net.setInput(img);
				net.passforward();
				if (net.getOutput() == (int) img.getLabel())
				{
					sumTrue++;
				}
			}
			error = 1 - ((float)sumTrue / (float)testData.length);
			System.out.println("Error(Testdaten): " + error);
		}
		
	}
	
	

}
