import java.util.Objects;
public class Product {
    private String name;
    private int price;
    private boolean inStock;
    private double discount;

    public Product(String name, int price, boolean inStock){
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }
    public Product(String name, int price, double discount,boolean inStock){
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.inStock = inStock;
    }
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    public String getName() {
        return name;
    }
    public boolean isInStock() {
        return inStock;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return price == product.price && inStock == product.inStock
                && Objects.equals(name, product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, price, inStock);
    }
}