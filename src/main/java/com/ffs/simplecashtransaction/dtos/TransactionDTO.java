package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderID, Long receiverID) {

}
