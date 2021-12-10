package com.puppy.admin.room.service;

import java.util.List;

import com.puppy.admin.room.vo.AdminRoomVO;

public interface AdminRoomService {

	public List<AdminRoomVO> roomList(AdminRoomVO param);

	public Object roomDetail(int no);

	public int roomAdd(AdminRoomVO param);

	public int roomDisabled(AdminRoomVO param);

}
