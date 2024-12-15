package positional_index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class PositionalIndexReducer extends Reducer<Text, Text, Text, Text> {
    private final Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Map<String, List<Integer>> docPositionsMap = new HashMap<>();

        // Process all values for the given key
        for (Text value : values) {
            String[] docAndPosition = value.toString().split("#");
            String docId = docAndPosition[0];
            int position = Integer.parseInt(docAndPosition[1]);

            docPositionsMap.computeIfAbsent(docId, k -> new ArrayList<>()).add(position);
        }


        StringJoiner finalOutput = new StringJoiner(", ");
        for (Map.Entry<String, List<Integer>> entry : docPositionsMap.entrySet()) {
            String docId = "doc#" + entry.getKey();
            String positions = entry.getValue().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            finalOutput.add(docId + ": " + positions);
        }


        result.set(finalOutput.toString());
        context.write(key, result);

        System.out.println(key.toString() + "\t" + result.toString());
    }
}
