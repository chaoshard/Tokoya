package com.nurinubi.tokoya.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nurinubi.tokoya.admin.domain.ScheduleVO;
import com.nurinubi.tokoya.admin.domain.StylistVO;
import com.nurinubi.tokoya.admin.repository.AdminRepository;
import com.nurinubi.tokoya.reservation.domain.ReservationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
* @Class Name : AdminController.java.java
* @Description :  BoardController.java Class
* @Modification Information
* @ 
* @	修正日			修正者			修正内容
* @ 	---------		---------		-------------------------------
* @ 	2017.07.12		Kim				最初作成
*           16		Kim				
* 			17		Lee				Add Method getStyBusyDate 
* @author Kim
* @since 2017.07.12
* @version 0.1
*
*  Copyright (C) by NuriNubi All right reserved.
*/

/**
 * スタイリスト、管理者のDB処理
 * @author Administrator
 *
 */
@Repository
public class AdminApplication implements AdminRepository {
	

	private static final Logger logger = LoggerFactory.getLogger(AdminApplication.class);

	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * スタイリスト全件表示
	 */
	@Override
	public List<StylistVO> getStylistList() {
		return this.sqlSession.selectList("getStylistList");
	}

	/**
	 * スタイリスト追加
	 */
	public void insertStylist(Map<String, Object> map) {
		this.sqlSession.insert("addStylist", map);
	}
	
	/**
	 * スタイリスト詳細
	 */
	@Override
	public List<StylistVO> getStylistDetail(String id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return this.sqlSession.selectList("getStylistDetail",id);
	}
	
	/**
	 * スタイリスト名更新
	 */
	@Override
	public int upStylist(StylistVO styvo) {
		return this.sqlSession.update("upStylist", styvo);
	}
	
	/**@Override
	public List<StylistVO> judgeStylist1(StylistVO styvo) {
		return this.sqlSession.selectList("judgeStylist",styvo);
	}**/
	
	@Override
	public List<StylistVO> judgeStylist(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		//System.out.println("///// id ：" + id + " /////");
		//System.out.println("///// param ：" + param + " /////");
		//System.out.println(this.sqlSession.selectList("judgeStylist",id));
		return this.sqlSession.selectList("judgeStylist",id);
	}
	
	@Override
	public int upStylistStatus(StylistVO styvo) {
		return this.sqlSession.update("upStylistStatus", styvo);
	}	

	@Override
	public Map<String, Object> getStylistSchedule(String date, String stylist) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("date", date+"01");
		param.put("stylistId", stylist);
		System.out.println(date);
		List<ScheduleVO> offDate = this.sqlSession.selectList("getStylistSchedule", param);
		List<ReservationVO> reservation = this.sqlSession.selectList("getReservationByStylist", param);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("offDate", offDate);
		result.put("reservation", reservation);
		return result;
	}

	@Override
	public int addSchedule(ScheduleVO vo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Integer i = sqlSession.selectOne("getScheduleCount");
		i = new Integer(i.intValue() + 1);
		vo.setScheduleId(i.toString());
		param.put("scheduleId", vo.getScheduleId());
		param.put("stylistId", vo.getStylistId());
		param.put("offDate", vo.getOffDate());
		int result = this.sqlSession.insert("addSchedule", param);
		return result;
	}

	@Override
	public String checkStyId(String id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return this.sqlSession.selectOne("checkStyId", param);
	}
}