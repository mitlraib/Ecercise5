import java.util.Scanner;

public class Shop {
    private Worker[] workers;
    private Customer[] customers;
    private Product[] products;
    private User[] users;
    final int ARRAY_SIZE = 100;
    final int MIN_CHARS_IN_PASSWORD = 6;
    int location = 0;
    Scanner scanner = new Scanner(System.in);

    int rank;
    boolean clubMember;

    public Shop() {
        this.users = new User[0];
        this.products = new Product[0];
        this.workers = new Worker[0];
        this.customers = new Customer[0];

    }

    public void createUser() {
        double discount = 1.0;

        //type of account
        boolean isWorker = false;
        System.out.println("Are you a worker? (true/false/yes/no)");
        String answerWorker = scanner.next();
        if (answerWorker.equals("yes") || answerWorker.equals("true")) {
            isWorker = true;
            boolean invalidRank = false;
            do {
                System.out.println("What is your rank:");
                System.out.println("1 - Regular worker ");
                System.out.println("2 - Manager ");
                System.out.println("3 - TeamManager ");
                rank = scanner.nextInt();
                if (!(rank == 1 || rank == 2 || rank == 3)) {
                    invalidRank = true;
                    System.out.println("Invalid! try again..");
                }
                switch (rank) {
                    case 1:
                        discount = 0.9;
                        break;
                    case 2:
                        discount = 0.8;
                        break;
                    case 3:
                        discount = 0.7;
                        break;
                }
            } while (invalidRank);
        }


        if (answerWorker.equals("false") || answerWorker.equals("no")) {
            isWorker = false;
        }
        System.out.println("Are you a member in our customers club? (true/false/yes/no)");
        String answerClubMember = scanner.next();
        if (answerClubMember.equals("yes") || answerClubMember.equals("true")) {
            clubMember = true;
            discount = 0.9;
        }

        if (answerClubMember.equals("false") || answerClubMember.equals("no")) {
            clubMember = false;
            discount = 1;
        }


        // first name;
        String firstName;
        do {
            System.out.println("Enter your first name: ");
            firstName = scanner.next();
        } while (isValidName(firstName));

        //last name
        String lastName;
        do {
            System.out.println("Enter your last name: ");
            lastName = scanner.next();
        } while (isValidName(lastName));

        //username
        String username;
        do {
            System.out.println("Enter username: ");
            username = scanner.next();
        } while (isUsernameExist(username));

        //password
        String password = null;
        do {
            System.out.println("Enter password: ");
            password = scanner.next();
        } while (!isStrongPassword(password));
        if (isStrongPassword(password)) {
            System.out.println("Great! You are registered :) ");
            System.out.println("Back to main menu... ");
        }

        addUserToArray(isWorker, firstName, lastName, username,
                password, discount, clubMember);
        User user = new User(isWorker, firstName, lastName,
                username, password, discount, clubMember);
    }


    public User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.next();

