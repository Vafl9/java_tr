package ru.stqa.ptf.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDateGenerator {


    @Parameter(names = "-c", description = "Group count")
    public int count;


    @Parameter(names = "-f", description = "File path")
    public String file;

    @Parameter(names = "-d", description = "Date format")
    public String format;

    public static void main(String[] args) throws IOException {

        GroupDateGenerator generator = new GroupDateGenerator();
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
        List<GroupData> group = generateGroups(count);

        if (format.equals("csv")) {
            saveAsCSV(group, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(group, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(group, new File(file));
        } else System.out.println("Error format");

    }

    private void saveAsJSON(List<GroupData> group, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(group);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(json);
        }
    }

    private void saveAsXML(List<GroupData> group, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(group);
        try(Writer writer = new FileWriter(file))
        {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test1 %s", i)).withHeader(String.format("test2 %s", i)).withFooter(String.format("test3 %s", i)));
        }
        return groups;
    }
}



