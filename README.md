# 💼 CBT-CIP Internship Projects

Welcome to the repository for **CBT-CIP** (CipherByte Technologies - Coding Internship Program), featuring two complete Java-based projects with both CLI and GUI interfaces:

- 📚 **Library Catalogue System**
- 🏦 **BankY – Java Banking System**

Both projects demonstrate Java development with clean object-oriented design, user-friendly GUIs (Swing), and MySQL integration (BankY).

---

## 📚 Library Catalogue System

A Java application to manage a digital catalog of books. It includes CLI and GUI interfaces, built with Java Swing, for performing CRUD operations on a book collection.

### ✨ Features

- ✅ Add new books to the catalog  
- 🔍 Search books by **Title** or **Author**  
- 📋 List all books  
- ✏️ Update book details by ISBN  
- ❌ Delete books from the catalog  
- 🖥️ Intuitive GUI using Java Swing  
- ⚡ Fast access using `HashMap` for ISBN lookup

### 📁 Folder Structure

LibraryCatalogSystem/
│
├── Book.java # Book model class
├── Library.java # Core logic (add, search, update, delete)
├── LibraryApp.java # CLI version
├── LibraryGUIApp.java # GUI using Swing
├── books.txt # Sample dataset
└── background1.png # Background image for GUI


### 🚀 How to Run

```bash
cd LibraryCatalogSystem
javac *.java
java LibraryGUIApp
```
📸 Screenshot
![Screenshot 2025-06-09 172222](https://github.com/user-attachments/assets/da32842c-4894-4772-b1cf-add5dc889be5)



🏦 BankY – Java Banking System
BankY is a professional-grade Java banking simulation system with persistent database integration. Built using Java Swing, MySQL, and JDBC.

✨ Features
➕ Create account with name, PIN, and deposit

💰 Deposit / Withdraw money

🔁 Transfer funds between accounts

💳 PIN-based protection

📊 Check account balance

📋 List all accounts

💾 All data stored and retrieved from MySQL

⚙️ Tech Stack
Java (JDK 17+)

Swing (with Nimbus Look & Feel)

JDBC (MySQL Database)

SQL (For creating and managing tables)

📁 Folder Structure
BankY/
│
├── Main.java             # CLI-based simulation
├── BankAccount.java      # Account data model
├── BankService.java      # DB logic (CRUD)
├── DBConnection.java     # MySQL connection
├── BankYGUI.java         # GUI with Swing and Nimbus theme
├── background2.png       # Professional GUI background
└── database.sql          # MySQL script to create tables
```
cd BankY
javac *.java
java BankYGUI

✅ Include mysql-connector-java.jar in your classpath when running.
```
📸 Screenshot

![Screenshot 2025-06-10 145719](https://github.com/user-attachments/assets/d487487f-cac7-43a7-baf5-f94c73fbb678)

🧑‍💻 Developed By
Vamsi Krishna B
🎓 3rd Year ECE Student, VIT Chennai
👨‍💻 Java Intern at CipherByte Technologies
📅 Internship Project - June 2025

CBT-CIP/
│
├── LibraryCatalogSystem/   # Book management system
├── BankY/                  # Banking system with MySQL
├── README.md               # This file
└── Other resources...

⭐ If you like the project, don't forget to star the repo!

---

### ✅ Next Steps

1. Save this content as `README.md` in your main repository folder.
2. Commit and push to GitHub:

```bash
git add README.md
git commit -m "Consolidated README for Library and BankY projects"
git push origin main
```
