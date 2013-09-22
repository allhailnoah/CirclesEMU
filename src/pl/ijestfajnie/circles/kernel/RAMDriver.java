package pl.ijestfajnie.circles.kernel;

import pl.ijestfajnie.circles.emulator.RandomAccessMemory;

public class RAMDriver extends Driver {

	private RandomAccessMemory ram;
	
	public enum RAM_RESULT {
		OK, INDEX_OUT_OF_BOUNDS, VALUE_TOO_HIGH, RAM_NOT_FOUND, ACCESS_DENIED, 
		NOT_ENOUGH_MEMORY, KERNEL_MODE_ERROR;
	}
	
	@Override
	public void Load() {
		//Boot the GPU
		ram = new RandomAccessMemory();
	}

	@Override
	public void Unload() {
		// TODO Auto-generated method stub
	}
	
	public RAM_RESULT SetRam(int index, char value) {
		if(index < 0 || index > ram.MAX_BYTES - 1) {
			return RAM_RESULT.INDEX_OUT_OF_BOUNDS;
		}
		ram.ram[index] = value;
		return RAM_RESULT.OK;
	}
	
	public char GetRam(int index) {
		if(index < 0 || index > ram.MAX_BYTES - 1) {
			return 255;
		}
		return ram.ram[index];
	}
	
	public RAM_RESULT AllocatePages(int processId, int bytes) {
		if(!((ram.flags_register & 0x1) == 0x1)) {
			return RAM_RESULT.KERNEL_MODE_ERROR;
		}
		if(bytes < 0 || bytes > ram.MAX_BYTES - 1 || bytes > ram.free_pages) {
			return RAM_RESULT.NOT_ENOUGH_MEMORY;
		}
		
		return RAM_RESULT.OK;
	}
	
	public void SetFlagsPositive(char flags) {
		ram.flags_register |= flags;
	}

	public void SetFlagsNegative(char flags) {
		ram.flags_register &= flags;
	}
	
	public int GetFlags(int flags) {
		return ram.flags_register & flags;
	}
}

