
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PMTest {
    
    public PM physicalMachine;
    
    public VM virtualMachine1;
    public VM virtualMachine2;
    
    @Before
    public void setUp() {
        physicalMachine = new PM(0.55f, 0.55f);
        
        virtualMachine1 = new VM(0.11f, 0.20f);
        virtualMachine2 = new VM(0.22f, 0.20f);
    }

    @Test
    public void testGetCpuCapacity() {
        assertEquals(0.55f, physicalMachine.getCpuCapacity(), 0.1);
    }
    
    @Test
    public void testGetMemCapacity() {
        assertEquals(0.55f, physicalMachine.getMemCapacity(), 0.1);
    }
    
    @Test
    public void testAllocateVirtualMachine() {
        physicalMachine.allocateVirtualMachine(virtualMachine1);
        
        assertEquals(0.44f, physicalMachine.getCpuCapacity(), 0.1);
        assertEquals(0.35f, physicalMachine.getMemCapacity(), 0.1);
        
        physicalMachine.allocateVirtualMachine(virtualMachine2);
        
        assertEquals(0.22f, physicalMachine.getCpuCapacity(), 0.1);
        assertEquals(0.15f, physicalMachine.getMemCapacity(), 0.1);
        
        assertEquals(2, physicalMachine.getAllocatedVMs().size());
    }
    

}
