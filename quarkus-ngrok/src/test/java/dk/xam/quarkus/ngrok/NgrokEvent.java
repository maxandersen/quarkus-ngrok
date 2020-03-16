package dk.xam.quarkus.ngrok;

public class NgrokEvent {

    final public String payload;

    public NgrokEvent(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

}
