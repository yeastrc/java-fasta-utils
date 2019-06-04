package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FASTAFileParserTest_GoodData_WithComments {

	private InputStream _IS = null;
	private FASTAFileLineReader _READER = null;
	private FASTAFileParser _PARSER = null;
	
	@Before
	public void setUp() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( ";this is a comment\n" );
		sb.append( "; so is this \n" );
		
		sb.append( ">protein1 protein1 description\n" );
		sb.append( "KMDJEKCDJDJKDMDKDKDD\n" );
		sb.append( "; oh, hey, another comment in the middle of a sequence??\n" );
		sb.append( "DLEDLDKMEKWOLDJDS\n" );
		sb.append( "HDKSKQLSKKKKKKKKKDKIE\n" );

		sb.append( ">protein2 protein2 description" + FASTAReaderUtils._CONTROL_A + "protein2.1 protein2.1 description\n" );
		sb.append( "KMDJEKCDJDJKDMDKDKDDHDKSKQLSKKKKKKKKKDKIE\n" );
		
		sb.append( ";comment here\n" );
		sb.append( ">protein3 protein3 description\n" );
		sb.append( "JSDKWIDSDKLSSSKS \n" );
		sb.append( "DLDKDKDLDGFKJDEKOLDJDS \n" );
		sb.append( "\n" );
		sb.append( "KDKDHHHHHHHHHHHWKWKSDKFDKDKSDKS\n" );
		
		sb.append( "\n" );
		
		sb.append( ">protein4 protein4 description\n" );
		sb.append( "DLDLDKDJKRURJFDDDD\n" );
		sb.append( "jjxjwidmkfgksjasjh\n" );
		sb.append( "DKKKkkdkdkKDKEDKEKEKDK\n" );
		
		sb.append( ";let's end with a comment" );
		
		_IS = IOUtils.toInputStream( sb.toString(), Charset.defaultCharset() );	
		_READER = new FASTAFileLineReader( _IS );
		_PARSER = new FASTAFileParser( _READER );
		
	}

	
	@Test
	public void testGetNextEntry() throws IOException {

		{
			// test first entry
			
			FASTAEntry entry = _PARSER.getNextEntry();
			
			Collection<FASTAHeader> headers = new HashSet<>();
			FASTAHeader h = new FASTAHeader( "protein1 protein1 description" );
			headers.add( h );
			
			String sequence = "KMDJEKCDJDJKDMDKDKDDDLEDLDKMEKWOLDJDSHDKSKQLSKKKKKKKKKDKIE";
			
			assertEquals( headers, entry.getHeaders() );
			assertEquals( sequence, entry.getSequence() );
			
		}
		
		{
			// test second entry
			
			FASTAEntry entry = _PARSER.getNextEntry();
			
			Collection<FASTAHeader> headers = new HashSet<>();
			FASTAHeader h = new FASTAHeader( "protein2 protein2 description" );
			headers.add( h );
			
			h = new FASTAHeader( "protein2.1 protein2.1 description" );
			headers.add( h );
			
			String sequence = "KMDJEKCDJDJKDMDKDKDDHDKSKQLSKKKKKKKKKDKIE";
			
			assertEquals( headers, entry.getHeaders() );
			assertEquals( sequence, entry.getSequence() );
			
		}
		
		
		{
			// test third entry
			
			FASTAEntry entry = _PARSER.getNextEntry();
			
			Collection<FASTAHeader> headers = new HashSet<>();
			FASTAHeader h = new FASTAHeader( "protein3 protein3 description" );
			headers.add( h );
			
			String sequence = "JSDKWIDSDKLSSSKSDLDKDKDLDGFKJDEKOLDJDSKDKDHHHHHHHHHHHWKWKSDKFDKDKSDKS";
			
			assertEquals( headers, entry.getHeaders() );
			assertEquals( sequence, entry.getSequence() );
			
		}
		
		{
			// test fourth entry
			
			FASTAEntry entry = _PARSER.getNextEntry();
			
			Collection<FASTAHeader> headers = new HashSet<>();
			FASTAHeader h = new FASTAHeader( "protein4 protein4 description" );
			headers.add( h );
			
			String sequence = "DLDLDKDJKRURJFDDDDJJXJWIDMKFGKSJASJHDKKKKKDKDKKDKEDKEKEKDK";
			
			assertEquals( headers, entry.getHeaders() );
			assertEquals( sequence, entry.getSequence() );
			
		}
		
		
		// should be returning null now
		assertNull( _PARSER.getNextEntry() );

		// should still be returning null now
		assertNull( _PARSER.getNextEntry() );
		
		
		
	}
	
}
