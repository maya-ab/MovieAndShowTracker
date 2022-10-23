
package ui.console;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MovieShowTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}

