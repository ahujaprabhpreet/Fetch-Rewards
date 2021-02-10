package com.fetchrewards.exercise.controller;

import com.fetchrewards.exercise.model.Point;
import com.fetchrewards.exercise.model.Account;
import com.fetchrewards.exercise.repository.AccountRepository;
import com.fetchrewards.exercise.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    //Get All points in the user account
    @GetMapping("/allPoints")
    public ResponseEntity getAllPoints(){
        List<Account> points = accountRepository.findAll();
        return new ResponseEntity(points, HttpStatus.OK);
    }

    //Add points to user account for specific payer and date
    @PostMapping(value = "/points")
    public ResponseEntity<Account> addPointsToUser(@RequestBody Account account){
        accountService.saveAndUpdate(account);
        return new ResponseEntity(account, HttpStatus.CREATED);
    }

    //Deduct points from user account
    @PostMapping(value = "/deduct")
    public ResponseEntity<Account> deductPointsFromUser(@RequestBody Point points){
        if(points.getPoints() > accountService.getTotalPoints()){
            return new ResponseEntity("Not Enough Points", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        HashMap<String, Integer> pointsMap = accountService.deductPoints(points.getPoints());
        return new ResponseEntity(pointsMap, HttpStatus.OK);
    }

    //Return point balance per user that would list all positive points per payer
    @GetMapping("/points")
    public ResponseEntity pointsPerPayer(){
        HashMap<String, Integer> points = accountService.getAllPointsPerPayer();
        return new ResponseEntity(points, HttpStatus.OK);
    }





}
