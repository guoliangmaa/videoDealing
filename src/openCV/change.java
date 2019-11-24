package openCV;

public class change {
	public native void changeVedio(int x,int y,int width,int height,int Num, String path);
	static {
		System.loadLibrary("OPCV");
	}
	
}
