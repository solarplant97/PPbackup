package com.puppy.admin.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.puppy.admin.reservation.service.AdminReservationService;
import com.puppy.client.reservation.service.ReservationService;
import com.puppy.client.reservation.vo.ReservationVO;


@Controller
@RequestMapping(value="/")
public class AdminMainController {
	
	@Autowired
	private AdminReservationService reservationService;

	@RequestMapping("admin") //당일 예약 리스트
	public ModelAndView todayReservationList(@ModelAttribute ReservationVO param) {

		List<ReservationVO> list = reservationService.todayReservationList(param);

		ModelAndView mav = new ModelAndView();
		mav.addObject("reservationList", list);
		mav.setViewName("admin/main/mainPage");

		return mav;
	}
	
}
