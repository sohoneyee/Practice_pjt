package com.example.demo.repository;

import com.example.demo.domain.Album;
import com.example.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumRepositoryTest {
    @Autowired AlbumRepository albumRepository;

    @Test
    public void 앨범조회() throws Exception {
        Member member1 = new Member();
        member1.setPk("1");
        Album album = new Album();
        album.setTitle("졸업");
        album.setPk("ASDF");
        album.setMember(member1);
        album.setCoverUrl("이미지");
        Album getAlbum = albumRepository.findAlbumByMemberPk(album.getMember().getPk());
        assertEquals(member1.getPk(), getAlbum.getPk());
    }
}