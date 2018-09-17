package hadoop.Util;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsUtils {

	public static String uri = "hdfs://192.168.153.20:8020";

	public static boolean mkdir(String dir) throws IOException {
		if (StringUtils.isEmpty(dir)) {
			return false;
		}

		System.setProperty("HADOOP_USER_NAME", "lock");
		dir = uri + dir;
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(dir), conf);

		if (!fs.exists(new Path(dir))) {
			fs.mkdirs(new Path(dir));
			return true;
		} else {
			System.err.println("Â·¾¶ÒÑ´æÔÚ");
			return false;
		}
	}

	public static boolean isExitsFile(String fileName) throws IOException {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}

		System.setProperty("HADOOP_USER_NAME", "lock");
		fileName = uri + fileName;
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(fileName), conf);

		if (fs.isFile(new Path(fileName))) {
			return true;
		} else {
			return false;
		}

	}
}
