package com.najinji.domain.bookmark;

import javax.persistence.*;

@Table(name="bookmark")
@Entity
public class Bookmark {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int bookmark_id;
    private String title;
    @Column(name="usr_email")
    private String email;

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
