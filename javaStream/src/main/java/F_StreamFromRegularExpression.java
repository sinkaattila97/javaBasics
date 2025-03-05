import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class F_StreamFromRegularExpression {
    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps over the lazy dog";

        String[] words = sentence.split(" ");
        Stream<String> wordsStream = Arrays.stream(words);

        long count = wordsStream.count();
        System.out.println("Number of words: " + count);

        //a better approach is to use pattern
        /*
        * The difference between the two is that the second , there is no construction of any array
        * there is no pressure on the memory
        * less pressure on the CPU
        * */
        Pattern pattern = Pattern.compile(" ");
        long count2 = pattern.splitAsStream(sentence).count();
        System.out.println("Number of words: " + count2);
    }
}
