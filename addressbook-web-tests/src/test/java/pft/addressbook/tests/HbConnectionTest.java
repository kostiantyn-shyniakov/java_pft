package pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pft.addressbook.model.ContactData;
import pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by kshyniakov on 10.01.2017.
 */
public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    @Test
    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        /*List<GroupData> groups = session.createQuery( "from GroupData" ).list();
        for ( GroupData group : groups ) {
            System.out.println(group);
        }*/

        List<ContactData> contacts = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        for ( ContactData contact : contacts ) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
    }
}
