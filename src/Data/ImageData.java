package Data;

public class ImageData {
	
	// Array of Gray pixel Values 0 - 255
	private int grayValues[]; 
	// Classification of an Image 0-9
	private int label;
	
	// Getters and Setters
	public int[] getGrayValues() {
		return grayValues;
	}
	public int getLabel() {
		return label;
	}
	
	
	public ImageData(int[] grayValues,int label ) {
		this.label = label;
		this.grayValues = grayValues;
	}
	
	@Override 
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.label+ "\n\n");
		for(int grayValue :this.grayValues)
			sb.append(grayValue+ " ");
		return sb.toString();
	}
	
	
}
