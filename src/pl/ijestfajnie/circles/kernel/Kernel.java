package pl.ijestfajnie.circles.kernel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pl.ijestfajnie.circles.DebugManager;
import pl.ijestfajnie.circles.kernel.RAMDriver.RAM_RESULT;

public class Kernel {
	
	private static Kernel kernel;
	
	public enum KERNEL_MODE {
		MICHIE_REAL, HAWK_PROTECTED;
	}
	
	public enum SYSTEM_RESULT {
		OK, ERROR, UNKNOWN_CALL;
	}

	List<Process> processes = new ArrayList<Process>();
	List<Driver> drivers = new ArrayList<Driver>();

	public Kernel() {
		kernel = this;
		drivers.add(new GraphicsDriver());
		drivers.add(new RAMDriver());
		for(int i = 0; i < drivers.size(); i++) {
			drivers.get(i).Load();
		}
	}
	
	private Driver GetDriver(Class<?> driver) {
		for(int i = 0; i < drivers.size(); i++) {
			if(drivers.get(i).getClass() == driver) {
				return drivers.get(i);
			}
		}
		return null;
	}
	
	public void RunProcess(FileInputStream file) {
		String[] cartridge = ReadCartridge(file);
		processes.add(new Process(processes.size() + 1, cartridge));
		new Thread(processes.get(processes.size() - 1)).start();
	}
	
	private String[] ReadCartridge(FileInputStream file) {
		String[] cartridge;
		BufferedReader bfr = new BufferedReader(new InputStreamReader(file));
		List<String> strr = new ArrayList<String>();
		String line = "";
		try {
			while ((line = bfr.readLine()) != null) {
				strr.add(line);
			}
		} catch (IOException e) {
			DebugManager.debug(5);
		}
		cartridge = new String[strr.size()];
		for (int i = 0; i < strr.size(); i++) {
			cartridge[i] = strr.get(i);
		}
		return cartridge;
	}
	
	public static Enum<?> SystemCall(String call, String[] args) {
		if(call.equalsIgnoreCase("setram")) {
			RAMDriver ramDriver = (RAMDriver)kernel.GetDriver(RAMDriver.class);
			if(!(ramDriver.GetFlags(Integer.parseInt("00000001", 2)) == Integer.parseInt("00000001", 2))) {
				return ramDriver.SetRam(Integer.parseInt(args[0]), (char)Integer.parseInt((args[1])));
			} else {
				return RAM_RESULT.KERNEL_MODE_ERROR;
			}
		} else if(call.equalsIgnoreCase("setvideoram")) {
			GraphicsDriver graphicsDriver = (GraphicsDriver)kernel.GetDriver(GraphicsDriver.class);
			RAMDriver ramDriver = (RAMDriver)kernel.GetDriver(RAMDriver.class);
			if(!(ramDriver.GetFlags(Integer.parseInt("00000001", 2)) == Integer.parseInt("00000001", 2))) {
				return graphicsDriver.SetVideoRam(Integer.parseInt(args[0]), (char)Integer.parseInt((args[1])));
			} else {
				return RAM_RESULT.KERNEL_MODE_ERROR;
			}
		}
		return SYSTEM_RESULT.UNKNOWN_CALL;
	}
	
	public static int SystemCallValue(String call, String[] args) {
		if(call.equalsIgnoreCase("getram")) {
			RAMDriver ramDriver = (RAMDriver)kernel.GetDriver(RAMDriver.class);
			if(!(ramDriver.GetFlags(Integer.parseInt("00000001", 2)) == Integer.parseInt("00000001", 2))) {
				return ramDriver.GetRam(Integer.parseInt(args[0]));
			}
		} else if(call.equalsIgnoreCase("getvideoram")) {
			RAMDriver ramDriver = (RAMDriver)kernel.GetDriver(RAMDriver.class);
			GraphicsDriver graphicsDriver = (GraphicsDriver)kernel.GetDriver(GraphicsDriver.class);
			if(!(ramDriver.GetFlags(Integer.parseInt("00000001", 2)) == Integer.parseInt("00000001", 2))) {
				return graphicsDriver.GetVideoRam(Integer.parseInt(args[0]));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		try {
			new Kernel().RunProcess(new FileInputStream(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
