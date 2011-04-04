package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

import lms.exception.FileConverException;
import lms.model.Book;

public class TypeConver4WinPdf implements ITypeConver{

	private String common;
	
	@Override
	public void conver(String postfix, File bookFile,String fileId, String swfDir,Book book,Properties prop) throws FileConverException{
		if(!"pdf".equals(postfix))return;
		File swf = new File(swfDir,fileId+".swf");
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(common,bookFile.getAbsolutePath(),"-o",swf.getAbsolutePath());
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			BufferedReader br = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null){
//				System.out.println(line);
			}
			if(!swf.exists() || process.exitValue()!=0){
				throw new FileConverException(postfix,new Exception("文件内部存在问题，转换失败"));
			}
		}catch(Exception e) {
			throw new FileConverException("服务器格式转换程序执行出现错误",e);
		}
	}
	
	public void setCommon(String common) {
		this.common = common;
	}

	public static void main(String[] args) throws Exception{
//		ProcessBuilder processBuilder = new ProcessBuilder("D:\\Program Files\\SWFTools\\pdf2swf.exe","E:\\test\\book\\e3d730ae-3bca-4b4d-a97e-607664705cc0.pdf","-o","E:\\test\\swf\\1.swf");
//		Process process = processBuilder.start();
//		Runtime.getRuntime().exec("D:\\Program Files\\SWFTools\\pdf2swf.exe E:\\test\\book\\e3d730ae-3bca-4b4d-a97e-607664705cc0.pdf -o E:\\test\\swf\\1.swf");
		Properties props = System.getProperties();
		for(String s:props.stringPropertyNames()){
			System.out.println(s+"\t"+props.getProperty(s));
		}
	}

}
