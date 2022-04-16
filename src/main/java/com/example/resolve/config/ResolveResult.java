package com.example.resolve.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : niejun
 * @Description: TODO
 * @date Date : 2022年04月16日 21:31
 **/
public class ResolveResult<T> {
    List<T> list = new ArrayList<T>(10);

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
