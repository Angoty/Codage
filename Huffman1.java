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

    // question 1 -b
    public static String encoderTexte(String texte, HashMap<Character, String> codes){
        StringBuilder encodage= new StringBuilder();
        for(char c: texte.toCharArray()){
            encodage.append(codes.get(c));
        }
        return encodage.toString();
    }

    // question 1 -c
    public static String decoderTexte(String texteEncode, HashMap<String, Character> reverse){
        StringBuilder decodage= new StringBuilder();
        int voalohany= 0;
        for(int b=1; b<=texteEncode.length(); b++){
            String code = texteEncode.substring(voalohany, b);
            if(reverse.containsKey(code)){
                decodage.append(reverse.get(code));
                voalohany = b;
            }
        }
        return decodage.toString();
    }

    public static void main(String[] args){
        char[] lettres= {'a', 'b', 'c', 'd', 'e', 'f'};
        double[] proba= {0.3, 0.2, 0.15, 0.15, 0.1, 0.1};
        HashMap<Character, String> code=new HashMap<>();
        construireArbre(lettres, proba, code);

        HashMap<String, Character> reverse=new HashMap<>();
        for(Map.Entry<Character, String> entry : code.entrySet()){
            reverse.put(entry.getValue(), entry.getKey());
        }

        System.out.println("Codes:"+ code);
        String text="abcd";
        String encode= encoderTexte(text, code);
        String decode= decoderTexte(encode, reverse);
        System.out.println("Etat original: "+ text);
        System.out.println("Etat encode: "+encode);
        System.out.println("Etat decode: "+decode);

    }
    
}