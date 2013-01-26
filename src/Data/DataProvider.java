package Data;

import ch.systemsx.cisd.hdf5.HDF5Factory;
import ch.systemsx.cisd.hdf5.IHDF5Reader;

public class DataProvider 
{
	private String path;
	private ImageData[] data;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public DataProvider(String path)
	{
		this.path = path;
		this.data = readData();
	}
	

	public ImageData[][] getData(int numberOfParts)
	{
		int dataLenght = data.length;
		int count = (int) dataLenght / numberOfParts ;
		ImageData[][] learnData = new ImageData[numberOfParts][count];
		for(int i = 0; i <  numberOfParts; i++)
		{
			for(int j = 0; j < count; j++)
			learnData[i][j] = data[i*count+j];
		}
		return learnData;
	}
	
	private ImageData[] readData(){
			try{
				IHDF5Reader reader = HDF5Factory.openForReading(path);
				 float[][] data = reader.readFloatMatrix("data/data");
				 float[] labels = reader.readFloatArray("data/label");
				 reader.close();
				 ImageData[] imageDataArray = new ImageData[labels.length];
				 if(labels.length == data[0].length)
				 {
					 for(int i = 0; i < labels.length; i++)
					 {
						 float greyValues[] = new float[data.length];
						 for(int j=0; j < data.length ;j++)
						 {
							 if(data[j][i] > 40)
								 greyValues[j] = 1;
							 else
								 greyValues[j] = 0;
						 }
						 imageDataArray[i] = new ImageData(greyValues, (int) labels[i]);
					 }
					 shuffle(imageDataArray);
					 return imageDataArray;
				 } else
					 return null;
			}catch(Exception e)
			{
				return null;
			}
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
