import Data.DataProvider;
import Data.ImageData;
import NeuralNetwork.Network;




public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataProvider dataProvider = new DataProvider("./bin/test.h5");
		ImageData[] learningData = dataProvider.getLerntData();
		ImageData[] testData = dataProvider.getTestData();
		
		Network net = new Network(0.01f);
		float error = 1; 
		//TODO: determine best learningrate
		//TODO: use Crossvalidation
		//TODO: compare normalisation and no normalisation
		//TODO: dynamic learnrate
		while(error > 0.01)
		{
			for(ImageData img :learningData)
			{
				net.learn(img.getGrayValues(), img.getLabel());
			}
			float sumTrue = 0;
			for(ImageData img :testData)
			{
				net.setInput(img);
				net.passforward();
				if (net.getOutput() == (int) img.getLabel())
					sumTrue++;
			}
			error = 1 - (sumTrue / testData.length);
			System.out.println(error);
		}
		
	}
	
	

}
