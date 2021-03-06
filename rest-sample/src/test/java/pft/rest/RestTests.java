package pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by kshyniakov on 24.01.2017.
 */
public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(5);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test subject").withDescription("Test description");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
