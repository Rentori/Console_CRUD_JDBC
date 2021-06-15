package view;

import view.viewFactory.LabelViewFactory;
import view.viewFactory.PostViewFactory;
import view.viewFactory.ViewFactory;
import view.viewFactory.WriterViewFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView implements View{
    String operationsInfo = ("""
            ----------------------------------
            1: Label
            2: Post
            3: Writer
            4: Exit.
            ----------------------------------
            """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    ViewFactory viewFactory = null;
    View view = null;

    @Override
    public void run() {
        try {
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();
                getView(operation);

                switch (operation) {
                    case "1" -> view.run();
                    case "2" -> view.run();
                    case "3" -> view.run();
                    case "4" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void getView(String operation) {
        if (operation.equals("1")) {
            viewFactory = new LabelViewFactory();
            view = viewFactory.getView();
        }
        else if (operation.equals("2")) {
            viewFactory = new PostViewFactory();
            view = viewFactory.getView();
        }
        else if (operation.equals("3")) {
            viewFactory = new WriterViewFactory();
            view = viewFactory.getView();
        }
    }
}
