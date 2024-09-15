package Model;

public class Client {
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional;

    public Client(String name, String address, String phone, boolean isProfessional) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isProfessional = isProfessional;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }
}
