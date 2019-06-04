package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class FASTAFileParserTest_GoodData_EmptyFile {

	private InputStream _IS = null;
	private FASTAFileLineReader _READER = null;
	private FASTAFileParser _PARSER = null;
	
	@Before
	public void setUp() {
		
		_IS = new ByteArrayInputStream( new byte[0] );	// our simulated empty file
		_READER = new FASTAFileLineReader( _IS );
		_PARSER = new FASTAFileParser( _READER );
		
	}

	
	@Test
	public void testGetNextEntry() throws IOException {

		assertNull( _PARSER.getNextEntry() );
		
	}
	
}
