public class Person {
    private String name;
    private String address;

    //Contructor
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Setter
    public void setAddress(String address) {
        this.address = address;
    }

    //toString
    public String toString() {
        return name +  " (" + address + ")";
    }
}