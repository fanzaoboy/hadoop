package hadoop.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BiShi {

	public static void main(String[] args) throws IOException {
		//read file from local
		File file = new File("E:\\个人文档\\学习资料\\大数据\\笔试题\\笔试题一\\test_input1.txt");
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String s = "";
		//read line
		while ((s = br.readLine()) != null) {
			s = BiShi.replaceSymbol(s);
			System.out.println(s);
			sb.append(s + "\n");
		}
		//Write to local
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\个人文档\\学习资料\\大数据\\笔试题\\笔试题一\\test_output.txt"));
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	/**
	 * 
	 * @param str
	 * @return String
	 * @desc 替换符号
	 */
	public static String replaceSymbol(String str) {
		//replace the Symbol
		String result = str.replace(",", "").replace(".", "").replace("!", "").replace("?", "").replace(":", "")
				.replace(";", "");
		//transform to LowerCase
		result = result.trim().toLowerCase();
		//return
		String res = "";
		//empty line show then slogan
		if (result == null || "".equals(result)) {
			result = "[REMOVED]";
		}
		//cut the line and split by Space,and remove excess Space
		String[] strs = result.split(" ");
		for (String str1 : strs) {
			if (!"".equals(str1)) {
				res += str1.trim() + " ";
			}
		}
		return res;
	}

	public static String replaceNumber(String str) {

		return "";

	}
}
