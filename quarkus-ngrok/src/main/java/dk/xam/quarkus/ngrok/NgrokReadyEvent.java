package dk.xam.quarkus.ngrok;

public class NgrokReadyEvent {

    final public String address;

    public NgrokReadyEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
