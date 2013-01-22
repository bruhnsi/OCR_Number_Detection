package Data;

import ch.systemsx.cisd.hdf5.HDF5Factory;
import ch.systemsx.cisd.hdf5.IHDF5Reader;

public class DataProvider 
{
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public DataProvider(String path)
	{
		this.path = path;
	}
	
	public ImageData[] readData(){
			try{
				IHDF5Reader reader = HDF5Factory.openForReading(path);
				 int[][] data = reader.readIntMatrix("data/data");
				 float[] labels = reader.readFloatArray("data/label");
				 reader.close();
				 ImageData[] imageDataArray = new ImageData[labels.length];
				 if(labels.length == data[0].length)
				 {
					 for(int i = 0; i < labels.length; i++)
					 {
						 int greyValues[] = new int[data.length];
						 for(int j=0; j < data.length ;j++)
							 greyValues[j] = data[j][i];
						 imageDataArray[i] = new ImageData(greyValues, (int) labels[i]);
					 }
					 return imageDataArray;
				 } else
					 return null;
			}catch(Exception e)
			{
				return null;
			}
	}
	
}
