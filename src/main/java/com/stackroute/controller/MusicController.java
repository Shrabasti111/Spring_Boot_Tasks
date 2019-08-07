package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MusicController {

    private MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/music")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {

        ResponseEntity responseEntity;

        try {
            musicService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>("Could not create", HttpStatus.ALREADY_REPORTED);
        }
        return responseEntity;
    }

    @GetMapping("/musics")
    public ResponseEntity<?> getTrack() {
        return new ResponseEntity<List<Track>>(musicService.getTrack(), HttpStatus.OK);
    }

    @GetMapping("/music/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {

        Track track;

        try{
            track = musicService.getById(id);
            return new ResponseEntity<Track>(track, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Could not find by id", HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/music/{id}")
    public String deleteById(@PathVariable int id) {
        musicService.deleteById(id);
        return "track deleted";
    }

    @PutMapping("/music/{id}")
    public String updateById(@RequestBody Track track, @PathVariable int id) {

        if(musicService.updateById(track, id)) {
            return "track updated";
        }

        return "track could not be updated";

    }


}

