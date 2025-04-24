package ec.com.account.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovementCreateRequestDto {

	private Long Id;
	private String message;

}
