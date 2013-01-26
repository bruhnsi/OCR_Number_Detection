package NeuralNetwork;

import java.io.DataOutputStream;
import java.io.DataInputStream;

public interface Savable {
	public void save(DataOutputStream dS);
	public void load(DataInputStream dS);
}
