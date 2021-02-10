package com.fetchrewards.exercise.service;

import com.fetchrewards.exercise.model.Account;
import com.fetchrewards.exercise.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void saveAndUpdate(Account user) {
        accountRepository.save(user);
    }

    public int getTotalPoints(){
        int totalPoints = 0;
        List<Account> accountList = accountRepository.findAll();

        for (Account account : accountList){
            totalPoints = totalPoints + account.getPoints();
        }

        return totalPoints;
    }

    public HashMap<String, Integer> deductPoints(int points){
        int balancePoints = points;
        HashMap<String, Integer> totalPointsMap = new HashMap<>();
        List<Account> accountList = accountRepository.findAll();

        int i = 0;

        while(balancePoints != 0 && i < accountList.size() - 1) {

            Account account = accountList.get(i);
                if (totalPointsMap.containsKey(account.getPayerName())) {
                    if(account.getPoints() > 0){
                        int x = totalPointsMap.get((account.getPayerName()));
                        x += account.getPoints();

                        if (balancePoints >= account.getPoints()) {

                            totalPointsMap.put(account.getPayerName(), x);
                        }
                        else{
                            totalPointsMap.put(account.getPayerName(), totalPointsMap.get((account.getPayerName())) + balancePoints);
                            balancePoints = 0;
                        }
                    } else {
                        balancePoints += Math.abs(account.getPoints());
                        totalPointsMap.put(account.getPayerName(), totalPointsMap.get((account.getPayerName())) + account.getPoints());
                    }

                } else {
                    if (balancePoints >= account.getPoints()) {
                        totalPointsMap.put(account.getPayerName(), account.getPoints());
                        balancePoints = balancePoints - account.getPoints();

                    } else {
                        totalPointsMap.put(account.getPayerName(), balancePoints);
                        balancePoints = 0;
                    }

                }

                i++;
        }

        for(HashMap.Entry entry: totalPointsMap.entrySet()) {
            String key = (String) entry.getKey();
            Integer val = (Integer) entry.getValue();

            totalPointsMap.put(key, val * -1);

            Account acc = new Account();
            acc.setPayerName(key);
            acc.setPoints(val * -1);
            saveAndUpdate(acc);
        }

        return totalPointsMap;

    }

    public HashMap<String, Integer> getAllPointsPerPayer(){
        HashMap<String, Integer> result = new HashMap<>();
        List<Account> accountList = accountRepository.findAll();

        for (Account account : accountList){
            if (result.containsKey(account.getPayerName())){
                int temp = result.get((account.getPayerName()));
                result.put(account.getPayerName(), temp + account.getPoints());
            } else {
                result.put(account.getPayerName(), account.getPoints());
            }
        }

        return result;
    }
}
