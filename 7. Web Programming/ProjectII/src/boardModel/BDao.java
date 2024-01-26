package boardModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDao {
	DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void write(String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public ArrayList<BDto> list(int currentPage){
		System.out.println("BDAO : list Method 실행");
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int showListNum = 10;
		try {
			int startRow = (currentPage -1) *showListNum + 1 ;
			int endRow = startRow + showListNum -1;
			con = dataSource.getConnection();
			String query = "SELECT bId, bTitle, bContent, bDate, bHit, bName, bGroup, bStep, bIndent\r\n" + 
					"FROM (\r\n" + 
					"    SELECT bId, bTitle, bContent, bDate, bHit, bName, bGroup, bStep, bIndent,\r\n" + 
					"           ROW_NUMBER() OVER (ORDER BY bId DESC) AS rnum\r\n" + 
					"    FROM MVC_BOARD\r\n" + 
					") \r\n" + 
					"WHERE rnum BETWEEN ? AND ?";
					System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				int bId = rs.getInt("bId");
				String bTitle = rs.getString("bTitle");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				Timestamp dbDate = rs.getTimestamp("bDate");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String bDate = simpleDate.format(dbDate);
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BDto contentView(String strID) {
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp dbDate = rs.getTimestamp("bDate");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String bDate = simpleDate.format(dbDate);
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int modify(String bId, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bContent=?, bTitle=? where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bContent);
			pstmt.setString(2, bTitle);
			pstmt.setInt(3, Integer.parseInt(bId));
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from mvc_board where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public BDto reply_view(String str) {
		BDto dto = new BDto();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String query ="select * from mvc_board where bId=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bId = rs.getInt("bId");
				String bTitle = rs.getString("bTitle");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				Timestamp dbDate = rs.getTimestamp("bDate");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String bDate = simpleDate.format(dbDate);
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				System.out.println("bGroup : "+bGroup);
				System.out.println("bStep : "+bStep);
				System.out.println("bIndent : "+bIndent);
				
				dto = new BDto (bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public void reply(String bId, String bTitle, String bName, String bContent, String bGroup, String bStep, String bIndent) {
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board(bId, bTitle,  bName, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bName);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			
			int rn = pstmt.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void replyShape(String strGroup,String strStep) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep+1 where bGroup=? and bStep>?";
			//그룹은 같고 원래 글(step)보다 큰 것들의 step수를 하나씩 증가
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn =pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public int getLIstSize() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int size = 0;
		try {
			con = dataSource.getConnection();
			String query = "select count(*) from MVC_BOARD";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				size=rs.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return size;
	}
	
	
}
