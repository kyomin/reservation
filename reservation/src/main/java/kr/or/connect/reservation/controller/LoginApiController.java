package kr.or.connect.reservation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.login.Login;
import kr.or.connect.reservation.service.LoginService;

@RestController
@RequestMapping(path = "/api/login")
public class LoginApiController {
	private final LoginService loginService;
	
	public LoginApiController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid Login login, BindingResult bindingResult) throws Exception {
		// 이메일 형식을 서버에서 한 번 더 검증하여 잘못 되었을 때의 응답이다.
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		
		// 조회되는 이메일이 없는 경우이다.
		if(loginService.getLoginInfoCount(login.getReservationEmail()) == 0) {
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
}