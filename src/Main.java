import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int ARRAY_SIZE = 100;
        User [] users = new User[ARRAY_SIZE];
        Product [] products = new Product[ARRAY_SIZE];
        Customer [] customers = new Customer[ARRAY_SIZE];


        final int CREATE_NEW_ACCOUNT = 1;
        final int LOG_IN = 2;
        final int EXIT = 3;
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop();
        boolean loggedIn = false;
        boolean isWorker = false;

        Cart cart = new Cart(users[0]);

        while (true) {
            System.out.println("Please select one of the following options:");
            System.out.println("1 - Create new account ");
            System.out.println("2 - Log in ");
            System.out.println("3 - Exit ");
            int cases = scanner.nextInt();
            User user;
            switch (cases) {
                case CREATE_NEW_ACCOUNT:
                    System.out.println("You chose to creat a new account");
                    shop.createUser();
                    String password = new Scanner(System.in).nextLine();
                    for (int i = 0; i < password.length(); i++) {
                        char currentChar = password.charAt(i);

                    }
                    break;
                case LOG_IN:

                    user =  shop.login ();
                    cart = new Cart(user);

                    if (user == null){
                        System.out.println("incorrect username or password");
                        break;
                    }
                    int workerOptions=0;


                    if (user.getIsWorker()){
                        System.out.println("Please select one of the following options:");
                        System.out.println("1 - Print all customers details ");
                        System.out.println("2 - Print club customers details ");
                        System.out.println("3 - Print customers who purchase details ");
                        System.out.println("4 - Print the details of the customer who has spent the most money here  ");
                        System.out.println("5 - Add a new product ");
                        System.out.println("6 - Change in stock status");
                        System.out.println("7 - Purchase ");
                        System.out.println("8 - Exit");


                        workerOptions= scanner.nextInt();

                        switch (workerOptions) {
                            case 1:
                                shop.printCustomers();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 2:
                                shop.printClubCustomers();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 3:
                                shop.printAllUsersThatPurchased();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 4:
                                shop.printMostSpentUser();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 5:
                                shop.newProduct();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 6:
                                shop.changeInStockStatus();
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                            case 8:
                                System.out.println("Good bye");
                                System.out.println(" ");
                                break;
                        }
                        if (workerOptions<=0||workerOptions>8){
                            System.out.println("Invalid! Good bye...");
                        }


                    }

                    if (!user.getIsWorker()||workerOptions ==7) {
                        shop.printProducts();
                        System.out.println("please choose the number of the product you want to add to your cart" +
                                "or chose -1 to finish the perches");
                        shop.myProducts(cart);
                        System.out.println(" ");
                        System.out.println("Here is your cart: ");
                        cart.printCart();
                    }
            }
            if (cases == 3) {
                break;
            }

        }
    }
}