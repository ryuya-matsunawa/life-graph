package com.lifegraph.team20.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//JPAっていうのがあるらしい

//@CrossOriginの参考サイト
//https://qiita.com/m_kikuchi/items/df8c070b228df44a5462#spring-mvc-%E3%81%AE-cross-origin-resource-sharing-%E8%A8%AD%E5%AE%9A
//HTTPリクエストについて
//https://webtan.impress.co.jp/e/2010/01/12/7156
//HTTP
//https://wa3.i-3-i.info/word165.html
@CrossOrigin(origins = "*", maxAge = 3600)
//@RestControllerの参考サイト
//https://qiita.com/TEBASAKI/items/267c261db17f178e33eb#requestmapping
@RestController
//@RequestMappingの参考サイト
//https://qiita.com/TEBASAKI/items/267c261db17f178e33eb#requestmapping
@RequestMapping("/api/test")
public class GraphController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}