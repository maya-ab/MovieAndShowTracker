package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Source: based on https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// TextFieldDemo, TabbedPaneDemo, Button Demo, IconDemo, LabelDemo

//Creates a new MainPage that runs the movie and TV show tracker application
public class MainPage extends JPanel {

    //Set up Fields
    public ButtonDesign enterTitleBelow;
    public ButtonDesign enterNoteBelow;
    public ButtonDesign selectMovieOrShow;
    public ButtonDesign selectRating;
    public ButtonDesign instructionForWantToWatch;
    public ButtonDesign instructionForWatched;
    public ButtonDesign showWatched;
    public ButtonDesign showWantToWatch;

    public JLabel whatYouWantToWatch;
    public JLabel whatYouHaveWatched;

    public JTextField enterTitleField;
    public JTextField enterNoteField;

    JTabbedPane selectMovieOrShowPane;
    JComponent selectMovie;
    JComponent selectShow;

    JTabbedPane rateOutOfTenPane;
    JComponent rateOne;
    JComponent rateTwo;
    JComponent rateThree;
    JComponent rateFour;
    JComponent rateFive;
    JComponent rateSix;
    JComponent rateSeven;
    JComponent rateEight;
    JComponent rateNine;
    JComponent rateTen;

    ButtonDesign addToWatched;
    ButtonDesign lookWatchedTitles;
    ButtonDesign saveButton;
    ButtonDesign addToWantToWatch;
    ButtonDesign lookWantToWatchedTitles;
    ButtonDesign loadButton;

    public WatchedAndWantToWatch watchedAndWantToWatch = new WatchedAndWantToWatch();
    JLabel titleLabel;

    //MODIFIES: this
    //EFFECTS: Sets up layout and adds items to it
    public MainPage() {
        super(new GridLayout(7, 3, 10, 10));

        init();
        setUpHeader();

        add(enterTitleBelow.getButton());
        add(selectMovieOrShow.getButton());
        add(instructionForWantToWatch.getButton());

        add(enterTitleField);
        add(selectMovieOrShowPane);
        add(addToWantToWatch.getButton());


        add(enterNoteBelow.getButton());
        add(selectRating.getButton());
        add(instructionForWatched.getButton());

        add(enterNoteField);
        add(rateOutOfTenPane);
        add(addToWatched.getButton());

        add(lookWatchedTitles.getButton());
        add(lookWantToWatchedTitles.getButton());
        add(saveButton.getButton());

        add(showWatched.getButton());
        add(showWantToWatch.getButton());
        add(loadButton.getButton());

    }

    //MODIFIES: This
    //EFFECTS: Sets up header
    private void setUpHeader() {
        JLabel titlePic;
        JLabel iconLabel;
        ImageIcon icon = createImageIcon("images/clapperBoard.png",
                "clapperBoard");

        titlePic = new JLabel("Image and Text",
                icon,
                JLabel.CENTER);


        titlePic.setVerticalTextPosition(JLabel.BOTTOM);
        titlePic.setHorizontalTextPosition(JLabel.CENTER);


        formatTitleLabel();

        iconLabel = new JLabel(icon);
        titleLabel.setVerticalTextPosition(JLabel.TOP);


        add(iconLabel);
        add(titleLabel);
        add(new JLabel(""));

        titlePic.setFont(new Font("Serif", Font.BOLD, 20));
       // setBackground(Color.gray.darker());
        setBackground(Color.black);

        addButtonAction();
        setStyle();
    }

