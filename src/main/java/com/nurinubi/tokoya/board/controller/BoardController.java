package com.nurinubi.tokoya.board.controller;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nurinubi.tokoya.board.repository.BoardRepository;
import com.nurinubi.tokoya.common.CommandMap;

/**
 * @Class Name : BoardController.java.java
 * @Description : BoardController.java Class
 * @Modification Information
 * @ @ 修正日 修正者 修正内容 @ --------- --------- ------------------------------- @
 *   2017.07.12 Kim 最初作成
 * 
 * @author Kim
 * @since 2017.07.12
 * @version 0.1
 *
 *          Copyright (C) by NuriNubi All right reserved.
 */

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardRepository boardRepository;

	// お知らせのホーム画面
	@RequestMapping(value = "/admin/board", method = RequestMethod.GET)
	public String listBoard(Model model) throws Exception {
		logger.info("お知らせのホーム画面(ユーザー)");
		model.addAttribute("result", boardRepository.getBoardList());
		return "/board/board";
	}

	// お知らせの作成画面
	@RequestMapping(value = "/admin/board/write", method = RequestMethod.GET)
	public String formWrite(CommandMap cmdMap) throws Exception {

		return "/board/write";
	}

	// お知らせの作成処理
	@RequestMapping(value = "/board/insertWrite.do", method = RequestMethod.GET)
	public ModelAndView insertWrite(CommandMap cmdMap)
			throws Exception {
		logger.info("お知らせの作成処理");
		ModelAndView mv = new ModelAndView("/home");
		Date date = new Date();
		cmdMap.put("REGISTERDATE", date);
		boardRepository.insertBoard(cmdMap.getMap());
		mv.setViewName("redirect:/admin/board");
		return mv;
	}

	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model) {

		return "/board/view";
	}
}
