package com.example.springbootapi.global.entity;

import com.example.springbootapi.domain.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Files extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_IDX")
    private Long id;

    private String originalFileName;

    private String filepath;

    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_IDX")
    private Customer customer;

    @Builder
    public Files(String originalFileName, String filepath, Long fileSize) {
        this.originalFileName = originalFileName;
        this.filepath = filepath;
        this.fileSize = fileSize;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        // 고객 정보에 현재 파일이 존재하지 않는다면.
        if(!customer.getFiles().contains(this)) {
            customer.getFiles().add(this);
        }
    }

}
