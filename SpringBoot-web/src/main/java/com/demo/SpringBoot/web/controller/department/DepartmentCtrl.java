package com.demo.SpringBoot.web.controller.department;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.SpringBoot.common.util.CommonForm;
import com.demo.SpringBoot.common.util.jsonresult.JsonResult;

import com.demo.SpringBoot.dao.entity.DepartmentEty;
import com.demo.SpringBoot.dao.mapper.base.DepartmentMapper;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/department/DepartmentCtrl/")
public class DepartmentCtrl {

	@Autowired
	private DepartmentMapper departmentMapper;		
	
	/**
	 * 查询
	 */
    @ResponseBody
	@RequestMapping(value="search")
	public JsonResult search(@RequestBody CommonForm<DepartmentEty> departmentEty) throws Exception {
		long count = departmentMapper.selectLimitCount(departmentEty.getData());
		List<DepartmentEty> list = departmentMapper.selectByLimit(departmentEty.getData(), departmentEty.getExtLimit());
		return JsonResult.pager(list, count);
	}
	
	/**
	 * 保存
	 */
    @ResponseBody
	@RequestMapping(value="save")
	public JsonResult save(@RequestBody DepartmentEty departmentEty) throws Exception {
		if(departmentEty.getId() == null) {
			departmentMapper.insert(departmentEty);
		}
		else {
			departmentMapper.updateById(departmentEty);
		}
		return JsonResult.success();
	}
	
	/**
	 * 删除
	 */
    @ResponseBody
	@RequestMapping(value="delete")
	public JsonResult delete(@RequestParam("id") long id) {
		departmentMapper.deleteById(id);
		return JsonResult.success();
	}
	
	/**
	 * 得到详细信息
	 */
    @ResponseBody
	@RequestMapping(value="getDetailInfo")
	public JsonResult getDetailInfo(@RequestParam("id") long id) throws Exception {
		DepartmentEty departmentEty = departmentMapper.selectById(id);
		return JsonResult.success(departmentEty);
	}
	
}