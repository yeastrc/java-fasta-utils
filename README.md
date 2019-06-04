[![Build Status](https://travis-ci.com/yeastrc/java-fasta-utils.svg?branch=master)](https://travis-ci.com/yeastrc/java-fasta-utils)

# java-fasta-utils

Java library for reading FASTA sequence files.

Download the .jar from the [release page](https://github.com/yeastrc/java-fasta-utils/releases).

Very simple example that uses java-fasta-utils to print information from a FASTA file

```java
package your.package;

import org.yeastrc.proteomics.fasta.*;

/**
 * Very simple example for processing a FASTA file.
 */
public class Example {

	/**
	 * Iterate over the supplied FASTA filename and print simple information to console
	 * 
	 * @param filename The filename to process.
	 * @throws Exception If there is a problem.
	 */
	private void processFASTAFile( String filename ) throws Exception {

		try ( FASTAFileParser parser = FASTAFileParserFactory.getInstance().getFASTAFileParser(  new File( filename ) ) ) {
		
			for ( FASTAEntry entry = parser.getNextEntry(); entry != null; entry = parser.getNextEntry() ) {		
		
				System.out.println( "Found " + entry.getHeaders().size() + " headers for this FASTA entry." );
				System.out.println( "Found this sequence: " + entry.getSequence() );		
		
			}
		}
	}
	

	/**
	 * Run program, supply full path to FASTA file as first argument
	 * @param args
	 * @throws Exception
	 */
	public static void main( String[] args ) throws Exception {
		
		Example example = new Example();
		example.processFASTAFile( args[ 0 ] );
		
	}
	
}

```
