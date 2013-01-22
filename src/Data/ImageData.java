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
	public void setGrayValues(int[] grayValues) {
		this.grayValues = grayValues;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	
	public ImageData(int[] grayValues,int label ) {
		this.label = label;
		this.grayValues = grayValues;
	}
	
	
}
