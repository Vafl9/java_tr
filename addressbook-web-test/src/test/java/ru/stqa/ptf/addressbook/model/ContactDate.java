package ru.stqa.ptf.addressbook.model;

public class ContactDate {
    private final String name;
    private final String lastName;
    private final String mail;
    private String group;



    private int id;


    public ContactDate(String name, String lastName, String mail, String group, int id) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.group = group;
        this.id = id;

    }

    public ContactDate(String name, String lastName, String mail, String group) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.group = group;
        this.id = Integer.MAX_VALUE;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDate that = (ContactDate) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
