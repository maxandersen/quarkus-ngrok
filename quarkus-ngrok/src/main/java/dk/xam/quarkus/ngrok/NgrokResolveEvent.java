package dk.xam.quarkus.ngrok;

public class NgrokResolveEvent {

    final public String address;

    public NgrokResolveEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
