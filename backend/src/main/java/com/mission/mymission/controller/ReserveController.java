package com.mission.mymission.controller;

import com.mission.mymission.entity.*;
import com.mission.mymission.repository.*;
import com.mission.mymission.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api")
public class ReserveController {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ShopRegisterRepository shopRegisterRepository;
    private final ReserveRepository reserveRepository;
    private final ReservesettingRepository reservesettingRepository;

    @GetMapping("/reserve")
    public List<Reserve> getReserve(@CookieValue(value = "token", required = false) String token){
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int seq = jwtService.getSeq(token);
        Store stores = storeRepository.findBySeq(seq);
        String storeid = stores.getId();
        List<Reserve> reserve = reserveRepository.findByStoreid(storeid);
        Collections.reverse(reserve);

        return reserve;
    }

    @GetMapping("/myreserve")
    public List<Reserve> getMyReserve(
            @CookieValue(value = "token", required = false) String token
    ){
        if (!jwtService.isValid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int seq = jwtService.getSeq(token);
        User users = userRepository.findBySeq(seq);
        List<Reserve> reserves = reserveRepository.findByReserver(users.getNickname());
        return reserves;
    }
    //유저가 예약할 때 사용하는 메소드
    @PostMapping("/reserve/insert")
    public ResponseEntity<Reserve> insertReserve(@RequestParam String storename, @CookieValue(value = "token", required = false) String token, @RequestBody Reserve newReserve) {
        newReserve.setStatus(1);
        //세팅한 max팀, max 인원 수와 예약된 테이블의 팀, 인원 수 비교해서
        // 0 보다 작으면 false , 0보다 크면 true
        int seq = jwtService.getSeq(token);
        User users = userRepository.findBySeq(seq);
        String nickname = users.getNickname();
        newReserve.setReserver(nickname);
        newReserve.setStorename(storename);
        ShopRegister shopRegister = shopRegisterRepository.findByStorename(storename);
        String storeid = shopRegister.getStoreid();
        newReserve.setStoreid(storeid);

        String reservesettingstoreid = newReserve.getStoreid(); // vue에서 가져온 식당고유번호
        Date reservesettingDate = newReserve.getDate(); // vue에서 가져온 날짜
        int reservesettingtime0810 = newReserve.getTime0810(); // vue에서 가져온 시간대
        int reservesettingtime1012 = newReserve.getTime1012(); // vue에서 가져온 시간대
        int reservesettingtime1214 = newReserve.getTime1214(); // vue에서 가져온 시간대
        int reservesettingtime1416 = newReserve.getTime1416(); // vue에서 가져온 시간대
        int reservesettingtime1618 = newReserve.getTime1618(); // vue에서 가져온 시간대
        int reservesettingtime1820 = newReserve.getTime1820(); // vue에서 가져온 시간대
        int reservesettingtime2022 = newReserve.getTime2022(); // vue에서 가져온 시간대

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime0810SumPeople = reservesettingRepository.findSumPeopleByParams0810(reservesettingstoreid, reservesettingDate, reservesettingtime0810);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime0810SumTeam = reservesettingRepository.findSumTeamByParams0810(reservesettingstoreid, reservesettingDate, reservesettingtime0810);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime1012SumPeople = reservesettingRepository.findSumPeopleByParams1012(reservesettingstoreid, reservesettingDate, reservesettingtime1012);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime1012SumTeam = reservesettingRepository.findSumTeamByParams1012(reservesettingstoreid, reservesettingDate, reservesettingtime1012);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime1214SumPeople = reservesettingRepository.findSumPeopleByParams1214(reservesettingstoreid, reservesettingDate, reservesettingtime1214);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime1214SumTeam = reservesettingRepository.findSumTeamByParams1214(reservesettingstoreid, reservesettingDate, reservesettingtime1214);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime1416SumPeople = reservesettingRepository.findSumPeopleByParams1416(reservesettingstoreid, reservesettingDate, reservesettingtime1416);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime1416SumTeam = reservesettingRepository.findSumTeamByParams1416(reservesettingstoreid, reservesettingDate, reservesettingtime1416);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime1618SumPeople = reservesettingRepository.findSumPeopleByParams1618(reservesettingstoreid, reservesettingDate, reservesettingtime1618);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime1618SumTeam = reservesettingRepository.findSumTeamByParams1618(reservesettingstoreid, reservesettingDate, reservesettingtime1618);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime1820SumPeople = reservesettingRepository.findSumPeopleByParams1820(reservesettingstoreid, reservesettingDate, reservesettingtime1820);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime1820SumTeam = reservesettingRepository.findSumTeamByParams1820(reservesettingstoreid, reservesettingDate, reservesettingtime1820);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 인원 수
        int reservesettingtime2022SumPeople = reservesettingRepository.findSumPeopleByParams2022(reservesettingstoreid, reservesettingDate, reservesettingtime2022);
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 최대 팀 수
        int reservesettingtime2022SumTeam = reservesettingRepository.findSumTeamByParams2022(reservesettingstoreid, reservesettingDate, reservesettingtime2022);

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime0810SumPeople = reserveRepository.findSumPeopleByParams0810Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime0810);
        reservetime0810SumPeople = reservetime0810SumPeople != null ? reservetime0810SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime0810SumTeam = reserveRepository.findSumTeamByParams0810Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime0810);
        reservetime0810SumTeam = reservetime0810SumTeam != null ? reservetime0810SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime1012SumPeople = reserveRepository.findSumPeopleByParams1012Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1012);
        reservetime1012SumPeople = reservetime1012SumPeople != null ? reservetime1012SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime1012SumTeam = reserveRepository.findSumTeamByParams1012Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1012);
        reservetime1012SumTeam = reservetime1012SumTeam != null ? reservetime1012SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime1214SumPeople = reserveRepository.findSumPeopleByParams1214Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1214);
        reservetime1214SumPeople = reservetime1214SumPeople != null ? reservetime1214SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime1214SumTeam = reserveRepository.findSumTeamByParams1214Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1214);
        reservetime1214SumTeam = reservetime1214SumTeam != null ? reservetime1214SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime1416SumPeople = reserveRepository.findSumPeopleByParams1416Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1416);
        reservetime1416SumPeople = reservetime1416SumPeople != null ? reservetime1416SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime1416SumTeam = reserveRepository.findSumTeamByParams1416Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1416);
        reservetime1416SumTeam = reservetime1416SumTeam != null ? reservetime1416SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime1618SumPeople = reserveRepository.findSumPeopleByParams1618Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1618);
        reservetime1618SumPeople = reservetime1618SumPeople != null ? reservetime1618SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime1618SumTeam = reserveRepository.findSumTeamByParams1618Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1618);
        reservetime1618SumTeam = reservetime1618SumTeam != null ? reservetime1618SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime1820SumPeople = reserveRepository.findSumPeopleByParams1820Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1820);
        reservetime1820SumPeople = reservetime1820SumPeople != null ? reservetime1820SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime1820SumTeam = reserveRepository.findSumTeamByParams1820Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime1820);
        reservetime1820SumTeam = reservetime1820SumTeam != null ? reservetime1820SumTeam : 0;

        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 인원 수 합
        Integer reservetime2022SumPeople = reserveRepository.findSumPeopleByParams2022Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime2022);
        reservetime2022SumPeople = reservetime2022SumPeople != null ? reservetime2022SumPeople : 0;
        //vue에서 가져온 해당 식당 고유번호, 날짜, 시간대의 예약된 팀 수 합
        Integer reservetime2022SumTeam = reserveRepository.findSumTeamByParams2022Reserve(reservesettingstoreid, reservesettingDate, reservesettingtime2022);
        reservetime2022SumTeam = reservetime2022SumTeam != null ? reservetime2022SumTeam : 0;

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople0810 = reservetime0810SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam0810 = reservetime0810SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople1012 = reservetime1012SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam1012 = reservetime1012SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople1214 = reservetime1214SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam1214 = reservetime1214SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople1416 = reservetime1416SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam1416 = reservetime1416SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople1618 = reservetime1618SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam1618 = reservetime1618SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople1820 = reservetime1820SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam1820 = reservetime1820SumTeam + newReserve.getTeam();

        //해당 식당 고유번호, 날짜, 시간대의 예약되어있는 인원 수 합 + vue에서 넘어온 인원(people 수)
        int plusPeople2022 = reservetime2022SumPeople + newReserve.getPeople();
        //해당 식당 고유번호 ,날짜, 시간대의 예약되어있는 팀 수 합 + vue에서 넘어온 팀(team 수)
        int plusTeam2022 = reservetime2022SumTeam + newReserve.getTeam();

        if(reservesettingtime0810 == 1){
            if(reservesettingtime0810SumPeople - plusPeople0810 > 0 && reservesettingtime0810SumTeam - plusTeam0810 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime1012 == 1){
            if(reservesettingtime1012SumPeople - plusPeople1012 > 0 && reservesettingtime1012SumTeam - plusTeam1012 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime1214 == 1){
            if(reservesettingtime1214SumPeople - plusPeople1214 > 0 && reservesettingtime1214SumTeam - plusTeam1214 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime1416 == 1){
            if(reservesettingtime1416SumPeople - plusPeople1416 > 0 && reservesettingtime1416SumTeam - plusTeam1416 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime1618 == 1){
            if(reservesettingtime1618SumPeople - plusPeople1618 > 0 && reservesettingtime1618SumTeam - plusTeam1618 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime1820 == 1){
            if(reservesettingtime1820SumPeople - plusPeople1820 > 0 && reservesettingtime1820SumTeam - plusTeam1820 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        if(reservesettingtime2022 == 1){
            if(reservesettingtime2022SumPeople - plusPeople2022 > 0 && reservesettingtime2022SumTeam - plusTeam2022 > 0){
                Reserve savedReserve = reserveRepository.save(newReserve);
                return ResponseEntity.ok(savedReserve);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/reserve/delete/{seq}")
    public ResponseEntity removeReserve(@PathVariable("seq") int seq){
        Reserve reserve = reserveRepository.findBySeq(seq);

        reserveRepository.delete(reserve);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}