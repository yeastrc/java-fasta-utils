package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void testStripFirstChar() {
		
		assertEquals( "line", FASTAReaderUtils.stripFirstCharacter( ">line" ) );
	}
	
	@Test
	public void testSeparateHeaders() {
		
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
	
}
