package hadoop.test;

import java.io.IOException;

import hadoop.Util.HdfsUtils;

public class TestUtils {

	public static void main(String[] args) throws IOException {
//		System.out.println(HdfsUtils.mkdir("/wc"));
//		System.out.println(HdfsUtils.deleteDir("/wc"));
		String[] strs = HdfsUtils.showDir("/flume/");
		for(String str : strs) {
			System.out.println(str);
		}
	}
}
