package com.study.c_026;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 *
 * @date: 2019/7/17
 * @author: gxl
 */
public class T01_Executor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
