package pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/kshyniakov/Documents/GitHub/java_pft/addressbook-web-tests/src/test/resources/groups.xml")));
        String xml="";
        String line = reader.readLine();
        while (line != null) {
            xml +=line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/kshyniakov/Documents/GitHub/java_pft/addressbook-web-tests/src/test/resources/groups.json")));
        String json="";
        String line = reader.readLine();
        while (line != null) {
            json +=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());//List<GroupData>.class
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        appManager.goTo().groupPage();
        Groups before = appManager.db().groups();
        appManager.group().create(group);
        appManager.group().returnToGroupPage();
        Groups after = appManager.db().groups();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
