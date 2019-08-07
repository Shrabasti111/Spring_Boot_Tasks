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

        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Track>>(musicService.getTrack(), HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return  responseEntity;

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
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        try {
            musicService.deleteById(id);
            return  ResponseEntity.noContent().build();

        } catch (TrackNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/music/{id}")
    public ResponseEntity<?> updateById(@RequestBody Track track, @PathVariable int id) {

        ResponseEntity responseEntity;

        try {
            musicService.updateById(track, id);
            responseEntity = new ResponseEntity<String>("Successfully updated", HttpStatus.CREATED);

        } catch (TrackNotFoundException e) {

            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
        }

        return responseEntity;

    }

    @GetMapping("/music/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name) {

        ResponseEntity responseEntity;

        try {
            List<Track> track = musicService.getTrackByName(name);
            responseEntity = new ResponseEntity<List<Track>>(track, HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }



}


