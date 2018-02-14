# java-fasta-utils

Java library for reading FASTA sequence files.

Download the .jar from the [release page](https://github.com/yeastrc/java-fasta-utils/releases), and use org.yeastrc.proteomics.fasta.FASTAReader.getInstance(String filename), getInstance( InputStream is), or getInstance(File file) to instantiate a new FASTAReader and iteratively call readNext() until it returns null to process each entry in the FASTA file.

Very simple example that uses java-fasta-utils to print information from a FASTA file

```java
package org.yeastrc.proteomics.fasta;

/**
 * Very simple example for processing a FASTA file.
 *
 */
public class Example {

	/**
	 * Iterate over the supplied FASTA filename and print simple information to
	 * console
     * 
	 * @param filename
	 * @throws Exception
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
