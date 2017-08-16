package com.shsxt.crm.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.dto.SaleChanceDto;
import com.shsxt.crm.dto.SaleChanceQuery;
import com.shsxt.crm.exception.ParamException;
import com.shsxt.crm.model.SaleChance;
import com.shsxt.crm.util.AssertUtil;

@Service
public class SaleChanceService {
	
	@Autowired
	private SaleChanceDao saleChanceDao;
	
	private static Logger logger = LoggerFactory.getLogger(SaleChanceService.class);
	
	public Map<String, Object> selectForPage(SaleChanceQuery saleChanceQuery){
		// 构建一个分页对象
		Integer page=saleChanceQuery.getPage();
		if (page==null) {
			page=1;
		}
		
		Integer pageSize=saleChanceQuery.getRows();
		if (pageSize==null) {
			pageSize=10;
		}
		//构建排序
		String str=saleChanceQuery.getSort();
		if (StringUtils.isNotBlank(str)) {
			str="id.desc";
		}
		
		
		//构建一个 分页
		//pageBounds 是jar 包中自带的   其中有很多的 重载方法？
		PageBounds pageBounds=new PageBounds(page, pageSize, Order.formString(str));

		// 查询
		List<SaleChance> saleChances=saleChanceDao.selectForPage(saleChanceQuery,pageBounds);
		PageList<SaleChance> result = (PageList<SaleChance>) saleChances;
		
		// 返回分页结果
		Paginator paginator=result.getPaginator();//得到分页器，通过Paginator可以得到总页数等值
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("paginator", paginator);
		map.put("rows", result);
		map.put("total", paginator.getTotalCount());
		
		return map;
	}

	
	
	//增加营销机会
	public void add(SaleChanceDto saleChanceDto,String loginUserName) {
		//参数验证，验证id coustname
		checkParams(saleChanceDto.getCustomerId(), saleChanceDto.getCustomerName(),
				saleChanceDto.getCgjl());
		//验证分配状态
		
		String assginMan=saleChanceDto.getAssignMan();
		int state=0;
		Date assginTime=null;
		if (StringUtils.isNoneBlank(assginMan)){
			state=1;
			assginTime=new Date();
		}
		//执行添加
		
		SaleChance saleChance=new SaleChance();
		
		
		BeanUtils.copyProperties(saleChanceDto, saleChance);
		saleChance.setAssignTime(assginTime);
		saleChance.setState(state);
		saleChance.setCreateMan(loginUserName);
		saleChanceDao.insert(saleChance);
		
	}

	
	//更新记录
	public void update(SaleChance saleChance) {
		//参数验验证
		
		Integer id=saleChance.getId();
		AssertUtil.intIsNotNull(id, "请选择一行记录进行更新。。。");
		checkParams(saleChance.getCustomerId(), saleChance.getCustomerName(),
				saleChance.getCgjl());
		
		//分配状态验证
		checkState(saleChance);
		saleChance.setUpdateDate(new Date());
		saleChanceDao.update(saleChance);
		
		

		
	}
	
	

	public void delete(String ids) {
		AssertUtil.stringIsNotEmpty(ids, "请选择记录进行删除。。");
		saleChanceDao.delete(ids);
	}
	
//参数基本验证
 private void checkParams(Integer customerId,String customerName,Integer cgjl){
	
	AssertUtil.intIsNotNull(customerId, "请输入用户。。。");
	AssertUtil.intIsNotNull(cgjl, "请输入成功几率。。");
	AssertUtil.stringIsNotEmpty(customerName, "请输入用户。。。");
}
 //分配状态验证
 
 private void checkState(SaleChance saleChance) {
		SaleChance saleChanceFromDB = saleChanceDao.findById(saleChance.getId());
		AssertUtil.notNull(saleChanceFromDB, "该记录不存在，请重新选择");
		
		int state = saleChanceFromDB.getState();
		Date assignTime = null;
		String assignMan = saleChanceFromDB.getAssignMan();
		if (saleChanceFromDB.getState() == 0) { // 未分配
			if (StringUtils.isNoneBlank(saleChance.getAssignMan())) {
				state = 1; // 已分配
				assignTime = new Date();
			}
			
		} else { // 已分配
			if (!saleChanceFromDB.getAssignMan().equals(saleChance.getAssignMan())) { // 页面传入的指派人和数据库中的指派人不相等
				if (StringUtils.isBlank(saleChance.getAssignMan())) { // 客户端没有传值
					state = 0; // 处于未分配的状态
					assignTime = null;
				} else { // 客户端传值
					assignMan = saleChance.getAssignMan();
					assignTime = new Date();
				}
			} else {
				assignTime = saleChanceFromDB.getAssignTime();
			}
		}
		saleChance.setAssignMan(assignMan);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
	}
 
 
}
