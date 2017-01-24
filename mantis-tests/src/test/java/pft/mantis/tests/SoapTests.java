package pft.mantis.tests;

import org.testng.annotations.Test;
import pft.mantis.model.Issue;
import pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by kshyniakov on 24.01.2017.
 */
public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(2);
        Set<Project> projects = appManager.soap().getProjects();
        System.out.println(projects.size());
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = appManager.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = appManager.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
