package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@Autowired
	private HelloService helloservice;


	// GetMappping... localhost/helloへのGETを対応する
	@GetMapping("/hello")
	public String getHello() {
		System.out.println("getHeelooooだよ");
		// 文字列のhelloを返しているわけではない
		// template配下のファイルを返している。拡張子は不要
		return "hello";
	}

	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str, Model model) {
		model.addAttribute("sample", str);
		return "helloRequest";
	}

	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2") String str, Model model) {
		int id = Integer.parseInt(str);
		// 1件検索
		Employee employee = helloservice.findOne(id);

		// 検索結果をModelに登録
		model.addAttribute("id", employee.getEmployeeId());
		model.addAttribute("name", employee.getEmployeeName());
		model.addAttribute("age", employee.getAge());

		return "helloResponseDB";
	}


}
