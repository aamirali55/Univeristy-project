# Library Management System

The Library Management System is a Java-based application designed to manage a collection of books using various data structures such as linked lists, heaps, and binary search trees. This project was developed as a part of my Data Structures course to demonstrate the implementation of fundamental data structures and algorithms for efficient book storage, retrieval, and management.

## Features

- **Add Book**: Add a new book to the library collection with unique ID, title, author, ISBN, price, quantity, and rating points.
- **Delete Book**: Remove a book from the library by its title.
- **Sort Books**: Sort the books in the library based on their rating points.
- **Display Books**: Display all books stored in the library.
- **Search Books**:
  - Search for a book by title using a linked list (linear search).
  - Search for a book by title using a max heap (linear search).
  - Search for a book by title using a binary search tree (binary search).

## Components

- **Book Class**:
  - Represents a book entity with attributes such as ID, title, author, ISBN, price, quantity, and rating points.
  
- **LinkedList Class**:
  - Implements a linked list data structure to store and manage books.
  
- **Heap Class**:
  - Implements a max heap data structure to store books based on their rating points.
  
- **BST (Binary Search Tree) Class**:
  - Represents a binary search tree to organize books based on rating points and ID.

- **LibraryManagementSystem Class**:
  - Main class for interacting with the library management system through a menu-driven interface.
  - Allows users to add, delete, sort, display, and search for books using different data structures.

## Installation and Usage

### Prerequisites

- Java Development Kit (JDK) installed on your machine.

### Running the Application

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/M-Ehtesham-Ul-Hassan-Malik/Library-Management-System.git
   ```

2. Navigate to the project directory:

   ```bash
   cd src
   ```

3. Compile the Java files:

   ```bash
   javac LibraryManagementSystem.java
   ```

4. Run the application:

   ```bash
   java LibraryManagementSystem
   ```

5. Follow the on-screen instructions to interact with the Library Management System.

## Contributing

Contributions are welcome! If you would like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
