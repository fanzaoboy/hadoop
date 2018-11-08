package hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	public static class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String[] lines = value.toString().split(",");

			for (String line : lines) {
				context.write(new Text(line), new IntWritable(1));
			}
		}

	}

	public static class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			int count = 0;

			for (IntWritable value : values) {
				count += value.get();
			}
			context.write(key, new IntWritable(count));
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		System.out.println("[INFO] args.length = " + args.length);

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.0.23:8020");
		FileSystem fileSystem = FileSystem.get(conf);

		if (fileSystem.exists(new Path(args[1]))) {
			fileSystem.delete(new Path(args[1]), true);
		}
		Job job = Job.getInstance(conf);
		job.setJarByClass(WordCount.class);

		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReduce.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
