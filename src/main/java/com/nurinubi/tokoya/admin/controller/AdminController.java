package com.nurinubi.tokoya.admin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nurinubi.tokoya.admin.repository.AdminRepository;
import com.nurinubi.tokoya.common.CommandMap;
import com.nurinubi.tokoya.reservation.repository.ReservationRepository;

/**
* @Class Name : AdminController.java.java
* @Description :  BoardController.java Class
* @Modification Information
* @ 
* @	修正日			修正者		修正内容
* @ 	---------		---------		-------------------------------
* @ 	2017.07.12		Kim		最初作成
* 
* @author Kim
* @since 2017.07.12
* @version 0.1
*
*  Copyright (C) by NuriNubi All right reserved.
*/

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	public String time[]= {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	/** WriteService */
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping(value = "/admin", method = {RequestMethod.POST, RequestMethod.GET})
	public String adminHome(Model model) throws Exception {
		List<Map<String, Object>> reservation = reservationRepository.getReservationListByToday();
		model.addAttribute("reservation", reservation);
		model.addAttribute("stylist", adminRepository.getStylistList());
		model.addAttribute("time", time);
		System.out.println(model);
		return "/admin/admin";
	}
	
	@RequestMapping(value = "/searchReservation.do", method = RequestMethod.POST)
	public ModelAndView serchReservation(@RequestParam String param) throws Exception {
		System.out.println("------------------test----------------");
		System.out.println(param);
		ModelAndView mav = new ModelAndView(); 
		Date date = new Date();
		DateFormat frm = new SimpleDateFormat("yyyy/MM/dd");
		date = frm.parse(param);
		mav.addObject("reservation", reservationRepository.getReservationByDate(date));
		mav.addObject("stylist", adminRepository.getStylistList());
		mav.addObject("time", time);
		mav.setViewName("jsonView");
		System.out.println(mav);
		return mav;
	}
	/**
	 * スタイリスト追加画面：デフォルト
	 * @return
	 */
	@RequestMapping(value = "/admin/stylist/add", method = RequestMethod.GET)
	public String addStylist() {
		//表示するページ設定
		System.out.println("=====================");
		return "/admin/stylist/addStylist";
	}
	
	/**
	 * スタイリスト管理画面：デフォルト
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/stylist/management", method = RequestMethod.GET)
	public String management(Model model) {
		logger.info("Stylistmanagement");
		
        model.addAttribute("result", adminRepository.getStylistList());
		//表示するページ設定
		return "/admin/stylist/management";
	}
	/**
	 * スタイリスト管理画面：追加画面遷移
	 * @return
	 */
	@RequestMapping(value = "/stylistinsert", method = RequestMethod.POST, params = "stylistinsert")
	public String stylistinsert() {
		logger.info("addStylist");
		
		//表示するページ設定
		return "/admin/stylist/addStylist";
	}
	
	/**
	 * スタイリスト追加画面：管理画面遷移
	 * @return
	 */
	@RequestMapping(value = "/addstylistform", method = RequestMethod.POST, params = "cansel")
	public String stylistadd() {
		logger.info("addStylist");
		
		//表示するページ設定
		return "/admin/stylist/management";
	}
	
	// スタイリストの追加処理
	@RequestMapping(value = "/admin/stylist/insertWrite.do", method = RequestMethod.POST)
	public ModelAndView insertWrite(CommandMap cmdMap)
			throws Exception {
		logger.info("スタイリストの追加処理");
		ModelAndView mv = new ModelAndView("/admin");
		adminRepository.insertStylist(cmdMap.getMap());
		mv.setViewName("redirect:/admin");
		return mv;
	}
}
