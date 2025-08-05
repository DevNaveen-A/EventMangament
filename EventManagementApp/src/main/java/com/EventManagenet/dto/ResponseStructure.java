package com.EventManagenet.dto;

public class ResponseStructure<T> {
private String message;
private T Data;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public T getData() {
	return Data;
}
public void setData(T data) {
	Data = data;
}
}
