package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.LprodVO;

/*
	jdbcTest프로젝트에 있는 JdbcTest05.java에서 작성한 기능을
	ibatis를 이용하여 재작성 하시오.
*/
public class JdbcToIbatisTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		SqlMapClient smc = null;
//		
//		try {
//			Charset charset = Charset.forName("UTF-8");
//			Resources.setCharset(charset);
//			
//			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapConfig.xml");
//			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//			
//			rd.close();
//		} catch (Exception e) {
//			System.out.println("iBatis 환경 설정 오류..");
//			e.printStackTrace();
//		}
		//--------------------------------------------------
		try {
			SqlMapClient smc = SqlMapClientFactory.getSqlMapClient();
			
			int id = (int) smc.queryForObject("jdbc.getMaxId") + 1;
			
			String gu = "";
			
			while(true) {
				System.out.print("LPROD_GU >> ");
				gu = sc.next().toUpperCase();
				
				LprodVO lvo = (LprodVO) smc.queryForObject("jdbc.checkLprod", gu);
				
				if(lvo == null) break;
				else {
					System.out.println("이미 존재하는 값입니다.");
					System.out.println("다시 입력해 주세요.\n");
				}
			}
			
			System.out.print("LPROD_NM >> ");
			String nm = sc.next();
			
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(id);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			Object obj = smc.insert("jdbc.insertLprod", lvo);
			if(obj == null) {
				System.out.println("등록 성공");
			}else {
				System.out.println("등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
