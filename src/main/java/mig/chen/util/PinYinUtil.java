package mig.chen.util;
 
import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
 
public class PinYinUtil {
 
//	public static void main(String[] args) {
//		String pinyin= getFirstSpell("阳阳");
//		System.out.println(pinyin);
//	}
	/**
	 * 获取汉子拼音(全拼音)
	 * @param inputString
	 * @return
	 */
	public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = inputString.trim().toCharArray();
        String output = "";
 
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }
	
	/**获取汉字串拼音首字母(第一个字母的首字母)，英文字符不变  
	 *   
	 * **/
	 public static String getFirstSpell(String chinese) {   
         try {
			StringBuffer pybf = new StringBuffer();   
			 char[] arr = chinese.toCharArray();   
			 HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
			 defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
			 defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
			 if(arr.length >0){
				 String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[0], defaultFormat);   
			     if (temp != null) {   
			             pybf.append(temp[0].charAt(0));   
			     }  
			 }
			 return pybf.toString().replaceAll("\\W", "").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return "Other";
     }   
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */  
    public static String getEachFirstSpell(String chinese) {   
        StringBuffer pybf = new StringBuffer();   
        char[] arr = chinese.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < arr.length; i++) {   
                if (arr[i] > 128) {   
                        try {   
                                String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                if (temp != null) {   
                                        pybf.append(temp[0].charAt(0));   
                                }   
                        } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                e.printStackTrace();   
                        }   
                } else {   
                        pybf.append(arr[i]);   
                }   
        }   
        return pybf.toString().replaceAll("\\W", "").trim();   
    }   
    /**  
     * 获取汉字串拼音，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音  
     */  
    public static String getFullSpell(String chinese) {   
        StringBuffer pybf = new StringBuffer();   
        char[] arr = chinese.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < arr.length; i++) {   
                if (arr[i] > 128) {   
                        try {   
                                pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                        } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                e.printStackTrace();   
                        }   
                } else {   
                        pybf.append(arr[i]);   
                }   
        }   
        return pybf.toString();   
    }
    
    /**  
     * 进行汉语分词，返回分词结果为名词的部分  
     * @param question 汉字串  
     * @return 名词  
     */  
    public static List<String> getSegments(String question) {
    	List<Term> termList = HanLP.segment(question);
    	List<String> results = new ArrayList<>();
		for (Term term : termList) {
			if (term.nature.toString().equals("n")) {
				results.add(term.word);
			}
		}
		return results;
	}
    
	public static void addCustomDictionary() {
		CustomDictionary.add("瀑布沟水电站", "nm");
		CustomDictionary.add("瀑布沟", "nm");
		CustomDictionary.add("水电站", "nm");

		CustomDictionary.add("水质监测因子", "nd");
		CustomDictionary.add("植物物种组成", "nd");
		CustomDictionary.add("生态保护目标", "nd");
		CustomDictionary.add("鱼类保护措施", "nd");
		CustomDictionary.add("动物", "nd");
		CustomDictionary.add("植物", "nd");
		CustomDictionary.add("鱼类", "nd");
		CustomDictionary.add("社会环境", "nd");

	}
	
}


