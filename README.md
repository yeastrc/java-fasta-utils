# java-fasta-utils

Java library for reading FASTA sequence files.

Download the .jar from the release page, and use org.yeastrc.fasta.FASTAReader.getInstance(String filename), getInstance( InputStream is), or getInstance(File file) to instantiate a new FASTAReader and iteratively call readNext() until it returns null to process each entry in the FASTA file.
