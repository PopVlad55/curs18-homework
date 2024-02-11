package org.fasttrackit.curs18homework;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions(@RequestParam(required = false) String product,
                                                @RequestParam(required = false) TransactionType type,
                                                @RequestParam(required = false) Double minAmount,
                                                @RequestParam(required = false) Double maxAmount) {
        return transactionService.getAllTransactions(product, type, minAmount, maxAmount);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction replaceTransaction(@PathVariable long id, @RequestBody Transaction newTransaction) {
        return transactionService.replaceTransaction(id, newTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/reports/type")
    public Map<TransactionType, List<Transaction>> getTypeReports() {
        return transactionService.getTypeReports();
    }

    @GetMapping("/reports/product")
    public Map<String, List<Transaction>> getProductReports() {
        return transactionService.getProductReports();
    }
}