# SKY ATM
The SKY ATM project is a graphical ATM simulation built with JavaFX which works same as the real world ATM. 

## Overview 
The Sky ATM project is a graphical ATM simulation built with JavaFX. It simulates ATM functionalities such as Deposit, withdraw, pin change, checking bank balance and viewing a mini statement, and interacting with a simple ATM system. The project includes multiple screens (scenes) with user interaction elements such as buttons, labels, text fields, and tables.

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies](#technologies)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## installation

Prerequisites
Make sure you have the following software installed:

1. **Java Development Kit (JDK) (version 11 or higher)**  
   Download and install from the [Oracle JDK Download page](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **MySQL Database Server**  
   Install MySQL from the [MySQL Downloads page](https://dev.mysql.com/downloads/installer/).

3. **IDE for Java Development**  
   We recommend using **IntelliJ IDEA** or **Eclipse** for developing and running Java-based projects. You can download IntelliJ IDEA from [here](https://www.jetbrains.com/idea/download/).

4. **Maven** (Optional, if using Maven for project management)  
   Install Maven from the [Maven Download page](https://maven.apache.org/download.cgi).

5. **JavaFX SDK**  
   Since this project uses JavaFX for the UI, download and set up the JavaFX SDK from [Gluon](https://gluonhq.com/products/javafx/).

---

1. **Clone the repository**
   ```sh
   git clone https://github.com/SKY975Yadav/SKY_ATM.git
2. **Set up the database consisits of 3 tables : Accounts, Transactions, Ratings**
   ### For Account :
   ```sh
   CREATE TABLE accounts (
    Name VARCHAR(30) DEFAULT NULL,
    Account_Number BIGINT NOT NULL PRIMARY KEY,
    atm_number BIGINT NOT NULL UNIQUE,
    Pin INT NOT NULL,
    Balance DECIMAL(15, 2) DEFAULT NULL
    );
   ```

   ```sh
   ### For Transactions :
   CREATE TABLE transactions (
    transaction_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    atm_number BIGINT,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    transaction_type VARCHAR(50) DEFAULT NULL,
    amount DECIMAL(10, 2) DEFAULT NULL,
    balance DECIMAL(10, 2) DEFAULT NULL,
    FOREIGN KEY (atm_number) REFERENCES accounts(atm_number)
    );
   ```

   ```sh
   ### For Ratings :
   CREATE TABLE ratings (
    transaction_id BIGINT NOT NULL PRIMARY KEY,
    transaction_type VARCHAR(20) DEFAULT NULL,
    atm_number BIGINT NOT NULL,
    dateandtime DATETIME DEFAULT CURRENT_TIMESTAMP,
    rating TINYINT DEFAULT NULL,
    FOREIGN KEY (atm_number) REFERENCES accounts(atm_number)
    );
   ```
   
3. **Configure the Database Connection** :
    Open the project in your chosen IDE (e.g., IntelliJ IDEA).
    Update the database connection settings in the project to match your MySQL server:
    modify the code in databse connection and service class :
    URL: jdbc:mysql://localhost:3306/atm_project
    Username: root (or your MySQL username)
    Password: your_password
    You can find the database connection code in the project’s DatabaseConfig.java or similar file.

5. **Set Up JavaFX in Your IDE**
    If using IntelliJ IDEA, follow these steps:
    Go to File > Project Structure.
    Under Libraries, add the JavaFX SDK.
    In the Run Configuration, add the VM options to include the JavaFX libraries:
   ```sh
   --module-path "path_to_javafx_sdk/lib" --add-modules javafx.controls,javafx.fxml
   
7. **Run the Application**
    In your IDE, locate the main class (likely Main.java or ATMApplication.java) and run the project.
    The JavaFX ATM interface should launch, and you can test the functionality.

## Usage

Once the project is set up and running, you can interact with the ATM interface through the JavaFX window. Here's how you can use the system:
1. **Login with ATM Number: Enter a valid ATM number** "

   ![image](https://github.com/user-attachments/assets/007505c7-8b03-42a7-ab05-97e2c3f7b1cc)


2. **Select Transaction**

   ![image](https://github.com/user-attachments/assets/181a261b-c4d2-4088-9990-acf11a861640)

   For example : Withdraw

3. **Enter Amount**
   
   ![image](https://github.com/user-attachments/assets/51d19f00-4d2d-4b92-bac2-2764cfdc0e3f)

4. **Enter PIN**
   ![image](https://github.com/user-attachments/assets/e285db4f-42ca-4d1a-b99f-cb08e7ba63cf)

5. **Transaction Output**
   ![image](https://github.com/user-attachments/assets/834e5283-d81b-4a64-ad0f-0df76e6abf7b)

This how you its works

## Features

1. ATM Functionalities : Withdraw, deposit, balance check, pinchange, Mini Statement, Ratings.
2. Transaction History: View a detailed transaction history, including date, type, and amount.
3. MySQL Database Integration: The system uses a MySQL database to store user accounts, transaction records, and ratings.
4. JavaFX User Interface: A visually interactive interface built using JavaFX for a smooth user experience.

## Technologies

The SKY ATM Project leverages the following technologies:

- **Java**: The core programming language used to implement the ATM system. The project is compatible with JDK 11 or higher.
- **JavaFX**: A powerful GUI library used to build the user interface of the ATM system, providing rich graphical components and controls.
- **MySQL**: A relational database management system used to store user data, transaction records, and ratings. MySQL provides a robust, secure solution for handling the backend of the ATM system.
- **Maven** (Optional): A build automation tool that can be used for managing project dependencies and building the project in a streamlined manner. If not using Maven, you can configure the project manually.
- **JDBC**: Java Database Connectivity (JDBC) is used to connect the Java application to the MySQL database and perform operations like data insertion, retrieval, and updates.
- **IntelliJ IDEA** (or any Java IDE): The Integrated Development Environment (IDE) used to develop and run the Java code. IntelliJ IDEA is highly recommended for its support for JavaFX and ease of managing dependencies.
- **Git**: Version control system used to manage the source code and collaborate with others. The project is hosted on GitHub for easy access and collaboration.

## Contributing

We welcome contributions from the community! To contribute to the SKY ATM project, please follow these steps:

1. Fork the repository to your GitHub account.
2. Clone the repository to your local machine.
   ```sh
   git clone https://github.com/your-username/SKY_ATM.git

3. Create a new branch for your changes.
   ```sh
   git checkout -b your-feature-branch

4. Make your changes, commit them, and push them to your forked repository.
   ```sh
   git commit -m "Add feature/fix"
   git push origin your-feature-branch

5. Open a Pull Request to the main branch of the original repository.
6. Ensure that your changes are well-tested and documented.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
If you have any questions or feedback, feel free to contact us:

Project Repository: https://github.com/SKY975Yadav/SKY_ATM
Email: saikrishnagatumida@gmail.com

