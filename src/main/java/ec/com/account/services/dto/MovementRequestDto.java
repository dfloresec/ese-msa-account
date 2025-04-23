package ec.com.account.services.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MovementRequestDto {

	private Long accountId;
	private BigDecimal valuee;

}
