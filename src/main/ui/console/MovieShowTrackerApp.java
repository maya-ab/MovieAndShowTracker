
package ui.console;

import model.Media;
import model.Watched;
import model.WantToWatch;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


//Represents Movie and TV Show Tracker Application with a wantToWatchWorkRoom
public class MovieShowTrackerApp {

    public Watched watchedList;
    public WantToWatch wantToWatch;
    public Scanner input; // from Teller App ui.TellerApp.
    boolean keepRunning; //from Teller App ui.TellerApp.

    public static final String JSON_STORE = "./data/workroom.json";
    public static final String JSON_STORE_WATCHED = "./data/workroomWatched.json";

    public JsonWriter jsonWriterWantToWatch;
    public JsonReader jsonReaderWantToWatch;

    public JsonReader jsonReaderWatched;
    public JsonWriter jsonWriterWatched;

    //EFFECTS: runs the Movie and TV show tracker application and constructs a wantToWatchWorkRoom
    public MovieShowTrackerApp() throws FileNotFoundException {
        watchedList = new Watched("My workroom");
        wantToWatch = new WantToWatch("My workroom");

        jsonWriterWantToWatch = new JsonWriter(JSON_STORE);
        jsonReaderWantToWatch = new JsonReader(JSON_STORE);

        jsonWriterWatched = new JsonWriter(JSON_STORE_WATCHED);
        jsonReaderWatched = new JsonReader(JSON_STORE_WATCHED);

        runMovieShowTrackerApp();
    }

    //EFFECTS: runs tracker application and processes user inputs
    //MODIFIES: this
    public void runMovieShowTrackerApp() {
        keepRunning = false;  //from Teller App ui.TellerApp.
        String command = null; //from Teller App ui.TellerApp.

        setUp();

        //from Teller App ui.TellerApp.runTeller
        while (keepRunning) {
            mainMenu();
            command = input.next();
            command.toLowerCase();
            if (command.equals("e")) {
                keepRunning = false;
            } else {
                processChoice(command);
            }
        }

    }

    //EFFECTS: presents menu of options for user
    public void mainMenu() {
        System.out.println("Do you want to:");
        System.out.println("\ta -> Enter a movie or show you've watched?");
        System.out.println("\tb -> Enter a movie or show you want to watch in the future?");
        System.out.println("\tc -> Look at titles in your watched movies and shows list, view one in detail,"
                                      + " or rate one?  ");
        System.out.println("\td -> Look at titles in your want to watch movies and shows list"
                                    + " or mark one off as watched?");
        System.out.println("\ts -> Save titles in your want to watch list");
        System.out.println("\tl -> Load titles in your want to watch list");
        System.out.println("\te -> Close application");
    }

    //EFFECTS: processes choice from menu made by user
    //from Teller App ui.TellerApp.processCommand
    private void processChoice(String command) {
        if (command.equals("a")) {
            enterWatchedMedia();
        } else if (command.equals("b")) {
           // enterWantToWatchMedia();
        } else if (command.equals("c")) {
            lookAtTitlesWatched();
        } else if (command.equals("d")) {
            lookAtTitlesWantToWatch();
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Please select one of a, b, c, d, or e");
        }
    }


    //EFFECTS: initializes watchedLists and wantToWatchList
    private void setUp() {
        input = new Scanner(System.in); //from Teller App


    }

    //EFFECTS: adds a new media to Watched
    //Modifies: this and Watched
    private void enterWatchedMedia() {
        String selectedMedia = selectMovieOrShow();
        System.out.println("Enter Title (Please use UpperCamelCase)");
        String title = input.next(); //from Teller App

        if (selectedMedia == "m") {
            Media enteredMovie = new Media(title, "Movie");
            watchedList.addMedia(enteredMovie);
        } else if (selectedMedia == "s") {
            Media enteredShow = new Media(title, "Show");
            watchedList.addMedia(enteredShow);
        } else {
            System.out.println("Must be either a show or a movie");
        }

    }



    //EFFECTS: adds a new media to WantToWatch
    //Modifies: this and wantToWatchWorkShop
    public void enterWantToWatchMedia(String title, String type) {
       // String selectedMedia = selectMovieOrShow();
       // String title = enterMediaTitle();

        if (type == "Movie") {
            Media enteredMovie = new Media(title, "Movie");
            wantToWatch.addMediaWantToWatch(enteredMovie);
        } else if (type == "Show") {
            Media enteredShow = new Media(title, "Show");
            wantToWatch.addMediaWantToWatch(enteredShow);
        }
    }

