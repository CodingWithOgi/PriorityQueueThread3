public class Product implements Comparable<Product>{
    String name;
    String type;
    double price;

    public Product() {
        this.name = "";
        this.type = "";
        this.price = 0.0;
    }

    public Product(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return -Double.compare(o.getPrice(),this.price);
    }

    @Override
    public String toString() {
        return (type+" of type "+name+" costs "+price);
    }
}
