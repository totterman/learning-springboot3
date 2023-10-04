package com.totterman.mvnspringboot3;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    VideoService service;
    @Mock VideoRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new VideoService(repository);
    }

    @Test
    void getVideosShouldReturnAll() {
        // given
        VideoEntity video1 = new VideoEntity("Spring Boot 3 Intro", "Learn the basics!", "alice");
        VideoEntity video2 = new VideoEntity("Spring Boot 3 Deep Dive", "Go deep!", "alice");
        when(repository.findAll()).thenReturn(List.of(video1, video2));
        // when
        List<VideoEntity> videos = service.getVideos();
        // then
        assertThat(videos).containsExactly(video1, video2);
    }

    @Test
    void creatingANewVideoShouldReturnTheSameData() {
        // given
        given(repository.saveAndFlush(any(VideoEntity.class)))
        .willReturn(new VideoEntity("name", "desc", "alice"));
        // when
        VideoEntity newVideo = service.create(new Video("name", "des"), "alice");
        // then
        assertThat(newVideo.getName()).isEqualTo("name");
        assertThat(newVideo.getDescription()).isEqualTo("desc");
        assertThat(newVideo.getUsername()).isEqualTo("alice");
    }

    @Test
    void deletingAVideoShouldWork() {
        // given
        VideoEntity entity = new VideoEntity("name", "desc", "alice");
        entity.setId(1L);
        when(repository.findById(1L))
            .thenReturn(Optional.of(entity));
        // when
        service.delete(1L);
        // then
        verify(repository).findById(1L);
        verify(repository).delete(entity);
    }
}
