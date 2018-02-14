package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FASTAFileParserTest_BadData_MissingSequenceInMiddle {

	private InputStream _IS = null;
	private FASTAFileLineReader _READER = null;
	private FASTAFileParser _PARSER = null;
	
	@Before
	public void setUp() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( ">protein1 protein1 description\n" );
		sb.append( "KMDJEKCDJDJKDMDKDKDD\n" );
		sb.append( "DLEDLDKMEKWOLDJDS\n" );
		sb.append( "HDKSKQLSKKKKKKKKKDKIE\n" );

		sb.append( ">protein2 protein2 description" + FASTAReaderUtils._CONTROL_A + "protein2.1 protein2.1 description\n" );
		
		sb.append( ">protein3 protein3 description\n" );
		sb.append( "JSDKWIDSDKLSSSKS \n" );
		sb.append( "DLDKDKDLDGFKJDEKOLDJDS \n" );
		sb.append( "\n" );
		sb.append( "KDKDHHHHHHHHHHHWKWKSDKFDKDKSDKS\n" );
				
		_IS = IOUtils.toInputStream( sb.toString(), Charset.defaultCharset() );	
		_READER = new FASTAFileLineReader( _IS );
		_PARSER = new FASTAFileParser( _READER );
		
	}

	
	@Test
	public void testGetNextEntry() throws IOException {

		_PARSER.getNextEntry();	// should work
		
		try {

			_PARSER.getNextEntry(); // should fail, no sequence data
			
			fail( "getNextEntry() should have failed because of no sequence data." );
			
		} catch( Exception e ) { ; }
	}
	
}
