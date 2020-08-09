package com.wangqi.util;

public class PageCalculator {
    /**
     * 把页位置转为数据库中的行位置
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
