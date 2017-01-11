package pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kshyniakov on 21.12.2016.
 */
public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegateAttr;

    public Contacts(Contacts contacts) {
        this.delegateAttr = new HashSet<ContactData>(contacts.delegateAttr);
    }

    public Contacts() {
        this.delegateAttr = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegateAttr = new HashSet<ContactData>(contacts);
    }

    @Override
    protected Set delegate() {
        return delegateAttr;
    }

    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
