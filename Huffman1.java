package coding;
import java.util.*;

public class Huffman1{

    // question 1 -a
    public static void construireArbre(char[] lettre, double[] probabilite, HashMap<Character, String> codes){
        Comparator<Noeud> comp= new Comparator<Noeud>(){
            public int compare(Noeud x, Noeud y){
                return Double.compare(x.data, y.data);
            }

        };

        PriorityQueue<Noeud> priorite= new PriorityQueue<Noeud>(comp);
        for(int i=0; i<lettre.length; i++){
            priorite.add(new Noeud(lettre[i], probabilite[i]));
        }
        while(priorite.size()>1){
            Noeud left=priorite.poll();
            Noeud right=priorite.poll();
            double somme= left.data + right.data;
            priorite.add(new Noeud('S', somme, left, right));
        }

        Noeud root = priorite.peek();
        printCode(root, "", codes);

    }

    public static void printCode(Noeud root, String s, HashMap<Character, String> si){
        if(root.gauche==null &&  root.droite==null && Character.isLetter(root.si)){
            si.put(root.si, s);
            return;
        }
        printCode(root.gauche, s+ "0", si);
        printCode(root.droite, s+ "1", si);
    }   

    public static void main(String[] args){
        char[] lettres= {'a', 'b', 'c', 'd', 'e', 'f'};
        double[] proba= {0.3, 0.2, 0.15, 0.15, 0.1, 0.1};
        HashMap<Character, String> code=new HashMap<>();
        construireArbre(lettres, proba, code);
        System.out.println("Codes:"+ code);
    }
    
}