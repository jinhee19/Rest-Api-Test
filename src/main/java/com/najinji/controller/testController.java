package com.najinji.controller;

import com.najinji.domain.bookmark.Bookmark;
import com.najinji.domain.bookmark.BookmarkRepository;
import com.najinji.domain.data.Data;
import com.najinji.domain.data.DataRepository;
import com.najinji.domain.userInfo.UserInfo;
import com.najinji.domain.userInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test")
@RestController
public class testController {

    private DataRepository repository;
    private UserInfoRepository userInfoRepository;
    private BookmarkRepository bookmarkRepository;

    @Autowired
    public testController(DataRepository repository, UserInfoRepository userInfoRepository, BookmarkRepository bookmarkRepository){
        this.repository = repository;
        this.userInfoRepository = userInfoRepository;
        this.bookmarkRepository = bookmarkRepository;
    }


    // 전체 관광지 정보 가져옴
    @GetMapping(value="/select")
    public List<Data> list(){
       return repository.findAll();
    }

    // 전체 관광지의 이름 가져옴
    @GetMapping(value="/titles")
    public List<String> getTitle(){
        /*Optional<Data> option = repository.findById(title);
        String name = option.get().getTitle();
        return name;
         */
        List<Data> lists = repository.findAll();
        List<String> titles = new ArrayList<>(300);

        for(int i = 0; i < lists.size(); i++)
            titles.add(lists.get(i).getTitle());

        return titles;
    }

    // 특정 관광지의 이미지 URL 가져오기
    @GetMapping(value="/url/{title}")
    public String getUrl(@PathVariable("title") String title){
        return repository.findById(title).orElse(null).getUrl();
    }

    // 사용자 정보 생성하기
    @PostMapping(value="/userinfo")
    public UserInfo createUserInfo(@Valid @RequestBody UserInfo userInfo){
        List<UserInfo> allUser = userInfoRepository.findAll();

        for(UserInfo info:allUser){
            if(info.getEmail().equals(userInfo.getEmail()))
                return null;
        }
        return userInfoRepository.save(userInfo);
    }

    // 전체 사용자 정보 가져오기
    @GetMapping(value="/userinfo")
    public List<UserInfo> getUserInfo(){
        List<UserInfo> info = userInfoRepository.findAll();
        return info;
    }

    /*
    // 해당 메소드들은 UserInfoRepository에서 제네릭의 String을 Integer로 바꾸고
    // UserInfo 엔티티에서 id에 @Id 어노테이션을 삽입해야 동작한다.
    // 특정 사용자 정보 가져오기(id를 사용해서)
    @GetMapping(value="/userinfo/{id}")
    public UserInfo getUserInfo(@PathVariable("id") int id){
        return userInfoRepository.findById(id).orElse(null);
    }
    // PostMapping
    @PostMapping(value="/userinfo/{id}")
    public UserInfo getUserInfo(@PathVariable("id") int id, @RequestBody UserInfo userinfo){
        return userInfoRepository.findById(id).orElse(null);
    }
    */

    // 특정 사용자 정보 가져오기(email를 사용해서)
    @PostMapping(value="/userinfo/{email}")
    public UserInfo getUserInfo(@PathVariable("email") String email){
        return userInfoRepository.findById(email).orElse(null);
    }

    // 사용자 정보 수정하기
    @PutMapping(value="/userinfo/{email}")
    public UserInfo updateUserInfo(@PathVariable("email") String email, @Valid @RequestBody UserInfo info){
        UserInfo userInfo = userInfoRepository.findById(email).orElse(null);
        userInfo.setSex(info.getSex());
        userInfo.setAge(info.getAge());
        userInfo.setResidence(info.getResidence());

        UserInfo updateInfo = userInfoRepository.save(userInfo);
        return updateInfo;
    }


    // 북마크 생성하기
    @PostMapping(value="/bookmark")
    public Bookmark createBookmark(@Valid @RequestBody Bookmark bookmark){
        List<Bookmark> allBookmark = bookmarkRepository.findAll();

        for(Bookmark bm : allBookmark){
            if(bm.getTitle().equals(bookmark.getTitle()) && bm.getEmail().equals(bookmark.getEmail()))
                return null;
        }
        return bookmarkRepository.save(bookmark);
    }

    // 북마크 목록 가져오기(전체)
    @GetMapping(value="/bookmark")
    public List<Bookmark> getBookmarks(){
        return bookmarkRepository.findAll();
    }

    /*
    // 북마크 목록 가져오기(특정 사용자) --> equals를 사용해서 비교
    @GetMapping(value="/bookmark/{email}")
    public List<Bookmark> getBookmarks(@PathVariable("email")String email){
        List<Bookmark> allBookmarks = bookmarkRepository.findAll();
        List<Bookmark> userBookmarks = new ArrayList<>();

        for(Bookmark bm : allBookmarks){
            if(bm.getUsr_email().equals(email))
                userBookmarks.add(bm);
        }
        return userBookmarks;
    }
     */

    // 북마크 목록 가져오기(특정 사용자) --> BookmarkRepository에 선언한 findByEmail 함수를 이용(JPQL)
    @GetMapping(value="/bookmark/{email}")
    public List<Bookmark> getBookmarks(@PathVariable("email")String email){
        return bookmarkRepository.findByEmail(email);
    }

    // 북마크 내용 가져오기(BookmarkRepository와 DataRepository를 이용해서)
    @GetMapping(value="/content/{email}")
    public List<Data> getContents(@PathVariable("email")String email){
        List<Bookmark> list = bookmarkRepository.findByEmail(email);
        List<Data> bookmarks = new ArrayList<>();

        for(Bookmark bm : list){
            bookmarks.add(repository.findById(bm.getTitle()).get());
        }
        return bookmarks;
    }

    // 북마크 삭제하기(email과 title을 사용해서)
    @DeleteMapping(value="/bookmark/{email}/{title}")
    public String deleteBookmark(@PathVariable("email") String email, @PathVariable("title") String title){
        Bookmark bm = bookmarkRepository.findByEmailAndTitle(email, title);

        bookmarkRepository.delete(bm);
        return "success!";
    }
}
