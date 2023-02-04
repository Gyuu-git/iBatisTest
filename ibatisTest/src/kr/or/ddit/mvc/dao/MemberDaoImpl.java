package kr.or.ddit.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDAO {
	private static MemberDaoImpl instance;
	private MemberDaoImpl() {}
	public static MemberDaoImpl getInstance() {
		if(instance == null) instance = new MemberDaoImpl();
		return instance;
	}
	private SqlMapClient smc;

	@Override
	public Object insertMember(MemberVO memVo) {
		Object obj = null;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			obj = smc.insert("mymember.insertMember", memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return obj;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;	// 반환값이 저장될 변수 선언
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = smc.delete("mymember.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;	// 반환값이 저장될 변수 선언
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = smc.update("mymember.updateMember", memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = null;		// 반환값이 저장될 변수
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			memList = smc.queryForList("mymember.getAllMember");
		} catch (SQLException e) {
			memList = null;
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int cnt = 0;	// 반환값이 저장될 변수
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = (int) smc.queryForObject("mymember.checkId", memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		int cnt = 0;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = smc.update("mymember.updateMember2", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		
		return cnt;
	}

}
