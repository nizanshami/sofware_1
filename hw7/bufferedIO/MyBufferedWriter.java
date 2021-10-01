package il.ac.tau.cs.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	private FileWriter fWriter;
	private int bufferSize;
	private String buffer;
	
	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.fWriter = fWriter;
		this.bufferSize = bufferSize;
		this.buffer = "";
	}

	
	@Override
	public void write(String str) throws IOException {
		this.buffer += str;
		if(buffer.length() <= this.bufferSize){
			this.fWriter.write(buffer);
		}else {
			String s = buffer.substring(0, this.bufferSize);
			this.fWriter.write(s);
		}
		buffer = buffer.substring(this.bufferSize);

	}
	
	@Override
	public void close() throws IOException {
		this.fWriter.write(buffer);
		this.fWriter.close();
	}

}