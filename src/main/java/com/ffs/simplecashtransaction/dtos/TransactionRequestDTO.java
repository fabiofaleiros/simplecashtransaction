package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;

public record TransactionRequestDTO(BigDecimal value, Long senderID, Long receiverID) {

}
