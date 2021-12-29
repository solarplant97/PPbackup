package com.puppy.client.mypage.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.puppy.admin.room.vo.CageRoomVO;
import com.puppy.client.member.vo.MemberVO;
import com.puppy.client.mypage.service.MypageService;
import com.puppy.client.reservation.vo.ReservationVO;
import com.puppy.common.file.FileUploadUtil;
import com.puppy.common.vo.PetVO;

@Controller
@RequestMapping(value = "/client/mypage")
public class MypageController {
	 
	private Logger log = LoggerFactory.getLogger(MypageController.class);
	
	
	@Autowired
	private MypageService mypageService;
	
	
	//펫리스트 구현하기
	@RequestMapping(value="/petList", method=RequestMethod.GET)
	public String petList(PetVO pvo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("petList 호출 성공");
		
		HttpSession session = request.getSession();
  		String m_id = (String)session.getAttribute("userId");
  		log.info("m_id = " + m_id);	
  		pvo.setM_id(m_id);
  		log.info("pvo.m_id = " + pvo.getM_id());
  	
		
		List<PetVO> petList = mypageService.petList(m_id);
		model.addAttribute("petList", petList);
		model.addAttribute("data");
		
		return "client/mypage/mypagePetinfo";
	}
	
	//펫등록 폼 출력하기
	@RequestMapping(value="/insertForm")
	public String insertForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("insertForm 호출 성공");
		
		return "client/mypage/mypagePetinfoinsert";
	}
	
	//펫등록 구현하기
	@RequestMapping(value = "/petInsert", method = RequestMethod.POST)
	public ModelAndView petInsert(PetVO pvo, @RequestPart(value = "file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("petInsert 호출 성공");
		
		String resultStr = "";

		String c_file = FileUploadUtil.fileUpload(file, request, "petImages");
		pvo.setP_picture(c_file);
		log.info(pvo.getP_picture());

		int result = mypageService.petInsert(pvo);

		ModelAndView mav = new ModelAndView();

		if (result > 0) {
			resultStr = "펫 등록이 완료되었습니다.";
		} else {
			resultStr = "펫 등록이 문제가 있어 완료하지 못하였습니다.";
		}
		mav.addObject("file", pvo.getP_picture());
		mav.addObject("result", resultStr);
		mav.setViewName("redirect:/client/mypage/petList");
		
		return mav;
	}
	
	//펫 상세보기 구현
	@RequestMapping(value="petDetail", method = RequestMethod.POST)
	public String petDetail(@ModelAttribute PetVO pvo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("PetDetail 호출 성공");
		
		log.info("p_no = " + pvo.getP_no());
			
		PetVO detail = new PetVO();
		detail = mypageService.petDetail(pvo);
			
		model.addAttribute("detail", detail);
		return "client/mypage/mypagePetinfodetail";
			 
	}
		
	//펫수정 폼 출력하기
	@RequestMapping(value="/updateForm")		
	public String updateForm(@ModelAttribute PetVO pvo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("updateForm 호출 성공");
		
		log.info("p_no = " + pvo.getP_no());
		
		PetVO updateData=new PetVO();
		updateData=mypageService.petDetail(pvo);
			
		model.addAttribute("updateData", updateData);
		return "client/mypage/mypagePetinfoupdate";
	}
	
	//펫수정 구현하기
	@RequestMapping(value="/petUpdate", method=RequestMethod.POST)
	public String petUpdate(@ModelAttribute PetVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("petUpdate 호출 성공");
		
		
		mypageService.petUpdate(pvo);
		
		return "redirect:/client/mypage/petList";
	}
	
	
	 //펫정보 삭제
    @RequestMapping(value = "/petDelete")
    public String petDelete(@ModelAttribute PetVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("petDelete 호출 성공");
        
        int result = 0;
        String url = "";
        
        result=mypageService.petDelete(pvo.getP_no());
        
        if(result==1) {
        	url="/client/mypage/petList";
        }else {
        	url="/client/mypage/petDetale?p_no="+pvo.getP_no();
        }
        return "redirect:"+url;
    }
    
	/*
	 * //예약리스트 구현하기
	 * 
	 * @RequestMapping(value="/reservationList", method=RequestMethod.GET) public
	 * String reservationList(Model model, Authentication authentication) throws
	 * Exception { log.info("reservationList 호출 성공");
	 * 
	 * }
	 */
  	
  	//내정보 구현하기
  	@RequestMapping(value="/myDetail", method = RequestMethod.GET)
  	public String myDetail(@ModelAttribute MemberVO mvo, Model model, HttpServletRequest request) throws Exception{
  		log.info("myDetail 호출 성공");
  		HttpSession session = request.getSession();
  		String m_id = (String)session.getAttribute("userId");
  		log.info("m_id = " + m_id);	
  		mvo.setM_id(m_id);
  		session.setAttribute("m_id", m_id);
  		MemberVO detail = new MemberVO();
  		detail=mypageService.myDetail(mvo);
  		
  		model.addAttribute("detail", detail);
  		return "client/mypage/mypageMyinfo";
  			 
  	}
  	
  //내정보 수정 폼 출력하기
  	@RequestMapping(value="/myUpdateForm")		
  	public String myUpdateForm(@ModelAttribute MemberVO mvo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
  		log.info("myUpdateForm 호출 성공");
  		HttpSession session = request.getSession();
  		String m_id = (String)session.getAttribute("userId");
  		log.info("m_id = " + m_id);	
  		mvo.setM_id(m_id);
  		session.setAttribute("m_id", m_id);
  		
  		MemberVO updateData=new MemberVO();
  		updateData = mypageService.myDetail(mvo);
  			
  		model.addAttribute("updateData", updateData);
  		return "client/mypage/mypageMyinfoupdate";
  	}
  	
  	//내정보 수정 구현하기
  	@RequestMapping(value="/myUpdate", method=RequestMethod.POST)
  	public String myUpdate(@ModelAttribute MemberVO mvo, HttpServletRequest request, HttpServletResponse response) throws Exception{
  		log.info("myUpdate 호출 성공");
  		HttpSession session = request.getSession();
  		String m_id = (String)session.getAttribute("userId");
  		log.info("m_id = " + m_id);	
  		mvo.setM_id(m_id);
  		session.setAttribute("m_id", m_id);
  		
  		mypageService.myUpdate(mvo);
  		
  		return "redirect:/client/mypage/myDetail";
  	}
  	
  
}
