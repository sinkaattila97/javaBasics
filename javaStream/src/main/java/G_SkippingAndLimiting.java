import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class G_SkippingAndLimiting {
    public static void main(String[] args) {
        IntStream.range(0, 30)
                .skip(10) //I want to skip the first 10 elements
                .limit(10) //I only need 10 elements. ___Limit is called on the steam with the skip applied___
                .forEach(index -> System.out.println(index + " "));


        Path path = Path.of("src/main/resources/People.txt");

        try (Stream<String> lines = Files.lines(path);){
            lines.skip(5).limit(10).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
