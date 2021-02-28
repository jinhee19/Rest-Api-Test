package com.najinji.domain.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findByEmail(String email);
    Bookmark findByEmailAndTitle(String email, String title);
}
