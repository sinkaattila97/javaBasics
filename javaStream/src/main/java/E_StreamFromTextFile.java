import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class E_StreamFromTextFile {
    public static void main(String[] args) {

        //taking a text file which will process line by line then create a stream of it
        Path path = Path.of("src/main/resources/People.txt");

        try (Stream<String> lines = Files.lines(path);){
            //close the file after it had been read
            long count = lines.count();
            System.out.println("Number of lines in the file: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
