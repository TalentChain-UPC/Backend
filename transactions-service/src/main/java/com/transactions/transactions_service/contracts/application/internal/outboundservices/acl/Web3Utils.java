package com.transactions.transactions_service.contracts.application.internal.outboundservices.acl;

import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Component
public class Web3Utils {
    private final Web3j web3j;
    private final Credentials credentials;

    public Web3Utils(Web3j web3j, Credentials credentials) {
        this.web3j = web3j;
        this.credentials = credentials;
    }

    public BigInteger getReward(String contractAddress){
        try{
            Function function = new Function(
                    "recompensa",
                    List.of(),
                    List.of(new TypeReference<Uint256>() {})
            );

            String data = FunctionEncoder.encode(function);

            EthCall response = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            null,
                            contractAddress,
                            data
                    ),
                    DefaultBlockParameterName.LATEST
            ).send();

            List<Type> output = FunctionReturnDecoder.decode(
                    response.getValue(),
                    function.getOutputParameters()
            );

            if(output.isEmpty()){
                return BigInteger.ZERO;
            }

            return (BigInteger) output.get(0).getValue();

        }catch (Exception e){
            return BigInteger.ZERO;
        }
    }

    public String executeContract(String contractAddress, String company, String employee){
        try {
            Function function = new Function(
                    "ejecutar",
                    List.of(new Utf8String(company), new Utf8String(employee)),
                    Collections.emptyList()
            );

            String encodedFunction = FunctionEncoder.encode(function);
            BigInteger nonce = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                    .send().getTransactionCount();

            BigInteger gasPrice = Convert.toWei("2", Convert.Unit.GWEI).toBigInteger();
            BigInteger gasLimit = BigInteger.valueOf(300_000);

            RawTransaction rawTx = RawTransaction.createTransaction(
                    nonce,
                    gasPrice,
                    gasLimit,
                    contractAddress,
                    BigInteger.ZERO,
                    encodedFunction
            );

            byte[] signedMessage = TransactionEncoder.signMessage(rawTx, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            EthSendTransaction txResponse = web3j.ethSendRawTransaction(hexValue).send();

            if (txResponse.hasError()) {
                throw new RuntimeException("Error: " + txResponse.getError().getMessage());
            }

            return txResponse.getTransactionHash();

        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar contrato", e);
        }
    }
}
