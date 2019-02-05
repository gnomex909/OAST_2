package OAST_2;

import java.util.*;

public class BruteForce {
    /*
    Dla ułatwienia, wyobraź sobie te dwie listy jako listy dwuwymiarowych macierzy, set to lista z sprawdzeniem czy nie ma identycznych elementów już wewnątrz
     */
    private List<Set<List<Integer>>> possibleSums;
    private List<Set<List<Integer>>> permutations;
    private List<Result> results;
/*
Klasa mająca za zadanie wygenerować metodą Brute Force wszystkie możliwe rozwiązania. Uwaga, z uwagi na ograniczenie indeksu list, nie jesteśmy w stanie wyliczyć sytuacji,
gdy liczba rozwiązań przekracza integer.

Jako parametr bierze obiekt reader, który zawiera potrzebne do generacji listy obiektów.
 */
    public BruteForce(Reader reader, int timeLimit){
        possibleSums = new ArrayList<>();
        permutations = new ArrayList<>();
        long finishTime = System.currentTimeMillis()+(timeLimit*1000);
        MathFunctions mathFunctions = new MathFunctions();
        ArrayList<Demand> demands = reader.getDemandArrayList();
        System.out.println("Started with Demands");
//        Rozbijamy liczbę danego demandu na wszystkie możliwe sumy
        for(Demand d : demands){
            if (System.currentTimeMillis()< finishTime) {
                System.out.println("Starting with " + d.getDemandValue());
                Set<List<Integer>> sums = mathFunctions.getIntegersSum(d.getDemandValue(),finishTime);
                List<List<Integer>> toRemove = new ArrayList<>();
                for (List<Integer> list : sums) {
//                Jeśli suma jest większa niż liczba ścieżek, to dodajemy do listy do usunięcia, jeśli jest mniejsze - dodajemy zera aż do liczby ścieżek
                    if (list.size() > d.getPathList().size()) {//            System.out.println(d);

                        toRemove.add(list);
                    } else {
                        int i = d.getPathList().size() - list.size();
                        for (int a = 0; a < i; a++) {
                            list.add(0);
                        }
                    }
                }
                sums.removeAll(toRemove);
                possibleSums.add(sums);//            System.out.println(sums);
            }
        }
        for(Set<List<Integer>> set : possibleSums){
//            Dla każdego elementu z zestawu możliwych sum, generujemy wszystkie możliwe permutacje
            if (System.currentTimeMillis()< finishTime) {
                Set<List<Integer>> chromosomePermutations = new HashSet<>();
                for (List<Integer> list : set) {
                    Set<List<Integer>> result = mathFunctions.generatePermutations(list);
                    chromosomePermutations.addAll(result);//            System.out.println("Final sums:" + sums);
                }
                permutations.add(chromosomePermutations);
            }
        }
        if(System.currentTimeMillis()< finishTime) {
            Iterator<Set<List<Integer>>> iter = permutations.iterator();
        List<List<List<Integer>>> res = new ArrayList<>();
//        Iteracyjnie tworzymy wszystkie możliwe rozwiązanie
        while(iter.hasNext()){
            Set<List<Integer>> chromosomes = iter.next();
//            System.out.println(chromosomes.size());
//            Jeśli nie jest to pierwsze wykonanie, dla każdego istniejącego wariantu rozwiązania dodajemy każda możliwość permutacje następnego zapotrzebowania
            if(res.size()>0){
                List<List<List<Integer>>> newRes = new ArrayList<>();
                for(List<List<Integer>> list : res){
                    for(List<Integer> temp : chromosomes){
                        List<List<Integer>> chrom = new ArrayList<>(list);
                        chrom.add(temp);
                        newRes.add(chrom);
                    }
                }
                res = new ArrayList<>(newRes);
//                Jeśli to pierwsze wykonanie algorytmu, permutacje pierwszego zapotrzebowania to wszystkie elementy
            }else{
                for(List<Integer> list :chromosomes){
                    List<List<Integer>> temp = new ArrayList<>();
                    temp.add(list);
                    res.add(temp);
                }
            }
        }
        results = new ArrayList<>();
//        Tworzymy listę wyników
        for(List<List<Integer>> list : res){
            results.add(new Result(list));
        }
        System.out.println("Results amount: " + results.size());
//        Wywołujemy zapis do pliku
        new Writer(results,reader.getLinkArrayList(), demands);
    }}
}
