package com.example.demo.repository;

import com.example.demo.domain.Album;
import com.example.demo.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, String> {
    // 특정 앨범 조회
    @Query(value = "select *" + " from Album a" + " where a.member_pk= :memberPk", nativeQuery = true)
    Album findAlbumByMemberPk(@Param("memberPk") String pk);

    // 특정 앨범의 메모리리스트 조회
    @Query(value = "select *" + " from Memory m" + " where m.album_pk= :albumPk", nativeQuery = true)
    List<Memory> findMemoryListByAlbumPk(@Param("albumPk") String pk);
}
