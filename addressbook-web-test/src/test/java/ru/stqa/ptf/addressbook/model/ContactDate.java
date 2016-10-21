package ru.stqa.ptf.addressbook.model;

public class ContactDate {
    private String name;
    private String lastName;
    private String mail;
    private String group;
    public String editButtonXPath;



    private int id = Integer.MAX_VALUE;


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

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public ContactDate withId(int id) {
        this.id = id;
        return this;
    }

    public ContactDate withName(String name) {
        this.name = name;
        return this;
    }

    public ContactDate withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactDate withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getEditButtonXPath() {
        return editButtonXPath;
    }

    public ContactDate withEditButton(String editButton) {

        this.editButtonXPath = editButton;
        return this;
    }

    public int getId() {
        return id;
    }
}
