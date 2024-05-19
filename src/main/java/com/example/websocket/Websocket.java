package com.example.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Websocket {
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String GetList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return "list";
	}
}
