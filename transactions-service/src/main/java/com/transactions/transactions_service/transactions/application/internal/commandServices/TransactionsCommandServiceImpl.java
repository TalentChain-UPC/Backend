package com.transactions.transactions_service.transactions.application.internal.commandServices;

import com.transactions.transactions_service.transactions.domain.model.commands.CreateTransactionCommand;
import com.transactions.transactions_service.transactions.domain.services.TransactionsCommandService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionsCommandServiceImpl implements TransactionsCommandService {
    private final SimpMessagingTemplate messagingTemplate;

    public TransactionsCommandServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void handle(CreateTransactionCommand command) {
        //createHash

        //create description/message

        //create transaction
        String destination = "/topic/transactions/"+command.companyId();
        messagingTemplate.convertAndSend(destination, command);//replace with trx entity
    }
}
