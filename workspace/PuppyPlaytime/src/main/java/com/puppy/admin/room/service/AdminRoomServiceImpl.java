package com.puppy.admin.room.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppy.admin.room.dao.AdminRoomDAO;
import com.puppy.admin.room.vo.AdminRoomVO;


@Service
@Transactional
public class AdminRoomServiceImpl implements AdminRoomService {

	@Autowired
	private AdminRoomDAO adminRoomDAO;
	
	@Override
	public List<AdminRoomVO> roomList(AdminRoomVO param) {
		// TODO Auto-generated method stub
		List<AdminRoomVO> list = new ArrayList<AdminRoomVO>();
		list = adminRoomDAO.roomList(param);
		return list;
	}

	@Override
	public Object roomDetail(int no) {
		// TODO Auto-generated method stub
		return adminRoomDAO.roomDetail(no);
	}

	@Override
	public int roomAdd(AdminRoomVO param) {
		// TODO Auto-generated method stub
		return adminRoomDAO.roomAdd(param);
	}

	@Override
	public int roomDisabled(AdminRoomVO param) {
		// TODO Auto-generated method stub
		return adminRoomDAO.roomDisabled(param);
	}

}
