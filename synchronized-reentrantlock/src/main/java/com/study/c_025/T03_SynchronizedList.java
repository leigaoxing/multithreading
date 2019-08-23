package com.study.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 对非同步容器进行包装
 *
 * @date: 2019/7/16
 * @author: gxl
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
