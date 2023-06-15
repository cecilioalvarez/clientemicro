package es.logixs.microcliente.cliente.dto;

public class UserMobileDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserMobileDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public UserMobileDTO() {
    }

    private String name;
    private String lastName;
}
