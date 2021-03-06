package com.example.kiber.DAO;

import com.example.kiber.Model.city;
import com.example.kiber.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CityDAO {

    @Autowired
    ExelParser exelParser;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CityRepository cityRepository;

    @Transactional
    public boolean updatecity(){
        HashMap<Integer, ArrayList<city>> MapList;
        ArrayList<String> listcity = getCity();
        int q = jdbcTemplate.update("truncate city");
        for(int j=0; j< listcity.size(); j++) {
            MapList = new HashMap<>();
            for (int i = 1; i <= 12; i++) {
                ArrayList<city> Listkyiv = new ExelParser().parse("C:\\Users\\Family\\IdeaProjects\\kiber\\src\\main\\resources\\dataset\\" + listcity.get(j) + "\\2012-" + i + ".xlsx");
                MapList.put(i, Listkyiv);
            }
            int finalJ = j;
            MapList.forEach(((integer, list) -> {
                list.forEach(x -> {
                    x.setMounth(integer);

                    int year = 2011;
                    String str = year+"."+x.getMounth()+"."+x.getDay()+" "+x.getTime();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                    try {
                        Date parsingDate = formatForDateNow.parse(str);
                        x.setDate(parsingDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    x.setName(listcity.get(finalJ));
                    cityRepository.save(x);
                });
            }));
        }
        return true;
    }


    public String extractData(String name, String date1, String date2, Model model) throws ParseException {
        // ?????? ??????????????????????
        List<String> windDirections = new ArrayList<>();
        // ?????? ????????
        List<String> dates = new ArrayList<>();
        // ?????? ??????????????????????
        List<Integer> temps = new ArrayList<>();
        // ???????????????????? ?????????????? ???????????????????????? ?????????????????????? ?? ????????????????????
        List<Integer> countTemps = new ArrayList<>();
        // ???????????????????? ?????????????? ?????????????????????????? ?????????????????????? ?? ????????????????????
        List<Integer> countWinds = new ArrayList<>();
        // ???????????????????? ???????????????? ????????????????
        List<Integer> distinctSpeeds =  new ArrayList<>();
        // ???????????????????? ???????????????????? ?????????????????????? ????????????????
        List<Integer> countDistinctSpeeds = new ArrayList<>();
        // ???????????????????? ???????????????? ?????????? ???? ????????????????????????
        List<List<Integer>> distinctSpeedsByWinds = new ArrayList<>();



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate = formatter.parse(date1);
        Date endDate = formatter.parse(date2);
        List<city> records = jdbcTemplate.query(
                "select * from city where name_city = ? and Date between ? and ?",
                new Object[]{name, startDate, endDate},
                new BeanPropertyRowMapper<>(city.class));
        jdbcTemplate.query("SELECT * FROM `city` GROUP BY `direction` LIMIT 9,9", new BeanPropertyRowMapper<>(city.class))
                .forEach(x-> windDirections.add(x.getDirection()));
        records.forEach(x->{
            int year = 2011;
            String str = year+"."+x.getMounth()+"."+x.getDay()+" "+x.getTime();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            try {
                Date parsingDate = formatForDateNow.parse(str);
                dates.add(parsingDate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            temps.add(x.getTemperature());
        });

        //???????????????????? ???????????????? ?????????????????????? (?????? ??????????????????)
        Set<Integer> distinctTemps = new TreeSet<>(temps);
        distinctTemps.forEach(x-> {
            countTemps.add(cityRepository.countRecordsByTemperatureEqualsAndNameIsAndDateAfterAndDateBefore(x,name,startDate,endDate));
        });
        windDirections.forEach(x-> {
            countWinds.add(cityRepository.countRecordsByDirectionIsAndNameIsAndDateAfterAndDateBefore(x,name,startDate,endDate));
        });
        jdbcTemplate.query("SELECT DISTINCT speed FROM `city` WHERE `name_city` = ? AND`Date`BETWEEN ? AND ?", new Object[]{name, startDate, endDate}, new BeanPropertyRowMapper<>(city.class))
                .forEach(x -> {distinctSpeeds.add(x.getSpeed());});
        distinctSpeeds.forEach(x->{
            countDistinctSpeeds.add(cityRepository.countRecordsBySpeedIsAndNameIsAndDateAfterAndDateBefore(x, name, startDate, endDate));
        });
        windDirections.forEach(x->{
            distinctSpeedsByWinds.add(cityRepository.findDistinctSpeedByDirection(x, name, startDate, endDate));
        });

        // ??????????????????????????
        List<Set<Integer>> orderedDistinctSpeedsByWinds = new ArrayList<>();
        distinctSpeedsByWinds.forEach(x->{
            Set<Integer> orderedDistinctSpeedsByWind = new TreeSet<>(x);
            orderedDistinctSpeedsByWinds.add(orderedDistinctSpeedsByWind);
        });
        // ???????????????????? ?????????????? ???? ???????????????????????? ?? ????????????????
        List<List<Integer>> countOrderedDistinctSpeedsByWinds = new ArrayList<>(orderedDistinctSpeedsByWinds.size());
        for (int i = 0; i < windDirections.size(); i++) {
            countOrderedDistinctSpeedsByWinds.add(new ArrayList<>());
            Iterator<Integer> iterator = orderedDistinctSpeedsByWinds.get(i).iterator();
            for (int j = 0; j < orderedDistinctSpeedsByWinds.get(i).size(); j++) {
                countOrderedDistinctSpeedsByWinds.get(i).add(
                        cityRepository.countRecordsBySpeedIsAndDirectionIsAndNameIsAndDateAfterAndDateBefore(iterator.next(), windDirections.get(i), name, startDate, endDate) / orderedDistinctSpeedsByWinds.get(i).size());
            }
        }

        model.addAttribute("dates", dates.toArray());
        model.addAttribute("temps", temps.toArray());
        model.addAttribute("distinctTemps", distinctTemps.toArray());
        model.addAttribute("countTemps", countTemps.toArray());
        model.addAttribute("winds", windDirections);
        model.addAttribute("countWinds", countWinds.toArray());
        model.addAttribute("distinctSpeedsByWinds", orderedDistinctSpeedsByWinds);
        model.addAttribute("countSpeedsByWinds", countOrderedDistinctSpeedsByWinds);
        model.addAttribute("distinctSpeeds", distinctSpeeds);
        model.addAttribute("countSpeeds", countDistinctSpeeds);
        return "/vkladka2/main";
    }

    public static ArrayList<String> getCity(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("??????????????????????????????");
        arr.add("??????????????");
        arr.add("????????");
        arr.add("???????????? ??????");
        arr.add("????????????????");
        arr.add("??????????");
        arr.add("??????????");
        arr.add("??????????????????????");
        arr.add("????????????");
        arr.add("??????????????????????????????");
        return arr;
    }

}


