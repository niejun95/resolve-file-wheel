package com.example.resolve.listener.impl;


import com.example.resolve.listener.Listener;

import java.util.logging.Logger;

/**
 * @author : niejun
 * @Description: 默认监听器
 * @date Date : 2022年04月16日 15:20
 **/
public class DefaultListener extends Listener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void invoke(Object object) {
        logger.info("默认监听器 defaultlistener" + "监听到一条已处理信息");
        logger.info("信息内容为" + object.toString());
    }

    @Override
    public void invokeHead() {
    }

    public DefaultListener() {
    }
}
