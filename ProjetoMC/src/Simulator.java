
public class Simulator {
	
	public static void main(String[] args) {
		
		Server server = new Server(10, 0.55f, 0.55f);
		
		FirstFit firstFitAlgorithm = new FirstFit(server.getAvailablePMs());
		
		//firstFitAlgorithm.allocate();
		
	}

}
