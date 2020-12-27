package blockchain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Blockchain implements Serializable {
    private static long serialVersionUID = 1L;

    //private Blockchain instance = new Blockchain();
    private Deque<Block> list;
    private int countOfZeros = 0;

    Blockchain(){
        list = new LinkedList<>();
    }

    public int getCountOfZeros() {
        return countOfZeros;
    }

    public String getHashOfTheLastBlock(){
        return list.size() == 0 ? "0" : list.peekLast().getHash();
    }

    public int getIdOfTheLastBlock(){
        return list.size() == 0 ? 0 : list.peekLast().getId();
    }
    public int getSize(){
        return list.size();
    }


    synchronized public void add(Block block) {
        if (getHashOfTheLastBlock().equals(block.getPrevHash()) && checkCountOfZeros(block.getHash())) {
            list.add(block);
            System.out.print(block);
            if (countOfZeros < 5 && block.getTimeGeneration() < 1){
                countOfZeros++;
                System.out.println("N was increased to " + countOfZeros);
            }else if (countOfZeros > 0 && block.getTimeGeneration() > 5){
                countOfZeros--;
                System.out.println("N was decreased by " + countOfZeros);
            }else {
                System.out.println("N stays the same");
            }
            System.out.println("");

        }

//        int beginTime = LocalTime.now().toSecondOfDay();
//        list.add(new Block(list.size() + 1, count, list.size() == 0 ? "0" : list.get(list.size() - 1).getaHashOfAllFieldsOfaBLock()));
//        System.out.println(list.get(list.size() - 1));
        serialVersionUID++;
    }

    public boolean checkCountOfZeros(String HashOfAllFieldsOfaBLock){
        return HashOfAllFieldsOfaBLock.matches("^0{" + countOfZeros + "}.+");
    }

    public boolean check(){
        if (list.size() > 0 && "0".equals(list.peekFirst().getPrevHash())) {
            Iterator iterator = list.iterator();
            Block pred;
            Block curr = (Block) iterator.next();
            while (iterator.hasNext()){
                pred = curr;
                curr = (Block) iterator.next();
                if (!pred.getHash().equals(curr.getPrevHash())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void print(){
        list.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "list=" + list +
                '}';
    }
}
