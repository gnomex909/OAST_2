package OAST_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Evolution {
    private List<Result> bestResults;
    private Reader reader;
    public Evolution(Reader reader, long seed, double mutation, int firstGenAmount, int pairAmount, int childAmount, int deleteAmount, int generationLimit, int timeLimit){
        bestResults = new ArrayList<>();
        this.reader = reader;
        Long limit = System.currentTimeMillis() + 60 * 1000 * timeLimit;
        List<Result> results = new ArrayList<>();
        Random random = new Random(seed);
        for(int i =0; i<firstGenAmount;i++){
            Result result = generateRandom(reader.getDemandArrayList(), random);
            results.add(result);
        }
        setResults(results, reader.getDemandArrayList(), reader.getLinkArrayList());
//        System.out.println("First generation:");
//        for(Result r: results){
//            System.out.println(r);
//        }
        int generation = 0;
        while(generation<generationLimit && System.currentTimeMillis()<limit){
            System.out.println("Generation " + generation);
            generateNewGeneration(results, mutation, pairAmount, childAmount, deleteAmount, random);
            generation++;
        }
        new Writer(bestResults, reader.getLinkArrayList(), reader.getDemandArrayList());
    }
    private Result generateRandom(ArrayList<Demand> demandArrayList, Random random){
        List<List<Integer>> chromosomes = new ArrayList<>();
        for(Demand demand : demandArrayList){
            List<Integer> chromosome = new ArrayList<>();
            for(Path path : demand.getPathList()){
                chromosome.add(0);
            }
            for(int i =0; i<demand.getDemandValue();i++){
                int next = random.nextInt(demand.getPathList().size());
                chromosome.set(next, (chromosome.get(next)+1));
            }
            chromosomes.add(chromosome);
        }
        Result result = new Result(chromosomes);
        return result;
    }

    private void setResults(List<Result> results, ArrayList<Demand> demands, ArrayList<Link> linkArrayList){
//        System.out.println("Setting results");
        MathFunctions mathFunctions = new MathFunctions();
        int min = 99999;
//        System.out.println(results);
        Result best = new Result();
        for(Result r : results){
            mathFunctions.setUsages(r,demands);
            r.setResult(mathFunctions.setResult(linkArrayList));
            if(r.getResult()< min){
                min = r.getResult();
                best = new Result(r.getChromosomes(), r.getResult());
            }
            mathFunctions.zeroUsage(linkArrayList);
        }
//        System.out.println("Finished setting results");
        bestResults.add(best);
    }
    private void generateNewGeneration(List<Result> results, double mutation, int pairAmount, int childAmount, int deleteAmount, Random random){
//        System.out.println("Generating new generation");
        List<Result> newGen = new ArrayList<>();
        for(int i = 0;i<pairAmount;i++){
//            System.out.println("Pair: " + i);
            int chance = results.size() / 10;
            int middle = results.size() /2;
            Result first = results.get(i);
            Result second = results.get(random.nextInt(chance)+middle);
            for(int j =0; j<childAmount;j++){
//                System.out.println("Child: " + j);
                List<List<Integer>> chromosomes = new ArrayList<>();
                for(int k=0;k<first.getChromosomes().size();k++){
                    if(random.nextInt(2) == 1){
                        chromosomes.add(new ArrayList<>(first.getChromosomes().get(k)));
                    }else{
                        chromosomes.add(new ArrayList<>(second.getChromosomes().get(k)));
                    }
                    if(random.nextDouble()<mutation){
                        int source = random.nextInt(chromosomes.get(k).size());
                        int destination = random.nextInt(chromosomes.get(k).size());
//                        System.out.println("Mutation detected in " + k + "! Swapping " + source + "with " + destination);
                        int temp = chromosomes.get(k).get(source);
                        int temp2 = chromosomes.get(k).get(destination);
//                        System.out.println("Place: " + source + "Value: " + temp);
//                        System.out.println("Place: " +  destination+ "Value: "+ temp2);
                        chromosomes.get(k).set(source, temp2);
                        chromosomes.get(k).set(destination, temp);
//                        System.out.println("Place: " + source + "Value: " +chromosomes.get(k).get(source));
//                        System.out.println("Place: " +  destination+ "Value: "+ chromosomes.get(k).get(destination));
                    }
                }
//                System.out.println(first);
//                System.out.println(second);
//                System.out.println(chromosomes);
                newGen.add(new Result(chromosomes));
            }
        }
        results.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return (o1.getResult()-o2.getResult());
            }
        });
        for(int i =0; i<deleteAmount;i++){
            results.remove(results.size()-1);
        }
        setResults(newGen, reader.getDemandArrayList(), reader.getLinkArrayList());
        results.addAll(newGen);
    }
}
