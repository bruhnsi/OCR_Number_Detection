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
		
		Network net = new Network(0.2f);
		float error = 1; 
		int countLearningData = learningData.length;
		int countTestData = testData.length;
		while(error > 0.2)
		{
			for(ImageData img :learningData)
			{
				net.learn(img.getGrayValues(), img.getLabel());
			}
			for(ImageData img :testData)
			{
				net
			}
		}
	}
	
	

}
