package com.xsodus.az.app_service.backend.service;

import com.xsodus.az.app_service.backend.component.MyFirstBean;
import org.springframework.stereotype.Service;

@Service
public class MyStoryService {
    private final MyFirstBean myFirstBean;

    public MyStoryService(MyFirstBean myFirstBean) {
        this.myFirstBean = myFirstBean;
    }


    public String getMyFirstStory() {
        return "My first story is from:" + myFirstBean.getName();
    }
}
