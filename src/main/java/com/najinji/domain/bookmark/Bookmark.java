package com.najinji.domain.bookmark;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="bookmark")
@Entity
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditing 기능을 사용함을 알림 */
public class Bookmark {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int bookmark_id;
    private String title;
    @Column(name="usr_email")
    private String email;

    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime creation_date;

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public Bookmark(){
    }

    public Bookmark(String title, String email){
        this.title = title;
        this.email = email;
    }

    public int getBookmark_id() {
        return bookmark_id;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }
}
