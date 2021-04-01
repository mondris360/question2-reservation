package com.mondris.question2.demo.Service.Impl;

import com.mondris.question2.demo.Dto.ReservationReqDto;
import com.mondris.question2.demo.Dto.ReservationResDto;
import com.mondris.question2.demo.Model.Reservation;
import com.mondris.question2.demo.Repository.ReservationRepository;
import com.mondris.question2.demo.Service.ReservationService;
import com.mondris.question2.demo.Util.Api.Exception.CustomErrorClass.NotFoundException;
import com.mondris.question2.demo.Util.Api.Response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl  implements ReservationService {

    @Resource
    private ReservationRepository reservationRepository;

    @Resource
    private ModelMapper modelMapper;

    private final  String currentPath =  "/v1/reservations";

    @Override
    public ResponseEntity<ApiResponse> getAllReservations() {

        final List<Reservation> allReservations = reservationRepository.findAll();

        final List<ReservationResDto> reservations = allReservations.stream()
                .map(entity -> modelMapper.map(entity, ReservationResDto.class))
                .collect(Collectors.toList());

        ApiResponse apiResponse =  new ApiResponse("Successful", HttpStatus.OK, "A List of all reservations", reservations);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());

    }

    @Override
    public ResponseEntity<ApiResponse> getReservationById(int id) {

        final Reservation reservation = reservationRepository.getById(id);

        if (reservation ==  null){
            throw new NotFoundException("Reservation Not Found", currentPath);
        }

        ApiResponse apiResponse  =   new ApiResponse("Successful", HttpStatus.OK, "Reservation Details", reservation);

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @Override
    public ResponseEntity<ApiResponse> createReservation(ReservationReqDto request) {

        if (request.getDate() == null){

            request.setDate(new Timestamp(System.currentTimeMillis()));
        }

        final Reservation reservation = modelMapper.map(request, Reservation.class);

        final Reservation createdReservation = reservationRepository.save(reservation);

        final ReservationResDto reservationDto = modelMapper.map(createdReservation, ReservationResDto.class);

        ApiResponse apiResponse  = new ApiResponse("Successful", HttpStatus.CREATED, "Your reservation was successfully created",
                reservationDto);


        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @Override
    public ResponseEntity<ApiResponse> deleteReservationById(int id) {

        final Reservation reservation = reservationRepository.getById(id);

        if (reservation == null){
            throw  new NotFoundException("No Record Found", currentPath);
        }
        reservationRepository.delete(reservation);

        final ApiResponse apiResponse = new ApiResponse("Successful", HttpStatus.OK, "Reservation Was Successfully Deleted");

        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
