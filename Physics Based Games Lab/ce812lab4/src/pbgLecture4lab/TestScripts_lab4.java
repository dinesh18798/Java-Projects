package pbgLecture4lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class TestScripts_lab4 {

	// A test class to help you check if you've programmed some basic steps correctly
	// DO NOT alter any code in this file.  If you feel the need to alter it then you are doing something wrong elsewhere
	//
	// If this class is causing compile problems then make sure the JUnit4 jars are in your classpath 
	// (i.e. including junit.jar and org.hamcrest.core_1.3.0.v201303031735.jar) 
	// You can add JUnit quickly to your class path in Eclipse by right-clicking on line "18 @Test" and choose "Quick Fix: Add JUnit to Build Path"
	
	@Test
	public void test1_screenXtoWorldX() {
		double worldX=BasicPhysicsEngine.convertScreenXtoWorldX(BasicPhysicsEngine.convertWorldXtoScreenX(14));
		assertEquals("screenXtoWorldX is not working as a true inverse function to convertWorldXtoScreenX ",14,worldX,1e-6);
	}
	
	@Test
	public void test2_screenYtoWorldY() {
		double worldY=BasicPhysicsEngine.convertScreenYtoWorldY(BasicPhysicsEngine.convertWorldYtoScreenY(14));
		assertEquals("screenYtoWorldY is not working as a true inverse function to convertWorldYtoScreenY",14,worldY,1e-6);
	}
	
		
}


