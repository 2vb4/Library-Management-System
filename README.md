# Library-Management-System

1. Project Overview
 The objective is to build a Library Management System in Java that manages books, members, and borrowing activities. The system must support adding and searching for items, registering members, issuing/ returning items, input validation using Regular Expressions (RegEx), and storing/retrieving data from text files.

2. Core OOP Requirements & Conceptual Mapping

   1. Classes and Objects:
      • Book, Magazine, Member, Library, and Borrowing.
      
   2. Constructors:
      • Initialize Book objects with title, author, ISBN, and available copies.
      • Initialize Member and Borrowing records with required details.
      
   3. Encapsulation (Private Attributes + Setters/Getters):
      •  Keep all attributes private.
      •  Enforce validation in setters:
          Number of book copies must be >= 0
          Member age must be >= 12
   4. Static Members:
      •  Track the total number of books currently borrowed across the application using a static variable (e.g., totalBorrowedBooks) and a static method.
      
   5. Arrays of Objects / Internal Arrays:
      •  Library contains arrays for storing Book[] and Member[].
      •  Store and manage multiple books, members, and borrowing transactions.

   6. Aggregation and Composition:
      • Aggregation and Composition:
      •  Composition: Borrowing maintains direct references to a specific Book and Member.
      
   7.  Searching Logic:
      •  Search books by ISBN or title.
      •  Search members by ID or name.

   8. Inheritance & Abstraction:
      •  Create an abstract base class LibraryItem with shared attributes/methods.
      •  Extend LibraryItem into specialized subclasses such as Book and Magazine.
      
   9. Polymorphism:
      •  Override methods in derived subclasses (Book, Magazine) to display custom item details or handle unique borrowing rules.

   10. Interfaces:
       •  Define contracts like Borrowable or Searchable and implement them in relevant classes.

   11. Regular Expressions (RegEx):
       •  Validate input strings:
           - ISBN Format
           - Email Address
           - Member ID
       
   12. File Handling:
       • Save system data (books, members, borrowing records) to .txt files when exiting.
       • Read and reload data from .txt files when the program starts.
       
   13. Exception / Error Handling:
       •  Gracefully handle invalid inputs, unavailable copies, nonexistent records, full arrays, and file reading/writing errors using try-catch blocks.



   3. Step-by-Step Scenario Workflow
      1:Instantiate the Library.
      2:Instantiate the Library. Add several Book and Magazine objects with titles, authors, ISBNs, and copy counts.
      3:Register multiple Member objects with validated emails, IDs, and ages.
      4:Allow a member to borrow an item (reduces available copies and increments the static borrowed counter).
      5:Search for books/magazines by title or ISBN.
      6:Return a borrowed item (increases available copies).
      7: Display summaries of available items, borrowed items, and members.
      8:Save all data to text files upon exit and verify data loads when

restarting the system





