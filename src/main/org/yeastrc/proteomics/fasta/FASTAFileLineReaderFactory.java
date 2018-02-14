package org.yeastrc.proteomics.fasta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FASTAFileLineReaderFactory {

	private static final FASTAFileLineReaderFactory _INSTANCE = new FASTAFileLineReaderFactory();
	private FASTAFileLineReaderFactory() { }
	public static FASTAFileLineReaderFactory getInstance() { return _INSTANCE; }
	
	public FASTAFileLineReader getFASTAFileLineReader( File file ) throws FileNotFoundException {
		return new FASTAFileLineReader( new FileInputStream( file ) );
	}
	
}
