package Session3;

import java.util.List;

public class B6_s3 {

    record Post(List<String> tags) {}

    public static void main(String[] args) {

        List<Post> posts = List.of(
                new Post(List.of("java", "backend")),
                new Post(List.of("python", "data"))
        );
        // cách 1
        posts.stream()
                .flatMap(p -> p.tags().stream())
                .forEach(System.out::println);
        // cách 2
        List<String> tags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .toList();

        System.out.println(tags);
    }
}
