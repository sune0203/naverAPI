package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DB.DB;
import DTO.*;
public class SearchDAO {

	public void insert(UserDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.conn();
			String sql = "INSERT INTO napi (title, originallink, link, description) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 데이터 binding
			//꺼냄
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getOriginallink());
			pstmt.setString(3, dto.getLink());
			pstmt.setString(4, dto.getDescription());
			
			int count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("데이터 입력 실패");
			} else {
				System.out.println("데이터 입력 성공");
			}

		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<UserDTO> select() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 전달 변수(dto 담을 그릇)
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		try {
			conn = DB.conn();
			String sql = "SELECT * FROM napi";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setTitle(rs.getString("title"));
				dto.setOriginallink(rs.getString("originallink"));
				dto.setLink(rs.getString("link"));
				dto.setDescription(rs.getString("description"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if( rs != null && !rs.isClosed()){
                    rs.close();
                }
				if( pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
