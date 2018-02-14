package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FASTAFileLineTest {

	private final String LINE_CONTENT = "this is the line content";
	private final int LINE_NUMBER = 42;
	
	private FASTAFileLine FASTA_FILELINE= null;
	
	@Before
	public void setUp() {
		FASTA_FILELINE = new FASTAFileLine( LINE_NUMBER, LINE_CONTENT );
	}
	
	@Test
	public void testInstantiation() throws Exception {
		
		assertEquals( LINE_NUMBER, FASTA_FILELINE.getLineNumber() );
		assertEquals( LINE_CONTENT, FASTA_FILELINE.getLineContent() );
		
	}
	
}
