package br.com.jesse.sample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StandardError {

	private Long timeStamp;

	private Integer status;

	private String error;

	private String message;

	private String path;

}
