/* CS207 Cryptogram Project - Group 25 2026 */
package org.group25;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class GUI extends JPanel {
  private final AppData appData;
  private JFrame mainFrame;
  private static JPanel contentPane;
  private JLabel titleLabel;

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
    mainFrame.setLayout(new GridLayout(3, 1));
    mainFrame.setVisible(true);

    titleLabel = new JLabel("Group 25 Cryptogram Game", SwingConstants.CENTER);
    mainFrame.add(titleLabel);

    contentPane = new JPanel();
    mainFrame.add(contentPane);

    // Use invokeLater to ensure thread safety
    SwingUtilities.invokeLater(() -> switchContent(new LoginSignUp(appData)));
  }

  protected static void switchContent(JPanel pane) {
    contentPane.removeAll();
    contentPane.add(pane);
    contentPane.revalidate(); // Revalidate the content pane to reflect changes
    contentPane.repaint(); // Ensure the UI is updated
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
    this.add(tabs, setConstraints(0, 0, 1, 1));
    this.revalidate();
    this.repaint();
  }

  class SignUp extends JPanel {
    private JLabel errorLabel;
    private JTextField usernameField;
    private final AppData appData;

    public SignUp(AppData appData) {
      this.appData = appData;

      this.setLayout(new GridBagLayout());
      this.add(new JLabel("Welcome to the app!:"), setConstraints(0, 0, 2, 1));
      this.add(new JLabel("Username:"), setConstraints(0, 1, 1, 1));

      usernameField = new JTextField(20);
      this.add(usernameField, setConstraints(1, 1, 1, 1));

      JButton logInButton = new JButton("Sign Up");
      logInButton.addActionListener(e -> signUpButton());
      this.add(logInButton, setConstraints(0, 2, 2, 1));

      errorLabel = new JLabel("");
      this.add(errorLabel, setConstraints(0, 3, 2, 1));
    }

    private void signUpButton() {
      errorLabel.setText("");
      usernameField.setText(usernameField.getText().strip());

      if (usernameField.getText().isEmpty()) {
        errorLabel.setText("Username cannot be empty!");
      } else if (this.appData
          .getUsers()
          .anyMatch(username -> username.getUsername().equals(usernameField.getText()))) {
        errorLabel.setText("Username already exists!");
      } else {
        System.out.println("user added!");
        this.appData.addUser(usernameField.getText());
        System.out.println("logged in as " + this.appData.getCurrentUser().getUsername());
      }
    }
  }

  class Login extends JPanel {
    private JLabel errorLabel;
    private JTextField usernameField;
    private final AppData appData;

    public Login(AppData appData) {
      this.appData = appData;
      this.setLayout(new GridBagLayout());
      this.add(new JLabel("Welcome back!:"), setConstraints(0, 0, 2, 1));
      this.add(new JLabel("Username:"), setConstraints(0, 1, 1, 1));

      usernameField = new JTextField(20);
      this.add(usernameField, setConstraints(1, 1, 1, 1));

      JButton logInButton = new JButton("Log In");
      logInButton.addActionListener(e -> loginButton());
      this.add(logInButton, setConstraints(0, 2, 2, 1));

      errorLabel = new JLabel("");
      this.add(errorLabel, setConstraints(0, 3, 2, 1));
    }

    public void loginButton() {
      errorLabel.setText("");
      usernameField.setText(usernameField.getText().strip());
      if (usernameField.getText().isEmpty()) {
        errorLabel.setText("Username cannot be empty!");
        return;
      }
      if (this.appData.setCurrentUser(usernameField.getText())) {
        System.out.println("logged in as " + this.appData.getCurrentUser().getUsername());
        SwingUtilities.invokeLater(() -> GUI.switchContent(new Scoreboard(appData)));
      } else {
        errorLabel.setText("Username already exists!");
      }
    }
  }

  private GridBagConstraints setConstraints(int x, int y, int width, int height) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.gridwidth = width;
    gbc.gridheight = height;
    return gbc;
  }
}

class Scoreboard extends JPanel {
  private final AppData appData;
  JTable table;

  public Scoreboard(AppData appData) {
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

    JScrollPane scrollPane = new JScrollPane(table);
    this.add(scrollPane);
  }
}
