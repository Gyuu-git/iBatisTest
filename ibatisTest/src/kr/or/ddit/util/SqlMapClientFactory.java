package kr.or.ddit.util;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	private static SqlMapClient smc = null;
	
	static {
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
	}
	
	public static SqlMapClient getSqlMapClient() {
		return smc;
	}
	
	// SqlMapClient객체가 필요한 곳에서
	// SqlMapClient aa = SqlMapClientFactory.getSqlMapClient()
}
