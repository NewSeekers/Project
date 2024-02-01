package memberModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import memberModel.MemberDto;

public class MemberDao {
	DataSource dataSource;
	private static MemberDao instance=new MemberDao();
	
	private MemberDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int insertMember(MemberDto dto) {
		int ri=0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query ="insert into members values(?,?,?,?,?,?)";
		
		try {
//			connection = getConnection();
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			System.out.println("pstmt:"+pstmt);
			pstmt.executeUpdate();
			ri=1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int confirmId(String id) {
		int ri=0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query ="select id from members where id=?";
		
		try {
//			connection = getConnection();
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri=1;
			}else {
				ri=0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	public int userCheck(String id,String pw) {
		int ri=0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set =null;
		String query = "select pw from members where id=?";
		System.out.println("유저 체크");
		
		try {
			/* connection=getConnection(); */
			connection = dataSource.getConnection();
			pstmt=connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				  System.out.println("DB에서 가져온 비밀번호: " + dbPw);
				if(dbPw.equals(pw)) {
					System.out.println("if 비밀번호 값"+dbPw);
					ri=1;
				}else {
					 System.out.println("else 비밀번호 값");
					ri=0;
				 } 
				
				}else {
					System.out.println("해당 ID의 사용자가 없다.");
					ri=-1;
				}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public MemberDto getMember(String id) {
		
		Connection connection = null;
		PreparedStatement pstmt =null;
		ResultSet set = null;
		String query = "select * from members where id=?";
		MemberDto dto=null;
		
		try {	
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1,id);
			set=pstmt.executeQuery();
		
			  // 로그 추가: 조회 쿼리와 파라미터 값 출력
	        System.out.println("조회 쿼리: " + query);
	        System.out.println("파라미터 값: " + id);

			
			if(set.next()){
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
				
				 // 로그 추가: 조회된 회원 정보 출력
	            System.out.println("회원정보 출력: " + dto.getId());
	            
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try {
					set.close();
					pstmt.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		System.out.println("check dto Dao :: " + dto);
		return dto;
//			try {
//			}catch(Exception e2) {
//				e2.printStackTrace();
//			}
		
	}
	
	public int updateMember(MemberDto dto) {
		int ri=0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query ="update members set pw=?,eMail=?,address=? where id=?";
		System.out.println("수정하려고 modify에서 요청 들어옴");
			try {
//				connection=getConnection();
				connection = dataSource.getConnection();
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1,dto.getPw());
				pstmt.setString(2,dto.geteMail());
				pstmt.setString(3,dto.getAddress());
				pstmt.setString(4,dto.getId());
				ri=pstmt.executeUpdate();
			}catch(Exception e2) {
			e2.printStackTrace();
		  }finally{
			try {
				pstmt.close();
				connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
			return ri;
	}
	

//	public ArrayList<MemberDto> membersAll() {
//		ArrayList<MemberDto>dtos=new ArrayList<MemberDto>();
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "select * from members";
//		
//		try {
////			connection = getConnection();
//			pstmt = connection.prepareStatement(query);
//			rs=pstmt.executeQuery();
//			
//			System.out.println("===========");
//			while(rs.next()) {
//				MemberDto dto = new MemberDto();
//				dto.setId(rs.getString("id"));
//				dto.setPw(rs.getString("pw"));
//				dto.setName(rs.getString("name"));
//				dto.seteMail(rs.getString("eMail"));
//				dto.setrDate(rs.getTimestamp("rDate"));
//				dto.setAddress(rs.getString("address"));
//				dtos.add(dto);
//			}
//			System.out.println("-----------------");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//				connection.close();
//			}catch(Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return dtos;
//	}
	
//	private Connection getConnection() {
//		Context context = null;
//		DataSource dataSource = null;
//		Connection connection = null;
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
//			connection = dataSource.getConnection();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}

}
