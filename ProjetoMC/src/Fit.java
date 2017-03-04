import java.util.List;

public abstract class Fit {
	
	public List<PM> availablePMs;
	
	public Fit(List<PM> availablePMs) {
		this.availablePMs = availablePMs;
	}
	
	abstract void allocate(List<VM> virtualMachines);
}
