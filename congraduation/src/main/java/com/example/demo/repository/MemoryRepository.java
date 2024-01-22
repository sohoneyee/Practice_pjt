package com.example.demo.repository;

import com.example.demo.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoryRepository extends JpaRepository<Memory, String> {
    // 특정 메모리 조회 : 얘도 기본메서드가 해주지 않나?
//    @Query("select m from Memory m where m.memory_pk= :memoryPk")
//    Memory findOneMemory(@Param("memoryPk") String pk);

}
