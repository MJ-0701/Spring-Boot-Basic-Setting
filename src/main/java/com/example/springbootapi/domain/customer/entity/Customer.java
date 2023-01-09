package com.example.springbootapi.domain.customer.entity;

import com.example.springbootapi.global.entity.BaseTimeEntity;
import com.example.springbootapi.global.entity.Files;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "CUSTOMER")
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_IDX")
    @Comment("고객_IDX")
    private Long id;



    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '물건번호'")
    private String itemNumber;

    @Column(columnDefinition = "VARCHAR(10) NOT NULL COMMENT '이름'")
    private String userName;

    @Column(columnDefinition = "VARCHAR(11) NOT NULL COMMENT '핸드폰 번호'")
    private String phoneNumber;

    @Lob
    private String contents;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Files> files = new ArrayList<>();

    @Builder
    public Customer(String itemNumber, String userName, String phoneNumber, String contents) {
        this.itemNumber = itemNumber;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.contents = contents;
    }

    // Customer에서 파일 처리
    public void addFiles(Files files) {
        this.files.add(files);

        // 고객 정보에 해당 파일이 저장돼있지 않은 경우
        if(files.getCustomer() != this) {
            // 파일 저장
            files.setCustomer(this);
        }

    }

}
