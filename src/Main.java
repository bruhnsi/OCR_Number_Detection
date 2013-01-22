import Data.DataProvider;
import Data.ImageData;




public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataProvider dataProvider = new DataProvider("./bin/test.h5");
		ImageData[] data = dataProvider.readData();
		shuffle(data);
		System.out.print(data[0]);
	}
	
	// Shuffles an array
    private static <T> void shuffle(T[] array) {
        for (int i = array.length; i > 1; i--) {
                T temp = array[i - 1];
                int randIx = (int) (Math.random() * i);
                array[i - 1] = array[randIx];
                array[randIx] = temp;
        }
    }
}
