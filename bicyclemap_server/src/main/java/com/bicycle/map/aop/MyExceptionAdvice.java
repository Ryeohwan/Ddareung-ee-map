package com.bicycle.map.aop;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 동기로 하고싶으면 RestControllerAdvice
@RestControllerAdvice  // 비동기로 하고싶으면 
public class MyExceptionAdvice {
	
//	@ExceptionHandler(Exception.class)// 모든 익셉션이 발생했을 때 이렇게 하겠다.
//	public String a (Exception e){  
//		e.printStackTrace();
//		return	"fail";    // 이 리턴은 뷰 페이지로 가는 것이다.
//	}
	
	@ExceptionHandler(NullPointerException.class)// 모든 익셉션이 발생했을 때 이렇게 하겠다.
	public String a (NullPointerException e){  
		e.printStackTrace();
		return	"fail null";    // 이 리턴은 뷰 페이지로 가는 것이다.
	}
	
	@ExceptionHandler(java.sql.SQLSyntaxErrorException.class)// 모든 익셉션이 발생했을 때 이렇게 하겠다.
	public String a (java.sql.SQLSyntaxErrorException e){  
		e.printStackTrace();
		return	"fail sql";    // 이 리턴은 뷰 페이지로 가는 것이다.
	}
}