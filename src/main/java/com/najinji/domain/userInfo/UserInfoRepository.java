package com.najinji.domain.userInfo;

import com.najinji.domain.bookmark.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByEmail(String email);
}
