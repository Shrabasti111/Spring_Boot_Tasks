package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusicService  {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getTrack() throws TrackNotFoundException;

    public Track getById(int id) throws TrackNotFoundException;

    public List<Track> deleteById(int id) throws TrackNotFoundException;

    public Track updateById(Track track, int id) throws TrackNotFoundException;

    public List<Track> getTrackByName(String name) throws TrackNotFoundException;

}
