package com.transactions.transactions_service.transactions.application.internal.commandServices;

import com.transactions.transactions_service.transactions.domain.model.aggregates.Transaction;
import com.transactions.transactions_service.transactions.domain.model.commands.CreateTransactionCommand;
import com.transactions.transactions_service.transactions.domain.services.TransactionsCommandService;
import com.transactions.transactions_service.transactions.infrastructure.persistence.jpa.repositories.TransactionsRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionsCommandServiceImpl implements TransactionsCommandService {
    private final TransactionsRepository transactionsRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public TransactionsCommandServiceImpl(
            TransactionsRepository transactionsRepository,
            SimpMessagingTemplate messagingTemplate) {
        this.transactionsRepository = transactionsRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void handle(CreateTransactionCommand command) {

        String description = "Transferencia de " + command.virtualCoins() + " monedas por evidencia de tipo "
                + command.evidenceType() + " al empleado " + command.fullName();

        var transaction = new Transaction(command, description);
        transactionsRepository.save(transaction);

        String destination = "/topic/transactions/"+command.companyId();
        messagingTemplate.convertAndSend(destination, transaction);
    }
}
