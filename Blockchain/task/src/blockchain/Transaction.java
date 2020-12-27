package blockchain;

public class Transaction {
    private final TypeTransaction typeTransaction;
    private final int countVC;
    private final String name;

    Transaction(TypeTransaction typeTransaction, int countVC, String name) {
        this.typeTransaction = typeTransaction;
        this.countVC = countVC;
        this.name = name;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public enum TypeTransaction{
        GET,SENT
    }

    @Override
    public String toString() {
        return " sent " + countVC + " VC to " + name;
    }
}
