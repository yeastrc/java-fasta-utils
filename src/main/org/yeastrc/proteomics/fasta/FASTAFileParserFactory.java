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

import java.io.File;
import java.io.FileNotFoundException;

public class FASTAFileParserFactory {

	private static final FASTAFileParserFactory _INSTANCE = new FASTAFileParserFactory();
	public static FASTAFileParserFactory getInstance() { return _INSTANCE; }
	private FASTAFileParserFactory() { }

	/**
	 * Use this to get the implementation of the FASTAFileParser to use.
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public FASTAFileParser getFASTAFileParser( File fastaFile ) throws FileNotFoundException {
		return new FASTAFileParser( FASTAFileLineReaderFactory.getInstance().getFASTAFileLineReader( fastaFile ) );
	}
	
}
