package com.nurinubi.tokoya.reservation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nurinubi.tokoya.reservation.domain.ReservationVO;
import com.nurinubi.tokoya.reservation.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
<!--
 * 
 * @ClassName : ReservationApplication.java
 * @Description : ReservationApplication Class
 * @
 * @		修正日			修正者			修正内容
 * @ 	---------		---------		-------------------------------
 * @ 	2017. 7. 14.		李　多　浩			最初作成
 * 		2017. 7. 16.		李　多　浩			getStaffList追加
 * 		2017. 7. 18		李　多　浩			getReserveHistory追加
 * 
 * @author 李　多　浩
 * @since 2017
 * @version 0.1
 *
 *  Copyright (C) by NuriNubi All right reserved.
 * -->
 */

@Repository
public class ReservationApplication implements ReservationRepository {

	private static final Logger logger = LoggerFactory.getLogger(ReservationApplication.class);

	@Autowired
	private SqlSession sqlSession;
	
	//1.
	@Override
	public List<HashMap<String, Object>> getCourseList() {
		logger.debug("======================================getCourseListApplicationStart===================================");
		List<HashMap<String, Object>> list = this.sqlSession.selectList("getCourseList");
		logger.debug(list.toString());
		logger.debug("======================================getCourseListApplicationEnd=====================================");
		return list;
	}
	
	//2.
	@Override
	public List<HashMap<String, Object>> getStaffList(Map<String, Object> commandMap) {
		logger.debug("======================================getStaffListApplicationStart===================================");
		logger.debug(commandMap.toString());
		commandMap.put("time", commandMap.get("date") + "" + commandMap.get("time") + "0000");
		commandMap.put("endTime",commandMap.get("date") + "230000");
		List<HashMap<String, Object>> list = this.sqlSession.selectList("getStaffList", commandMap);
		logger.debug(list.toString());
		logger.debug("======================================getStaffListApplicationEnd=====================================");
		return list;
	}
	
	//3.
	@Override
	public ReservationVO getCheckInfo(ReservationVO rDomain) {
		logger.debug("======================================getCheckInfoApplicationStart===================================");
		logger.debug("before : "+rDomain.toString());
		ReservationVO rtn = this.sqlSession.selectOne("getCheckInfo", rDomain);
		rtn.setReservationDate(rDomain.getReservationDate());
		rtn.setReservationStartTime(rDomain.getReservationStartTime());
		logger.debug(rtn.toString());
		logger.debug("======================================getCheckInfoApplicationEnd=====================================");
		return rtn;
	}
	
	//4.
	@Override
	public int setReserve(ReservationVO rDomain) {
		logger.debug("======================================setReserveApplicationStart===================================");
		rDomain.setReservationDateTime(rDomain.getReservationDate()+""+rDomain.getReservationStartTime()+"0000");
		rDomain.setReservationEndDateTime(rDomain.getReservationDate()+""+(Integer.parseInt(rDomain.getCourseTime())+Integer.parseInt(rDomain.getReservationStartTime()))+"0000");
		rDomain.setReservationStatus("1");
		//ReservationIDを設定するためテーブルの全体カウンター
		Integer i = sqlSession.selectOne("getReservCount");
		i = new Integer(i.intValue() + 1);
		String s = ""+i;
		rDomain.setReservationId(s);
		//Insert
		int rtn = this.sqlSession.insert("setReserveInfo", rDomain);
		logger.debug("======================================setReserveApplicationEnd=====================================");
		return rtn;
	}
	
	@Override
	public List<Map<String, Object>> getReservationListByToday() throws Exception {
		logger.debug("======================================getReservationListByTodayStart===============================");
		logger.debug("======================================getReservationListByTodayEnd=================================");
		return this.sqlSession.selectList("getReservationListByToday");
	}

	@Override
	public List<Map<String, Object>> getReservationByDate(Date date) throws Exception {
		logger.debug("======================================getReservationByDateStart====================================");
		logger.debug("======================================getReservationByDateEnd======================================");
		return this.sqlSession.selectList("getReservationByDate", date);
	}

	@Override
	public List<ReservationVO> getReserveHistory(ReservationVO rDomain) {
		logger.debug("======================================getReserveHistoryStart=======================================");
		logger.debug(rDomain.toString());
		logger.debug("======================================getReserveHistoryEnd=========================================");
		return this.sqlSession.selectList("getReserveHistory", rDomain);
	}
	
	@Override
	public int setReserveCancel(ReservationVO rDomain) {
		logger.debug("======================================setReserveCancelStart=======================================");
		logger.debug(rDomain.toString());
		logger.debug("======================================setReserveCancelEnd=========================================");
		return this.sqlSession.update("setReserveCancel", rDomain);
//		return null;
	}
}