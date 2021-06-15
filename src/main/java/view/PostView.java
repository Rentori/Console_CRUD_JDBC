package view;

import controller.PostController;
import model.Post;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PostView implements View {
    String operationsInfo = ("""
                ----------------------------------
                1: Save post.
                2: Update post.
                3: Get post by ID.
                4: Delete post.
                5: Get all posts.
                6: Exit.
                ----------------------------------
                """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    PostController postController = new PostController();

    @Override
    public void run() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> savePostView();
                    case "2" -> updatePostView();
                    case "3" -> getPostByIdView();
                    case "4" -> deletePostByIdView();
                    case "5" -> getAllPostView();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void savePostView() {
        System.out.print("Input content: ");
        String content = in.next();
        System.out.print("Input writer ID: ");
        Long id = in.nextLong();
        Post post = new Post(content, id);
        postController.save(post);
        System.out.println("Saved!");
    }

    public void updatePostView() {
        System.out.print("Inpust post ID:");
        Long id = in.nextLong();
        System.out.print("Input new content: ");
        String content = in.next();
        Post post = new Post(id, content);
        postController.update(post);
        System.out.println("Updated!");
    }

    public void getPostByIdView() {
        System.out.print("Input post ID:");
        Long id = in.nextLong();
        Post post = postController.getById(id);
        System.out.println(post);
    }

    public void deletePostByIdView() {
        System.out.println("Input post ID:");
        Long id = in.nextLong();
        postController.deleteById(id);
        System.out.println("Deleted!");
    }

    public void getAllPostView() {
        List<Post> postList = postController.getAll();
        postList.forEach(System.out::println);
    }
}
