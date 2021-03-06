package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	private final ReservationService reservationService;
	
	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping
	public ResponseEntity<?> createReservation(@RequestBody @Valid Reservation reservation, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
	
		reservationService.createReservations(reservation);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getReservations(@RequestParam(name = "reservationEmail", required=false)String reservationEmail) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ReservationInfo> reservations = reservationService.getReservations(reservationEmail);
		
		resultMap.put("reservations", reservations);
		resultMap.put("size", reservations.size());
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@PutMapping("/{reservationId}")
	public ResponseEntity<?> updateCancelFlagByReservationInfoId(@PathVariable(name = "reservationId")Integer reservationId) {
		reservationService.updateCancelFlagByReservationInfoId(reservationId);
	
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
}