package OAST_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
Klasa, która wczytuje informacje z pliku. W konstruktorze ustala na zmiennych prywatnych wartości węzłów i demandów, będzie trzebe je później pobrać przez cokolwiek co będzie robić rzeczy dalej.
 */
public class Reader {
    private ArrayList<Link> linkArrayList;
    private ArrayList<Demand> demandArrayList;

    public ArrayList<Link> getLinkArrayList() {
        return linkArrayList;
    }

    public void setLinkArrayList(ArrayList<Link> linkArrayList) {
        this.linkArrayList = linkArrayList;
    }

    public ArrayList<Demand> getDemandArrayList() {
        return demandArrayList;
    }

    public void setDemandArrayList(ArrayList<Demand> demandArrayList) {
        this.demandArrayList = demandArrayList;
    }
/*
source - plik z którego wczytujemy dane
line - string szczytany z pliku
lineInBlock - linia danego bloku, używana do testów i ustawienia ID Linków
blockNumber - który to blok tekstu, użwany po to, żeby rozrónić czy to Linki czy Demandy
 */
    public Reader(File source){
        linkArrayList = new ArrayList<>();
        demandArrayList = new ArrayList<>();
        ArrayList<Path> pathArrayList = new ArrayList<>();
        String line;
        int lineInBlock =0;
        int blockNumber = 0;
        try{
//            System.out.println("File read started");
            /*
            Wczytujemy plik i ustawiamy na niego bufferedReader by czytać po linii
             */
            FileReader fileReader = new FileReader(source);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
//                System.out.println("Starting reading line");
                /*
                Sprawdzamy czy linia zawiera jakiś tekst, by coś wczytywać, czy też nie, co oznacza, że przechodzimy do następnego bloku tekstu
                 */
                if(line.length() >0){
                    /*
                    Jesli to pierwszy blok tekstu, oznacza to, że mamy doczynienia z Linkami
                     */
                    if(blockNumber == 0){
                        /*
                        Sprawdzenie czy to pierwsza linia, wtedy nie potrzebujemy z niej tak naprawdę nic, czy też kolejne, zawierające linki.
                        Jeśli to następne linie, wczytujemy wartości i tworzymy obiekty typu Link, chyba, że linia zawierałą -1, to oznacza koniec tego bloku
                         */
                        if (lineInBlock == 0) {
//                            System.out.println("First line in first block");
                        }else {
//                            System.out.println("Scanning for numbers");
                            Scanner scanner = new Scanner(line);
                            ArrayList<Integer> nodeNumbers = new ArrayList<>();
                            while (scanner.hasNextInt()) {
                                nodeNumbers.add(scanner.nextInt());
                            }
                            if (!nodeNumbers.contains(-1)) {
//                                System.out.println("Adding node, lineInBlock " + lineInBlock);
                                linkArrayList.add(new Link(nodeNumbers.get(0), nodeNumbers.get(1), (nodeNumbers.get(2) * nodeNumbers.get(4)), lineInBlock));
                            } else {
//                                System.out.println("End of first block");
                            }
                        }
                        /*
                        Blok 1 nas nie interesuje, bo zawiera informacje o ilości demandów, która jest nam zbędna. Przechodzimy do następnych, które są pełne demandów
                         */
                    }else if(blockNumber>1){
                        /*
                        Jeśli to pierwsza linia, to tworzymy obiekt demandu i wczytujemy wartości startNode, endNode i value
                        W kolejnych liniach (poza numer 1, ona zawiera liczbę Pathów) opisują Pathe do danego Demanda
                         */
                        if(lineInBlock==0){
//                            System.out.println("Reading first line of demand block");
                            Scanner scanner = new Scanner(line);
                            ArrayList<Integer> demandNumbers = new ArrayList<>();
                            while (scanner.hasNextInt()) {
                                demandNumbers.add(scanner.nextInt());
                            }
                            demandArrayList.add(new Demand(demandNumbers.get(0),demandNumbers.get(1),demandNumbers.get(2)));
                        }else if(lineInBlock>1){
//                            System.out.println("Reading next lines of demand block");
                            Scanner scanner = new Scanner(line);
                            ArrayList<Integer> pathNumbers = new ArrayList<>();
                            while (scanner.hasNextInt()) {
                                pathNumbers.add(scanner.nextInt());
                            }
                            ArrayList<Link> linkList = new ArrayList<>();
                            int id = 0;
                            for(int i =0;i<pathNumbers.size();i++){
                                if(i==0){
                                    id = pathNumbers.get(i);
                                }else{
                                    linkList.add(linkArrayList.get(pathNumbers.get(i)-1));
                                }
                            }
                            pathArrayList.add(new Path(id, linkList));
                        }
                    }
                    lineInBlock++;
                }else{
                    /*
                    Jeśli pathArrayList istnieje, to oznacza, że należy go dodać do Demanda stworzonego w ostatnim bloku a następnie wyczyścić.
                     */
                    if(pathArrayList.size()>0){
//                        System.out.println("Setting pathList for Demand");
                        demandArrayList.get(blockNumber -2).setPathList(pathArrayList);
                        pathArrayList = new ArrayList<>();
                    }
                    blockNumber++;
                    lineInBlock=0;
                }
            }
            if(pathArrayList.size()>0){
//                        System.out.println("Setting pathList for Demand");
                demandArrayList.get(blockNumber -2).setPathList(pathArrayList);
                pathArrayList = new ArrayList<>();
            }
            bufferedReader.close();
//            System.out.println("Link array list size: " + linkArrayList.size());
//            System.out.println(linkArrayList);
//            System.out.println("Demand array list size:" + demandArrayList.size());
//            System.out.println(demandArrayList);
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
