# java-fasta-utils

Java library for reading FASTA sequence files.

Download the .jar from the [release page](https://github.com/yeastrc/java-fasta-utils/releases), and use org.yeastrc.fasta.FASTAReader.getInstance(String filename), getInstance( InputStream is), or getInstance(File file) to instantiate a new FASTAReader and iteratively call readNext() until it returns null to process each entry in the FASTA file.

Very simple example that uses java-fasta-utils to print information from a FASTA file

```java
package org.yeastrc.fasta;

import org.yeastrc.fasta.*;

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

		// Instantiate a reader for the file with the supplied filename
		FASTAReader reader = FASTAReader.getInstance( filename );
		
		FASTAEntry entry = reader.readNext();
		while( entry != null ) {

			/*
			 *  A given FASTA entry may have multiple headers associated with a
			 *  sequence. Show them all.
			 */
			for( FASTAHeader header : entry.getHeaders() ) {
				System.out.println( "Got a FASTA header with name=" +
					header.getName() + " and description=" + header.getDescription() );
			}
			
			// show the sequence for this FASTA entry
			System.out.println( "Got this sequence for those headers: " + entry.getSequence() + "\n" );
			
			// get the next entry in the FASTA file
			entry = reader.readNext();
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
