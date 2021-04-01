package com.mondris.question2.demo.Service;

import com.mondris.question2.demo.Dto.ReservationReqDto;
import com.mondris.question2.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    ResponseEntity<ApiResponse> getAllReservations();
    ResponseEntity<ApiResponse> getReservationById(int id);
    ResponseEntity<ApiResponse> createReservation(ReservationReqDto request);
    ResponseEntity<ApiResponse> deleteReservationById(int id);


}
