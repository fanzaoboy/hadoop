package hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import hadoop.Util.CutStringUtils;
import hadoop.Util.HdfsUtils;

public class AccessLog {

	public static class AccessLogMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String IP = CutStringUtils.parse(line)[0];
			String time = CutStringUtils.parse(line)[1];
			String url = CutStringUtils.parse(line)[2];
			String status = CutStringUtils.parse(line)[3];
			String traffic = CutStringUtils.parse(line)[4];
			context.write(key,new Text(IP + "\t" + time + "\t" + url + "\t" + status + "\t" + traffic));

		}

	}

	public static class AccessLogReduce extends Reducer<LongWritable, Text, Text, NullWritable> {
		@Override
		protected void reduce(LongWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
			for (Text value : values) {
				context.write(new Text(value), NullWritable.get());
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		if (args.length != 2) {
			System.out.println("[ERROR] Usage: args's length not equal to 2!");
		}

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.0.23:8020");

		if (HdfsUtils.isExitsFile(args[1])) {
			HdfsUtils.deleteDir(args[1]);
		}

		Job job = Job.getInstance(conf);
		job.setJarByClass(AccessLog.class);
		job.setMapperClass(AccessLogMapper.class);
		job.setReducerClass(AccessLogReduce.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
