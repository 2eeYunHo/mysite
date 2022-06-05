package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql =  " insert" 
						+ " into board" 
						+ " values(null, ?, ?, 0, now(), 1, 1, 1, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUser_no());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll(int pages) {
		List<BoardVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			// 3. SQL 준비
			String sql =  " select a.no, a.title, a.contents, a.hit, "
						+ " a.reg_date, a.group_no, a.order_no, a.depth, b.name, a.user_no " 
						+ " from board a , user b "
						+ " where a.user_no = b.no " 
						+ " order by group_no desc, order_no asc, depth asc  " 
						+ " limit ?, 5";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, (pages - 1) * 5);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getLong(4));
				vo.setReg_date(rs.getString(5));
				vo.setGroup_no(rs.getLong(6));
				vo.setOrder_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserName(rs.getString(9));
				vo.setUser_no(rs.getLong(10));

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<BoardVo> findByNo(long no) {

		List<BoardVo> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql =  "select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, b.no, b.name"
						+ "	from board a, user b" 
						+ "	where a.user_no = b.no and a.no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setReg_date(rs.getString(4));
				vo.setHit(rs.getLong(5));
				vo.setGroup_no(rs.getLong(6));
				vo.setOrder_no(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUser_no(rs.getLong(9));
				vo.setUserName(rs.getString(10));

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public void delete(long no, long user_no) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql =  "delete from board" 
						+ " where no=?" 
						+ " and user_no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setLong(2, user_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			String sql =  " update board set " 
						+ " title = ? , " 
						+ " contents = ? " 
						+ " where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

//	public void reply(BoardVo vo) {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			String title = vo.getTitle();
//			String contents = vo.getContents();
//			long group_no = vo.getGroup_no();
//			long order_no = vo.getOrder_no();
//			long depth = vo.getDepth();
//			long user_no = vo.getUser_no();
//			long no = vo.getNo();
//
//			connection = getConnection();
//
//			if (order_no == 1) {
//				String sql =  "insert into  " 
//							+ " board (title, contents, hit, reg_date, group_no, order_no, depth, user_no) "
//							+ " select  ?, ?, 0, now(), ? , Max(order_no)+1, ?, ? " 
//							+ " from board where no = ?";
//				pstmt = connection.prepareStatement(sql);
//
//				pstmt.setString(1, title);
//				pstmt.setString(2, contents);
//				pstmt.setLong(3, group_no);
//				pstmt.setLong(4, depth + 1);
//				pstmt.setLong(5, user_no);
//				pstmt.setLong(6, no);
//
//			} else {
//				String sql =  "insert into  " 
//							+ " board (title, contents, hit, reg_date, group_no, order_no, depth, user_no) "
//							+ " select  ?, ?, 0, now(), ? , ?, ?, ? " 
//							+ " from board where no = ?";
//				pstmt = connection.prepareStatement(sql);
//
//				pstmt.setString(1, title);
//				pstmt.setString(2, contents);
//				pstmt.setLong(3, group_no);
//				pstmt.setLong(4, order_no);
//				pstmt.setLong(5, depth + 1);
//				pstmt.setLong(6, user_no);
//				pstmt.setLong(7, no);
//			}
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public void reply(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			String title = vo.getTitle();
			String contents = vo.getContents();
			long group_no = vo.getGroup_no();
			long order_no = vo.getOrder_no();
			long depth = vo.getDepth();
			long user_no = vo.getUser_no();
			long no = vo.getNo();
			
			connection = getConnection();
			if(order_no == 1) {
				String sql =  "INSERT INTO  "
							+ " board (title, contents, hit, reg_date, group_no, order_no, depth, user_no) "
							+ " select  ?, ?, 0, now(), ? , MAX(order_no)+1, ?, ? "
							+ " from board where group_no = ?";
				pstmt = connection.prepareStatement(sql);			
				
				pstmt.setString(1, title);
				pstmt.setString(2, contents);
				pstmt.setLong(3, group_no);
				pstmt.setLong(4, depth+1);
				pstmt.setLong(5, user_no);
				pstmt.setLong(6, group_no);
				
			}
			else {
				String sql = "INSERT INTO  "
						+ " board (title, contents, hit, reg_date, group_no, order_no, depth, user_no) "
						+ " select  ?, ?, 0, now(), ? , ?, ?, ? "
						+ " from board where no = ?";
				pstmt = connection.prepareStatement(sql);			
				
				pstmt.setString(1, title);
				pstmt.setString(2, contents);
				pstmt.setLong(3, group_no);
				pstmt.setLong(4, order_no);
				pstmt.setLong(5, depth+1);
				pstmt.setLong(6, user_no);
				pstmt.setLong(7, no);
				
			}	
			
			pstmt.executeUpdate();
			
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
		
	}
	
	
	public boolean HitUp(long no) {
		boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql =  " update board" 
						+ " set hit = hit + 1" 
						+ " where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	public int count() {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "select count(*)" + "	from board";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.0.146:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return connection;
	}
}