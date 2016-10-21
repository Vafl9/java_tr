package ru.stqa.ptf.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupDate> {


    private Set<GroupDate> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupDate>(groups.delegate());
    }

    @Override
    protected Set<GroupDate> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupDate group)
    {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups()
    {
        this.delegate = new HashSet<>();
    }


    public Groups withOut(GroupDate group)
    {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
