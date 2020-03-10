package org.tat.gginl.api.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {

	private String responseStatus;
	
	@Builder.Default
	private String message = "Success";
	
	private T responseBody; 
}
