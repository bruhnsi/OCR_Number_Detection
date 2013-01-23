package Data;

public class ImageData {
	
	// Array of Gray pixel Values 0 - 255
	private float grayValues[]; 
	// Classification of an Image 0-9
	private int label;
	
	// Getters and Setters
	public float[] getGrayValues() {
		return grayValues;
	}
	public int getLabel() {
		return label;
	}
	
	
	public ImageData(float[] grayValues,int label ) {
		this.label = label;
		this.grayValues = grayValues;
	}
	
	@Override 
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.label+ "\n\n");
		for(float grayValue :this.grayValues)
			sb.append(grayValue+ " ");
		return sb.toString();
	}
	
	
}
