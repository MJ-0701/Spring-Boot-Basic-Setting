package com.example.springbootapi.domain.customer.web.controller;

import com.example.springbootapi.domain.customer.service.CustomerService;
import com.example.springbootapi.domain.customer.web.dto.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR"),
        @ApiResponse(responseCode = "900", description = "서버 에러"),
        @ApiResponse(responseCode = "999", description = "미등록 회원"),
        @ApiResponse(responseCode = "998", description = "데이터 에러"),
})
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/save")
    @Operation(summary = "회원정보 저장",description = "회원 정보", tags = {"Customer Controller"})
    public void customerInfoSave(
            @RequestBody CustomerDto dto
    ) {
        customerService.saveCustomerInfo(dto);
//        return ResponseEntity.ok().body(customerService.saveCustomerInfo(dto));
    }

    @PostMapping("/upload-customer-info")
    @Operation(summary = "회원정보 + 파일 저장",description = "회원 정보", tags = {"Customer Controller"})
    public void uploadFile(
            @RequestBody CustomerDto dto
    ) throws Exception {
        customerService.uploadCustomerInfo(dto);
    }

    @GetMapping("/info")
    @Operation(summary = "id로 회원 찾기",description = "회원 정보", tags = {"Customer Controller"})
    public ResponseEntity<CustomerDto> getInfo(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok().body(customerService.getCustomerInfo(id));
    }


    @Operation(summary = "핸드폰 정보로 회원 찾기",description = "회원 정보", tags = {"Customer Controller"})
    @GetMapping("/get-info")
    public ResponseEntity<CustomerDto> getInfoByPhone(
            @RequestParam String phoneNumber
    ) {
        return ResponseEntity.ok().body(customerService.getInfoByPhone(phoneNumber));
    }

    @PutMapping("/file-upload")
    @Operation(summary = "파일 저장",description = "회원 정보", tags = {"Customer Controller"})
    public void fileUpload(
            List<MultipartFile> files,
            @RequestParam Long id
    ) throws Exception {
        customerService.uploadFile(id, files);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "회원 삭제",description = "회원 정보", tags = {"Customer Controller"})
    public void deleteUser(
            @RequestParam Long id
    ) {
        customerService.deleteUser(id);
    }




}
