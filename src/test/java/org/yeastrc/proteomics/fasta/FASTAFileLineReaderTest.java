package org.yeastrc.proteomics.fasta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FASTAFileLineReaderTest {

	private InputStream _IS = null;
	private FASTAFileLineReader _READER = null;
	
	@Before
	public void setUp() {
		StringBuilder sb = new StringBuilder();
		
		sb.append( "line1\n" );
		sb.append( "line2\n" );
		sb.append( "line3\n" );
		sb.append( "line4\n" );
		sb.append( "line5\n" );
		
		_IS = IOUtils.toInputStream( sb.toString(), Charset.defaultCharset() );	
		_READER = new FASTAFileLineReader( _IS );
		
	}

	
	@Test
	public void testHasRead() throws IOException {

		assertFalse( _READER.hasReadData() );
		
		_READER.readLine();
		
		assertTrue( _READER.hasReadData() );

	}
	
	@Test
	public void testLastReadLine() throws IOException {
		
		assertNull( _READER.getLastReadLine() );
		
		_READER.readLine();
		assertEquals( "line1", _READER.getLastReadLine().getLineContent() );
		assertEquals( 1, _READER.getLastReadLine().getLineNumber() );
		
		_READER.readLine();
		assertEquals( "line2", _READER.getLastReadLine().getLineContent() );
		assertEquals( 2, _READER.getLastReadLine().getLineNumber() );
		
		_READER.readLine();
		assertEquals( "line3", _READER.getLastReadLine().getLineContent() );
		assertEquals( 3, _READER.getLastReadLine().getLineNumber() );
		
		_READER.readLine();
		assertEquals( "line4", _READER.getLastReadLine().getLineContent() );
		assertEquals( 4, _READER.getLastReadLine().getLineNumber() );
		
		_READER.readLine();
		assertEquals( "line5", _READER.getLastReadLine().getLineContent() );
		assertEquals( 5, _READER.getLastReadLine().getLineNumber() );
		

		
		_READER.readLine();	// should be null
		assertNull( _READER.getLastReadLine() );			// beyond last line, last read line should be null
		
		_READER.readLine();	// should be null
		assertNull( _READER.getLastReadLine() );			// beyond last line, last read line should still be null
	}
	
	@Test
	public void testReadLine() throws IOException {
		
		
		FASTAFileLine fileLine = _READER.readLine();
		assertEquals( "line1", fileLine.getLineContent() );
		assertEquals( 1, fileLine.getLineNumber() );
		
		fileLine = _READER.readLine();
		assertEquals( "line2", fileLine.getLineContent() );
		assertEquals( 2, fileLine.getLineNumber() );
		
		fileLine = _READER.readLine();
		assertEquals( "line3", fileLine.getLineContent() );
		assertEquals( 3, fileLine.getLineNumber() );
		
		fileLine = _READER.readLine();
		assertEquals( "line4", fileLine.getLineContent() );
		assertEquals( 4, fileLine.getLineNumber() );
		
		fileLine = _READER.readLine();
		assertEquals( "line5", fileLine.getLineContent() );
		assertEquals( 5, fileLine.getLineNumber() );
		
		fileLine = _READER.readLine();
		assertNull( fileLine );
		
		fileLine = _READER.readLine();
		assertNull( fileLine );
		
	}
	
	@Test
	public void testClose() throws IOException {
		
		
		_READER.readLine();
		_READER.close();
		
		try {
			_READER.readLine();
			fail( "Calling readLine() after closing did not throw an exception." );
		} catch( Exception e ) { ; }
		
		
	}
	
}