        System.out.println("Enter your password: ");
        String password = scanner.next();

        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] == null) {
                System.out.println("incorrect username or password");
                break;
            }

            if (this.users[i].getUsername().equals(username)) {
                if (this.users[i].getPassword().equals(password)) {
                    printGreetingMessage(username);

                    return this.users[i];
                }
                return null;
            }
        }
        return null;
    }


    public void printAllUsersThatPurchased() {
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getTotalMoneySpent() > 0) {
                System.out.print(this.users[i].getFirstName());
                System.out.print(" " + this.users[i].getLastName());
                System.out.println(" ");
            }

        }
    }

    public void printMostSpentUser() {
        int max = 0;
        User user = this.users[0];
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getTotalMoneySpent() > max) {
                max = this.users[i].getTotalMoneySpent();
                user = this.users[i];
            }
            System.out.print(this.users[i].getFirstName());
            System.out.print(" " + this.users[i].getLastName());
            System.out.println(" ");
        }

    }

    private boolean isValidName(String name) {
        boolean invalid = true;
        for (int i = 0; i < name.length(); i++) {
            char firstNameChar = name.charAt(i);
            if (Character.isDigit(firstNameChar)) {
                System.out.println("Invalid! name can't be with digits");
                System.out.println("Please try again...");
                invalid = true;
                break;
            } else {
                invalid = false;
            }
        }
        return invalid;
    }

    private void addUserToArray(boolean isWorker, String firstname, String lastname, String username,
                                String password, double discount, boolean clubMember) {
        User[] newArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newArray[i] = this.users[i];
        }
        User userToAdd = new User(isWorker, firstname, lastname,
                username, password, discount, this.clubMember);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;
        if (isWorker) {
            Worker worker = new Worker(username, password, discount);
            Worker[] workers = new Worker[this.workers.length + 1];
            for (int i = 0; i < this.workers.length; i++) {
                workers[i] = this.workers[i];
            }
            workers[workers.length - 1] = worker;
            this.workers = workers;
        }
    }

    public void printGreetingMessage(String username) {
        String message = "";
        int index = 0;
        for (int i = 0; i < this.users.length; i++) {
            if (username.equals(this.users[i].getUsername())) {
                message = message.concat("Hello " + this.users[i].getFirstName() + " " + this.users[i].getLastName());
                index = i;
                break;
            }
        }
        if (this.users[index].getIsWorker()) {
            for (int i = 0; i < this.workers.length; i++) {
                if (this.workers[i].getUsername().equals(username)) {
                    message = message.concat(" " + this.workers[i].getRank() + "!");
                    System.out.println(message);
                    return;
                }

            }
        }
        if (this.users[index].getClubMember()) {
            message = message.concat("{VIP!}");
            System.out.println(message);
            return;
        }
        message = message.concat("!");
        System.out.println(message);

    }

    public void printCustomers() {

        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] != null) {
                System.out.print(this.users[i].getFirstName());
                System.out.print(" " + this.users[i].getLastName());
                System.out.println(" ");
                System.out.println(" ");
            }
        }
    }

    public void printClubCustomers() {
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i] != null) {
                if (this.users[i].getClubMember()) {
                    System.out.print(this.users[i].getFirstName());
                    System.out.print(" " + this.users[i].getLastName());
                    System.out.println(" ");
                    System.out.println(" ");
                }
            }
        }
    }

    public void newProduct() {
        System.out.println("Please enter product name: ");
        String productName = scanner.next();

        System.out.println("Please enter the price of the product: ");
        int productPrice = scanner.nextInt();

        System.out.println("Please enter the discount of the product: ");
        double productDiscount = scanner.nextDouble();

        addProduct(productName, productPrice, productDiscount, true);

    }

    public void changeInStockStatus() {
        printProducts();
        System.out.println(" ");
        for (int i = 0; i < this.products.length; i++) {
            if (this.products[i] != null) {
                System.out.println(this.products[i]);
                System.out.println("Do you want to change this product's status?( true/false/yes/no)");
                String answerChangeStatus = scanner.next();
                if (answerChangeStatus.equals("yes") || answerChangeStatus.equals("true")) {
                    if (this.products[i].isInStock()) {
                        this.products[i].setInStock(false);
                    }

                }
            }
        }
        printProducts();
    }

    public void printProducts() {
        System.out.println("Here are our in stock products: ");
        addProduct("corn", 10, 0, true);
        addProduct("Toilet papers", 40, 10, false);
        addProduct("Six pack mineral water", 12, 0, true);
        addProduct("oil", 15, 10, false);
        addProduct("cola", 10, 10, true);


        for (int i = 0; i < products.length; i++) {
            if (products[i].isInStock()) {
                System.out.println(i + 1 + " " + products[i]);

            }

        }
    }

    public Cart myProducts(Cart cart) {

        int userChoice = -1;
        do {
            userChoice = scanner.nextInt();
            if (userChoice <= 0 || userChoice > this.products.length) {
                System.out.println("Invalid! try again...");
            } else if (!this.products[userChoice - 1].isInStock()) {
                System.out.println("Out of stock!");
            } else {
                System.out.println("enter amount of product:");
                int amount = 1;
                do {
                    amount = scanner.nextInt();
                } while (amount < 1);
                cart.addToCart(this.products[userChoice - 1], amount);
            }
        } while (userChoice != -1);

        return cart;
    }

    public void addProduct(String productName, int price, double discount, boolean inStock) {
        Product[] newProducts = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            newProducts[i] = this.products[i];
        }

        Product productToAdd = new Product(productName, price, discount, inStock);
        newProducts[newProducts.length - 1] = productToAdd;
        this.products = newProducts;
    }

    private boolean isStrongPassword(String passwordToCheck) {
        boolean strong = false;
        boolean validLength = false;
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (passwordToCheck.length() >= MIN_CHARS_IN_PASSWORD) {
            validLength = true;
        }
        for (int i = 0; i < passwordToCheck.length(); i++) {
            char currentChar = passwordToCheck.charAt(i);
            if (Character.isDigit(currentChar)) {
                hasDigit = true;
            } else if (Character.isAlphabetic(currentChar)) {
                hasLetter = true;
            }
            if (hasDigit && hasLetter) {
                break;
            }
        }
        if (validLength && hasLetter && hasDigit) {
            strong = true;
        }
        return strong;
    }

    boolean isUsernameExist(String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if
            (currentUser.getUsername().equals(usernameToCheck)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public Shop(User[] users, Worker[] workers, Customer[]
            customers, int location, Scanner scanner) {
        this.users = users;
        this.workers = workers;
        this.customers = customers;
        this.location = location;
        this.scanner = scanner;
    }
}