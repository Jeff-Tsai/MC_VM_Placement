import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
					ArrayList<Integer> sortedTriedFull = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); 
					//System.out.println(triedPMs.toString());
					while(triedPMs.size()<10 && triedPMs.contains(index)) {
						index = rand.nextInt(10);
					}
					break;
//					do {
//						index = rand.nextInt(10);
//						System.out.println(triedPMs.size());
//					}while(triedPMs.size()<10 && triedPMs.contains(index)) ;
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