    //EFFECTS: Shows users titles of media in Watched and gives users
    //         option to add a rating or see details of one the media in the list
    public void lookAtTitlesWatched() {
        System.out.println("Here are all the titles of media you've watched:");
        System.out.println(watchedList.getTitlesWatchedList());
        String response = selectRateOrDetails();

        if (response == "r") {
            rateMedia();
        } else if (response == "d") {
            System.out.println("Please enter title of the movie or show you want to see the details of");
            String title = enterMediaTitle();
            System.out.println("Here are the details:");
            List<String> details = watchedList.getMediaDetails(title);
            System.out.println(details);
        } else if (response == "e") {
            System.out.println("You will be returned to the main menu");
        }
    }

    //EFFECTS: allows user to give media a rating and a note
    //MODIFIES: this, and media selected
    public void rateMedia() {
        System.out.println("Please Enter the title of the movie or show you want to rate");

        String title = enterMediaTitle();
        System.out.println("Now enter a rating out of 10");
        Integer rate = input.nextInt(); //from Teller App
        System.out.println("Now enter a note (Use upperCamelCase)");
        String note = input.next();//from Teller App

        watchedList.addRatingAndNote(title, rate, note);
    }

    //EFFECTS: allows users to select if they want to rate or see details of a media, records their input
    //from Teller App ui.TellerApp.runTeller.selectAccount
    public String selectRateOrDetails() {
        String response = "";
        while (!(response.equals("r") || response.equals("d") || response.equals("e"))) {
            System.out.println("\tr -> Do you want to rate a movie or TV show in your list and leave a note?");
            System.out.println("\td -> Do you want to view a movie or TV show in your list in detail?");
            System.out.println("\te -> Go back to main menu");

            response = input.next();
            response = response.toLowerCase();
        }

        if (response.equals("r")) {
            return "r";
        } else if (response.equals("d")) {
            return "d";
        } else if (response.equals("e")) {
            return "e";
        } else {
            return "";
        }
    }

    //EFFECTS: Shows users titles in WantToWatchWorkShop and asks them if they want to mark one as watched
    public void lookAtTitlesWantToWatch() {
        System.out.println(wantToWatch.getTitlesWantToWatch());
        System.out.println("Do you want to mark a movie or show as watched?");
        String answerSelected = answerYesOrNo();

        if (answerSelected == "y") {
            String titleMove = enterMediaTitle();
            wantToWatch.moveToWatched(titleMove, watchedList);
        } else if (answerSelected == "n") {
            System.out.println("You will be returned to main menu");
        }

    }

    //EFFECTS: Asks user to pick yes or no, records their response
    //from Teller App ui.TellerApp.runTeller.selectAccount
    public String answerYesOrNo() {
        String answer = "";

        while (!(answer.equals("y") || answer.equals("n"))) {
            System.out.println("\ty -> yes");
            System.out.println("\tn -> no");
            answer = input.next();
            answer = answer.toLowerCase();
        }

        if (answer.equals("y")) {
            return "y";
        } else if (answer.equals("n")) {
            return "n";
        } else {
            return "";
        }
    }

    //EFFECTS: Asks user to pick if they're entering a movie or a show, records their response
    //from Teller App ui.TellerApp.runTeller.selectAccount
    private String selectMovieOrShow() {
        String selectMedia = "";

        while (!(selectMedia.equals("m") || selectMedia.equals("s"))) {
            System.out.println("Do you want to add a Movie or TV show?");
            System.out.println("\tm -> Movie");
            System.out.println("\ts -> Show");
            selectMedia = input.next();
            selectMedia = selectMedia.toLowerCase();
        }

        if (selectMedia.equals("m")) {
            return "m";
        } else if (selectMedia.equals("s")) {
            return "s";
        } else {
            return "";
        }
    }

    //EFFECTS: asks user to input media title, records their response
    public String enterMediaTitle() {
        System.out.println("Enter Title (Please use UpperCamelCase)");
        String title = input.next(); //from Teller App ui.TellerApp.runTeller.selectAccount
        return title;
    }

    //Modelled after ui/WorkroomApp/
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: saves the wantToWatchWorkShop to file
    private void saveWorkRoom() {
        try {
            jsonWriterWantToWatch.open();
            jsonWriterWantToWatch.write(wantToWatch);
            jsonWriterWantToWatch.close();

            jsonWriterWatched.open();
            jsonWriterWatched.write(watchedList);
            jsonWriterWatched.close();
            System.out.println("Saved titles");

        } catch (FileNotFoundException e) {
            System.out.println("Can't write file");
        }
    }


    //Modelled after ui/WorkroomApp/
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //MODIFIES: THIS
    //EFFECTS: loads wantToWatchWorkShop from file
    private void loadWorkRoom() {
        try {

            wantToWatch = jsonReaderWantToWatch.read();

            watchedList = jsonReaderWatched.readWatched();
            System.out.println("Loaded titles ");

        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

}
