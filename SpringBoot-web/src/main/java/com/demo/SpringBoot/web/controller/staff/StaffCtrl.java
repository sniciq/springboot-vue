package com.demo.SpringBoot.web.controller.staff;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.SpringBoot.common.util.CommonForm;
import com.demo.SpringBoot.common.util.jsonresult.JsonResult;

import com.demo.SpringBoot.dao.entity.StaffEty;
import com.demo.SpringBoot.dao.mapper.base.StaffMapper;

/**
 * 员工管理
 */
@Controller
@RequestMapping("/staff/StaffCtrl/")
public class StaffCtrl {

	@Autowired
	private StaffMapper staffMapper;		
	
	/**
	 * 查询
	 */
    @ResponseBody
	@RequestMapping(value="search")
	public JsonResult search(@RequestBody CommonForm<StaffEty> staffEty) throws Exception {
		long count = staffMapper.selectLimitCount(staffEty.getData());
		List<StaffEty> list = staffMapper.selectByLimit(staffEty.getData(), staffEty.getExtLimit());
		return JsonResult.pager(list, count);
	}
	
	/**
	 * 保存
	 */
    @ResponseBody
	@RequestMapping(value="save")
	public JsonResult save(@RequestBody StaffEty staffEty) throws Exception {
		if(staffEty.getId() == null) {
			staffMapper.insert(staffEty);
		}
		else {
			staffMapper.updateById(staffEty);
		}
		return JsonResult.success();
	}
	
	/**
	 * 删除
	 */
    @ResponseBody
	@RequestMapping(value="delete")
	public JsonResult delete(@RequestParam("id") long id) {
		staffMapper.deleteById(id);
		return JsonResult.success();
	}
	
	/**
	 * 得到详细信息
	 */
    @ResponseBody
	@RequestMapping(value="getDetailInfo")
	public JsonResult getDetailInfo(@RequestParam("id") long id) throws Exception {
		StaffEty staffEty = staffMapper.selectById(id);
		return JsonResult.success(staffEty);
	}
	
}