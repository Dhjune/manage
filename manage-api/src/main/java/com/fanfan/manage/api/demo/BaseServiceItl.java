package com.fanfan.manage.api.demo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-4
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
public interface BaseServiceItl<T> {

    public T get();

    public List<T> list()throws Exception;

    public void put(T obj)throws Exception;

    public void delete(T obj)throws Exception;

    public void update(T obj) throws Exception;
    

}
