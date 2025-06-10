import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n===== Library Catalog Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Author");
            System.out.println("4. List All Books");
            System.out.println("5. Sort Books by Title");
            System.out.println("6. Sort Books by Author");
            System.out.println("7. Update Book by ISBN");
            System.out.println("8. Delete Book by ISBN");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter Title to Search: ");
                    library.searchByTitle(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter Author to Search: ");
                    library.searchByAuthor(scanner.nextLine());
                    break;
                case 4:
                    library.listBooks();
                    break;
                case 5:
                    library.sortByTitle();
                    break;
                case 6:
                    library.sortByAuthor();
                    break;
                case 7:
                    System.out.print("Enter ISBN of the book to update: ");
                    String isbnToUpdate = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    library.updateBook(isbnToUpdate, newTitle, newAuthor);
                    break;
                case 8:
                    System.out.print("Enter ISBN of the book to delete: ");
                    String isbnToDelete = scanner.nextLine();
                    library.deleteBook(isbnToDelete);
                    break;
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}