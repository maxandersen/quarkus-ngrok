package dk.xam.quarkus.ngrok;

public class NgrokEvent {

    final public String address;

    public NgrokEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
