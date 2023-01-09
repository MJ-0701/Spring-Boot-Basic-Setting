package com.example.springbootapi.global.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<?> exception(Exception e) {
//        log.info("에러 유형 : {}",e.getClass().getName());
//        log.info("에러 메세지 : {}",e.getLocalizedMessage());
//
//        if (Objects.equals(e.getLocalizedMessage(), "없는 번호 입니다.")) {
//            return ResponseEntity.status(599).body("없는 번호 입니다.");
//        }else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
//        }
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
