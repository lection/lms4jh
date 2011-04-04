package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import lms.exception.FileConverException;
import lms.model.Book;
import static util.BookUploadMessageUtil.*;

public class TypeConver4WinPdfSegments implements ITypeConver{
	private String common;
	private int	definition = 0;
	private int maxSize = 1048576;
	
	private int process(String srcName,String targetName,String... commons) throws IOException{
		List<String> commonList = new ArrayList<String>();
		commonList.add(common);
		commonList.add(srcName);
		commonList.addAll(Arrays.asList(commons));
		commonList.add(targetName);
		ProcessBuilder pb = new ProcessBuilder(commonList);
		pb.redirectErrorStream(true);
		Process proc = pb.start();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null){
//				System.out.println(line);
			}
		br.close();
		return proc.exitValue();
	}
	
	@Override
	public void conver(String postfix, File bookFile, String fileId, String swfDir,Book book,Properties prop)
			throws FileConverException {
		int definition = this.definition;
		int maxSize = this.maxSize;
		if(prop != null){
			if(prop.containsKey("definition")){
				definition = Integer.parseInt(prop.getProperty("definition"));
			}
			if(prop.containsKey("maxSize")){
				maxSize = Integer.parseInt(prop.getProperty("maxSize"));
			}
		}
		File dir = new File(swfDir,fileId);
		if(!dir.exists()){
			dir.mkdir();
		}
		File testFile = new File(dir,"test.swf");
		try {
			message("文件转换测试开始");
			int length = 0;
			for(int i=1;i<=3;i++){
				int ev = process(bookFile.getAbsolutePath(),testFile.getAbsolutePath()
						,"-j",definition+"","-t","-p",1+book.getPages()*(i-1)/3+"","-o");
				if(ev != 0 || !testFile.exists() || testFile.length() == 0){
					message("转换测试失败");
					throw new FileConverException(postfix
							,new Exception("文件内部存在问题，转换失败"));
				}
				length += testFile.length();
			}
			length = length/3;
			int segmentSize = maxSize/length+1;
			int segments = (book.getPages()+segmentSize-1)/segmentSize;
			message("转换测试成功。文件共"+book.getPages()+"页。");
			message("浏览文件预计平均每页大小"+length+"字节。");
			message("浏览文件拆分为"+segments+"段，每段大约包含"+segmentSize+"页。");
			message("开始正式转换工作，请耐心等待。");
			File segSwf = null;
			for(int i=1;i<=segments;i++){
				segSwf = new File(dir,i+".swf");
				long start = System.currentTimeMillis();
				process(bookFile.getAbsolutePath(),segSwf.getAbsolutePath()
						,"-j","0","-t","-p",(1+(segmentSize*(i-1)))+"-"+(segmentSize*i),"-o");
				message("第"+i+"段文件转换成功，耗时"+
						(System.currentTimeMillis()-start)/1000+"秒");
				if(!segSwf.exists()){
					segments = i-1;
					message("转换工作提前完成，分段"+segments+"，可能总页数输入不正确。");
					break;
				}
			}
			book.setSegments(segments);
			book.setSegmentSize(segmentSize);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileConverException("服务器格式转换程序执行出现错误",e);
		}
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public int getDefinition() {
		return definition;
	}

	public void setDefinition(int definition) {
		this.definition = definition;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public static void main(String[] args) throws FileConverException{
		TypeConver4WinPdfSegments cover = new TypeConver4WinPdfSegments();
		cover.setCommon("D:\\Program Files\\SWFTools\\pdf2swf.exe");
		Book book = new Book();
		book.setPages(732);
		File bookFile = new File("E:\\ebook\\it\\framework\\Struts 2权威指南--基于WebWork核心的MVC开发.pdf");
		cover.conver("pdf", bookFile, "123", 
				"D:\\Program Files\\SWFTools\\test", book,null);
	}
	
}
