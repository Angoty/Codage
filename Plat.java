import java.util.HashMap;
public class Plat{

    public static String conversion(int n, int b){
        if(n==0){
            return "0";
        }
        StringBuilder resultat=new StringBuilder();
        while(n>0){
            int r= n%b;
            if(r<10){
                resultat.append(r);
            }
            else{
                char a= (char) ('A'+ r-10);
                resultat.append(a);
            }
            n = n/b;
        }
        return resultat.reverse().toString();
    }
    public static int conversionDecimale(String nombre, int base){
        if(nombre.equals("0")){
            return 0;
        }
        int resultat=0;
        int longueur=nombre.length();
        for(int i=longueur -1; i>=0 ;i--){
            int res= Character.getNumericValue(nombre.charAt(i));
            resultat += res * Math.pow(base, longueur -i -1);
        }
        return resultat;

    }
    public static String conversionBinHex(String nombre){
        HashMap <String, Character> version= new HashMap<String, Character>();
        version.put("0000", '0');
        version.put("0001", '1');
        version.put("0010", '2');
        version.put("0011", '3');
        version.put("0100", '4');
        version.put("0101", '5');
        version.put("0110", '6');
        version.put("0111", '7');
        version.put("1000", '8');
        version.put("1001", '9');
        version.put("1010", 'A');
        version.put("1011", 'B');
        version.put("1100", 'C');
        version.put("1101", 'D');
        version.put("1110", 'E');
        version.put("1111", 'F');

        if(nombre.length() %4 !=0){
            int difference= 4-(nombre.length()%4);
            nombre= "0".repeat(difference)+ nombre;
        }
        StringBuilder resultat=new StringBuilder();
        for(int i= nombre.length() -4; i>=0; i-=4 ){
            String decoupage= nombre.substring(i, i+4);
            char chiffre = version.get(decoupage);
            resultat.insert(0, chiffre);
        }
       return resultat.toString();

    }

    public static String conversionBinOct(String nombre){
        if(nombre.length() %3 !=0){
            int difference= 3-(nombre.length()%3);
            nombre= "0".repeat(difference)+ nombre;
        }
        StringBuilder resultat=new StringBuilder();
        for(int i= nombre.length() -3; i>=0; i-=3 ){
            String decoupage= nombre.substring(i, i+3);
            int chiffre = Integer.parseInt(decoupage, 2);
            resultat.insert(0, chiffre);
        }
        return resultat.toString();
    }

  
    public static String conversionBaseVersBase(String nombre, int base, int base1){
        int conversion = conversionDecimale(nombre, base);
        String resultat = conversion(conversion, base1); 
        return resultat;
    }

    public static void main(String[] args){
        System.out.println(conversionBaseVersBase("3030", 4, 16));  
    }
}