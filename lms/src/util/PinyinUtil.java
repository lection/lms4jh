package util;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	private static final String[] BLANK_ARRAY = new String[]{"",""};
	private static HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat(); 
	static{
		pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
		pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}
	
	public static String[] converString(String str){
		if(StringUtils.isBlank(str))return BLANK_ARRAY;
		str = str.replace(" ", "");
		StringBuilder pinyin = new StringBuilder();
		StringBuilder py = new StringBuilder();		
		char[] buffer = str.toCharArray();
		String[] result = null;
		for(int i=0;i<buffer.length;i++){
			if(buffer[i] > 128){
				try {
					result = PinyinHelper.toHanyuPinyinStringArray(buffer[i],pinyinFormat);
					if(result!=null && result.length>1){
						pinyin.append(result[0]);
						py.append(result[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
				}
			}else{
				pinyin.append(buffer[i]);
				py.append(buffer[i]);
			}
		}
		return new String[]{pinyin.toString(),py.toString()};
	}
	
	public static void main(String[] args){
		System.out.println(converString("很好"));
	}
}
