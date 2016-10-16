package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String mail;
    private String group;


    public ContactData(String name, String lastName, String mail, String group) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.group = group;

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getGroup() {
        return group;
    }
}
