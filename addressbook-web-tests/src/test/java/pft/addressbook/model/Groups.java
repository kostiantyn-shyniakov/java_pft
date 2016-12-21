package pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kshyniakov on 19.12.2016.
 */
public class Groups extends ForwardingSet<GroupData> {
    private Set<GroupData> delegateAttr;

    public Groups(Groups groups) {
        this.delegateAttr = new HashSet<GroupData>(groups.delegateAttr);
    }

    public Groups() {
        this.delegateAttr = new HashSet<>();
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegateAttr;
    }

    public Groups withAdded(GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
