import Data.DataProvider;
import Data.ImageData;




public class Main {
	//test von mir
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataProvider dataProvider = new DataProvider("./bin/test.h5");
		ImageData[] data = dataProvider.readData();
		System.out.print(data[0]);
	}

}
