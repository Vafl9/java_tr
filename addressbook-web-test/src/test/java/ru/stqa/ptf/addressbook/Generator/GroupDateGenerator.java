package ru.stqa.ptf.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.ptf.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDateGenerator {


    @Parameter (names = "-c",description = "Group count")
    public int count;


    @Parameter (names = "-f",description = "File path")
    public String file;

    public static void main (String[] args) throws IOException {

        GroupDateGenerator generator = new GroupDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }
        catch (ParameterException ex)
        {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<GroupDate> group = generateGroups(count);
        save(group,new File(file));
    }

    private void save(List<GroupDate> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(GroupDate group: groups)
        {
            writer.write(String.format("%s;%s;%s\n",group.getName(),group.getHeader(),group.getFooter()));
        }
        writer.close();
    }

    private List<GroupDate> generateGroups(int count) {
        List<GroupDate> groups = new ArrayList<>();
        for (int i = 0; i<count;i++)
        {
            groups.add(new GroupDate().withName(String.format("test1 %s",i)).withHeader(String.format("test2 %s",i)).withFooter(String.format("test3 %s",i)));
        }
        return groups;
    }
}



