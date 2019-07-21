package com.demo.SpringBoot.common.util;

public class CommonForm<T> {
    private T data;
    private ExtLimit extLimit;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ExtLimit getExtLimit() {
        return extLimit;
    }

    public void setExtLimit(ExtLimit extLimit) {
        this.extLimit = extLimit;
    }
}
