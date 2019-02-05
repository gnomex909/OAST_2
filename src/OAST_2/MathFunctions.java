package OAST_2;

import java.util.*;

public class MathFunctions {
    public <T> Set<List<T>> generatePermutations(List<T> list) {
        List<T> in = new ArrayList<>(list);
        Set<List<T>> out = new HashSet<>(factorial(list.size()));

        int n = list.size();
        int[] p = new int[n +1];
        for (int i = 0; i < p.length; i ++) {
            p[i] = i;
        }
        int i = 0;
        while (i < n) {
            p[i]--;
            int j = 0;
            if (i % 2 != 0) { // odd?
                j = p[i];
            }
            // swap
            T iTmp = in.get(i);
            in.set(i, in.get(j));
            in.set(j, iTmp);

            i = 1;
            while (p[i] == 0){
                p[i] = i;
                i++;
            }
            out.add(new ArrayList<>(in));
        }
        return out;
    }

    private static int factorial(int num) {
        int count = num;
        while (num != 1) {
            count *= --num;
        }
        return count;
    }
    public Set<List<Integer>> getIntegersSum(int num, long finishTime) {
//        System.out.println("Starting with " + num);
        Set<List<Integer>> returnSet = new HashSet<List<Integer>>();
        if(System.currentTimeMillis() < finishTime) {
            if (num == 0) {//                System.out.println("Example list: " + l);

                returnSet.add(new LinkedList<Integer>());
                return returnSet;
            }

            for (int i = 1; i <= num; i++) {
                Set<List<Integer>> listNMinI = getIntegersSum(num - i, finishTime);
                for (List<Integer> l : listNMinI) {
                    l.add(0, i);
                    returnSet.add(l);
                }
            }
        }
        return returnSet;
    }
    public void setUsages(Result r, List<Demand> demandList){
//        System.out.println(r);
            for(int i =0; i<r.getChromosomes().size();i++){
                List<Integer> chromosome = r.getChromosomes().get(i);
                ArrayList<Path> pathArrayList = demandList.get(i).getPathList();
                for(int k =0; k<chromosome.size();k++){
                    int traffic = chromosome.get(k);
                    for(Link link : pathArrayList.get(k).getLinkList()){
                        link.increaseUsage(traffic);
                    }
                }
            }
    }
    public void zeroUsage(ArrayList<Link> linkArrayList){
        for(Link link : linkArrayList){
            link.setUsage(0);
        }
    }
    public int setResult(ArrayList<Link> linkArrayList){
        //Włókna, podzielić przez dwa i w górę
        int result = 0;
        int max = 0;
        for(Link link : linkArrayList){
            max += link.getCapacity()/2;
            if(link.getCapacity()/2 >=Math.ceil(link.getUsage()/2)){
                result += Math.ceil(link.getUsage()/2);
            }
            else{
                result += Math.ceil((link.getCapacity()*5 + link.getUsage())/2);
            }
        }
        return (result-max);
    }
}
