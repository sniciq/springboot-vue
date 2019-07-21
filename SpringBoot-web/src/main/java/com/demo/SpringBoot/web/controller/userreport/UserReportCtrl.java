package com.demo.SpringBoot.web.controller.userreport;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.SpringBoot.common.util.CommonForm;
import com.demo.SpringBoot.web.SessionUtil;
import com.demo.SpringBoot.common.util.jsonresult.JsonResult;

import com.demo.SpringBoot.dao.vo.userreport.UserReportForm;
import com.demo.SpringBoot.dao.vo.userreport.UserReportBean;
import com.demo.SpringBoot.dao.mapper.module.UserReportMapper;

/**
 * 员工报表
 */
@Controller
@RequestMapping("/userreport/UserReportCtrl/")
public class UserReportCtrl {

	@Autowired
	private UserReportMapper userReportMapper;	
	
	/**
	 * 查询
	 */
	 @ResponseBody
	@RequestMapping(value="search")
	public JsonResult search(@RequestBody CommonForm<UserReportForm> userReportForm) throws Exception {
		long count = userReportMapper.selectUserReportBeanCount(userReportForm.getData());
		List<UserReportBean> list = userReportMapper.selectUserReportBeanByLimit(userReportForm.getData(), userReportForm.getExtLimit());
		return JsonResult.pager(list, count);
	}
}