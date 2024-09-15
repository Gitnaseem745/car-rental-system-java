# Car Rental System

A **Terminal-Based Car Rental System** implemented in Java. This project was developed to test and demonstrate Java programming skills, particularly focusing on object-oriented programming concepts and the use of Java Streams API.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Compilation and Execution](#compilation-and-execution)
- [Usage](#usage)
- [Classes and Methods](#classes-and-methods)
  - [Car Class](#1-car-class)
  - [Customer Class](#2-customer-class)
  - [Rental Class](#3-rental-class)
  - [CarRentalSystem Class](#4-carrentalsystem-class)
  - [Main Class](#5-main-class)
- [Future Improvements](#future-improvements)
- [Author](#author)

## Introduction

This project is a simple command-line application that simulates a car rental system. It allows users to rent and return cars, displaying available cars and calculating rental costs based on the number of days. The application uses Java collections, classes, and methods to manage cars, customers, and rentals.

## Features

- **Rent a Car**: Users can view available cars and rent one by providing their name, selecting a car ID, and specifying the rental duration.
- **Return a Car**: Users can return a rented car by providing the car ID.
- **Car Management**: The system maintains a list of cars with unique IDs, brands, models, and daily rental costs.
- **Customer Management**: Each rental is associated with a customer who is assigned a unique customer ID.
- **Rental Management**: Keeps track of active rentals, including the car, customer, and rental duration.
- **Data Structures**: Utilizes Java `ArrayList` and `Optional` for efficient data handling.
- **Java Streams API**: Implements streams for filtering and finding cars and rentals.

## Technologies Used

- **Java**: Programming language used to develop the application.
- **Java Streams API**: For efficient data processing.
- **Java Collections Framework**: Using `ArrayList` to store data.

## Project Structure

- **Main Class**: Contains the `main` method to run the application.
- **Car Class**: Represents a car with attributes like ID, brand, model, daily rent cost, and availability status.
- **Customer Class**: Represents a customer with a unique ID and name.
- **Rental Class**: Represents a rental transaction, linking a car and a customer with the rental duration.
- **CarRentalSystem Class**: Contains the core functionalities of the system, including menus and operations for renting and returning cars.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
- **Command-Line Interface**: Terminal or command prompt access.

### Compilation and Execution

1. **Clone the Repository**: (Assuming you have the project hosted on GitHub)

   ```bash
   git clone https://github.com/Gitnaseem745/car-rental-system-java.git
   cd car-rental-system-java
   ```

2. **Compile the Java Files**:

   ```bash
   javac Main.java
   ```

3. **Run the Application**:

   ```bash
   java Main
   ```

## Usage

1. **Launch the Application**: Run the `Main` class.

2. **Main Menu**:

   ```
   ===== Car Rental System =====
   1. Rent a Car
   2. Return a Car
   3. Exit
   Enter your choice:
   ```

3. **Renting a Car**:

   - Select option `1` to rent a car.
   - Enter your name when prompted.
   - View the list of available cars.
   - Enter the car ID you wish to rent.
   - Specify the number of days for rental.
   - Confirm the rental by entering `Y`.

4. **Returning a Car**:

   - Select option `2` to return a car.
   - Enter the car ID you wish to return.
   - Confirmation of the return will be displayed.

5. **Exiting**:

   - Select option `3` to exit the application.

## Classes and Methods

### 1. `Car` Class

Represents a car in the rental system.

- **Attributes**:
  - `carId`: Unique identifier.
  - `brand`: Car brand.
  - `model`: Car model.
  - `dailyRentCost`: Rental cost per day.
  - `isAvailable`: Availability status.

- **Methods**:
  - `getCarId()`: Returns the car ID.
  - `getBrand()`: Returns the car brand.
  - `getModel()`: Returns the car model.
  - `getPrice()`: Returns the daily rental cost.
  - `isAvailable()`: Checks if the car is available.
  - `getTotalPrice(int days)`: Calculates total price for rental.
  - `rent()`: Marks the car as rented.
  - `returnCar()`: Marks the car as available.

### 2. `Customer` Class

Represents a customer renting a car.

- **Attributes**:
  - `customerId`: Unique identifier.
  - `name`: Customer's name.

- **Methods**:
  - `getCustomerId()`: Returns the customer ID.
  - `getCustomerName()`: Returns the customer's name.

### 3. `Rental` Class

Represents a rental transaction.

- **Attributes**:
  - `car`: The rented car.
  - `customer`: The customer who rented the car.
  - `rentalDays`: Number of days the car is rented.

- **Methods**:
  - `getCar()`: Returns the rented car.
  - `getCustomer()`: Returns the customer.
  - `getRentalDays()`: Returns the rental duration.

### 4. `CarRentalSystem` Class

Manages the rental system operations.

- **Attributes**:
  - `cars`: List of all cars.
  - `customers`: List of all customers.
  - `rentals`: List of all active rentals.

- **Methods**:
  - `addCar(Car car)`: Adds a car to the system.
  - `addCustomer(Customer customer)`: Adds a customer.
  - `rentCar(Car car, Customer customer, int rentalDays)`: Processes car rental.
  - `returnCar(Car car)`: Processes car return.
  - `menu()`: Displays the main menu and handles user input.
  - `displayMenu()`: Displays menu options.
  - `rentCarMenu(Scanner scan)`: Handles the car renting process.
  - `returnCarMenu(Scanner scan)`: Handles the car returning process.

### 5. `Main` Class

- **Purpose**: Contains the `main` method to run the application.
- **Responsibilities**:
  - Initializes the `CarRentalSystem`.
  - Adds a predefined list of cars to the system.
  - Starts the menu loop.

- **Code Snippet**:

  ```java
  public class Main {
      public static void main(String[] args) {
          CarRentalSystem rentalSystem = new CarRentalSystem();
          Car[] cars = {
              new Car("C001", "Bentley", "Flying Spur", 350.0),
              new Car("C002", "Rolls-Royce", "Phantom", 500.0),
              new Car("C003", "Lamborghini", "Aventador", 450.0),
              // ... (other cars)
              new Car("C038", "Kia", "Sorento", 65.0)
          };

          for (Car car : cars) {
              rentalSystem.addCar(car);
          }
          rentalSystem.menu();
      }
  }
  ```


**Disclaimer**: This project was developed as a learning exercise and may not cover all real-world scenarios in a car rental system. Use it as a foundation and customize it according to your needs.

**Feel free to contribute, suggest improvements, or use this project as a reference for learning Java and object-oriented programming concepts.**

---

*This README was generated to provide a comprehensive overview of the Car Rental System project, detailing its features, structure, and usage instructions in a format suitable for GitHub.*
