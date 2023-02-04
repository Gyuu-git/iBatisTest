package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.LprodVO;

public class LprodIbatisTest {
	
	// iBatis를 이용하여 DB자료를 처리하는 방법 및 순서
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SqlMapClient smc = null;
		
		// 1. iBatis의 환경설정 파일을 읽어와서 실행한다. (sqlMapConfig.xml)
		try {
			// 1-1. 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경설정 파일 읽기
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제환경 설정을 완성한 후에
			//		SQL문을 호출해서 실행할 수 있는 객체를 생성한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();		// Reader객체 닫기
			
		} catch (Exception e) {
			System.out.println("iBatis 환경 설정 오류..");
			e.printStackTrace();
		}
		//------------------------------------------------------
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		try {
			/*
			// 2-1. INSERT 연습
			System.out.println("insert 작업 시작..");
			System.out.print("Lprod_id 입력 >> ");
			int num = sc.nextInt();
			
			System.out.print("Lprod_gu 입력 >> ");
			String gu = sc.next();
			
			System.out.print("Lprod_nm 입력 >> ");
			String nm = sc.next();
			
			// 1) 입력받은 자료를 VO객체에 저장한다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(num);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			// 2) sqlMapClient객체 변수(smc)를 이용하여 처리할 SQL문을 호출하여 실행한다.
			// 	형식) smc.insert("namespace속성값.id속성값", 파라미터클래스)
			//		=> 반환값 : insert성공 >> null, insert실패 >> 오류객체
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj == null) {
				System.out.println("insert 작업 성공");
			}else {
				System.out.println("insert 작업 실패");
			}
			System.out.println("----------------------------------------");
			
			
			// 2-2. UPDATE 연습
			System.out.println("update 시작");
			
			System.out.print("수정할 Lprod_gu 입력 >> ");
			String lgu = sc.next();
			
			System.out.print("새로운 Lprod_id 입력 >> ");
			int lid = sc.nextInt();
			
			System.out.print("새로운 Lprod_nm 입력 >> ");
			String lnm = sc.next();
			
			// 1) 입력받은 자료를 VO객체에 저장한다.
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(lid);
			lvo2.setLprod_gu(lgu);
			lvo2.setLprod_nm(lnm);
			
			// 2) 쿼리문 실행하기
			// 형식) smc.update("namespace속성값.id속성값", 파라미터클래스)
			//		=> 반환값 : 작업에 성공한 레코드 수
			int cnt = smc.update("lprod.updateLprod", lvo2);
			
			if(cnt > 0) System.out.println("update 작업 성공!");
			else System.out.println("update 작업 실패~");
			System.out.println("-------------------------------------");
			
			
			// 2-3. DELETE 연습
			System.out.println("delete 작업 연습...");
			
			System.out.print("삭제할 Lprod_gu 입력 >> ");
			String lgu2 = sc.next();
			
			// 1) 쿼리문 실행하기
			// 	형식) smc.delete("namespace속성값.id속성값", 파라미터클래스)
			//		=> 반환값 : 작업에 성공한 레코드 수
			int cnt2 = smc.delete("lprod.deleteLprod", lgu2);
			
			if(cnt2 > 0) System.out.println("delete 작업 성공!");
			else System.out.println("delete 작업 실패~");
			System.out.println("-------------------------------------");
			
			
			// 2-4. SELECT 연습
			
			// 1) SELECT한 결과가 여러개인 경우
			System.out.println("SELECT 연습 시작 (결과가 여러개일 경우)...");
			
			// SELECT한 결과가 여러개일 경우에는 queryForList()메서드를 사용하는데,
			// 이 메서드는 여러개의 레코드 각각을 VO에 담은 후 이 VO객체를 List에 추가해 주는
			// 작업을 자동으로 수행한다.
			// 형식) smc.queryForList("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 => 데이터가 저장된 List객체
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			
			for(LprodVO lvo3 : lprodList) {
				System.out.println("Lprod_id : " + lvo3.getLprod_id());
				System.out.println("Lprod_gu : " + lvo3.getLprod_gu());
				System.out.println("Lprod_nm : " + lvo3.getLprod_nm());
				System.out.println("--------------------------------");
			}
			System.out.println("출력 끝..");
			System.out.println("------------------------------------------------");
			*/
			
			// 2) SELECT한 결과가 1개일 경우
			System.out.println("SELECT 연습 시작 (결과가 1개일 경우)...");
			
			System.out.print("검색할 Lprod_gu 입력 >> ");
			String lgu3 = sc.next();
			
			// SELECT한 결과가 1개일 경우에는 queryForObject()메서드를 사용한다.
			// 형식) smc.queryForObject("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 : 1) 쿼리문의 결과가 여러개일 경우 => Exception객체
			//				 2) 쿼리문의 결과가 1개일 경우 	=> 검색한 객체 (정상)
			//				 3) 쿼리문의 결과가 없을 경우 	=> null
			LprodVO lprodVo = (LprodVO) smc.queryForObject("lprod.getLprod", lgu3);
			
			if(lprodVo == null) {
				System.out.println("검색한 결과가 없습니다.");
				return;
			}
			System.out.println(" -- 검색 결과 --");
			System.out.println("Lprod_id : " + lprodVo.getLprod_id());
			System.out.println("Lprod_gu : " + lprodVo.getLprod_gu());
			System.out.println("Lprod_nm : " + lprodVo.getLprod_nm());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
