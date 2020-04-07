package test.VendingMachine;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import VendingMachine.Coffee;
import VendingMachine.Cola;
import VendingMachine.OrangeJuice;
import VendingMachine.VendingMachine;


public class VendingMachineTest {

	@Test
	public void testCalculateChange() {
		VendingMachine vm = new VendingMachine();
		vm.setAmountPaid(0.0);
		assertEquals(-1, vm.calculateChange(3, "TE"), 0.01);
		vm.setAmountPaid(0.0);
		assertEquals(0, vm.calculateChange(3, "TE OE"), 0.01);
		vm.setAmountPaid(0.0);
		assertEquals(-0.5, vm.calculateChange(3, "TE FC"), 0.01);
		vm.setAmountPaid(0.0);
		assertEquals(-0.8, vm.calculateChange(3, "TE TC"), 0.01);
		
		assertEquals(2.2, vm.getAmountPaid(), 0.01);
				
				
		vm.setAmountPaid(0.0);
		assertEquals(-3.0, vm.calculateChange(3, "TP"), 0.01);
	}
	
	@Test
	public void testCalculateReturningCoins() {
		VendingMachine vm = new VendingMachine();
		int coinList[] = vm.calculateReturningCoins(1.75);
		assertEquals(0, coinList[0]);
		assertEquals(1, coinList[1]);
		assertEquals(1, coinList[2]);
		assertEquals(1, coinList[3]);
		
		coinList = vm.calculateReturningCoins(3.75);
		assertEquals(1, coinList[0]);
		assertEquals(1, coinList[1]);
		assertEquals(1, coinList[2]);
		assertEquals(1, coinList[3]);
	}
	
	@Test
	public void testDisplayReturningCoins() {
		VendingMachine vm = new VendingMachine();
		String displayText = vm.displayReturningCoins(2.75);
		assertTrue(displayText.contains("1 x 2Euro"));
	
	}
	
	@Test
	public void testDisplayMenu() {
		VendingMachine vm = new VendingMachine();
		vm.powerUpVendingMechine();
		vm.drinkChamber.takeACola();
		vm.drinkChamber.takeACoffee();
		vm.drinkChamber.takeACoffee();
		vm.drinkChamber.takeAOJ();
		vm.drinkChamber.takeAOJ();
		vm.drinkChamber.takeAOJ();
		vm.DisplayMenu();
		assertTrue(vm.getDisplayMenu().contains("COLA\t\tprice: [3.0] euro\tstill have: [9]can"));
		assertTrue(vm.getDisplayMenu().contains("COFFEE\t\tprice: [2.5] euro\tstill have: [8]can"));
		assertTrue(vm.getDisplayMenu().contains("ORANGE_JUICE\tprice: [5.0] euro\tstill have: [7]can"));
		
	}
	
	@Test
	public void testCaptureMoney() {
		VendingMachine vm = new VendingMachine();
		vm.setAmountPaid(0.0);
		String data = "TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.captureMoney("COLA", 3));
		
		vm.setAmountPaid(0.0);
		data = "TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.captureMoney("COFFEE", 2.5));
		
		vm.setAmountPaid(0.0);
		data = "TE TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.captureMoney("ORANGE_JUICE", 5));
		
		vm.setAmountPaid(0.0);
		data = "\u001a";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertFalse(vm.captureMoney("ORANGE_JUICE", 5));
		
		
	}
	
	@Test
	public void testProcessSelection() {
		VendingMachine vm = new VendingMachine();
		vm.powerUpVendingMechine();
		
		vm.setAmountPaid(0.0);
		String data = "TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.processSelection("COLA", true) instanceof Cola);
		
		vm.setAmountPaid(0.0);
		data = "TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.processSelection("COFFEE", true) instanceof Coffee);
		
		vm.setAmountPaid(0.0);
		data = "TE TE OE FC\r\n";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		assertTrue(vm.processSelection("ORANGE_JUICE", true) instanceof OrangeJuice);
		
		
	}
	
	
	
	

}
