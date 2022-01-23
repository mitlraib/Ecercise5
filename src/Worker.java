public class Worker extends Customer {
    public final String MANAGER = "manager";
    public final String MANAGER_TEAM = "managerMemberTeam";
    public final String REGULAR_WORKER = "RegularWorker";
    private double discount;
    private String rank;
    public Worker(String username, String password, double discount)
    {
        super(username, password);
        if (discount == 0.8) {
            this.discount = 0.8;
            this.rank = MANAGER;
        } else if (discount == 0.7) {
            this.discount = 0.7;
            this.rank = MANAGER_TEAM;
        } else {
            this.discount = 0.9;
            this.rank = REGULAR_WORKER;
        }
    }
    public Worker(String username, String password, String rank) {
        super(username, password);
        if (rank.equals(MANAGER)) {
            this.discount = 0.8;
            this.rank = MANAGER;
        } else if (rank.equals(MANAGER_TEAM)) {
            this.discount = 0.7;
            this.rank = MANAGER_TEAM;
        } else {
            this.discount = 0.9;
            this.rank = REGULAR_WORKER;
        }
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public void setRank(String rank) {
        if (rank.equals(MANAGER)) {
            this.discount = 0.8;
            this.rank = MANAGER;
        } else if (rank.equals(MANAGER_TEAM)) {
            this.discount = 0.7;
            this.rank = MANAGER_TEAM;
        } else {
            this.discount = 0.9;
            this.rank = REGULAR_WORKER;
        }
    }

    public String getRank() {
        return rank;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rank='" + rank + '\'' +
                ", discount=" + discount +
                '}';
    }
}