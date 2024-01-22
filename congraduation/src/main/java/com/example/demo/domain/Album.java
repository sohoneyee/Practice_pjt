package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Album {

  @Id
  @Column(name = "album_pk")
  private String pk;
  private String title;
  private String coverUrl;
  private String sharedUrl;
  private String graduationPlace;
  private LocalDateTime created_at;
  private LocalDateTime graduation_date;
  private LocalDateTime open_at;
  private LocalDateTime expired_at;

  @OneToOne
  @JoinColumn(name = "member_pk")
  private Member member;

  @OneToMany(mappedBy = "album")
  private List<Memory> MemoryList = new ArrayList<>();

}
