package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MusicRepositoryTest {

    @Autowired
    MusicRepository musicRepository;
    Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setId(18);
        track.setName("mansion");
        track.setComments("wow!");
    }

    @After
    public void tearDown() {

        musicRepository.deleteAll();
    }


    @Test
    public void testSaveTrack() {
        musicRepository.save(track);
        Track fetchUser = musicRepository.findById(track.getId()).get();
        Assert.assertEquals(18, fetchUser.getId());

    }

    @Test
    public void testSaveTrackFailure() {
        Track testTrack = new Track(3, "Monster", "wow");
        musicRepository.save(track);
        Track fetchTrack = musicRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack, track);
    }

    @Test
    public void testGetAllUser() {
        Track track1 = new Track(4,"Run","amazing");
        Track track2 = new Track(5,"Idol","wow");
        musicRepository.save(track1);
        musicRepository.save(track2);

        List<Track> list = musicRepository.findAll();
        Assert.assertEquals("Run", list.get(0).getName());


    }

    @Test
    public void testGetTrackByIdFailure(){

        Track track1 = new Track(25,"Bad guy","wow");
        Track track2 = new Track(35,"old town road","good");
        musicRepository.save(track1);
        musicRepository.save(track2);
        List<Track> list = musicRepository.findAll();
        Assert.assertNotEquals("Bad",list.get(0).getName());

    }



}