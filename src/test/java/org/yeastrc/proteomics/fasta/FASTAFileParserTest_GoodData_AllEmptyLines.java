package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FASTAFileParserTest_GoodData_AllEmptyLines {

	private InputStream _IS = null;
	private FASTAFileLineReader _READER = null;
	private FASTAFileParser _PARSER = null;
	
	@Before
	public void setUp() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( "\n" );
		sb.append( " \n" );
		sb.append( "  \n" );
		sb.append( "	\n" );
		sb.append( "\n" );
		sb.append( "\n" );

		
		_IS = IOUtils.toInputStream( sb.toString(), Charset.defaultCharset() );	
		_READER = new FASTAFileLineReader( _IS );
		_PARSER = new FASTAFileParser( _READER );
		
	}

	
	@Test
	public void testGetNextEntry() throws IOException {
		
		assertNull( _PARSER.getNextEntry() );

	}
	
}
