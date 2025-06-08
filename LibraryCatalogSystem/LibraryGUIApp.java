import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryGUIApp {
    public static void main(String[] args) {
        Library library = new Library();

        JFrame frame = new JFrame("Library Catalog System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setMinimumSize(new Dimension(400, 300));

        // Panel with background image
        JPanel panel = new JPanel() {
            Image bg = new ImageIcon("background.png").getImage(); // Replace with your image path

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Buttons
        JButton addButton = new JButton("Add Book");
        JButton searchTitleButton = new JButton("Search by Title");
        JButton searchAuthorButton = new JButton("Search by Author");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");
        JButton exitButton = new JButton("Exit");

        // Add buttons to panel
        panel.add(addButton, gbc);
        gbc.gridx++;
        panel.add(searchTitleButton, gbc);
        gbc.gridx++;
        panel.add(searchAuthorButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(updateButton, gbc);
        gbc.gridx++;
        panel.add(deleteButton, gbc);
        gbc.gridx++;
        panel.add(exitButton, gbc);

        // Button actions
        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter title:");
            String author = JOptionPane.showInputDialog("Enter author:");
            String isbn = JOptionPane.showInputDialog("Enter ISBN:");
            library.addBook(new Book(title, author, isbn));
        });

        searchTitleButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter title:");
            library.searchByTitle(title);
        });

        searchAuthorButton.addActionListener(e -> {
            String author = JOptionPane.showInputDialog("Enter author:");
            library.searchByAuthor(author);
        });

        updateButton.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN:");
            String newTitle = JOptionPane.showInputDialog("Enter new title:");
            String newAuthor = JOptionPane.showInputDialog("Enter new author:");
            library.updateBook(isbn, newTitle, newAuthor);
        });

        deleteButton.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN:");
            library.deleteBook(isbn);
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
