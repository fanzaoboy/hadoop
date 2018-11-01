package hadoop.Util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsUtils {

	/* Home */
//	public static String uri = "hdfs://192.168.153.20:8020";
//	public static String sysName = "lock";
	/* Company */
	public static String uri = "hdfs://192.168.0.23:8020";
	public static String sysName = "root";

	/**
	 * �����ļ��У�������True/False
	 * 
	 * @param dir Ҫ������·��
	 * @return boolean
	 */
	public static boolean mkdir(String dir) throws IOException {
		if (StringUtils.isEmpty(dir)) {
			return false;
		}

		System.setProperty("HADOOP_USER_NAME", sysName);
		dir = uri + dir;
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(dir), conf);

		if (!fs.exists(new Path(dir))) {
			fs.mkdirs(new Path(dir));
			return true;
		} else {
			System.err.println("Error:" + dir + "Path already exists!");
			return false;
		}
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param fileName �ļ���
	 * @return boolean
	 */
	public static boolean isExitsFile(String fileName) throws IOException {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}

		System.setProperty("HADOOP_USER_NAME", sysName);
		fileName = uri + fileName;
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(fileName), conf);

		if (fs.isFile(new Path(fileName))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * ɾ��HDFSĿ¼
	 * @throws IOException 
	 *
	 */
	public static boolean deleteDir(String dir) throws IOException {
		if (StringUtils.isEmpty(dir)) {
			return false;
		}
		
		System.setProperty("HADOOP_USER_NAME", sysName);
		dir = uri + dir;
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(dir), conf);
		if (fs.exists(new Path(dir))) {
			fs.delete(new Path(dir),true);
			return true;
		} else {
			System.err.println("Error:[" + dir + "] Path not exists!");
			return false;
		}
	}
	
	public static String[] showDir(String inPath) {
		String strs[] = new String[100];
		System.setProperty("HADOOP_USER_NAME", sysName);
		Configuration conf = new Configuration();
		try {
			FileSystem fs = FileSystem.get(new URI(uri), conf);
			Path path = new Path(uri);
			FileStatus status[] = fs.listStatus(path);
			for(int i = 0; i < status.length; i++) {
				System.out.println(status[i].getPath().toString());
				strs[i] = status[i].getPath().toString();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strs;
	}
}
