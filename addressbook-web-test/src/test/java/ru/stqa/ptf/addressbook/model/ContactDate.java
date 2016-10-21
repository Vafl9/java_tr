package ru.stqa.ptf.addressbook.model;

public class ContactDate {
    private String name;
    private String lastName;
    private String group;

    private String allPhones;
    private String email;
    private String secondEmail;

    private String home;
    private String mobile;
    private String work;

    private String allMail;


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


    public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }


    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public ContactDate withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactDate withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactDate withWork(String work) {
        this.work = work;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactDate withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public ContactDate withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {

        return email;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public ContactDate withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }


    public String getAllMail() {
        return allMail;
    }

    public ContactDate withAllMail(String allMail) {
        this.allMail = allMail;
        return this;
    }

    private int id = Integer.MAX_VALUE;


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }


    public String getGroup() {
        return group;
    }


    public int getId() {
        return id;
    }
}
