package com.xsodus.az.app_service.backend.util;

import java.util.ArrayList;
import java.util.List;

public class LinkList {

    private LinkList next;
    private String value;

    public LinkList(String value) {
        this.next = null;
        this.value = value;
    }

    public void addItem( String value ) {
        LinkList current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new LinkList(value);
    }

    public List<String> toArrayList() {
        LinkList temp = this;
        var list = new ArrayList<String>();
        while (temp != null) {
            list.add(temp.value);
            temp = temp.next;
        }
        return list;
    }
}
