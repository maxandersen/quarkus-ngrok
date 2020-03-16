package dk.xam.quarkus.ngrok.model;

import java.util.UUID;

public class Options {

    // https://ngrok.com/docs#tunnel-definitions
    String proto = "http";
    String addr;
    String name = UUID.randomUUID().toString();
  //  String inspect;
  //  int port = 80;
  //  String name;


    public String getProto() {
        return proto;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Options{" +
                "proto='" + proto + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}


