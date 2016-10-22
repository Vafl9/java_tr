package ru.stqa.ptf.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
@XStreamAlias("contact")
public class ContactDate {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String name;
    @Expose
    private String lastName;
    @Expose
    private String group;
    @Expose
    private String allPhones;
    @Expose
    private String email;
    @Expose
    private String secondEmail;
    @Expose
    private String home;
    @Expose
    private String mobile;
    @Expose
    private String work;
    @Expose
    private String allMail;
    @Expose
    private String allContactInformation;
    @Expose
    private String address;
    @Expose
    private File photo;


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


    public String getAddress() {
        return address;
    }

    public ContactDate withAddress(String address) {
        this.address = address;
        return this;
    }


    public String getAllContactInformation() {
        return allContactInformation;
    }

    public ContactDate withAllContactInformation(String allContactInformation) {
        this.allContactInformation = allContactInformation;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactDate withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
}
