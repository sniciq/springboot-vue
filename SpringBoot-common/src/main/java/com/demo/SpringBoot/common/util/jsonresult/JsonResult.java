package com.demo.SpringBoot.common.util.jsonresult;

import java.util.Collection;
/**
 * Json返回格式
 * @param <T>
*/
public class JsonResult<T> {
    private T data;
    private boolean success = true;
    private String info = "";

    public JsonResult() { }

    public JsonResult(T t) {
    this.data = t;
    }

    public JsonResult(String info) {
    this.data = (T) info;
    }

    public JsonResult(String info, boolean success) {
    this.info = info;
    this.success = success;
    }

    public static<T> JsonResult<T> success() {
        return new JsonResult<T>();
    }

    public static<T> JsonResult<T> success(T data) {
        return new JsonResult<T>(data);
    }

    public static<T> JsonResult<T> success(String msg) {
        return new JsonResult<T>(msg);
    }

    public static<T> JsonResult<T> error() {
        JsonResult r = new JsonResult<T>();
        r.setInfo("error");
        r.setSuccess(false);
        return r;
    }

    public static<T> JsonResult<T> error(String info) {
        JsonResult r = new JsonResult<T>();
        r.setInfo(info);
        r.setSuccess(false);
        return r;
    }

    public static <T extends Collection> JsonResultPager<T> pager(T dataList) {
        JsonResultPager pager = new JsonResultPager(dataList, dataList.size());
        return pager;
    }

    public static <T extends Collection> JsonResultPager<T> pager(T dataList, long total) {
        JsonResultPager pager = new JsonResultPager(dataList, total);
        return pager;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getResult() {
        return isSuccess() ? "success" : "fail";
    }
}
