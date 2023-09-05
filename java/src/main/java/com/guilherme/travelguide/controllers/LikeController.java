package com.guilherme.travelguide.controllers;

import com.guilherme.travelguide.dao.LikeDao;
import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Like;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LikeController {

    private final LikeDao likeDao;

    public LikeController(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    @GetMapping("/likes/{username}")
    public List<Like> getLikesByUsername (@PathVariable String username) {
        return likeDao.getLikesByUsername(username);
    }

    @GetMapping("/like/{likeCityId}/{username}")
    public String getCityNameByLikeCityId(@PathVariable int likeCityId, String username) {
       return likeDao.getCityNameByLikeCityId(likeCityId, username);
    }

}
