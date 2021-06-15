package view;

import controller.LabelController;
import model.Label;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LabelView implements View {
    String operationsInfo = ("""
                ----------------------------------
                1: Save label.
                2: Update label.
                3: Get label by ID.
                4: Delete label.
                5: Get all labels.
                6: Exit.
                ----------------------------------
                """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    LabelController labelController = new LabelController();

    @Override
    public void run() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> saveLabelView();
                    case "2" -> updateLabelView();
                    case "3" -> getByIdLabelView();
                    case "4" -> deleteByIdLabelView();
                    case "5" -> getAllLabelView();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    private void saveLabelView() {
        System.out.println("Input name: ");
        String name = in.next();
        System.out.println("Input post ID: ");
        Long postId = in.nextLong();
        Label label = new Label(null, name, postId);

        label = labelController.save(label);
        System.out.println("Added: " + label);
    }

    private void updateLabelView() {
        System.out.println("Select label ID: ");
        Long id = in.nextLong();
        System.out.println("Select new Name: ");
        String name = in.next();

        Label label = labelController.update(new Label(id, name));
        System.out.println("Updated label: " + label);
    }

    private void getAllLabelView() {
        List<Label> labelList = labelController.getAll();
        labelList.forEach(System.out::println);
    }

    private void deleteByIdLabelView() {
        System.out.println("Input label ID: ");
        Long id = in.nextLong();

        labelController.deleteById(id);
        System.out.println("Success delete!");
    }

    private void getByIdLabelView() {
        System.out.println("Input label ID: ");
        Long id = in.nextLong();

        Label label = labelController.getById(id);
        if (label == null) {
            System.out.println("ID not found, try again");
        }
        else System.out.println(label);
    }
}
