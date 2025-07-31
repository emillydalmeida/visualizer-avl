public class NoAVL {
    public int valor;          
    public NoAVL esquerda;     
    public NoAVL direita;     
    public int altura;        
    
    public NoAVL(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;  
    }
    
    public int getFatorBalanceamento() {
        int alturaEsquerda = (esquerda != null) ? esquerda.altura : 0;
        int alturaDireita = (direita != null) ? direita.altura : 0;
        return alturaEsquerda - alturaDireita;
    }

    public boolean ehFolha() {
        return esquerda == null && direita == null;
    }
    
    public String toString() {
        return String.valueOf(valor);
    }
}
