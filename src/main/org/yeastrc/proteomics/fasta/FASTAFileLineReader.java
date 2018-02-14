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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FASTAFileLineReader implements AutoCloseable {

	private BufferedReader bufferedReader = null;
	private int lineNumber = 0;
	private FASTAFileLine lastReadLine = null;
	private boolean hasReadData = false;
	
	@Override
	public void close() throws IOException {
		if( this.bufferedReader != null ) {
			this.bufferedReader.close();
			this.bufferedReader = null;
		}
	}

	public FASTAFileLine readLine() throws IOException  {
		
		hasReadData = true;
		
		if( this.bufferedReader != null ) {

			String lineContent = this.bufferedReader.readLine();
			if( lineContent != null ) {
				this.lineNumber++;
				
				FASTAFileLine readLine = new FASTAFileLine( lineNumber, lineContent );
				this.lastReadLine = readLine;
				
				return readLine;
			}

			this.lastReadLine = null;
			return null;
		
		}
		
		throw new IllegalStateException( "Call to readLine() after it has been closed." );
	}

	public FASTAFileLine getLastReadLine() {
		return this.lastReadLine;
	}
	
	/**
	 * Get an instance of this class that reads the supplied file.
	 * @param file
	 * @throws FileNotFoundException
	 */
	protected FASTAFileLineReader( InputStream is ) {
		this.bufferedReader = new BufferedReader( new InputStreamReader( is ) );		
	}
	
	/**
	 * Return true if this FASTAFileLineReader has already attempted to read any data, false if not.
	 */
	public boolean hasReadData() {
		return this.hasReadData;
	}
	
}