    //EFFECTS: Formats title Label
    private void formatTitleLabel() {
        titleLabel = new JLabel("<html>\n" + "<head>\n"
                + "<style>\n"
                + "p {text-align: center;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Track Your Movies <br> and Shows! </p>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        titleLabel.setForeground(Color.getHSBColor(357, 86, 58));
    }

    //MODIFIES: This
    //EFFECTS: Initializes fields
    private void init() {
        addToWatched = new ButtonDesign("Click here to enter a title that you've watched!");
        lookWatchedTitles = new ButtonDesign("Look at Titles You've watched");
        saveButton = new ButtonDesign("Click here to save all your entries");
        addToWantToWatch = new ButtonDesign("Click here to enter a title that you want to watch!");
        lookWantToWatchedTitles = new ButtonDesign("Look at Titles You Want to Watch");
        loadButton = new ButtonDesign("Click here to load your previous entries");

        enterTitleBelow = new ButtonDesign("Enter Title Below");

        enterNoteBelow = new ButtonDesign("Enter Note Below");
        selectMovieOrShow = new ButtonDesign("Select movie or a show");
        selectRating = new ButtonDesign("Select a rating");
        instructionForWantToWatch = new ButtonDesign("To enter something you want to watch just enter a "
                + "title and select if it's a movie or a show");
        instructionForWatched = new ButtonDesign("To enter something you've watched, "
                + "addtionally enter a note and select a rating");
        whatYouHaveWatched = new JLabel("Here's What You've Watched");
        whatYouWantToWatch = new JLabel("Here's What You Want To Watch");
        showWantToWatch = new ButtonDesign(" ");
        showWatched = new ButtonDesign(" ");

        enterTitleField = new JTextField();
        enterNoteField = new JTextField();

        initPanes();
        watchedAndWantToWatch = new WatchedAndWantToWatch();
    }

    //MODIFIES: This
    //EFFECTS: Initializes Panes
    private void initPanes() {
        rateOutOfTenPane = new JTabbedPane();
        rateOutOfTenPane.addTab("1", rateOne);
        rateOutOfTenPane.addTab("2", rateTwo);
        rateOutOfTenPane.addTab("3", rateThree);
        rateOutOfTenPane.addTab("4", rateFour);
        rateOutOfTenPane.addTab("5", rateFive);
        rateOutOfTenPane.addTab("6", rateSix);
        rateOutOfTenPane.addTab("7", rateSeven);
        rateOutOfTenPane.addTab("8", rateEight);
        rateOutOfTenPane.addTab("9", rateNine);
        rateOutOfTenPane.addTab("10", rateTen);
        rateOutOfTenPane.setBackground(Color.getHSBColor(357, 86, 58));
        rateOutOfTenPane.setForeground(Color.black);

        selectMovieOrShowPane = new JTabbedPane();
        selectMovieOrShowPane.addTab("Movie", selectMovie);
        selectMovieOrShowPane.addTab("Show", selectShow);
        selectMovieOrShowPane.setBackground(Color.getHSBColor(357, 86, 58));
        selectMovieOrShowPane.setForeground(Color.black);
    }


    //Based On: DemoIcon from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: Creates and returns an image icon with given path and description
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = MainPage.class.getResource(path);

        if (imgURL != null) {
            ImageIcon img = new ImageIcon(imgURL, description);
            Image scaled = img.getImage().getScaledInstance(100, 130, Image.SCALE_DEFAULT);
            ImageIcon imgScaled = new ImageIcon(scaled);
            return imgScaled;
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //Based on: LabelDemo https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //MODIFIES: this
    //EFFECTS: Creates, sets up, and displays the GUI window
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new MainPage());

        frame.pack();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    //MODIFIES: This, WatchedAndWantToWatch
    //EFFECTS: Provides action ability to buttons
    private void addButtonAction() {
        addToWatched.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titleGiven = enterTitleField.getText();
                String typeSelected = selectMovieOrShowPane.getTitleAt(selectMovieOrShowPane.getSelectedIndex());
                Integer selectedRating = rateOutOfTenPane.getSelectedIndex() + 1;
                String noteGiven = enterNoteField.getText();
                watchedAndWantToWatch.enterWatchedMedia(titleGiven, typeSelected, selectedRating, noteGiven);

                String buttonText = showWatched.formatButtonText(watchedAndWantToWatch.lookAtWatched());
                showWatched.getButton().setText(buttonText);


            }
        });

        addToWantToWatch.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titleGiven = enterTitleField.getText();
                String typeSelected = selectMovieOrShowPane.getTitleAt(selectMovieOrShowPane.getSelectedIndex());
                watchedAndWantToWatch.enterWantToWatchMedia(titleGiven, typeSelected);

                showWantToWatch.getButton().setText(watchedAndWantToWatch.lookAtTitlesWantToWatch());

                String buttonText = showWantToWatch.formatButtonText(watchedAndWantToWatch.lookAtTitlesWantToWatch());
                showWantToWatch.getButton().setText(buttonText);

            }
        });

        saveAndLoadAction();

    }

    //MODIFIES: This, WatchedAndWantToWatch
    //EFFECTS: Provides action ability to save and load buttons
    private void saveAndLoadAction() {
        saveButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SavedPage saveScreen = new SavedPage();

                saveScreen.createGUI();
                watchedAndWantToWatch.saveWorkRoom();
            }
        });

        loadButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                watchedAndWantToWatch.loadWorkRoom();

                String buttonText = showWatched.formatButtonText(watchedAndWantToWatch.lookAtWatched());
                showWatched.getButton().setText(buttonText);

                String buttonText2 = showWantToWatch.formatButtonText(watchedAndWantToWatch.lookAtTitlesWantToWatch());
                showWantToWatch.getButton().setText(buttonText2);


            }
        });
    }

    //MODIFIES: This
    //EFFECTS: Styles buttons on page
    public void setStyle() {
        instructionForWantToWatch.getButton().setBackground(Color.black);
        instructionForWantToWatch.getButton().setVerticalAlignment(JButton.BOTTOM);
        instructionForWantToWatch.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        instructionForWatched.getButton().setBackground(Color.black);
        instructionForWatched.getButton().setVerticalAlignment(JButton.BOTTOM);
        instructionForWatched.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        enterTitleBelow.getButton().setBackground(Color.black);
        enterTitleBelow.getButton().setVerticalAlignment(JButton.BOTTOM);
        enterTitleBelow.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        selectMovieOrShow.getButton().setBackground(Color.black);
        selectMovieOrShow.getButton().setVerticalAlignment(JButton.BOTTOM);
        selectMovieOrShow.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        enterNoteBelow.getButton().setBackground(Color.black);
        enterNoteBelow.getButton().setVerticalAlignment(JButton.BOTTOM);
        enterNoteBelow.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        selectRating.getButton().setBackground(Color.black);
        selectRating.getButton().setVerticalAlignment(JButton.BOTTOM);
        selectRating.getButton().setForeground(Color.getHSBColor(357, 86, 58));

        showWantToWatch.getButton().setBackground(Color.white);
        showWantToWatch.getButton().setFont(new Font("Helvetica", Font.PLAIN, 15));
        showWatched.getButton().setBackground(Color.white);
        showWatched.getButton().setFont(new Font("Helvetica", Font.PLAIN, 15));

        saveButton.getButton().setBackground(Color.decode("#ba2d38"));
        saveButton.getButton().setForeground(Color.white);

        loadButton.getButton().setBackground(Color.decode("#ba2d38"));
        loadButton.getButton().setForeground(Color.white);

        lookWatchedTitles.getButton().setBackground(Color.black);
        lookWatchedTitles.getButton().setVerticalAlignment(JButton.BOTTOM);
        lookWatchedTitles.getButton().setForeground(Color.white);

        lookWantToWatchedTitles.getButton().setBackground(Color.black);
        lookWantToWatchedTitles.getButton().setVerticalAlignment(JButton.BOTTOM);
        lookWantToWatchedTitles.getButton().setForeground(Color.white);
    }

    //EFFECTS: Runs app and displays interactive GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }


}
