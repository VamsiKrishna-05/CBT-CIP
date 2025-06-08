import java.io.*;
import java.util.*;

public class Library {
    private Map<String, Book> catalog = new HashMap<>();
    private final String FILE_NAME = "books.txt";

    public Library() {
        loadFromFile();
    }

    public void addBook(Book book) {
        if (catalog.containsKey(book.getIsbn())) {
            System.out.println("Book with this ISBN already exists!");
        } else {
            catalog.put(book.getIsbn(), book);
            System.out.println("Book added successfully!");
            saveToFile();
        }
    }

    public void listBooks() {
        if (catalog.isEmpty()) {
            System.out.println("No books in the catalog.");
        } else {
            catalog.values().forEach(book -> System.out.println(book.display()));
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book book : catalog.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book.display());
                found = true;
            }
        }
        if (!found)
            System.out.println("No books found with title: " + title);
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book book : catalog.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book.display());
                found = true;
            }
        }
        if (!found)
            System.out.println("No books found by author: " + author);
    }

    public void sortByTitle() {
        List<Book> sortedList = new ArrayList<>(catalog.values());
        sortedList.sort(Comparator.comparing(Book::getTitle));
        sortedList.forEach(book -> System.out.println(book.display()));
    }

    public void sortByAuthor() {
        List<Book> sortedList = new ArrayList<>(catalog.values());
        sortedList.sort(Comparator.comparing(Book::getAuthor));
        sortedList.forEach(book -> System.out.println(book.display()));
    }

    public void updateBook(String isbn, String newTitle, String newAuthor) {
        if (catalog.containsKey(isbn)) {
            Book updatedBook = new Book(newTitle, newAuthor, isbn);
            catalog.put(isbn, updatedBook);
            saveToFile();
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    public void deleteBook(String isbn) {
        if (catalog.containsKey(isbn)) {
            catalog.remove(isbn);
            saveToFile();
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : catalog.values()) {
                bw.write(book.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    Book book = new Book(parts[0], parts[1], parts[2]);
                    catalog.put(book.getIsbn(), book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file.");
        }
    }
}