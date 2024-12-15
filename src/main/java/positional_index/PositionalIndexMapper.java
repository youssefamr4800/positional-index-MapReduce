package positional_index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

public class PositionalIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Text wordKey = new Text();
    private final Text docPositionValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        FileSplit split = (FileSplit) context.getInputSplit();
        String fileName = split.getPath().getName().split("\\.")[0];

        StringTokenizer tokenizer = new StringTokenizer(value.toString());
        int position = 0;

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove special chars
            if (word.isEmpty()) {
                continue;
            }

            wordKey.set(word);

            docPositionValue.set(fileName + "#" + (1 + position));

            context.write(wordKey, docPositionValue);

            position++;
        }
    }
}
