package com.demo.SpringBoot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.demo.SpringBoot.web.SessionUtil;
import com.demo.SpringBoot.common.util.jsonresult.JsonResult;

@Controller
public class AppCtrl {

    @RequestMapping("/")
    public String app(HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmSSS");
        request.setAttribute("sysVersion", df.format(new Date()));
        return "views/app";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmSSS");
        request.setAttribute("sysVersion", df.format(new Date()));
        return "views/login";
    }

	@ResponseBody
	@RequestMapping(value="/sys/login")
	public JsonResult login(@RequestBody Map<String, String> paraMap) {
		String userNmae = paraMap.get("username");
		if(StringUtils.isEmpty(userNmae)) {
		    return JsonResult.error("用户名不能为空");
        }
		SessionUtil.login(userNmae);
		return JsonResult.success();
	}
	
	@RequestMapping(value="/sys/logout")
	public String login() {
		SessionUtil.logout();
		return "redirect:/login";
	}

	@ResponseBody
	@RequestMapping("/sys/route")
	public List<Map<String, String>> route(HttpServletRequest request) throws Exception {
		List<Map<String, String>> routeList = Lists.newArrayList();
		Map<String, String> route = null;
	
		route = Maps.newHashMap();
		routeList.add(route);
		route.put("name", "员工管理");
		route.put("ctrl", "StaffCtrl");
		route.put("path", "/module/staff");
		route.put("templateUrl", request.getContextPath() + "/modules/module/StaffListTpl.html?v=");
		route.put("files", request.getContextPath() + "/modules/module/StaffCtrl.js");
	
		route = Maps.newHashMap();
		routeList.add(route);
		route.put("name", "部门管理");
		route.put("ctrl", "DepartmentCtrl");
		route.put("path", "/module/department");
		route.put("templateUrl", request.getContextPath() + "/modules/module/DepartmentListTpl.html?v=");
		route.put("files", request.getContextPath() + "/modules/module/DepartmentCtrl.js");
	
		route = Maps.newHashMap();
		routeList.add(route);
		route.put("name", "员工报表");
		route.put("ctrl", "UserReportCtrl");
		route.put("path", "/module/UserReport");
		route.put("templateUrl", request.getContextPath() + "/modules/module/UserReportListTpl.html?v=");
		route.put("files", request.getContextPath() + "/modules/module/UserReportCtrl.js");
		return routeList;
	}

	@ResponseBody
	@RequestMapping("/sys/menus")
	public List<Map<String, Object>> ngMenus() throws Exception {
		List<Map<String, Object>> menuList = Lists.newArrayList();
		Map<String, Object> menu = null;
		Map<String, String> subMenu = null;
		List<Map<String, String>> subMenuList = null;
	
		menu = Maps.newHashMap();
		menuList.add(menu);
		menu.put("name", "后台管理");
		menu.put("type", "toggle");
		menu.put("showTip", "false");
		menu.put("icon", "fa-folder");
		
		subMenuList = Lists.newArrayList();
		menu.put("subMenus", subMenuList);
			
		subMenu = Maps.newHashMap();
		subMenu.put("name", "员工管理");
		subMenu.put("type", "link");
		subMenu.put("showTip", "false");
		subMenu.put("icon", "fa-file-o");
		subMenu.put("path", "/module/staff");
		subMenuList.add(subMenu);
			
		subMenu = Maps.newHashMap();
		subMenu.put("name", "部门管理");
		subMenu.put("type", "link");
		subMenu.put("showTip", "false");
		subMenu.put("icon", "fa-file-o");
		subMenu.put("path", "/module/department");
		subMenuList.add(subMenu);

		menu = Maps.newHashMap();
		menuList.add(menu);
		menu.put("name", "员工报表");
		menu.put("type", "link");
		menu.put("showTip", "false");
		menu.put("icon", "fa-file-o");
		menu.put("path", "/module/UserReport");
		
		
		return menuList;
	}
}
