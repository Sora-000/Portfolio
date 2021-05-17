package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBUtils;
import vo.LentVO;
import vo.MemberVO;

public class BookDAO {
	public ArrayList<MemberVO> getMemberList(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select custno, custname, joindate, decode(grade,'A','VIP','B','일반','C','직원') grade, address\r\n" + 
					"from mem_tbl_book");
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setAddress(rs.getString("address"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	public ArrayList<LentVO> getRentList(){
		ArrayList<LentVO> list = new ArrayList<LentVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select m.custno,custname,count(*) cnt\r\n"
					+ "from mem_tbl_book m, rent_tbl_book r\r\n"
					+ "where m.custno = r.custno\r\n"
					+ "group by (m.custno,custname)\r\n"
					+ "order by cnt desc, m.custno asc");
			rs = ps.executeQuery();
			while(rs.next()) {
				LentVO vo = new LentVO();
				vo.setNo(rs.getInt("custno"));
				vo.setName(rs.getString("custname"));
				vo.setCnt(rs.getInt("cnt"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	public ArrayList<LentVO> getBookList(){
		ArrayList<LentVO> list = new ArrayList<LentVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select bookno, count(*) cnt\r\n"
					+ "from rent_tbl_book\r\n"
					+ "group by bookno\r\n"
					+ "order by cnt desc, bookno asc");
			rs = ps.executeQuery();
			while(rs.next()) {
				LentVO vo = new LentVO();
				vo.setNo(rs.getInt("bookno"));
				vo.setCnt(rs.getInt("cnt"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		return list;
	}
	public int getMaxNo() {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select max(custno)+1 max from mem_tbl_book");
			rs = ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("max");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		
		return n;
	}
	public int MemberInsert(MemberVO vo) throws SQLException {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("insert into mem_tbl_book values(?,?,?,?,?)");
			ps.setInt(1, vo.getCustno());
			ps.setString(2, vo.getCustname());
			ps.setDate(3, vo.getJoindate());
			ps.setString(4, vo.getGrade());
			ps.setString(5, vo.getAddress());
			n = ps.executeUpdate();
			
			if(n > 0) {
				conn.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			DBUtils.close(conn, ps);
		}
		return n;
	}
	public MemberVO getMember(int custno) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO vo = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("select custno, custname, grade, joindate, address from mem_tbl_book where custno = ?");
			ps.setInt(1, custno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setGrade(rs.getString("grade"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
				DBUtils.close(conn, ps, rs);
		}
		return vo;
	}
	public int MemberUpdata(MemberVO vo) throws SQLException {
		int n = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement("Update mem_tbl_book set custname=?,joindate=?,grade=?,address=? where custno=?");
			ps.setString(1, vo.getCustname());
			ps.setDate(2, vo.getJoindate());
			ps.setString(3, vo.getGrade());
			ps.setString(4, vo.getAddress());
			ps.setInt(5, vo.getCustno());
			n = ps.executeUpdate();
			
			if(n > 0) {
				conn.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			DBUtils.close(conn, ps);
		}
		return n;
	}
}
