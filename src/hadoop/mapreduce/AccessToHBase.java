package hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import hadoop.Util.CutStringUtils;

public class AccessToHBase {

	public static class AccessToHBaseMap extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
		ImmutableBytesWritable rowkey = new ImmutableBytesWritable();
		
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] strs = CutStringUtils.parse(value.toString());
			rowkey.set(Bytes.toBytes(strs[0]));
			
			Put put = new Put(Bytes.toBytes(strs[0]));
			put.addColumn(Bytes.toBytes("IP"), Bytes.toBytes("datetime"), Bytes.toBytes(strs[1]));
			put.addColumn(Bytes.toBytes("IP"), Bytes.toBytes("url"), Bytes.toBytes(strs[2]));
			put.addColumn(Bytes.toBytes("IP"), Bytes.toBytes("status"), Bytes.toBytes(strs[3]));
			put.addColumn(Bytes.toBytes("IP"), Bytes.toBytes("traffic"), Bytes.toBytes(strs[4]));
			
			context.write(rowkey, put);
			
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf, AccessToHBase.class.getSimpleName());
		
		job.setJarByClass(AccessToHBase.class);
		job.setMapperClass(AccessToHBaseMap.class);
		job.setMapOutputKeyClass(ImmutableBytesWritable.class);
		job.setMapOutputValueClass(Put.class);
		job.setOutputKeyClass(TableOutputFormat.class);
		TableMapReduceUtil.initTableReducerJob("AccessLog", null, job);
		job.setNumReduceTasks(1);
		FileInputFormat.setInputPaths(job, new Path(args[0]));

		job.waitForCompletion(true);
	}
}
