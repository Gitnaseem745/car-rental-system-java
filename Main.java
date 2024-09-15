import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double dailyRentCost;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double dailyRentCost){
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.dailyRentCost = dailyRentCost;
        this.isAvailable = true;
    }

    public String getCarId(){
        return carId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public double getPrice(){
        return dailyRentCost;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public double getTotalPrice(int days){
        return dailyRentCost*days;
    }
    public void rent(){
        isAvailable = false;
    }
    public void returnCar(){
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;
    public Customer(String id, String name){
        this.customerId = id;
        this.name = name;
    }

    public String getCustomerId(){
        return customerId;
    }
    public String getCustomerName(){
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int rentalDays;
    public Rental(Car car, Customer customer, int rentalDays){
        this.car = car;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public Car getCar(){
        return car;
    }
    public Customer getCustomer(){
        return customer;
    }
    public int getRentalDays(){
        return rentalDays;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    public CarRentalSystem(){
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int rentalDays){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, rentalDays));
        }else{
            System.out.println("Car is not available for rent.");
        }
    }
    public void returnCar(Car car){
        car.returnCar();
        Rental removeRental = null;
        for(Rental rental : rentals){
            if(rental.getCar() == car){
                removeRental = rental;
                break;
            }
        }
        if(removeRental != null){
            rentals.remove(removeRental);
        }else{
            System.out.println("Car wasn't rented.");
        }
    }
    public void menu(){
        Scanner scan = new Scanner(System.in);
        int choice;
        do{
            displayMenu();
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    rentCarMenu(scan);
                    break;
                case 2:
                    returnCarMenu(scan);
                    break;
                case 3:
                    System.out.println("\n Thank you for using the Car Rental System!");
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }  while(choice != 3);
    }
    public void displayMenu(){
        System.out.println("===== Car Rental System =====");
        System.out.println("1. Rent a Car");
        System.out.println("2. Return a Car");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    public void rentCarMenu(Scanner scan){
        System.out.println("\n== Rent a Car ==\n");
        System.out.println("Enter your name: ");
        String customerName = scan.nextLine();
        System.out.println("\n Available Cars: ");
        cars.stream()
        .filter(Car::isAvailable)
        .forEach(car -> System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel()));
        System.out.println("\n Enter the car ID you want to rent: ");
        String carId = scan.nextLine();

        System.out.println("Enter the number of days for rental: ");
        int rentalDays = scan.nextInt();
        scan.nextLine();

        Customer newCustomer = new Customer("Cus" +(customers.size()+1), customerName);
        addCustomer(newCustomer);

        Optional<Car> selectedCar = cars.stream()
        .filter(car -> car.getCarId().equals(carId) && car.isAvailable())
        .findFirst();

        if(selectedCar.isPresent()){
            Car car = selectedCar.get();
            double totalPrice = car.getTotalPrice(rentalDays);
            System.out.println("\n== Rental Information ==\n");
            System.out.println("Customer ID: " + newCustomer.getCustomerId());
            System.out.println("Customer Name: " + newCustomer.getCustomerName());
            System.out.println("Car: " + car.getBrand() + " " + car.getModel());
            System.out.println("Rental Days: " + rentalDays);
            System.out.printf("Total Price: $%.2f%n", totalPrice);

            System.out.println("\n Confirm rental (Y/N): ");
            String confirm = scan.nextLine();

            if(confirm.equalsIgnoreCase("Y")){
                rentCar(car, newCustomer, rentalDays);
                System.out.println("\nCar rented succesfully.");
            }else {
                System.out.println("\nRental canceled.");
            }
        }else {
            System.out.println("\nInvalid car selection or car not available for rent.");
        }
    }
    public void returnCarMenu(Scanner scan){
        System.out.println("\n== Return a Car ==\n");
        System.out.println("Enter the car ID you want to return: ");
        String carId = scan.nextLine();

        Optional<Car> carToReturn = cars.stream()
        .filter(car -> car.getCarId().equals(carId) && !car.isAvailable())
        .findFirst();

        if(carToReturn.isPresent()){
            Car car = carToReturn.get();
            Optional<Rental> rental = rentals.stream()
            .filter(r -> r.getCar().equals(car))
            .findFirst();
            if(rental.isPresent()){
                Customer customer = rental.get().getCustomer();
                returnCar(car);
                System.out.println("Car returned succesfully  by " + customer.getCustomerName());
            }else{
                System.out.println("Rental Information is missing");
            }
        }else{
            System.out.println("Invalid car ID or car is not rented.");
        }
    }
}

public class Main{
    public static void main(String[] args){
        CarRentalSystem rentalSystem = new CarRentalSystem();
        Car[] cars = {
            new Car("C001", "Bentley", "Flying Spur", 350.0),
            new Car("C002", "Rolls-Royce", "Phantom", 500.0),
            new Car("C003", "Lamborghini", "Aventador", 450.0),
            new Car("C004", "Ford", "Focus", 55.0),
            new Car("C005", "Chevrolet", "Malibu", 65.0),
            new Car("C006", "BMW", "3 Series", 120.0),
            new Car("C007", "Audi", "A4", 130.0),
            new Car("C008", "Mercedes-Benz", "C-Class", 140.0),
            new Car("C009", "Volkswagen", "Passat", 60.0),
            new Car("C010", "Hyundai", "Elantra", 50.0),
            new Car("C011", "Kia", "Optima", 55.0),
            new Car("C012", "Nissan", "Altima", 58.0),
            new Car("C013", "Subaru", "Impreza", 65.0),
            new Car("C014", "Mazda", "Mazda6", 60.0),
            new Car("C015", "Tesla", "Model 3", 150.0),
            new Car("C016", "Volvo", "S60", 110.0),
            new Car("C017", "Jaguar", "XE", 130.0),
            new Car("C018", "Lexus", "IS", 125.0),
            new Car("C019", "Porsche", "911", 200.0),
            new Car("C020", "Ferrari", "488 Spider", 300.0),
            new Car("C021", "Lamborghini", "Huracán", 320.0),
            new Car("C022", "McLaren", "720S", 310.0),
            new Car("C023", "Aston Martin", "DB11", 250.0),
            new Car("C024", "Bentley", "Continental GT", 270.0),
            new Car("C025", "Rolls-Royce", "Ghost", 400.0),
            new Car("C026", "Alfa Romeo", "Giulia", 90.0),
            new Car("C027", "Dodge", "Charger", 80.0),
            new Car("C028", "Jeep", "Wrangler", 85.0),
            new Car("C029", "Mini", "Cooper", 75.0),
            new Car("C030", "Toyota", "Corolla", 55.0),
            new Car("C031", "Honda", "Civic", 60.0),
            new Car("C032", "Ford", "Mustang", 100.0),
            new Car("C033", "Chevrolet", "Camaro", 105.0),
            new Car("C034", "BMW", "5 Series", 130.0),
            new Car("C035", "Audi", "A6", 135.0),
            new Car("C036", "Mercedes-Benz", "E-Class", 140.0),
            new Car("C037", "Hyundai", "Sonata", 60.0),
            new Car("C038", "Kia", "Sorento", 65.0)
        };

        for(Car car : cars){
            rentalSystem.addCar(car);
        }
        rentalSystem.menu();
    }
}
