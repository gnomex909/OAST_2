package OAST_2;

import java.util.List;

public class Result {
    private List<List<Integer>> chromosomes;
    private int result;

    public Result() {
    }

    public Result(List<List<Integer>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public String toString() {
        return "Result{" +
                "chromosomes=" + chromosomes +
                ", result=" + result +
                '}';
    }

    public Result(List<List<Integer>> chromosomes, int result) {
        this.chromosomes = chromosomes;
        this.result = result;
    }

    public List<List<Integer>> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<List<Integer>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
