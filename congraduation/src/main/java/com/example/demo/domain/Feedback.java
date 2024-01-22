package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Getter
@Setter
public class Feedback {

  @Id
  @Column(name = "feedback_pk")
  private String pk;
  private String content;
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "member_pk")
  private Member writer;
}
