package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestBookRepository {
	@Autowired
	private DataSource dataSource;
	public boolean add(GuestbookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql =
				" insert" +
				" into guestbook" +
				" values (null, ?, ?, ?, now())";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			//System.out.println("드라이버 로딩 실패:" + e);
			throw new GuestbookRepositoryException(e.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			//3. SQL 준비
			String sql =
					"select no, name, date_format(reg_date, '%Y-%m-%d %H:%i:%s'), message"
					+" from guestbook"
				    +" order by no desc";
			pstmt = connection.prepareStatement(sql);
			
			//4. Parameter Mapping
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);

				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(reg_date);
				vo.setMessage(message);
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql =
			"delete from guestbook" +
			" where no=?" +
			" and password=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	//guestbook service 용
	public Boolean delete(Long no, String password) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql =
			"delete from guestbook" +
			" where no=?" +
			" and password=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}


}