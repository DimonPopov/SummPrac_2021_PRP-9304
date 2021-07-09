package sample.Prim;

import javafx.beans.property.SimpleStringProperty;



public class layotVertex {
    private SimpleStringProperty name;
    private SimpleStringProperty friend;
    private SimpleStringProperty price;

    public layotVertex(String a, String b, String c){
        this.name = new SimpleStringProperty(a);
        this.friend = new SimpleStringProperty(b);
        this.price = new SimpleStringProperty(c);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFriend() {
        return friend.get();
    }

    public SimpleStringProperty friendProperty() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend.set(friend);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
