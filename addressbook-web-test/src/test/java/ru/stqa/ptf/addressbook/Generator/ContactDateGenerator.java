package ru.stqa.ptf.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ptf.addressbook.model.ContactDate;

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
        List<ContactDate> contact = generateContacts(count);


        if (format.equals("csv")) {
            saveAsCSV(contact, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(contact, new File(file));
        } else {
            System.out.println("Error format");
        }
    }

    private void saveAsXML(List<ContactDate> contact, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactDate.class);
        String xml = xStream.toXML(contact);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCSV(List<ContactDate> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastName(), contact.getEmail()));
        }
        writer.close();
    }
    private List<ContactDate> generateContacts(int count) {
        File photo = new File("src/test/resources/stru.png");
        List<ContactDate> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDate().withName(String.format("Andrew %s", i)).withLastName(String.format("Dzhodzhua %s", i)).withEmail(String.format("Head@mail.ru %s", i)).withPhoto(photo));
        }
        return contacts;
    }
}




