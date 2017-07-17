package com.nurinubi.tokoya.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nurinubi.tokoya.admin.domain.AdminVO;
import com.nurinubi.tokoya.admin.domain.StylistVO;
import com.nurinubi.tokoya.admin.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminApplication implements AdminRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<StylistVO> getStylistList() {
		return this.sqlSession.selectList("getStylistList");
	}
	public void insertStylist(Map<String, Object> map) {
		this.sqlSession.insert("addStylist", map);
		}
	
	@Override
	public List<Map<String, Object>> getReservationListByStylist(Map<String, Object> cmdMap) throws Exception {
		return this.sqlSession.selectList("getReservationListByStylist", cmdMap);
	}
	
}