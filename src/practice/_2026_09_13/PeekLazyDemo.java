package practice._2026_09_13;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PeekLazyDemo {

    public static void main(String[] args) {
        List<String> names = List.of("java", "spring", "jpa", "mysql", "git");

        Stream<String> pipeline = names.stream()
                .filter(name -> name.length() >= 4)
                .peek(name -> System.out.println("peek : " + name));
        System.out.println("--- 끝 ---");


        //List<String> result = pipeline.toList();
        Optional<String> result2 = pipeline.findFirst()
    }
}
