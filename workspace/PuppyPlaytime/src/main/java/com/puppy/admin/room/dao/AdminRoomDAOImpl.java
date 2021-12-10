package com.puppy.admin.room.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.puppy.admin.room.vo.AdminRoomVO;

@Repository
public class AdminRoomDAOImpl implements AdminRoomDAO {

	@Inject
	private SqlSession SqlSession;
	
	private static final String namespace = "com.puppy.admin.room.dao.AdminRoomDAO";
	
	@Override
	public List<AdminRoomVO> roomList(AdminRoomVO param) {
		// TODO Auto-generated method stub
		return SqlSession.selectList(namespace+".roomList",param);
	}

	@Override
	public Object roomDetail(int no) {
		// TODO Auto-generated method stub
		return SqlSession.selectOne(namespace+".roomDetail",no);
	}

	@Override
	public int roomAdd(AdminRoomVO param) {
		// TODO Auto-generated method stub
		return SqlSession.insert(namespace+".roomAdd",param);
	}

	@Override
	public int roomDisabled(AdminRoomVO param) {
		// TODO Auto-generated method stub
		return SqlSession.update(namespace+".roomDisabled",param);
	}

}
