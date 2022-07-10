package uz.online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {

	private String message;
	
	private boolean succes;
	
	private Object object;

	public ApiResponse(String message, boolean succes) {
		super();
		this.message = message;
		this.succes = succes;
	}
	
	
}
