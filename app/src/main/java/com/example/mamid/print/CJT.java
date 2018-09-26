package com.example.mamid.print;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CJT implements Serializable {
@SerializedName(value = "version")
   private String version = "1.0";
@SerializedName(value = "print")
    private Printer print= new Printer();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Printer getPrint() {
        return print;
    }

    public void setPrint(Printer print) {
        this.print = print;
    }

    class Printer
    {
        @SerializedName("vendor_ticket_item")
       private List<Object> vendor_ticket_item = new ArrayList<>();
        @SerializedName("color")
        private Colour color= new Colour();
        @SerializedName("copies")
        private  Copies copies= new Copies();
        public List<Object> getVendor_ticket_item() {
            return vendor_ticket_item;
        }

        public void setVendor_ticket_item(List<Object> vendor_ticket_item) {
            this.vendor_ticket_item = vendor_ticket_item;
        }

        public Colour getColor() {
            return color;
        }

        public void setColor(Colour color) {
            this.color = color;
        }

        public Copies getCopies() {
            return copies;
        }

        public void setCopies(Copies copies) {
            this.copies = copies;
        }



class Colour
{
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String type = "STANDARD_MONOCHROME";
}

        class Copies
        {
            public int getCopies() {
                return copies;
            }

            public void setCopies(int copies) {
                this.copies = copies;
            }
            private     int copies=1;
        }
    }
}
