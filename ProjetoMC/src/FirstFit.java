import java.util.List;

public class FirstFit extends Fit {

	public FirstFit(List<PM> availablePMs) {
		super(availablePMs);
	}

	@Override
	void allocate() {
		//First Fit: It is a greedy approach which scheduler considers the PMs sequentially,
		//one by one, and places the VM to the first PM that has enough resources.
		// TODO Auto-generated method stub
		
	}

}
