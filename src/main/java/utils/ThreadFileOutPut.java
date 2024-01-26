package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ThreadFileOutPut extends PrintStream{
	
	public ThreadFileOutPut (OutputStream out)
	{super(out);}
	
	private static ThreadLocal<FileOutputStream> threadOutput = new ThreadLocal<>();
	
	private static PrintStream stdOut = System.out;
	private static PrintStream stdErr = System.err;
	
	static
	{
		System.setOut(new ThreadFileOutPut(stdOut));
		System.setErr(new ThreadFileOutPut(stdErr));
	}
	
	
	public static void startThreadOutputRedirect(FileOutputStream stream)
	{
		threadOutput.set(stream);
	}
	
	@Override
	public void write (byte [] b ) throws IOException {
		FileOutputStream stream = threadOutput.get();
		if(stream != null) {
		stream.write(b);
		}
		else
			super.write(b);
	}
	
	
	
	
	

}
