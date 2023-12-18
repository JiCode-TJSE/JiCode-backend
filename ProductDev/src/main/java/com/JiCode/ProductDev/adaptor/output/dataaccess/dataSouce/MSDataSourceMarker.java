package com.JiCode.ProductDev.adaptor.output.dataaccess.dataSouce;

// 注意里面都是全局的方法，是全局标志
public class MSDataSourceMarker {
    // 线程局部变量，标记粒度为线程级别
    private static final ThreadLocal<Boolean> flag = new ThreadLocal<Boolean>();

    // 返回标记
    public static Boolean get() {
        return flag.get();
    }

    // 写状态，标记为主库
    public static void master() {
        flag.set(Boolean.TRUE);
    }

    // 读状态，标记为从库
    public static void slave() {
        flag.set(Boolean.FALSE);
    }

    // 清空标记
    public static void clean() {
        flag.remove();
    }
}
