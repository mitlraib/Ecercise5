import java.util.Arrays;

public class Cart {
    private User user;
    private Product[] products;
    private int totalSum;

    public Cart(User user) {

        this.user = user;
        this.products = new Product[0];
        this.totalSum = 0;
    }

    public Cart(User user, Product[] products) {
        this.products = products;
        this.user = user;
    }

    public void printCart() {
        for (int i = 0; i < this.products.length; i++) {
            System.out.println(products[i]);
        }
        System.out.println(" ");
        System.out.println("The total amount of your cart is: " + this.totalSum);
    }

    public void addToCart(Product product, int amount) {
        Product[] newProducts = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            newProducts[i] = this.products[i];
        }
        newProducts[newProducts.length - 1] = product;
        this.products = newProducts;
        this.totalSum += product.getPrice() * this.user.getDiscount() * amount;
        this.user.addToTotalMoneySpent(totalSum);


    }

    public Product[] getProducts() {
        return products;
    }
    public User getUser() {
        return user;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + this.user +
                ", products=" + Arrays.toString(products) +
                "Total price= " + this.totalSum +
                '}';
    }
}