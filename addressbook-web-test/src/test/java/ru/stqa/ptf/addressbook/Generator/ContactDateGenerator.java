package ru.stqa.ptf.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDateGenerator {


    @Parameter(names = "-c", description = "Contact count")
    public int count;


    @Parameter(names = "-f", description = "File path")
    public String file;

    @Parameter(names = "-d", description = "Date format")
    public String format;

    public static void main(String[] args) throws IOException {

        ContactDateGenerator generator = new ContactDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contact = generateContacts(count);


        if (format.equals("csv")) {
            saveAsCSV(contact, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(contact, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(contact, new File(file));
        } else System.out.println("Error format");
    }

    private void saveAsJSON(List<ContactData> contact, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contact);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(json);
        }



    }

    private void saveAsXML(List<ContactData> contact, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contact);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastName(), contact.getEmail()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        //File photo = new File("src/test/resources/stru.png");
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withName(String.format("Andrew %s", i)).withLastName(String.format("Dzhodzhua %s", i)).withEmail(String.format("Head@mail.ru %s", i)).withHome("111").withMobile("222").withWork("333"));
        }
        return contacts;
    }
}




