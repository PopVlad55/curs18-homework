package org.fasttrackit.curs18homework;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getAllTransactions(String product, TransactionType type, Double minAmount, Double maxAmount) {
        return transactions.stream()
                .filter(t -> product == null || t.getProduct().equals(product))
                .filter(t -> type == null || t.getType() == type)
                .filter(t -> minAmount == null || t.getAmount() >= minAmount)
                .filter(t -> maxAmount == null || t.getAmount() <= maxAmount)
                .toList();
    }

    public Transaction getTransactionById(long id) {
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setId(transactions.size() + 1);
        transactions.add(transaction);
        return transaction;
    }

    public Transaction replaceTransaction(long id, Transaction newTransaction) {
        transactions = transactions.stream()
                .map(t -> t.getId() == id ? newTransaction : t)
                .toList();
        return newTransaction;
    }

    public void deleteTransaction(long id) {
        transactions = transactions.stream()
                .filter(t -> t.getId() != id)
                .toList();
    }

    public Map<TransactionType, List<Transaction>> getTypeReports() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getProductReports() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
