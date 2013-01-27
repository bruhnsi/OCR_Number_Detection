package Data;

import java.awt.image.BufferedImage;

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
	
	public BufferedImage getImage(){
		BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
		int color = 0;
		for (int i = 0; i < grayValues.length; i++) {
			color = (int) (grayValues[i]);
			color = color + (color << 16) + (color << 32);
			image.setRGB(i % 28, i/28, color);
		}
		return image;
	}
	
	@Override 
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.label+ "\n\n");
		//for(float grayValue :this.grayValues)
			//sb.append(grayValue+ " ");
		for (int i = 0; i < grayValues.length; i++) {
			if (i % 28 == 0)
				sb.append("\n");
			sb.append(grayValues[i]+ " ");
			
		}
		return sb.toString();
	}
	
	
}
