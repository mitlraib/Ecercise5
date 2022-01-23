import java.util.Objects;
public class Customer{
    private String username;
    private String password;
    private boolean inClub;
    public Customer(String username, String password){
        this.username = username;
        this.password = password;
        this.inClub = false;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setInClub(boolean inClub) {
        this.inClub = inClub;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public boolean equals2(Customer other){
        if(this.getUsername() == other.username){
            if(this.getPassword() == other.getPassword()){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return inClub == customer.inClub && Objects.equals(username,
                customer.username) && Objects.equals(password, customer.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(username, password, inClub);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", inClub=" + inClub +
                '}';
    }
}
