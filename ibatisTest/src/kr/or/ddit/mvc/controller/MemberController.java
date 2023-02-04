package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private Scanner sc;
	private IMemberService service;		// Servicec객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
//		service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new MemberController().startMemeber();
	}
	
	// 시작 메서드
	public void startMemeber() {
		System.out.println();
		System.out.println("**************************************");
		System.out.println("    회  원  관  리  프  로  그  램");
		System.out.println("**************************************");
		while(true) {
			int input = displayMenu();
			switch(input) {
			case 1:		// 추가
				insertMember();
				break;
			case 2:		// 삭제
				deleteMember();
				break;
			case 3:		// 수정
				updateMember();
				break;
			case 4:		// 전체 자료 출력
				displayMember();
				break;
			case 5:		// 수정2
				updateMember2();
				break;
			case 0:		// 작업 끝
				System.out.println("회원 관리 프로그램을 마칩니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.\n");
			}
		}
	}
		
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = sc.next();
		
		int cnt = service.getMemberCount(memId);
		if(cnt == 0) {
			System.out.println(memId + "은(는) 등록되지 않은 회원입니다.");
			System.out.println("수정 작업을 마칩니다..");
			return;
		}
		
		// 수정할 항목을 선택하기
		int num;						// 수정할 항목의 선택 번호
		String updateField = null;		// 수정할 컬럼명이 저장될 변수
		String updateTitle = null;		// 입력할 때 출력할 제목이 저장될 변수
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
			System.out.println("----------------------------------------------");
			System.out.print("수정할 항목 선택 >> ");
			num = sc.nextInt();
			
			switch(num) {
			case 1: updateField = "mem_pass"; updateTitle = "비밀번호"; break;
			case 2: updateField = "mem_name"; updateTitle = "회원이름"; break;
			case 3: updateField = "mem_tel"; updateTitle = "전화번호"; break;
			case 4: updateField = "mem_addr"; updateTitle = "회원주소"; break;
			default: 
				System.out.println("수정할 항목 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.\n");
			}
		}while(num < 1 || num > 4);
		
		sc.nextLine();	// 입력 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = sc.nextLine();
		
		// 만들어진 데이터를 저장할 Map객체 생성
		Map<String, String> pMap = new HashMap<>();
		
		// 데이터를 Map에 추가
		pMap.put("field", updateField);
		pMap.put("data", updateData);
		pMap.put("memid", memId);
		
		cnt = service.updateMember2(pMap);
		
		if(cnt > 0) System.out.println("수정 성공");
		else System.out.println("수정 실패");
	}

	private void displayMember() {
		List<MemberVO> memList = service.getAllMember();	// 전체 목록 가져오기
		
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("  ID    비밀번호      이름    전화번호    주소");
		System.out.println("--------------------------------------------------------");
		if(memList == null || memList.size() == 0) {
			System.out.println("등록된 회원 정보가 하나도 없습니다.");
		}else {
			for(MemberVO mvo : memList) {
				String memId = mvo.getMem_id();
				String memPass = mvo.getMem_pass();
				String memName = mvo.getMem_name();
				String memTel = mvo.getMem_tel();
				String memAddr = mvo.getMem_addr();
				
				System.out.println(memId + "    " + memPass + "    " + memName + "    " + memTel + "    " + memAddr);
			}
		}
		System.out.println("--------------------------------------------------------");
	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = sc.next();
		
		int cnt = service.getMemberCount(memId);
		if(cnt == 0) {
			System.out.println(memId + "은(는) 등록되지 않은 회원입니다.");
			System.out.println("수정 작업을 마칩니다..");
			return;
		}
		
		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = sc.next();
		System.out.print("새로운 회원이름 >> ");
		String newMemName = sc.next();
		System.out.print("새로운 전화번호 >> ");
		String newMemTel = sc.next();
		
		sc.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String newMemAddr = sc.nextLine();
		
		// 수정할 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		
		cnt = service.updateMember(memVo);
		if(cnt > 0) System.out.println(memId + " 회원 정보 수정 성공");
		else System.out.println(memId + " 회원 정보 수정 실패");
	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = sc.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt > 0) System.out.println(memId + " 회원 정보 삭제 완료");
		else System.out.println(memId + " 회원은 없는 회원이거나 삭제에 실패했습니다.");
	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int cnt = 0;	// 회원ID의 개수가 저장될 변수
		String memId;	// 회원ID가 저장될 변수
		do {
			System.out.print("회원ID >> ");
			memId = sc.next();
			cnt = service.getMemberCount(memId);
			if(cnt > 0) {
				System.out.println(memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요..\n");
			}
		}while(cnt > 0);
		
		System.out.print("비밀번호 >> ");
		String memPass = sc.next();
		
		System.out.print("이름 >> ");
		String memName = sc.next();
		
		System.out.print("전화번호 >> ");
		String memTel = sc.next();
		
		sc.nextLine();	// 입력 버퍼 비우기
		System.out.print("주소 >> ");
		String memAddr = sc.next();
		
		// MemberVO객체에 입력한 데이터를 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		Object obj = service.insertMember(memVo);
		if(obj == null) System.out.println(memId + " 회원 정보 추가 완료");
		else System.out.println(memId + " 회원 정보 추가 실패");
	}

	// 메뉴를 출력하고 입력한 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println(" == 작업 선택 ==");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정2");
		System.out.println("0. 작업 끝.");
		System.out.println("----------------");
		System.out.print(">> ");
		return sc.nextInt();
	}
}
