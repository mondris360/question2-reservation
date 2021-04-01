package com.mondris.question2.demo.Controller;

import com.mondris.question2.demo.Dto.ReservationReqDto;
import com.mondris.question2.demo.Service.ReservationService;
import com.mondris.question2.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/")
public class ReservationController {

  @Resource
  private ReservationService reservationService;

    @GetMapping("reservations")
    public ResponseEntity<ApiResponse> getAllReservations(){

        return reservationService.getAllReservations();
    }

  @PostMapping("reservations")
  public ResponseEntity<ApiResponse> createReservation(@Valid @RequestBody ReservationReqDto request){

    return reservationService.createReservation(request);

  }

  @GetMapping("reservations/{id}")
  public ResponseEntity<ApiResponse> getReservationById(@PathVariable int id){

    return reservationService.getReservationById(id);

  }

  @DeleteMapping("reservations/{id}")
  public ResponseEntity<ApiResponse> deleteReservationById(@PathVariable int id){

    return reservationService.deleteReservationById(id);

  }
}
