package org.group25;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;


public class GUI extends JPanel {
    private JFrame mainFrame;
    private JPanel contentPane;
    private JLabel titleLabel;

    public GUI() {
        setup();
    }

    private void setup() {
        System.out.println("Setting up GUI");
        mainFrame = new JFrame("Cryptogram Game");

        mainFrame.addWindowListener(new WindowAdapter() {
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
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        mainFrame.add(contentPane, BorderLayout.CENTER);

        // Use invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> setContent(new LoginSignUp()));
    }

    protected void setContent(JPanel pane) {
        contentPane.removeAll();
        contentPane.add(pane, BorderLayout.CENTER);
        contentPane.revalidate(); // Revalidate the content pane to reflect changes
        contentPane.repaint(); // Ensure the UI is updated
    }
}

class LoginSignUp extends JPanel {
    private JPanel contentPane;

    private GridBagConstraints setConstraints(int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        return gbc;
    }

    public LoginSignUp() {
        this.setLayout(new BorderLayout());
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.add(contentPane, BorderLayout.CENTER);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Login", new Login());
        tabs.addTab("Sign Up", new SignUp());
        contentPane.add(tabs, BorderLayout.NORTH);
    }

    protected void switchTo(JPanel panel) {
        contentPane.removeAll();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.revalidate(); // Revalidate the content pane to reflect changes
        contentPane.repaint(); // Ensure the UI is updated
    }

    class Choice extends JPanel {
        public Choice() {
            setLayout(new GridLayout(3, 1));

            JButton logInButton = new JButton("Log In");
            logInButton.addActionListener(e -> {
                System.out.println("Logging In");
                SwingUtilities.invokeLater(() -> switchTo(new Login())); // Use invokeLater to safely update UI
            });
            this.add(logInButton);

            JButton signUpButton = new JButton("Sign Up");
            this.add(signUpButton);
        }
    }

    class SignUp extends JPanel {
        public SignUp() {
            this.setLayout(new GridBagLayout());
            this.add(new JLabel("Welcome to the app!:"), setConstraints(0, 0, 2, 1));
            this.add(new JLabel("Username:"), setConstraints(0, 1, 1, 1));

            JTextField usernameField = new JTextField(20);
            this.add(usernameField, setConstraints(1, 1, 1, 1));

            JButton logInButton = new JButton("Sign Up");
            logInButton.addActionListener(e -> {
                System.out.println("Sign Up");
                System.out.println(usernameField.getText());
                App.addUser(usernameField.getText(), "");
            });
            this.add(logInButton, setConstraints(0, 2, 2, 1));
        }
    }

    class Login extends JPanel {
        public Login() {
            this.setLayout(new GridBagLayout());
            this.add(new JLabel("Welcome back!:"), setConstraints(0, 0, 2, 1));
            this.add(new JLabel("Username:"), setConstraints(0, 1, 1, 1));

            JTextField usernameField = new JTextField(20);
            this.add(usernameField, setConstraints(1, 1, 1, 1));

            JButton logInButton = new JButton("Log In");
            logInButton.addActionListener(e -> {
                System.out.println("Login");
                AtomicBoolean found = new AtomicBoolean(false);
                App.getUsers().forEachRemaining(user -> {
                    System.out.println(user.getUsername());
                    if (user.getUsername().equals(usernameField.getText())) {
                        App.setCurrentUser(user);
                        found.set(true);
                    }
                });
                if (!found.get()) {
                    System.out.println("Couldn't find user: " + usernameField.getText());
                }
            });
            this.add(logInButton, setConstraints(0, 2, 2, 1));
        }
    }
}

