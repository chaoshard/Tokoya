package com.nurinubi.tokoya.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nurinubi.tokoya.user.domain.UserVO;
import com.nurinubi.tokoya.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserApplication implements UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private SqlSession userlogin;

	@Override
	public List<UserVO> findOne(String username, String userpass) {
		return this.userlogin.selectOne(username, userpass);
	}

	@Override
	public void insertUser(Map<String, Object> map) throws Exception {
		map.get("email1");
		this.sqlSession.insert("addUser", map);
	}

	@Override
	public void login(Map<String, Object> map) throws Exception {
		this.sqlSession.insert("login", map);
	}
	
}