import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Apriori {

    private int MinSupport;
    private List<Transaction> Transactions;
    private List<List<Candidate>> itemsets = new ArrayList<>();

    public Apriori(List<Transaction> transactionList) {

        MinSupport = (int) (transactionList.size() * 0.01);
        Transactions = transactionList;

        System.out.println("MinSupport: " + MinSupport);
    }

    public List<Candidate> InitialCandiates() {

        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        Transactions.forEach(t -> {
                    t.getArticles().forEach(a -> {
                                Candidate c = new Candidate(a);
                                if (!candidates.contains(c)) {
                                    candidates.add(c);
                                }
                            }
                    );
                }
        );
        candidates.sort((Candidate c1, Candidate c2) -> c1.getArticles().get(0).compareTo(c2.getArticles().get(0)));
        return candidates;
    }

    public List<Candidate> supportFilter(List<Candidate> candidates) {

        Iterator<Candidate> iterator = candidates.iterator();
        while(iterator.hasNext()){
            Candidate c = iterator.next();
            if (c.getSupport() < this.MinSupport) {
                iterator.remove();
            }
        }

        return candidates;
    }

    public List<Candidate> getSupportForCandidates(List<Candidate> candidates) {

        candidates.forEach(c -> {
            for(int i = 0; i < Transactions.size(); i++) {
                if (Transactions.get(i).getArticles().containsAll(c.getArticles()))
                    c.IncrementSuport();
            }
        });
        return candidates;
    }

    public int getSupportForCandidate(Candidate candidate) {
        AtomicInteger counter = new AtomicInteger();

        Transactions.forEach(t -> {
            if (t.containsAll(candidate.getArticles())) {
                counter.getAndIncrement();
            }
        });

        return counter.get();
    }

    public List<Candidate> generateCandidates(List<Candidate> lastCandidates) {

        List<Candidate> newCandidates = new ArrayList<>();

        for (int i = 0; i < lastCandidates.size(); i++) {
            for (int j = i+1; j < lastCandidates.size(); j++) {
                Candidate ci = lastCandidates.get(i);
                Candidate cj = lastCandidates.get(j);

                Candidate newcand = generateCItemset(ci, cj);

                if (newcand != null) {
                    newCandidates.add(newcand);
                }
            }
        }

        return newCandidates;
    }

    private Candidate generateCItemset(Candidate ci, Candidate cj) {

        List<Integer> iArts = ci.getArticles();
        List<Integer> jArts = cj.getArticles();

        for(int i = 0; i < ci.getArticles().size() -1 ; i++ )
        {
            if(iArts.get(i) != (jArts.get(i)))
            {
                return null;
            }
        }
        if(iArts.get(iArts.size()-1) >= jArts.get(iArts.size()-1))
        {
            return null;
        }
        else
        {
            return new Candidate(ci, jArts.get(iArts.size()-1));
        }
    }

    public void doApriori() {

        System.out.println("Epoch: 1");
        List<Candidate> initialCandiates = InitialCandiates();
        initialCandiates = getSupportForCandidates(initialCandiates);
        initialCandiates = supportFilter(initialCandiates);

        System.out.println("Initial Candidates in first epoch: " + initialCandiates.size());
        itemsets.add(0, initialCandiates);

        for (int k = 2; !itemsets.get(k-2).isEmpty(); k++) {
            System.out.println("Epoch: " + k);

            List<Candidate> currentIterationCandidates = generateCandidates(itemsets.get(k-2));
            currentIterationCandidates = getSupportForCandidates(currentIterationCandidates);
            currentIterationCandidates = supportFilter(currentIterationCandidates);
            itemsets.add(k-1, currentIterationCandidates);

            System.out.println("Candidates in Epoch: " + k + ": "+ currentIterationCandidates.size());
        }

        itemsets.forEach(set -> {
            System.out.println("Itemset:");
            set.forEach(candidate -> {
                System.out.println(candidate.toString());
            });
        });
    }
}
