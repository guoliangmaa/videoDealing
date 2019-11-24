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
		// TODO �Զ����ɵĹ��캯�����
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
		String fileFinalPath = "D:/cut/"+Num+".avi";//�����������Ƶ����·��
		// ����һ��List����������ת����Ƶ�ļ�Ϊflv��ʽ������
		List<String> convert = new ArrayList<String>();
		convert.add("ffmpeg/bin/ffmpeg.exe"); // ���ת������·��
		convert.add("-i"); // ��Ӳ�����-i�����ò���ָ��Ҫת�����ļ�
		convert.add(path); // ���Ҫת�� ��ʽ����Ƶ�ļ���·��
		convert.add("-strict");//����ʱ��
		convert.add("-2");//
		convert.add("-vf");//������ʽ
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
		convert.add("-y"); // ��Ӳ�����-y�����ò���ָ���������Ѵ��ڵ��ļ�
		Process videoProcess = null;
		try {
			videoProcess = new ProcessBuilder(convert).start();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
        
		new PrintStream(videoProcess.getErrorStream()).start();
		            
		new PrintStream(videoProcess.getInputStream()).start();
		            
		try {
			videoProcess.waitFor();
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
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