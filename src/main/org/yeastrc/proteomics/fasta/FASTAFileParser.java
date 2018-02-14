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

import java.io.IOException;
import java.util.Collection;

/**
 * This class iterates over a FASTA file by calling readNext to return
 * FASTAEntry objects.
 */

public class FASTAFileParser implements AutoCloseable {

	FASTAFileLineReader _reader = null;
	
	public FASTAFileParser( FASTAFileLineReader fastaFileReader ) {
		this._reader = fastaFileReader;
	}
	

	/**
	 * Get the next header line
	 * @return
	 * @throws IOException
	 */
	private FASTAFileLine getNextHeaderLine() throws IOException {
		
		FASTAFileLine headerLine = null;
		if( !this._reader.hasReadData() ) {
			headerLine = this._reader.readLine();			// if we haven't read anything yet, get the first line
			
			if( headerLine == null )						// should only happen on an empty file/inputstream
				return null;
			
			// skip all comment lines and empty lines
			while( FASTAReaderUtils.isCommentLine( headerLine.getLineContent() ) ||
				   FASTAReaderUtils.isEmptyLine( headerLine.getLineContent() ) ) {
			
				headerLine = this._reader.readLine();
				
				if( headerLine == null )						// rest of file only contained empty lines and/or comments (no fasta entries)
					return null;
			}
			
		} else {
			headerLine = this._reader.getLastReadLine();	// the last line we read should have been a header line
		}
		
		return headerLine;
	}
	
	/**
	 * Get the next sequence. Must be called after getNextHeaderLine()
	 * @return
	 * @throws IOException
	 */
	private String getNextSequence() throws IOException {
		
		StringBuilder sequence = new StringBuilder();

		
		FASTAFileLine line = this._reader.readLine();
		if( line == null )
			return null;
		
		while( line != null ) {
			
			// if we find the next FASTA entry stop and return what we've found so far
			if( FASTAReaderUtils.isHeaderLine( line.getLineContent() ) )
				break;
			
			// if we find a comment line, go to the next line
			if( FASTAReaderUtils.isCommentLine( line.getLineContent() ) ) {
				line = this._reader.readLine();
				continue;
			}
			
			// if this is an empty line, skip it
			if( FASTAReaderUtils.isEmptyLine( line.getLineContent() ) ) {
				line = this._reader.readLine();
				continue;
			}
			
			String partialSequence = line.getLineContent();

			partialSequence = partialSequence.trim();			// remove trailing white spaces
			partialSequence = partialSequence.toUpperCase();	// force upper case
			
			sequence.append( partialSequence );
			
			line = this._reader.readLine();
		}
		
		return sequence.toString();
	}
	
	
	/**
	 * Get the next entry in the FASTA file. Returns null if end of file has been reached.
	 *
	 * @return
	 * @throws IOException 
	 * @throws FASTADataErrorException for data errors
	 * @throws Exception
	 */
	public FASTAEntry getNextEntry() throws IOException, FASTADataErrorException  {


		FASTAFileLine headerLine = getNextHeaderLine();
		
		if( headerLine == null ) return null;	// we've reached the end of the file
				
		Collection<FASTAHeader> headers = FASTAReaderUtils.getFASTAHeadersFromHeaderLine( headerLine.getLineContent() );
		if( headers == null || headers.size() < 1 )
			throw new FASTADataErrorException( "Could not find any FASTA headers in header line?\nLine: " + headerLine );
		
		
		String sequence = getNextSequence();
		if( sequence == null || sequence.length() < 1 )
			throw new FASTADataErrorException( "Could not find any sequence following:\nLine: " + headerLine );

		// If we've made it here, we've read another sequence entry in the FASTA data
		return new FASTAEntry( headers, sequence );
	}


	@Override
	public void close() throws Exception {
		if( this._reader != null ) {
			this._reader.close();
			this._reader = null;
		}
	}
}
