package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.City;
import com.sise.familyEducation.entity.Province;
import com.sise.familyEducation.repository.CityRepository;
import com.sise.familyEducation.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 基础
 * @author: wxy
 * @create: 2020-02-09 11:06
 **/

@Service
public class BasicService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;

    public List<Province> findAllProvince(){
        return provinceRepository.findAll();
    }

    public Province findProvinceById(int province_code){
        return provinceRepository.findById(province_code).get();
    }

    public City findCityById(int city_code){
        return cityRepository.findById(city_code).get();
    }



}
