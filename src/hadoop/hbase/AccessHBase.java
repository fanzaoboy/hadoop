package hadoop.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class AccessHBase {
	
	private static String tableName = "AccessLog";
	private static String rowkey = "27.19.74.143";
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.0.23:2181");
		
		HBaseAdmin ha = new HBaseAdmin(conf);
		HTable ht = new HTable(conf, tableName);
		Get get = new Get(Bytes.toBytes(rowkey));
		get.addFamily(Bytes.toBytes("IP"));
		Result res = ht.get(get);
		
		byte[] datetimes = res.getValue(Bytes.toBytes("IP"), Bytes.toBytes("datetime"));
		byte[] urls = res.getValue(Bytes.toBytes("IP"), Bytes.toBytes("url"));
		
		String datetime = Bytes.toString(datetimes);
		String url = Bytes.toString(urls);
		
		System.out.println(datetime+","+url);
		
	}
}
