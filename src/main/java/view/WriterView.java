package view;

import controller.WriterController;
import model.Writer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WriterView implements View {
    String operationsInfo = ("""
                ----------------------------------
                1: Save writer.
                2: Update writer.
                3: Get writer by ID.
                4: Delete writer.
                5: Get all writers.
                6: Exit.
                ----------------------------------
                """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    WriterController writerController = new WriterController();

    @Override
    public void run() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> saveWriterView();
                    case "2" -> updateWriterView();
                    case "3" -> getWriterByIdView();
                    case "4" -> deleteWriterByIdView();
                    case "5" -> getAllWritersView();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void saveWriterView() {
        System.out.print("Inpust first name: ");
        String firstName = in.next();
        in.reset();
        System.out.print("Input last name: ");
        String lastName = in.next();

        writerController.save(new Writer(null, firstName, lastName));
        System.out.println("Saved!");
    }

    public void updateWriterView() {
        System.out.print("Input writer ID: ");
        Long id = in.nextLong();
        System.out.print("Input new first name: ");
        String firstName = in.next();
        in.reset();
        System.out.print("Input new last name: ");
        String lastName = in.next();
        writerController.update(new Writer(id, firstName, lastName));
        System.out.println("Updated!");
    }

    public void getWriterByIdView() {
        System.out.print("Input writer ID: ");
        Long id = in.nextLong();
        Writer writer = writerController.getById(id);
        System.out.println(writer);
    }

    public void deleteWriterByIdView() {
        System.out.print("Input writer ID: ");
        Long id = in.nextLong();
        writerController.deleteById(id);
        System.out.println("Deleted!");
    }

    public void getAllWritersView() {
        List<Writer> writerList = writerController.getAll();
        writerList.forEach(System.out::println);
    }
}
