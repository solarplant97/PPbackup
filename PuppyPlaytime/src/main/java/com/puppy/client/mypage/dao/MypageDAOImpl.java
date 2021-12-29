package com.puppy.client.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppy.admin.room.vo.CageRoomVO;
import com.puppy.client.member.vo.MemberVO;
import com.puppy.client.reservation.vo.ReservationVO;
import com.puppy.common.vo.PetVO;

@Repository
public class MypageDAOImpl implements MypageDAO{
private static final String namespace="query.mypage";
	
	@Autowired
	private SqlSession session;
	//private static String namespace = "com.puppy.client.mypage.dao.MypageDAO";
	//펫리스트 구현
	@Override
	public List<PetVO> petList(String m_id) {
		return session.selectList("petList",m_id);
	}

	//펫등록 구현
	@Override
	public int petInsert(PetVO pvo) {
		return session.insert(namespace+".petInsert",pvo);
	}
	
	//펫수정 구현
	@Override
	public int petUpdate(PetVO pvo) {
		return session.update("petUpdate",pvo);
	}
	
	//펫삭제 구현
	@Override
	public int petDelete(int p_no) {
		return session.delete("petDelete", p_no);
	}

	//펫상세 구현
	@Override
	public PetVO petDetail(PetVO pvo) {
		return (PetVO)session.selectOne("petDetail",pvo);
		
		
	}

	//예약리스트 구현(리스트 정보)
	@Override
	public List<ReservationVO> reservationList() {
		return session.selectList("reservationList");
	}

	//예약리스트 구현(케이지 정보)
	@Override
	public List<CageRoomVO> cageList() {
		return session.selectList("cageList");
	}
	
	//내정보 구현
	@Override
	public MemberVO myDetail(MemberVO mvo) {
			return (MemberVO)session.selectOne("myDetail",mvo);
	}

	//내정보 수정 구현
	@Override
	public int myUpdate(MemberVO mvo) {
		return session.update("myUpdate",mvo);
	}

}
