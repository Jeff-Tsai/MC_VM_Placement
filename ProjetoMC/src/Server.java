import java.util.ArrayList;
import java.util.List;

public class Server {
	
	public List<PM> availablePMs;
	public int rejectedVMCounter;
	public int allocatedVMCounter;
	
	public Server(int numberOfPMs, Float PMCpuCapacity, Float PMMemCapacity) {
		this.availablePMs = new ArrayList<PM>();
		for(int i=0; i < numberOfPMs; i++) {
			this.availablePMs.add(new PM(PMCpuCapacity, PMMemCapacity));
		}
	};

}
