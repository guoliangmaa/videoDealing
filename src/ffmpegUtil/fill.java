package ffmpegUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openCV.change;

public class fill extends Thread {
	public String path;
	public int x;
	public int y,width,height,Num;
	public fill(String path, int x, int y, int width, int height, int num) {
		// TODO 自动生成的构造函数存根
		super();
		this.path = path;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Num = num;
	}
	public void run() {
		videoCut();
	}
	//ffmpeg -i 1.avi -strict -2 -vf crop=960:1080:960:0 out2.avi
	public String videoCut() {
		String fileFinalPath = "D:/cut/"+Num+".avi";//完整的输出视频绝对路径
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		List<String> convert = new ArrayList<String>();
		convert.add("ffmpeg/bin/ffmpeg.exe"); // 添加转换工具路径
		convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
		convert.add(path); // 添加要转换 格式的视频文件的路径
		convert.add("-strict");//结束时间
		convert.add("-2");//
		convert.add("-vf");//操作方式
		switch(Num) {
		case 1:
			convert.add("crop="+(304-x)+":"+(171-y)+":0:0");
			break;
		case 2:
			convert.add("crop="+(width-304+x)+":"+(171-y)+":"+(304-x)+":0");
			break;
		case 3:
			convert.add("crop="+(304-x)+":"+(height-171+y)+":0:"+(171-y));
			break;
		case 4:
			convert.add("crop="+(width-304+x)+":"+(height-171+y)+":"+(304-x)+":"+(171-y));
			break;
		}
		convert.add(fileFinalPath);
		convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
		Process videoProcess = null;
		try {
			videoProcess = new ProcessBuilder(convert).start();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
		new PrintStream(videoProcess.getErrorStream()).start();
		            
		new PrintStream(videoProcess.getInputStream()).start();
		            
		try {
			videoProcess.waitFor();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("zhuanhua");
		change change1=new change();
		String passPath="D:\\cut\\"+Num+".avi";
		change1.changeVedio(x, y, width, height, Num, passPath);
		System.out.println(Num);


		return fileFinalPath;	
	}
}