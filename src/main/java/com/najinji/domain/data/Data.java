package com.najinji.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "gyeonju_data")
@Entity
public class Data {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String category;

    @Id
    @Column(columnDefinition = "TEXT",nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",length = 500)
    private String tag;

    @Column(columnDefinition = "TEXT",length = 500)
    private String address;

    @Column(columnDefinition = "TEXT",length = 500)
    private String phone;

    @Column(columnDefinition = "TEXT",length = 500)
    private String homepage;

    @Column(columnDefinition = "TEXT",length = 500)
    private String sum_info;

    @Column(columnDefinition = "TEXT",length = 500)
    private String detail_info;

    public Data(){

    }

    public Data(String category, String title, String tag, String address, String phone, String homepage, String sum_info, String detail_info){
        this.category = category;
        this.title = title;
        this.tag = tag;
        this.address = address;
        this.phone = phone;
        this.homepage = homepage;
        this.sum_info = sum_info;
        this.detail_info = detail_info;
    }

    public String getCategory(){
        return category;
    }

    public String getTitle(){
        return title;
    }

    public String getTag(){
        return tag;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    public String getHomepage(){
        return homepage;
    }

    public String getSum_Info(){
        return sum_info;
    }

    public String getDetail_Info(){
        return detail_info;
    }
}
