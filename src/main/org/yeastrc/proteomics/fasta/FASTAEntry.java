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

/**
 * Represents a header line + associated sequence in the FASTA file
*/
public final class FASTAEntry {

	public int hashCode() {
		return this.headers.hashCode() + this.sequence.hashCode();
	}

	public boolean equals( Object o ) {
		if( !( o instanceof FASTAEntry ) ) return false;

		if( !((FASTAEntry)o).getSequence().equals( this.getSequence() ) )
			return false;

		if( !((FASTAEntry)o).getHeaders().equals( this.getHeaders() ) )
			return false;

		return true;
	}

	/**
	 * Get an immutable FASTAEntry
	 * @param headers
	 * @param sequence
	 * @param headerLine
	 */
	public FASTAEntry( Collection<FASTAHeader> headers, String sequence ) {
		this.headers = headers;
		this.sequence = sequence;
	}

	public Collection<FASTAHeader> getHeaders() {
		return headers;
	}
	public String getSequence() {
		return sequence;
	}
	



	private final Collection<FASTAHeader> headers;
	private final String sequence;


}
