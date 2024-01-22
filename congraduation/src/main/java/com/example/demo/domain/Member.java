package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

  @Id
  @Column(name = "member_pk")
  private String pk;
  private String nickname;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;

  @OneToMany(mappedBy = "writer")
  private List<Feedback> feedbackList = new ArrayList<>();

  @OneToOne(mappedBy = "member")
  private Album album;

  @OneToMany(mappedBy = "member")
  private List<Memory> memoryList = new ArrayList<>();

}
