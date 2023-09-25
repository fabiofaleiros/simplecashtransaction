package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponseDTO(BigDecimal value, Long senderID, Long receiverID, UUID transactionCode) {

}
