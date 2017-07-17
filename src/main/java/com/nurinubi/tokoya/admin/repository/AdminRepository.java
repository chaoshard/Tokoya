package com.nurinubi.tokoya.admin.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.nurinubi.tokoya.admin.domain.AdminVO;
import com.nurinubi.tokoya.admin.domain.StylistVO;

@Mapper
public interface AdminRepository {
	List<Map<String, Object>> getReservationListByStylist(Map<String, Object> cmdMap) throws Exception;
	
	List<StylistVO> getStylistList();
}
