/* CS207 Cryptogram Project - Iteration 1 - Group 25 2026 */
package org.group25;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class GUI extends JPanel {
  private final AppData appData;
  private JFrame mainFrame;
  private JLabel titleLabel;
  private static JPanel contentPane;

  public GUI(AppData appData) {
    this.appData = appData;
    SwingUtilities.invokeLater(this::setup);
  }

  private void setup() {
    System.out.println("Setting up GUI");
    mainFrame = new JFrame("Cryptogram Game");

    mainFrame.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent windowEvent) {
            App.shutdown();
            System.exit(0);
          }
        });

    mainFrame.setSize(800, 600);
    mainFrame.setLayout(new GridBagLayout());
    mainFrame.setVisible(true);

    titleLabel = new JLabel("Group 25 Cryptogram Game");
    mainFrame.add(titleLabel, GUI.setConstraints(0, 0, 1, 1));

    contentPane = new JPanel();
    mainFrame.add(contentPane, GUI.setConstraints(0, 1, 1, 1));

    switchContent(new LoginSignUp(appData));
  }

  protected static void switchContent(JPanel pane) {
    // Use invokeLater to ensure thread safety
    if (SwingUtilities.isEventDispatchThread()) {
      contentPane.removeAll();
      contentPane.add(pane);
      contentPane.revalidate();
      contentPane.repaint();
    } else {
      SwingUtilities.invokeLater(() -> switchContent(pane));
    }
  }

  static GridBagConstraints setConstraints(int x, int y, int width, int height) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = width;
    gbc.gridheight = height;
    return gbc;
  }
}

class LoginSignUp extends JPanel {
  private final AppData appData;

  public LoginSignUp(AppData appData) {
    this.appData = appData;
    this.setLayout(new GridBagLayout());

    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Sign Up", new SignUp(appData));
    tabs.addTab("Log In", new Login(appData));
    this.add(tabs, GUI.setConstraints(0, 0, 1, 1));
    this.revalidate();
    this.repaint();
  }

  class SignUp extends JPanel {
    private JTextField usernameField;
    private final AppData appData;

    public SignUp(AppData appData) {
      this.appData = appData;

      this.setLayout(new GridBagLayout());
      this.add(new JLabel("Welcome to the app!:"), GUI.setConstraints(0, 0, 2, 1));
      this.add(new JLabel("Username:"), GUI.setConstraints(0, 1, 1, 1));

      usernameField = new JTextField(20);
      this.add(usernameField, GUI.setConstraints(1, 1, 1, 1));

      JButton logInButton = new JButton("Sign Up");
      logInButton.addActionListener(e -> signUpButton());
      this.add(logInButton, GUI.setConstraints(0, 2, 2, 1));
    }

    private void signUpButton() {
      usernameField.setText(usernameField.getText().strip());

      if (usernameField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(
            this, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
      } else if (appData.getUsers().findAny().isPresent()
          && appData
              .getUsers()
              .anyMatch(username -> username.getUsername().equals(usernameField.getText()))) {
        JOptionPane.showMessageDialog(
            this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        System.out.println("user added!");
        appData.addUser(usernameField.getText());
        System.out.println("signed up as " + appData.getCurrentUser().getUsername());
        GUI.switchContent(new GameBoard(appData));
      }
    }
  }

  class Login extends JPanel {
    private final JTextField usernameField;
    private final AppData appData;

    public Login(AppData appData) {
      this.appData = appData;

      this.setLayout(new GridBagLayout());
      this.add(new JLabel("Welcome back!:"), GUI.setConstraints(0, 0, 2, 1));
      this.add(new JLabel("Username:"), GUI.setConstraints(0, 1, 1, 1));

      usernameField = new JTextField(20);
      this.add(usernameField, GUI.setConstraints(1, 1, 1, 1));

      JButton logInButton = new JButton("Log In");
      logInButton.addActionListener(e -> loginButton());
      this.add(logInButton, GUI.setConstraints(0, 2, 2, 1));
    }

    public void loginButton() {
      usernameField.setText(usernameField.getText().strip());
      if (usernameField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(
            this, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      if (this.appData.setCurrentUser(usernameField.getText())) {
        System.out.println("logged in as " + this.appData.getCurrentUser().getUsername());
        GUI.switchContent(new GameBoard(appData));
      } else {
        JOptionPane.showMessageDialog(
            this, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}

class GameBoard extends JPanel {
  private final AppData appData;
  private final Game game;

  public GameBoard(AppData appData) {
    this.appData = appData;
    game = new Game(appData);
    this.setLayout(new GridBagLayout());
    this.add(new GameSettings(appData), GUI.setConstraints(0, 0, 1, 1));
  }

  private class GameSettings extends JPanel {
    private final AppData appData;

    public GameSettings(AppData appData) {
      this.appData = appData;
      this.setLayout(new GridBagLayout());

      JButton newGameButton = new JButton("New Game");
      newGameButton.addActionListener(
          _ -> {
            game.newGame();
            GUI.switchContent(new MainGame(appData));
          });
      this.add(newGameButton, GUI.setConstraints(0, 0, 1, 1));

      JButton resumeGameButton = new JButton("Resume Game");
      resumeGameButton.addActionListener(
          _ -> {
            if (game.continueGame()) {
              GUI.switchContent(new MainGame(appData));
            } else {
              JOptionPane.showMessageDialog(
                  this, "No existing games!", "Error", JOptionPane.ERROR_MESSAGE);
            }
          });
      this.add(resumeGameButton, GUI.setConstraints(0, 1, 1, 1));

      JButton backButton = new JButton("Back");
      backButton.addActionListener(_ -> GUI.switchContent(new LoginSignUp(appData)));
      this.add(backButton, GUI.setConstraints(0, 2, 1, 1));
    }

    private class MainGame extends JPanel {
      private final AppData appData;

      public MainGame(AppData appData) {
        this.appData = appData;
        this.setLayout(new GridBagLayout());

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        JScrollPane scrollPane =
            new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        textArea.setText(game.getCurrentCryptogram().getSolution());
        this.add(scrollPane, GUI.setConstraints(0, 0, 1, 1));

        JButton scoreButton = new JButton("View Scoreboard");
        scoreButton.addActionListener(_ -> GUI.switchContent(new Scoreboard(appData, this)));
        this.add(scoreButton, GUI.setConstraints(0, 1, 1, 1));

        JButton back = new JButton("Back");
        back.addActionListener(_ -> GUI.switchContent(new GameBoard(appData)));
        this.add(back, GUI.setConstraints(0, 2, 1, 1));
      }
    }
  }
}

class Scoreboard extends JPanel {
  private final AppData appData;
  JTable table;

  public Scoreboard(AppData appData, JPanel previous) {
    this.appData = appData;

    this.setLayout(new GridBagLayout());

    String[] columnNames = {"Username", "Games Won"};
    String[][] data =
        this.appData
            .getUsers()
            .map(user -> new String[] {user.getUsername(), String.valueOf(user.getGamesWon())})
            .toArray(String[][]::new);
    table = new JTable(data, columnNames);
    table.setDefaultEditor(Object.class, null);

    JScrollPane scrollPane =
        new JScrollPane(
            table,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane, GUI.setConstraints(0, 1, 1, 1));

    JButton back = new JButton("Back");
    back.addActionListener(_ -> GUI.switchContent(previous));
    this.add(back, GUI.setConstraints(0, 0, 1, 1));
  }
}
