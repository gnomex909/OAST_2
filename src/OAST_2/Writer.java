package OAST_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    public Writer(List<Result> results, ArrayList<Link> linkArrayList, ArrayList<Demand> demands){
        System.out.println("Starting writing file");
        try {
            File resultFile = new File("result.txt");
            resultFile.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
            int max = 0;
            int min = 99999;
            MathFunctions mathFunctions = new MathFunctions();
//            System.out.println(results);
            for(Result r : results){
                mathFunctions.setUsages(r,demands);
                r.setResult(mathFunctions.setResult(linkArrayList));
                if(r.getResult()< min){
                    min = r.getResult();
                }
                mathFunctions.zeroUsage(linkArrayList);
            }
            for(List<Integer> list : results.get(0).getChromosomes()){
                max = Math.max(max, list.size());
            }
            bufferedWriter.write("Generated: " + System.currentTimeMillis());
            bufferedWriter.newLine();
            for(Result result : results){
                mathFunctions.setUsages(result,demands);
                bufferedWriter.write(linkArrayList.size()+" RES: " + result.getResult() + "GEN: " + results.indexOf(result));
                if(result.getResult()==min){
                    bufferedWriter.write(" BEST");
                }
                bufferedWriter.newLine();
                for(Link link : linkArrayList){
                    bufferedWriter.write(link.getId()+" " + (int) Math.ceil(link.getUsage()/2) + " " + link.getCapacity()/2);
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
                bufferedWriter.write("" + demands.size());
                bufferedWriter.newLine();
                for(int i = 0; i<demands.size();i++){
                    bufferedWriter.newLine();
                    Demand d = demands.get(i);
                    bufferedWriter.write(i+1+" "+ d.getPathList().size());
                    bufferedWriter.newLine();
                    List<Integer> temp = result.getChromosomes().get(i);
                    for(int j = 0; j<temp.size();j++){
                        bufferedWriter.write(j+1+" "+temp.get(j));
                        bufferedWriter.newLine();
                    }
                }
                bufferedWriter.newLine();
                mathFunctions.zeroUsage(linkArrayList);
            }
            /*
            for(Result result : results){
                bufferedWriter.newLine();
                bufferedWriter.write("Result: " + result.getResult());
                bufferedWriter.newLine();
                bufferedWriter.write(" ");
                for(int i = 0; i< result.getChromosomes().size();i++){
                    bufferedWriter.write("_");
                }
                bufferedWriter.newLine();
                for(int i =0; i<max; i++){
                    bufferedWriter.write("|");
                    for(List<Integer> list : result.getChromosomes()){
                        if(list.size()>i){
                            bufferedWriter.write(list.get(i)+"");
                            if(list.get(i)<10){
                                bufferedWriter.write(" ");
                            }
                        }else{
                            bufferedWriter.write("  ");
                        }
                    }
                    bufferedWriter.write("|");
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(" ");
                for(int i = 0; i< result.getChromosomes().size();i++){
                    bufferedWriter.write("-");
                }
            }
            */
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println("File has been saved");
    }
}
