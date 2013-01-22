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
		try
		{
			IHDF5Reader reader = HDF5Factory.openForReading("./bin/test.h5");
			 int[][] data = reader.readIntMatrix("data/data");
			 float[] labels = reader.readFloatArray("data/label");
			 reader.close();
			 if(labels.length == data[0].length)
			 {
				 for(int i = 0; i < labels.length; i++)
				 {
					 
				 }
			 }
		}
		
		 
	}
	
}
