package com.demo.SpringBoot.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

public class AjaxOut {

	private static Logger logger = LogManager.getLogger(AjaxOut.class);
	private static Gson gson = new Gson();

	public static void responseText(HttpServletResponse response, Object obj) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = "";
		try {
			json = ow.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		responseText(response, json, "text/html");
	}

	/**
	 * AJAX输出页面
	 *
	 * @param response
	 * @param s
	 */
	public static void responseText(HttpServletResponse response, String s, String contentType) {
		// 指定内容类型
		response.setContentType(contentType + ";charset=utf-8");
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			PrintWriter out = response.getWriter();
			out.print(s);
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 返回JSON
	 * @param response
	 * @param obj
	 */
	public static void responseJson(HttpServletResponse response, Object obj) {
		// 指定内容类型
		response.setContentType("application/json;charset=UTF-8");
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(obj));
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
