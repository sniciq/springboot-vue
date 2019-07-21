package com.demo.SpringBoot.common.util.jsonresult;

public class JsonResultPager<T> extends JsonResult {
    private long total;

    public JsonResultPager(T data, long total) {
    setData(data);
    this.total = total;
    }

    public long getTotal() {
    return total;
    }

    public void setTotal(long total) {
    this.total = total;
    }
}
