import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	
	public static List<VM> traceVMS = new ArrayList<VM>();
	final public static int NUMBER_OF_PMS = 10;
	final public static float MEM_CAP_OF_PM = 0.55f;
	final public static float CPU_CAP_OF_PM = 0.55f;
	
	public static void main(String[] args) {
		
		Server server = new Server(NUMBER_OF_PMS, CPU_CAP_OF_PM, MEM_CAP_OF_PM);
		
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
        generateLog(server,fit);
	}
	
	public static void generateLog(Server server, Fit fitAlgorithm) {
		
		Float memFragmentation = 0.0f;
		Float cpuFragmentation = 0.0f;

        for (int i = 0; i < server.getAvailablePMs().size(); i++) {
        	cpuFragmentation += server.getAvailablePMs().get(i).getCpuCapacity();
        	memFragmentation += server.getAvailablePMs().get(i).getMemCapacity();
		}
        
		try{
		    PrintWriter writer = new PrintWriter("collected_data.txt", "UTF-8");
		    writer.println("Number of Phisical Machines available: " + server.getAvailablePMs().size());
		    
		    writer.println("PMs Settings:");
		    writer.println("Memory Capacity: " + MEM_CAP_OF_PM);
		    writer.println("CPU Capacity: " + CPU_CAP_OF_PM);
		    
		    writer.println("Placement Algorithm: " + fitAlgorithm.getAlgorithmName());
		    writer.println("Total Memory Fragmentation: " + memFragmentation);
		    writer.println("Total CPU Fragmentation: " + memFragmentation);
		    
		    writer.println("Number of allocated VMs: " + fitAlgorithm.getAllocatedVMCounter());
		    writer.println("Number of Rejected VMs: " + fitAlgorithm.getRejectedVMCounter());

		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
