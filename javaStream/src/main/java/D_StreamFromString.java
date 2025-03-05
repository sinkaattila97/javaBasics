public class D_StreamFromString {
    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps over the lazy dog";

        sentence.chars()
                .mapToObj(codePoint -> Character.toString(codePoint)) // Convert int to String of the ASCII code
                .filter(letter -> !letter.equals(" "))
                .distinct()
                .sorted()
                .forEach(System.out::print);
    }
}
