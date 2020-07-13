import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String transactionsFile = "src/resources/transactions.txt";
        TransactionsParser tp = new TransactionsParser(transactionsFile);

        List<Transaction> Transactions = tp.getAllTransactions();

        Apriori ap = new Apriori(Transactions);

        long startime = System.currentTimeMillis();

        ap.doApriori();

        long endtime = System.currentTimeMillis();

        System.out.println("Time: " + (endtime - startime));
    }
}
