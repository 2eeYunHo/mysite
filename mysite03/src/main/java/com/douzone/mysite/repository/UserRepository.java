package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	public boolean insert(UserVo vo) {
		return sqlSession.insert("user.inser", vo) == 1;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword",vo);
	}



	public UserVo findByNo(Long userNo) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}

	public boolean update(UserVo vo) {
//			if ("".equals(vo.getPassword())) {
//				return sqlSession.selectOne("user.update1", vo) ==1
//			}
//			else {
//				return sqlSession.selectOne("user.update2", vo) ==1
//			} 동적쿼리를 지원해 주기때문에 상단의 이프 엘스 문을 한줄로 줄여줄수있다. user.xml의 choose when otherwise문을 이용한 동적쿼리문!!!
		return sqlSession.update("user.update",vo)==1;

	}
}
