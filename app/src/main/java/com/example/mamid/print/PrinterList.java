package com.example.mamid.print;

import java.util.List;

public class PrinterList {

//    {
//        "success": true,
//            "request": { ... },
//        "printers": [
//        {
//            "id": "2f4b5bca-c967-7728-6af5-1dea2a9eb2d3",
//                "name": "My home printer",
//                "proxy": "ksdjf-7237sf9238-2837jidf",
//                "ownerId": "example@gmail.com",
//   ... (many more fields) ...
//        },
//        {
//            "id": "5af36c4b-0e45-6cfd-c833-b6bca2a8e6d1",
//                "name": "Another printer",
//                "proxy": "ksdjf-7237sf9238-2837jidf",
//                "ownerId": "example@gmail.com",
//   ... (many more fields) ...
//        }
// ]
//    }

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

    private List<Printer> printers;

}
