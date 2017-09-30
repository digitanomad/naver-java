package naver.java.ch03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class ProcessRunner {
	public static void main(String[] args) throws InterruptedException, IOException {
		String[] command = new String[] {"echo", "hello"};
		ProcessRunner runner = new ProcessRunner();
		runner.byCommonsExec(command);
		runner.byProcessBuilderRedirect(command);
	}
	
	public void byRuntime(String[] command) throws InterruptedException, IOException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(command);
		printStream(process);
	}
	
	public void byProcessBuilder(String[] command) throws InterruptedException, IOException {
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		printStream(process);
	}
	
	public void byCommonsExec(String[] command) throws ExecuteException, IOException {
		DefaultExecutor executor = new DefaultExecutor();
		CommandLine cmdLine = CommandLine.parse(command[0]);
		for (int i = 1, n = command.length; i < n; i++) {
			cmdLine.addArgument(command[i]);
		}
		executor.execute(cmdLine);
	}
	
	public void byProcessBuilderRedirect(String[] command) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		builder.start();
	}
	
	private void printStream(Process process) throws InterruptedException, IOException {
		process.waitFor();
		try (InputStream psout = process.getInputStream()) {
			copy(psout, System.out);
		}
	}
	
	public void copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[1024];
		int n = 0;
		
		while ((n = input.read(buffer)) != -1) {
			output.write(buffer, 0, n);
		}
	}
	
}
