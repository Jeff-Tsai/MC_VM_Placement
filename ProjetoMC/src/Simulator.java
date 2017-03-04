import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	
	public static List<VM> traceVMS = new ArrayList<VM>();
	
	public static void main(String[] args) {
		
		Server server = new Server(10, 0.55f, 0.55f);
		
		//Fit fit = new FirstFit(server.getAvailablePMs());
		Fit fit = new RandomFit(server.getAvailablePMs());
		
		String csvFile = "trace_reduzido_test";
        String line = "";
        String splitter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	br.readLine(); //eliminate header

            while ((line = br.readLine()) != null) {

                // use comma as separator
            	String[] params = line.split(splitter);
                Float cpuReq = Float.parseFloat(params[6]);
                Float memReq = Float.parseFloat(params[7]);
                
                VM newVM = new VM(cpuReq, memReq);
                traceVMS.add(newVM);
                //System.out.println(newVM.getCpuReq() + " " + newVM.getMemoryReq());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        fit.allocate(traceVMS);
        
        for (int i = 0; i < server.getAvailablePMs().size(); i++) {
			System.out.println(server.getAvailablePMs().get(i).getAllocatedVMs().toString());
		}
	}

}
