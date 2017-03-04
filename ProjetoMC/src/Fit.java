import java.util.List;

public abstract class Fit {
	
	public int allocatedVMCounter;
	public int rejectedVMCounter;
	public List<PM> availablePMs;
	
	public Fit(List<PM> availablePMs) {
		this.availablePMs = availablePMs;
	}
	
	public int getAllocatedVMCounter() {
		return allocatedVMCounter;
	}
	
	public int getRejectedVMCounter() {
		return rejectedVMCounter;
	}
	
	abstract void allocate(List<VM> virtualMachines);
	abstract String getAlgorithmName();
}
