package ir.dealit.restful.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseWrapper<T> (T data, String status){
}
