package org.example.hillel.testdata;

public enum User {

    REG_USER("Autotest","Autotest","302346894","123456*1Nnm!");

    private final String firstName;
    private final String lastName;
    private final String number;
    private final String password;

    User(String firstName, String lastName, String number, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }
}
