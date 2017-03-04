import java.util.List;

public class FirstFit extends Fit {


	
	public FirstFit(List<PM> availablePMs) {
		super(availablePMs);
	}

	@Override
	public void allocate(List<VM> virtualMachines) {
		//First Fit: It is a greedy approach which scheduler considers the PMs sequentially,
		//one by one, and places the VM to the first PM that has enough resources.
		// TODO Auto-generated method stub
		
		boolean allocated = false;
		
		for(int i = 0; i < virtualMachines.size(); i++) {
			VM virtualMachine = virtualMachines.get(i);
			
			for(int j = 0; j < availablePMs.size(); j++) {
				PM physicalMachine = availablePMs.get(j);
				
				if(virtualMachine.getMemoryReq() < physicalMachine.getMemCapacity() &&
				   virtualMachine.getCpuReq() < physicalMachine.getCpuCapacity()) {
					physicalMachine.allocateVirtualMachine(virtualMachine);
					allocated = true;
					break;
				}
			}
			if(allocated){
				this.allocatedVMCounter++;
				allocated = false;
			} else {
				this.rejectedVMCounter++;
			}
		}	
	}
	
	@Override
	public String getAlgorithmName() {
		return "First Fit";
	}

}
