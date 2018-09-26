package com.example.mamid.print;

public class Printer {


//    {
//        "id": "2f4b5bca-c967-7728-6af5-1dea2a9eb2d3",
//            "name": "My home printer",
//            "proxy": "ksdjf-7237sf9238-2837jidf",
//            "ownerId": "example@gmail.com",
//   ... (many more fields) ...
//    },

    private String id;
    private String name;
    private String proxy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    private String ownerId;
}
