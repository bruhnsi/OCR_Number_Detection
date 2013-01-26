package Data;

import NeuralNetwork.Network;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Db4oNetworkPersister {
	
	private ObjectContainer db;
	
	public Db4oNetworkPersister(String path)
	{
		db = Db4oEmbedded.openFile(path);
	}
	
	public void storeNetwork(Network net)
	{
		db.store(net);
	}
	
	public Network[] loadNetworks()
	{
		ObjectSet<Network> result = db.queryByExample(Network.class);
		return (Network[]) result.toArray();
	}
	
	
}
