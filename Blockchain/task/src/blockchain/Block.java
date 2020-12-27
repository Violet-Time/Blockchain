package blockchain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Block implements Serializable{
    private final String numberMainer;
    private final int id;
    private final long timeStamp;
    private final int timeGeneration;
    private final String prevHash;
    private final String hash;
    private final int magic;
    private final Map<String, Transaction> transactions;

    Block(String numberMainer, int id, long timeStamp, int timeGeneration,
          String prevHash, String hash,
          int magic, Map<String, Transaction> transactions) {
        this.numberMainer = numberMainer;
        this.id = id;
        this.timeStamp = timeStamp;
        this.timeGeneration = timeGeneration;
        this.prevHash = prevHash;
        this.hash = hash;
        this.magic = magic;
        this.transactions = transactions;
    }

    public String getNumberMainer() {
        return numberMainer;
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getTimeGeneration() {
        return timeGeneration;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if(transactions.isEmpty()) {
            str.append("\nNo transactions");
        }else {
            transactions.forEach((k,v)->{
                if (v.getTypeTransaction() == Transaction.TypeTransaction.SENT) {
                    str.append("\n").append(k).append(v.toString());
                }
            });
        }
        return "Block:" +
                "\nCreated by: miner" + numberMainer +
                "\nminer" + numberMainer + " gets " + 100 + " VC" +
                "\nId: " + id +
                "\nTimestamp: " + timeStamp +
                "\nMagic number: " + magic +
                "\nHash of the previous block:\n" + prevHash +
                "\nHash of the block:\n" + hash +
                "\nBlock data:" + str +
                "\nBlock was generating for "+ timeGeneration +" seconds\n";
    }
}
