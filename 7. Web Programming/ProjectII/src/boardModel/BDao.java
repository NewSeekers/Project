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
	public void write(String user_Id, String title, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into COMMUNITY(community_num, user_Id, title, content, hit, group_num, step_num, indent_num) values(COMMUNITY_seq.nextval,?,?,?,0,COMMUNITY_seq.currval,0,0)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user_Id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
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
			String query = "SELECT community_num, user_Id, title, content, date_created, hit, group_num, step_num, indent_num " + 
					"FROM (" + 
					"    SELECT community_num, user_Id, title, content, date_created, hit, group_num, step_num, indent_num ," + 
					"           ROW_NUMBER() OVER (ORDER BY group_num DESC, step_num ASC) AS rnum " + 
					"    FROM COMMUNITY ) " + 
					" WHERE rnum BETWEEN ? AND ?";
					System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
				int community_num = rs.getInt("community_num");
				String title = rs.getString("title");
				String user_Id = rs.getString("user_Id");
				String content = rs.getString("content");
				Timestamp dbDate = rs.getTimestamp("date_created");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String date_created = simpleDate.format(dbDate);
				int hit = rs.getInt("hit");
				int group_num = rs.getInt("group_num");
				int step_num = rs.getInt("step_num");
				int indent_num = rs.getInt("indent_num");
				
				BDto dto = new BDto(community_num, user_Id, title, content, date_created, hit, group_num, step_num, indent_num);
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
	public ArrayList<BDto> indexList(){
		ArrayList<BDto> dtos = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String query = "SELECT * FROM ( SELECT * FROM COMMUNITY ORDER BY community_num DESC " + 
					") WHERE ROWNUM <= 5";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BDto dto = new BDto();
				int id = rs.getInt("community_num");
				String title = rs.getString("title");
				String user = rs.getString("user_Id");
				Timestamp dbDate = rs.getTimestamp("date_created");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String date_created = simpleDate.format(dbDate);
				
				dto.setCommunity_num(id);
				dto.setTitle(title);
				dto.setUser_Id(user);
				dto.setDate_created(date_created);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
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
			String query = "select * from COMMUNITY where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int community_num = rs.getInt("community_num");
				String user_Id = rs.getString("user_Id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp dbDate = rs.getTimestamp("date_created");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String date_created = simpleDate.format(dbDate);
				int hit = rs.getInt("hit");
				int group_num = rs.getInt("group_num");
				int step_num = rs.getInt("step_num");
				int indent_num = rs.getInt("indent_num");
				
				dto = new BDto (community_num, user_Id, title, content, date_created, hit, group_num, step_num, indent_num);
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
	
	private void upHit(String community_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update COMMUNITY set hit = hit + 1 where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, community_num);
			
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
	
	public int modify(String community_num, String title, String content) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = dataSource.getConnection();
			String query = "update COMMUNITY set content=?, title=? where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, title);
			pstmt.setInt(3, Integer.parseInt(community_num));
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
	
	public BDto getPostById(String community_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto post = null;
		try {
			con = dataSource.getConnection();
			String query = "select * from COMMUNITY where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, community_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post = new BDto();
				post.setCommunity_num(rs.getInt("community_num"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setUser_Id(rs.getString("user_Id"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public void delete(String community_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "delete from COMMUNITY where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(community_num));
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
			String query ="select * from COMMUNITY where community_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int community_num = rs.getInt("community_num");
				String user_Id = rs.getString("user_Id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp dbDate = rs.getTimestamp("date_created");
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd"); 
				String date_created = simpleDate.format(dbDate);
				int hit = rs.getInt("hit");
				int group_num = rs.getInt("group_num");
				int step_num = rs.getInt("step_num");
				int indent_num = rs.getInt("indent_num");
				
				dto = new BDto (community_num, user_Id, title, content, date_created, hit, group_num, step_num, indent_num);
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
	
	public void reply(String user_Id, String title, String content, String group_num, String step_num, String indent_num) {
		replyShape(group_num, step_num);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into COMMUNITY(community_num, user_Id, title, content, group_num, step_num, indent_num) "+ 
					" values (COMMUNITY_seq.nextval,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, user_Id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, Integer.parseInt(group_num));
			pstmt.setInt(5, Integer.parseInt(step_num)+1);
			pstmt.setInt(6, Integer.parseInt(indent_num)+1);
			
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
			String query = "update COMMUNITY set step_num = step_num+1 where group_num=? and step_num >?";
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
	
	//list의 총 게시글 수
	public int getLIstSize() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int size = 0;
		try {
			con = dataSource.getConnection();
			String query = "select count(*) from COMMUNITY";
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
