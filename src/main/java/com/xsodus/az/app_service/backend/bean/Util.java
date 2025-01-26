package com.xsodus.az.app_service.backend.bean;

import com.xsodus.az.app_service.backend.util.LinkList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Bean
    public LinkList linkList() {
        var linkList =  new LinkList("first");
        linkList.addItem("second");
        linkList.addItem("third");
        return linkList;
    }
}
