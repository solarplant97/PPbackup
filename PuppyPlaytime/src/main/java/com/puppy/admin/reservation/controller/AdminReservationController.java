package com.puppy.admin.reservation.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.puppy.admin.extraservice.service.ExtraServiceService;
import com.puppy.admin.reservation.service.AdminReservationService;
import com.puppy.admin.room.service.CageRoomService;
import com.puppy.admin.room.vo.CageRoomVO;
import com.puppy.client.member.service.MemberService;
import com.puppy.client.member.vo.MemberVO;
import com.puppy.client.reservation.vo.ReservationVO;
import com.puppy.common.vo.ExtraServiceVO;



@Controller
@RequestMapping(value="/admin/reservation")
public class AdminReservationController {

	private static final String CONTEXT_PATH = "admin/reservation";

	@Autowired
	private AdminReservationService reservationService;
	
	@Autowired
	private ExtraServiceService extraServiceService;
	
	@Autowired
	private CageRoomService cageRoomService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("/newReservationList") //새로운 예약 리스트
	public ModelAndView newReservationList(@ModelAttribute ReservationVO param) {

		List<ReservationVO> list = reservationService.newReservationList(param);

		ModelAndView mav = new ModelAndView();
		mav.addObject("newReservationList", list);
		mav.setViewName(CONTEXT_PATH + "/newReservationList");

		return mav;
	}
	
	@RequestMapping("/reservationList") //예약 리스트
	public ModelAndView reservationList(@ModelAttribute ReservationVO param) {

		List<ReservationVO> list = reservationService.reservationList(param);

		ModelAndView mav = new ModelAndView();
		mav.addObject("reservationList", list);
		mav.setViewName(CONTEXT_PATH + "/reservationList");

		return mav;
	}
	
	@RequestMapping("/reservationDetail") // 예약 상세 정보
	public ModelAndView reservationDetail(@RequestParam("r_no") String no) {

		ModelAndView mav = new ModelAndView();
		ReservationVO rvo = reservationService.reservationDetail(Integer.parseInt(no));
		/* MemberVO mvo = memberService.memberDetail(rvo.getM_id()); */
		CageRoomVO cvo = cageRoomService.roomDetail(rvo.getC_no());
		List<ExtraServiceVO> list = extraServiceService.extraServiceDetail2(rvo.getC_no());
		
		
		mav.addObject("reservationVO", rvo);
		mav.addObject("extraServiceList", list);
		mav.addObject("cageRoomVO", cvo);
		mav.setViewName(CONTEXT_PATH + "/reservationDetail");
		return mav;
	}
	
	@RequestMapping("/reservationApproval") // 예약 승인/거부
	public String reservationApproval(@ModelAttribute ReservationVO param) {
		String resultStr = "";
		int result = reservationService.reservationApproval(param);

		if (result > 0) {
			resultStr = "승인여부 결정이 완료되었습니다.";
		} else {
			resultStr = "승인여부 결정이 문제가 있어 완료하지 못하였습니다.";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", resultStr);
		mav.setViewName(CONTEXT_PATH + "/reservationCancel");

		return "redirect:/admin/reservation/newReservationList";
	}
	
	@RequestMapping("/reservationCancel") // 예약 취소
	public String reservationCancel(@ModelAttribute ReservationVO param) {
		String resultStr = "";
		int result = reservationService.reservationCancel(param);

		if (result > 0) {
			resultStr = "예약 취소가 완료되었습니다.";
		} else {
			resultStr = "예약 취소가 문제가 있어 완료하지 못하였습니다.";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("result", resultStr);
		mav.setViewName(CONTEXT_PATH + "/reservationCancel");

		return "redirect:/admin/reservation/reservationList";
	}
	
	@RequestMapping("/sendMail") //거부사유 메일
	public String sendMail(HttpServletRequest request) throws Exception{
        
        String subject = ""; //제목
        subject = request.getParameter("subject");
        String content = ""; //내용
        content = request.getParameter("content");
        String from = "PuppyPlaytime<chan978@naver.com>";//보내는사람 이메일
        String to = "";//받는이 아이디@도메인주소
        to = request.getParameter("to");
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
            
            mailHelper.setFrom(from);

            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/reservation/reservationList";
	}
	
	
	@RequestMapping(value = "/mail") // 부가서비스 리스트
	public ModelAndView writeForm(@ModelAttribute ExtraServiceVO param) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName(CONTEXT_PATH + "/reservationMail");

		return mav;
	}
}
