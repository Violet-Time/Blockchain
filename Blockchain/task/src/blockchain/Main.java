package blockchain;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        Blockchain blockchain = null;
//        try {
//            blockchain = (Blockchain) SerializationUtils.deserialize("D:\\Dev\\Java\\Blockchain1\\Blockchain\\task\\blockchain.data");
//            if (!blockchain.check()){
//                throw new IOException();
//            }
//            //blockchain.print();
//        } catch (IOException | ClassNotFoundException e) {
//
//        }
//        System.out.print("Enter how many zeros the hash must starts with: ");
        //int size = scanner.nextInt();
        Blockchain blockchain = new Blockchain();
        CreatorMiners mining = new CreatorMiners(blockchain,10,15);
        mining.run();

//        if(blockchain == null || blockchain.getCount() != (size > 0 ? size : 1)){
//            blockchain = new Blockchain(size);
//        }

//        for (int i = 0; i < 5; i++){
//            blockchain.add();
//            try {
//                SerializationUtils.serialize(blockchain,"D:\\Dev\\Java\\Blockchain1\\Blockchain\\task\\blockchain.data");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
