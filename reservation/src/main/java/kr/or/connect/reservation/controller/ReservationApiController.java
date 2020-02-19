package kr.or.connect.reservation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	private final ReservationService reservationService;
	
	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping
	public ResponseEntity<?> reserve(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	
		reservationService.createReservation(reservation);
        return new ResponseEntity<>("success", HttpStatus.OK);
	}
}