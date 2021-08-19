package com.mclgyh.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 牟春雷
 * @Description:
 * @Date: 16:47 2021/8/20
 * @Modified By:
 **/
@Controller
public class MySpringMvcTest {


	@GetMapping("/getName")
	public String getName(){
		return "12312312";
	}


	public static void main(String[] args) {

	}

}
