package com.puppy.admin.room.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.puppy.admin.room.service.AdminRoomService;
import com.puppy.admin.room.vo.AdminRoomVO;

@Controller
@RequestMapping(value ="/room")
public class AdminRoomController {
	private static final String CONTEXT_PATH = "admin/room";
	
	@Autowired
	private AdminRoomService adminRoomService;
	
	@RequestMapping("/roomList")//케이지 리스트
	public ModelAndView roomList(@ModelAttribute AdminRoomVO param) {
	
		List<AdminRoomVO> list = adminRoomService.roomList(param);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("roomList", list);
		mav.setViewName(CONTEXT_PATH+"/adminRoomList");
		
		return mav;
	}
	
	@RequestMapping("/roomDetail")//케이지 상세 정보
	public ModelAndView roomDetail(@RequestParam("no") int no) {
		
		ModelAndView mav = new ModelAndView();
		
		if(no == 0) {
			mav.addObject("mode", "insert");
		}else {
			mav.addObject("AdminRoomVO", adminRoomService.roomDetail(no));
			mav.addObject("mode", "update");
		}
		
		mav.setViewName(CONTEXT_PATH+"/adminRoomDetail");
		
		return mav;
	}
	
	@RequestMapping("/roomAdd") //케이지 등록
	public ModelAndView roomAdd(@ModelAttribute AdminRoomVO param) {
		String resultStr="";
		int result = adminRoomService.roomAdd(param);
		
		if(result > 0) {
			resultStr = "케이지 등록이 완료되었습니다.";
		}else {
			resultStr = "케이지 등록이 문제가 있어 완료하지 못하였습니다.";	
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", resultStr);
		mav.setViewName("/result");
		
		return mav;
	}
	
	@RequestMapping("/roomDisabled") //케이지 비활성화
	public ModelAndView roomDisabled(@ModelAttribute AdminRoomVO param) {
		String resultStr="";
		int result = adminRoomService.roomDisabled(param);
		
		if(result > 0) {
			resultStr = "케이지 비활성화가 완료되었습니다.";
		}else {
			resultStr = "케이지 비활성화가 문제가 있어 완료하지 못하였습니다.";	
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", resultStr);
		mav.setViewName("/result");
		
		return mav;
	}
}
