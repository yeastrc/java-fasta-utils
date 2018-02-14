/*
 *   Copyright 2015-2018 Michael Riffle
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.yeastrc.proteomics.fasta;

import java.util.Collection;
import java.util.HashSet;

public class FASTAReaderUtils {

	final static char _CONTROL_A = '\u0001'; 
	
	/**
	 * Return true if this is a FASTA header line.
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isHeaderLine( String line ) {
		if( line == null ) return false;
		
		return line.startsWith( ">" );
	}

	/**
	 * Return true if this is a comment line
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isCommentLine( String line ) {
		if( line == null ) return false;
		
		return line.startsWith( ";" );
	}

	/**
	 * Return true if this is an empty
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isEmptyLine( String line ) {
		if( line.length() < 1 ) return true;
		
		line = line.trim();
		if( line.length() < 1 ) return true;
		
		return false;
	}
	
	/**
	 * Return an array of strings, where each element is an individual header
	 * in the FASTA header line, separated by control-A characters.
	 * 
	 * @param line
	 * @return
	 */
	public static String[] getSeperateHeaders( String line ) {
		if( line == null ) return null;
		
		return line.split( String.valueOf( _CONTROL_A ) );
	}
	
	/**
	 * Remove first element from line, used to remove leading ">"
	 * 
	 * @param line
	 * @return
	 */
	public static String stripFirstCharacter( String line ) {
		if( line == null ) return null;
		
		return line.substring(1, line.length());
	}
	
	/**
	 * Get the headers parsed from the FASTA header line in the form of >name description  Supports
	 * multipe headers on the same line separated by control-a characters.
	 * 
	 * @param headerLineContent
	 * @return A collection of headers, or null if this is not a header line
	 */
	public static Collection<FASTAHeader> getFASTAHeadersFromHeaderLine( String headerLineContent ) {
		
		
		if( !isHeaderLine( headerLineContent ) )
			return null;

		
		Collection<FASTAHeader> headers = new HashSet<>();
		
		headerLineContent = FASTAReaderUtils.stripFirstCharacter( headerLineContent );	// strip off the leading ">" on the header line

		/*
		 * In FASTA files, multiple headers can be associated with the same sequence, and will
		 * be present on the same line.  The separate headers are separated by the CONTROL-A
		 * character, so we split on that here, and save each to the headers Set
		 */
		String[] lineHeaders = FASTAReaderUtils.getSeperateHeaders( headerLineContent );
		for (int i = 0; i < lineHeaders.length; i++)
			headers.add( new FASTAHeader( lineHeaders[i] ) );
		
		return headers;
	}
	
	
}
