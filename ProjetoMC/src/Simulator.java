import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	
	public static List<VM> traceVMS = new ArrayList<VM>();
	final public static int NUMBER_OF_PMS = 10;

	
	public static void main(String[] args) {
		
		
		float MEM_CAP_OF_PM = Float.parseFloat(args[1]);
		float CPU_CAP_OF_PM = Float.parseFloat(args[1]);
		String type_of_VM = args[2];
		int numberOfRequests = 0;

		Server server = new Server(NUMBER_OF_PMS, CPU_CAP_OF_PM, MEM_CAP_OF_PM);
		
        Fit fit;
		if(args[0].equals("first-fit")) {
			fit = new FirstFit(server.getAvailablePMs());
		}else {
			fit = new RandomFit(server.getAvailablePMs());
		}

		
		String csvFile = "trace_reduzido.txt";
        String line = "";
        String splitter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	br.readLine(); //eliminate header

            while ((line = br.readLine()) != null) {
            	String[] params = line.split(splitter);
            	String type = params[8].replace("\"", "");
                // use comma as separator
            	if(type.equals(type_of_VM)) {
                    Float cpuReq = Float.parseFloat(params[6]);
                    Float memReq = Float.parseFloat(params[7]);
                    VM newVM = new VM(cpuReq, memReq);
                    traceVMS.add(newVM);
                    numberOfRequests++;
            	}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		fit.allocate(traceVMS);
//		for (PM pm : server.availablePMs) {
//			System.out.println(pm.getAllocatedVMs().toString());
//		}
        generateLog(server,fit, MEM_CAP_OF_PM, CPU_CAP_OF_PM, args[0], type_of_VM, numberOfRequests);
	}
	
	public static void generateLog(Server server, Fit fitAlgorithm, float memCap, float cpuCap, String fileName, String typeOfVM, int numberOfRequests) {
		
		Float memFragmentation = 0.0f;
		Float cpuFragmentation = 0.0f;

        for (int i = 0; i < server.getAvailablePMs().size(); i++) {
        	cpuFragmentation += server.getAvailablePMs().get(i).getCpuCapacity();
        	memFragmentation += server.getAvailablePMs().get(i).getMemCapacity();
		}
        
		try{
			String s = System.lineSeparator();
		    FileWriter writer = new FileWriter("../Collected_data.txt", true);
		    writer.append("Number of Phisical Machines available: " + server.getAvailablePMs().size());
		    writer.append(s);
		    writer.append("PMs Settings: \n");
		    writer.append(s);
		    writer.append("Memory Capacity: " + memCap + "\n");
		    writer.append(s);
		    writer.append("CPU Capacity: " + cpuCap + "\n");
		    writer.append(s);

		    writer.append("Placement Algorithm: " + fitAlgorithm.getAlgorithmName() + "\n");
		    writer.append(s);

		    writer.append("Total Memory Fragmentation: " + memFragmentation + "\n");
		    writer.append(s);

		    writer.append("Total CPU Fragmentation: " + memFragmentation + "\n");
		    writer.append(s);

		    writer.append("Type of VM requested: " + typeOfVM + "\n");
		    writer.append(s);

		    writer.append("Total requests " + numberOfRequests + "\n");
		    writer.append(s);

		    writer.append("Number of allocated VMs: " + fitAlgorithm.getAllocatedVMCounter() + "\n");
		    writer.append(s);

		    writer.append("Number of Rejected VMs: " + fitAlgorithm.getRejectedVMCounter() + "\n");
		    writer.append(s);
		    writer.append(s);


		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
