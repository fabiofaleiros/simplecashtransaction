package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;

public record TransactionResponseDTO(BigDecimal value, Long senderID, Long receiverID) {

}
