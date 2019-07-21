package com.demo.SpringBoot.dao.mapper.module;

import com.demo.SpringBoot.dao.vo.userreport.UserReportBean;
import com.demo.SpringBoot.dao.vo.userreport.UserReportForm;
import java.util.List;
import com.demo.SpringBoot.common.util.ExtLimit;
import org.apache.ibatis.annotations.Param;

public interface UserReportMapper {

	public long selectUserReportBeanCount(@Param("form")UserReportForm form);

	public List<UserReportBean> selectUserReportBeanByLimit(@Param("form")UserReportForm form, @Param("limit") ExtLimit limit);

}