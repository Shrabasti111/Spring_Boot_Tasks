package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
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
        } catch (TrackAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
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
        } catch (TrackNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }


    @DeleteMapping("/music/{id}")
    public String deleteById(@PathVariable int id) {
        try {
            musicService.deleteById(id);

        } catch (TrackNotFoundException e) {
            return e.getMessage();
        }

        return "track deleted";
    }

    @PutMapping("/music/{id}")
    public String updateById(@RequestBody Track track, @PathVariable int id) {


        try {
            musicService.updateById(track, id);
        } catch (TrackNotFoundException e) {
            return e.getMessage();
        }

        return "song updated";

    }

    @GetMapping("/music/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) {

        List<Track> track = musicService.getTrackByName(name);
        return new ResponseEntity<List<Track>>(track, HttpStatus.OK);

    }



}


