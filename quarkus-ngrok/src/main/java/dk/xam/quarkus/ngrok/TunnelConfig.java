package dk.xam.quarkus.ngrok;

import java.util.HashMap;
import java.util.Map;

public class TunnelConfig {
    private String addr;
    private boolean inspect;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Boolean getInspect() {
        return inspect;
    }

    public void setInspect(Boolean inspect) {
        this.inspect = inspect;
    }

    }
