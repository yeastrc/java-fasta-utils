package org.uwpr.proteomics.fasta;

import static org.junit.Assert.*;

import org.junit.Test;

public class FASTAHeaderTest {

	@Test
	public void testCreateFromComponents() {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header = new FASTAHeader( name, description, line );

		assertEquals( name, header.getName() );
		assertEquals( description, header.getDescription() );
		assertEquals( line, header.getLine() );
		
	}
	
	@Test
	public void testCreateFromLine() throws Exception {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header = new FASTAHeader( line );

		assertEquals( name, header.getName() );
		assertEquals( description, header.getDescription() );
		assertEquals( line, header.getLine() );
	}
	
	@Test
	public void testEqualsExpectEqual() throws Exception {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( line );
		FASTAHeader header2 = new FASTAHeader( line );

		assertEquals( header1, header2 );
	}
	
	@Test
	public void testEqualsExpectNotEqual() throws Exception {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( line );

		line = line + "2";
		FASTAHeader header2 = new FASTAHeader( line );

		assertNotEquals( header1, header2 );
	}

	@Test
	public void testHashcodeExpectEqual() throws Exception {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( line );
		FASTAHeader header2 = new FASTAHeader( line );

		assertEquals( header1.hashCode(), header2.hashCode() );
	}
	
	@Test
	public void testHashcodeExpectNotEqual() throws Exception {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( line );

		line = line + "2";
		FASTAHeader header2 = new FASTAHeader( line );

		assertNotEquals( header1.hashCode(), header2.hashCode() );
	}
	
}
