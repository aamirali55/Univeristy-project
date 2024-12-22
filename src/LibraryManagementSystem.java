import java.util.Scanner;

class Book {
    int ID;
    static int idCounter = 1;
    String Title;
    String Author;
    String ISBN;
    double Price;
    int Quantity;
    double RatingPoints;

    public Book(int id, String title, String author, String isbn, double price, int quantity, double ratingPoints) {
        this.ID = id;
        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Price = price;
        this.Quantity = quantity;
        this.RatingPoints = ratingPoints;
    }

    public void displayBookDetails() {
        System.out.println("Book ID: " + ID);
        System.out.println("Title: " + Title);
        System.out.println("Author: " + Author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Price: Rs. " + Price);
        System.out.println("Quantity: " + Quantity);
        System.out.println("Rating Points: " + RatingPoints);
    }
}

class Node {
    Book data;
    Node next;

    public Node(Book book) {
        this.data = book;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void addBook(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Book searchBookByIsbn(String ISBN) {
        Node current = head;
        while (current != null) {
            if (current.data.ISBN.equals(ISBN)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void deleteBook(String Title) {
        Node current = head;
        Node previous = null;

        while (current != null && !current.data.Title.equals(Title)) {
            previous = current;
            current = current.next;
        }

        if (current != null) {
            if (previous != null) {
                previous.next = current.next;
            } else {
                head = current.next;
            }
            System.out.println("Book '" + Title + "' deleted.");
        } else {
            System.out.println("Book '" + Title + "' not found.");
        }
    }

    public void sortBooks() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            Node current = head;
            Node previous = null;
            swapped = false;

            while (current != null && current.next != null) {
                Node nextNode = current.next;

                if (current.data.RatingPoints < nextNode.data.RatingPoints
                        || (current.data.RatingPoints == nextNode.data.RatingPoints
                        && current.data.ID > nextNode.data.ID)) {
                    if (previous == null) {
                        head = nextNode;
                    } else {
                        previous.next = nextNode;
                    }
                    current.next = nextNode.next;
                    nextNode.next = current;
                    swapped = true;
                }

                previous = current;
                current = current.next;
            }
        } while (swapped);
    }

    public void displayBooks() {
        Node current = head;
        while (current != null) {
            current.data.displayBookDetails();
            System.out.println("---------------");
            current = current.next;
        }
    }

    public Book searchBook(String Title) {
        Node current = head;
        while (current != null) {
            if (current.data.Title.equals(Title)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Book searchBookById(int ID) {
        Node current = head;
        while (current != null) {
            if (current.data.ID == ID) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
}

class Heap {
    private Book[] heapArr;
    private int maxSize;
    private int currentSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArr = new Book[maxSize];
    }

    public void addBook(Book book) {
        if (currentSize == maxSize) {
            System.out.println("Heap is full. Cannot add more books.");
            return;
        }

        heapArr[currentSize] = book;
        trickleUp(currentSize++);
    }

    private void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Book bottom = heapArr[index];

        while (index > 0 && heapArr[parent].RatingPoints < bottom.RatingPoints) {
            heapArr[index] = heapArr[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArr[index] = bottom;
    }

    public void displayHeap() {
        System.out.println("Books in the Heap:");
        for (int i = 0; i < currentSize; i++) {
            heapArr[i].displayBookDetails();
            System.out.println("---------------");
        }
    }

    public Book searchBook(String Title) {
        for (int i = 0; i < currentSize; i++) {
            if (heapArr[i].Title.equals(Title)) {
                return heapArr[i];
            }
        }
        return null;
    }
}

class BST {
    static class TreeNode {
        Book data;
        TreeNode left, right;

        public TreeNode(Book book) {
            this.data = book;
            this.left = this.right = null;
        }
    }

    TreeNode root;

    public void insert(Book book) {
        root = insertRecursive(root, book);
    }

    private TreeNode insertRecursive(TreeNode root, Book book) {
        if (root == null) {
            return new TreeNode(book);
        }

        if (book.RatingPoints < root.data.RatingPoints
                || (book.RatingPoints == root.data.RatingPoints && book.ID < root.data.ID)) {
            root.left = insertRecursive(root.left, book);
        } else {
            root.right = insertRecursive(root.right, book);
        }

        return root;
    }

    public Book search(String Title) {
        return searchRecursive(root, Title);
    }

    private Book searchRecursive(TreeNode root, String Title) {
        if (root == null || root.data.Title.equals(Title)) {
            return (root != null) ? root.data : null;
        }

        if (Title.compareTo(root.data.Title) < 0) {
            return searchRecursive(root.left, Title);
        } else {
            return searchRecursive(root.right, Title);
        }
    }
}

public class LibraryManagementSystem {
    Scanner sc = new Scanner(System.in);
    LinkedList BooksList = new LinkedList();
    Heap bookHeap = new Heap(50);
    BST bookBST = new BST();

    public void start() {
        System.out.println("Welcome to Library Management System!");

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Sort Books");
            System.out.println("4. Display Books");
            System.out.println("5. Search Book (LinkedList)");
            System.out.println("6. Search Book (Heap)");
            System.out.println("7. Search Book (BST)");
            System.out.println("8. Display Heap");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println("------------------------------");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> sortBooks();
                case 4 -> displayBooks();
                case 5 -> searchBookInLinkedList();
                case 6 -> searchBookInHeap();
                case 7 -> searchBookInBST();
                case 8 -> displayHeap();
                case 9 -> System.out.println("Exiting Library Management System. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private void addBook() {
        System.out.println("Enter book details:");

        System.out.print("ID: ");
        int ID = sc.nextInt();

        while (BooksList.searchBookById(ID) != null) {
            System.out.println("A book with the same ID already exists. Please enter a unique ID.");
            System.out.print("ID: ");
            ID = sc.nextInt();
        }

        sc.nextLine();
        System.out.print("Title: ");
        String Title = sc.nextLine();

        while (BooksList.searchBook(Title) != null) {
            System.out.println("A book with the same title already exists. Please enter a unique title.");
            System.out.print("Title: ");
            Title = sc.nextLine();
        }

        System.out.print("Author: ");
        String Author = sc.nextLine();

        boolean uniqueISBN;
        String ISBN;
        do {
            uniqueISBN = true;
            System.out.print("ISBN: ");
            ISBN = sc.nextLine();

            if (BooksList.searchBookByIsbn(ISBN) != null) {
                uniqueISBN = false;
                System.out.println("A book with the same ISBN already exists. Please enter a unique ISBN.");
            }
        } while (!uniqueISBN);

        System.out.print("Price in Rs: ");
        double Price = sc.nextDouble();
        System.out.print("Quantity: ");
        int Quantity = sc.nextInt();
        System.out.print("Rating Points: ");
        double RatingPoints = sc.nextDouble();

        Book newBook = new Book(ID, Title, Author, ISBN, Price, Quantity, RatingPoints);
        BooksList.addBook(newBook);
        bookHeap.addBook(newBook);
        bookBST.insert(newBook);

        System.out.println("Book added successfully.");
        System.out.println("------------------------------");
    }

    private void deleteBook() {
        System.out.print("Enter the title of the book to delete: ");
        String Title = sc.nextLine();
        BooksList.deleteBook(Title);
    }

    private void sortBooks() {
        BooksList.sortBooks();
        System.out.println("Books sorted based on RatingPoints.");
    }

    private void displayBooks() {
        BooksList.displayBooks();
    }

    private void searchBookInLinkedList() {
        System.out.print("Enter the title of the book to search: ");
        String Title = sc.nextLine();

        long startTime = System.nanoTime();
        Book BookFound = BooksList.searchBook(Title);
        long endTime = System.nanoTime();

        if (BookFound != null) {
            System.out.println("Book found in LinkedList:");
            BookFound.displayBookDetails();
        } else {
            System.out.println("Book '" + Title + "' not found in LinkedList.");
        }

        long timeElapsed = endTime - startTime;
        System.out.println("Time complexity for searchBookInLinkedList(): O(n), where n is the number of elements in the linked list.");
        System.out.println("Time taken: " + timeElapsed + " nanoseconds");
    }


    private void searchBookInHeap() {
        System.out.print("Enter the title of the book to search: ");
        String Title = sc.nextLine();

        long startTime = System.nanoTime();
        Book BookFound = bookHeap.searchBook(Title);
        long endTime = System.nanoTime();

        if (BookFound != null) {
            System.out.println("Book found in Heap:");
            BookFound.displayBookDetails();
        } else {
            System.out.println("Book '" + Title + "' not found in Heap.");
        }

        long timeElapsed = endTime - startTime;
        System.out.println("Time complexity for searchBookInHeap(): O(n), where n is the number of elements in the heap.");
        System.out.println("Time taken: " + timeElapsed + " nanoseconds");
    }


    private void searchBookInBST() {
        System.out.print("Enter the title of the book to search: ");
        String Title = sc.nextLine();

        long startTime = System.nanoTime();
        Book BookFound = bookBST.search(Title);
        long endTime = System.nanoTime();

        if (BookFound != null) {
            System.out.println("Book found in BST:");
            BookFound.displayBookDetails();
        } else {
            System.out.println("Book '" + Title + "' not found in BST.");
        }

        long timeElapsed = endTime - startTime;
        System.out.println("Time complexity for searchBookInBST(): O(h), where h is the height of the BST (average O(log n), worst O(n)).");
        System.out.println("Time taken: " + timeElapsed + " nanoseconds");
    }


    private void displayHeap() {
        bookHeap.displayHeap();
    }

    public static void main(String[] args) {
        LibraryManagementSystem obj1 = new LibraryManagementSystem();
        obj1.start();
    }
}
