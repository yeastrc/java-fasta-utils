package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.yeastrc.proteomics.fasta.FASTAReaderUtils;

public class FASTAReaderUtilsTest {

	@Test
	public void testIsHeaderLine() {
		
		assertTrue( FASTAReaderUtils.isHeaderLine( ">thisisaheader" ) );
		assertFalse( FASTAReaderUtils.isHeaderLine( "thisisaheader" ) );	
		assertFalse( FASTAReaderUtils.isHeaderLine( " thisisaheader" ) );	
		assertFalse( FASTAReaderUtils.isHeaderLine( " >thisisaheader" ) );	
	}
	
	@Test
	public void testIsCommentLine() {
		
		assertTrue( FASTAReaderUtils.isCommentLine( ";thisisaheader" ) );
		assertFalse( FASTAReaderUtils.isCommentLine( ">thisisaheader" ) );	
		assertFalse( FASTAReaderUtils.isCommentLine( " ;thisisaheader" ) );	
		assertFalse( FASTAReaderUtils.isCommentLine( " >thisisaheader" ) );	
	}
	
	@Test
	public void testIsEmptyLine() {
		
		assertTrue( FASTAReaderUtils.isEmptyLine( "" ) );
		assertTrue( FASTAReaderUtils.isEmptyLine( " " ) );
		assertTrue( FASTAReaderUtils.isEmptyLine( "		" ) );
		assertTrue( FASTAReaderUtils.isEmptyLine( "\n" ) );
		assertTrue( FASTAReaderUtils.isEmptyLine( " \n" ) );

		
		
		assertFalse( FASTAReaderUtils.isCommentLine( "a" ) );	
	}
	
	@Test
	public void testStripFirstChar() {
		
		assertEquals( "line", FASTAReaderUtils.stripFirstCharacter( ">line" ) );
	}
	
	@Test
	public void testGetSeparateHeaders() {
		
		String header1 = "name1 description1";
		String header2 = "name2";
		String header3 = "name3 description 3";

		String line1 = header1;
		String line2 = header1 + FASTAReaderUtils._CONTROL_A + header2;
		String line3 = header1 + FASTAReaderUtils._CONTROL_A + header2 + FASTAReaderUtils._CONTROL_A + header3;
		
		String[] headers1 = FASTAReaderUtils.getSeperateHeaders( line1 );
		String[] headers2 = FASTAReaderUtils.getSeperateHeaders( line2 );
		String[] headers3 = FASTAReaderUtils.getSeperateHeaders( line3 );
		
		assertEquals( 1,  headers1.length );		
		assertEquals( 2,  headers2.length );
		assertEquals( 3,  headers3.length );

		assertEquals( header1, headers1[ 0 ] );
		
		assertEquals( header1, headers2[ 0 ] );
		assertEquals( header2, headers2[ 1 ] );
		
		assertEquals( header1, headers3[ 0 ] );
		assertEquals( header2, headers3[ 1 ] );
		assertEquals( header3, headers3[ 2 ] );
	}
	
	@Test
	public void testGetFASTAHeadersFromHeaderLine_InvalidHeader() {
		
		String headerLine = "invalid header line";
		assertNull( FASTAReaderUtils.getFASTAHeadersFromHeaderLine( headerLine ) );
		
	}
	
	@Test
	public void testGetFASTAHeadersFromHeaderLine_OneHeader() {
		
		String headerLine = ">protein_name valid header line";
		
		Collection<FASTAHeader> headers = FASTAReaderUtils.getFASTAHeadersFromHeaderLine( headerLine );

		Collection<FASTAHeader> testHeaders = new HashSet<FASTAHeader>();
		
		FASTAHeader h1 = new FASTAHeader( "protein_name", "valid header line", "protein_name valid header line" );
		
		testHeaders.add( h1 );
		
		assertEquals( testHeaders, headers );
	}
	
	@Test
	public void testGetFASTAHeadersFromHeaderLine_TwoHeaders() {
		
		String headerLine = ">protein_name valid header line" + FASTAReaderUtils._CONTROL_A + "protein_name2 valid header line2";
		Collection<FASTAHeader> headers = FASTAReaderUtils.getFASTAHeadersFromHeaderLine( headerLine );

		Collection<FASTAHeader> testHeaders = new HashSet<FASTAHeader>();
		
		FASTAHeader h1 = new FASTAHeader( "protein_name", "valid header line", "protein_name valid header line" );
		FASTAHeader h2 = new FASTAHeader( "protein_name2", "valid header line2", "protein_name2 valid header line2" );
		
		testHeaders.add( h1 );
		testHeaders.add( h2 );
		
		assertEquals( testHeaders, headers );
	}
	
}
