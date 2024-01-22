package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Memory {

  @Id
  @Column(name = "memory_pk")
  private String pk;

  private String content;
  private String imageUrl;
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "member_pk")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "album_pk")
  private Album album;

}
