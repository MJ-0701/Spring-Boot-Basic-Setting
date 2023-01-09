package com.example.springbootapi.domain.customer.advice

import com.example.springbootapi.domain.customer.web.controller.CustomerApiController
import com.example.springbootapi.global.web.ErrorField
import com.example.springbootapi.global.web.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [CustomerApiController::class])
@Slf4j
class CustomerControllerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e : MethodArgumentNotValidException, request : HttpServletRequest): ResponseEntity<ErrorResponse> {

        val errors = mutableListOf<ErrorField>()

        e.bindingResult.allErrors.forEach { errorObject ->

            val error = ErrorField().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue

            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = ""
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors // 커밋용
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }


    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun emptyResultDataAccessException( request : HttpServletRequest): ResponseEntity<ErrorResponse> {

        val errors = mutableListOf<ErrorField>()

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value().toString()
            this.httpMethod = request.method
            this.message = "메세지"
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception, request : HttpServletRequest): ResponseEntity<ErrorResponse>? {
        log.info("위치 : 커스텀")
        log.info("에러 유형 : {}", e.javaClass.name)
        log.info("에러 메세지 : {}", e.localizedMessage)
        val errors = mutableListOf<ErrorField>()

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value().toString()
            this.httpMethod = request.method
            this.message = "서버에러"
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(900).body(errorResponse)
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalArgumentException(e: Exception, request : HttpServletRequest): ResponseEntity<ErrorResponse>? {
        log.info("에러 유형 : {}", e.javaClass.name)
        log.info("에러 메세지 : {}", e.localizedMessage)
        val errors = mutableListOf<ErrorField>()

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value().toString()
            this.httpMethod = request.method
            this.message = "없는 번호 입니다."
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(999).body(errorResponse)
    }


    @ExceptionHandler(value = [DataIntegrityViolationException::class])
    fun dataIntegrityViolationException(e : DataIntegrityViolationException, request: HttpServletRequest) : ResponseEntity<ErrorResponse> {
        log.info("에러 유형 : {}", e.javaClass.name)
        log.info("에러 메세지 : {}", e.localizedMessage)
        val errors = mutableListOf<ErrorField>()

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value().toString()
            this.httpMethod = request.method
            this.message = "데이터 에러"
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(998).body(errorResponse)
    }
}