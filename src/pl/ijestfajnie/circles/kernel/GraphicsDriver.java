package pl.ijestfajnie.circles.kernel;

import javax.swing.JFrame;

import pl.ijestfajnie.circles.emulator.GraphicsProcessingUnit;

public class GraphicsDriver extends Driver {

	private GraphicsProcessingUnit gpu;
	
	public enum GRAPHICS_RESULT {
		OK, INDEX_OUT_OF_BOUNDS, VALUE_TOO_HIGH, GPU_NOT_FOUND;
	}
	
	@Override
	public void Load() {
		//Boot the GPU
		gpu = new GraphicsProcessingUnit();
		gpu.setResizable(false);
        gpu.setLocationRelativeTo(null);
        gpu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gpu.setVisible(true);
        gpu.run();
	}

	@Override
	public void Unload() {
		// TODO Auto-generated method stub
	}
	
	public GRAPHICS_RESULT SetVideoRam(byte value, int index) {
		if(index < 0 || index > gpu.MAX_BYTES - 1) {
			return GRAPHICS_RESULT.INDEX_OUT_OF_BOUNDS;
		}
		gpu.video_ram[index] = value;
		return GRAPHICS_RESULT.OK;
	}
	
	public byte GetVideoRam(int index) {
		if(index < 0 || index > gpu.MAX_BYTES - 1) {
			return -1;
		}
		return gpu.video_ram[index];
	}

}
