package hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import hadoop.Util.CutStringUtils;
import hadoop.Util.HdfsUtils;

public class AccessLog {

	public static class AccessLogMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String IP = CutStringUtils.parse(line)[0];
			String time = CutStringUtils.parse(line)[1];
			context.write(new Text(IP + "," + time), new IntWritable(1));

		}

	}

	public static class AccessLogReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> value, Context context)
				throws IOException, InterruptedException {
			int count = 0;
			for (IntWritable v : value) {
				count += v.get();
			}
			context.write(key, new IntWritable(count));
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
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
