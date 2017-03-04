import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFit extends Fit{


	public RandomFit(List<PM> availablePMs) {
		super(availablePMs);
	}

	@Override
	void allocate(List<VM> virtualMachines) {
		boolean allocated = false;
		
		for(int i = 0; i < virtualMachines.size(); i++) {
			ArrayList<Integer> triedPMs = new ArrayList<Integer>();
			VM virtualMachine = virtualMachines.get(i);
			
			Random rand = new Random();
			int index = rand.nextInt(10);
			
			while(true) {
				
				PM physicalMachine = availablePMs.get(index);
				if(virtualMachine.getMemoryReq() < physicalMachine.getMemCapacity() &&
						virtualMachine.getCpuReq() < physicalMachine.getCpuCapacity()) {
					triedPMs.add(index);
					physicalMachine.allocateVirtualMachine(virtualMachine);
					allocated = true;
					triedPMs.clear();
					break;
				}else {
					triedPMs.add(index);
					do {
						index = rand.nextInt(10);
					}while(triedPMs.contains(index));
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
		return "Random Fit";
	}

}
