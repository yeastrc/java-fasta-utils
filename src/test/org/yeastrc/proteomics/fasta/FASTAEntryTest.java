package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.yeastrc.proteomics.fasta.FASTAEntry;
import org.yeastrc.proteomics.fasta.FASTAHeader;
import org.yeastrc.proteomics.fasta.FASTAReaderUtils;

public class FASTAEntryTest {

	final char ctrlA = FASTAReaderUtils._CONTROL_A; 
	
	@Test
	public void testCreateFromOneHeader() {
		
		String name = "protein1";
		String description = "protein description";
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( name, description, line );
		Set<FASTAHeader> headers = new HashSet<>();		
		headers.add( header1 );
		
		String sequence = "PEPTIDEPEPPPTIDE";				

		FASTAEntry entry = new FASTAEntry( headers, sequence );
		
		assertEquals( sequence, entry.getSequence() );
		assertEquals( headers, entry.getHeaders() );
		
	}
	
	@Test
	public void testCreateFromTwoHeaders() {
		
		String name = "protein1";
		String description = "protein description";
		
		String line = name + " " + description;
		
		FASTAHeader header1 = new FASTAHeader( name, description, line );
		Set<FASTAHeader> headers = new HashSet<>();		
		headers.add( header1 );
		
		String name2 = "protein2";
		String description2 = "protein2 description";
		
		String line2 = name2 + " " + description2;
		
		FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
		headers.add( header2 );
				
		String sequence = "PEPTIDEPEPPPTIDE";				

		FASTAEntry entry = new FASTAEntry( headers, sequence );
		
		assertEquals( sequence, entry.getSequence() );
		assertEquals( headers, entry.getHeaders() );
		
	}
	
	@Test
	public void testEqualsExpectEqual() throws Exception {
		
		FASTAEntry entry = null;
		FASTAEntry entry2 = null;

		{
		
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry = new FASTAEntry( headers, sequence );
		}
		
		
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry2 = new FASTAEntry( headers, sequence );
		}
		
		assertEquals( entry, entry2 );

	}
	
	@Test
	public void testEqualsExpectNotEqual() throws Exception {
		

		FASTAEntry entry = null;
		FASTAEntry entry2 = null;
		FASTAEntry entry3 = null;

		{
		
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry = new FASTAEntry( headers, sequence );
		}
		
		// entry2 tests for different sequences
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDEE";
			
			entry2 = new FASTAEntry( headers, sequence );
		}
		
		// entry3 tests for different headers
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein3";
			String description2 = "protein3 description";	// difference is here
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry3 = new FASTAEntry( headers, sequence );
		}
		
		
		assertNotEquals( entry, entry2 );
		assertNotEquals( entry, entry3 );
		
	}

	@Test
	public void testHashcodeExpectEqual() throws Exception {
		
		FASTAEntry entry = null;
		FASTAEntry entry2 = null;

		{
		
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry = new FASTAEntry( headers, sequence );
		}
		
		
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry2 = new FASTAEntry( headers, sequence );
		}
		
		assertEquals( entry.hashCode(), entry2.hashCode() );
	}
	
	@Test
	public void testHashcodeExpectNotEqual() throws Exception {
		
		FASTAEntry entry = null;
		FASTAEntry entry2 = null;
		FASTAEntry entry3 = null;

		{
		
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
						
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry = new FASTAEntry( headers, sequence );
		}
		
		// entry2 tests for different sequences
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein2";
			String description2 = "protein2 description";
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
			
			String sequence = "PEPTIDEPEPPPTIDEE";
			
			entry2 = new FASTAEntry( headers, sequence );
		}
		
		// entry3 tests for different headers
		{
			
			String name = "protein1";
			String description = "protein description";
			
			String line = name + " " + description;
			
			FASTAHeader header1 = new FASTAHeader( name, description, line );
			Set<FASTAHeader> headers = new HashSet<>();		
			headers.add( header1 );
			
			String name2 = "protein3";
			String description2 = "protein3 description";	// difference is here
			
			String line2 = name2 + " " + description2;
			
			FASTAHeader header2 = new FASTAHeader( name2, description2, line2 );
			headers.add( header2 );
			
			String sequence = "PEPTIDEPEPPPTIDE";
			
			entry3 = new FASTAEntry( headers, sequence );
		}
		
		
		assertNotEquals( entry.hashCode(), entry2.hashCode() );
		assertNotEquals( entry.hashCode(), entry3.hashCode() );
	}
	
}
