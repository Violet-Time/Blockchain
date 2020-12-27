package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Miner implements Runnable {

    private int id;
    private String hashOfThePreviousBlock;
    private Blockchain blockchain;
    private Map<String, Transaction> transactions;

    Miner(Blockchain blockchain, Map<String, Transaction> transactions){
        this.id = blockchain.getIdOfTheLastBlock() + 1;
        this.hashOfThePreviousBlock = blockchain.getHashOfTheLastBlock();
        this.blockchain = blockchain;
        this.transactions = transactions;
    }

    private String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void startMining(){
        //System.out.println(Thread.currentThread().getName());
        long timeStamp = new Date().getTime();
        int magic;
        String hash;
        Random random = new Random();
        while (true) {
            if(blockchain.getIdOfTheLastBlock() + 1 != id) {
                break;
            }
            magic = random.nextInt(100000000);
            hash = applySha256("" + id + timeStamp + hashOfThePreviousBlock + magic + transactions.toString());

            if (blockchain.checkCountOfZeros(hash)) {
                blockchain.add(new Block(Thread.currentThread().getName().substring(14),
                        id,
                        timeStamp,
                        (int) ( new Date().getTime() - timeStamp) / 1000,
                        hashOfThePreviousBlock,
                        hash,
                        magic,
                        transactions));
                break;
            }
        }

    }

    private void update(){
        this.id = blockchain.getIdOfTheLastBlock() + 1;
        this.hashOfThePreviousBlock = blockchain.getHashOfTheLastBlock();
        this.blockchain = blockchain;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        startMining();
    }
}
