package coding;
public class Noeud{
    double data;
    char si;
    Noeud gauche, droite;

    public Noeud(char si, double data){
        this.si=si;
        this.data=data;
        this.gauche=null;
        this.droite=null;
    }


    public Noeud(char si, double data, Noeud gauche, Noeud droite){
        this.si=si;
        this.data=data;
        this.gauche=gauche;
        this.droite=droite;
    }

}