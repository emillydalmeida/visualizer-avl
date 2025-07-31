import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL {
    private NoAVL raiz;
    
    public ArvoreAVL() {
        this.raiz = null;
    }
    
    private int obterAltura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private void atualizarAltura(NoAVL no) {
        if (no != null) {
            no.altura = 1 + Math.max(obterAltura(no.esquerda), obterAltura(no.direita));
        }
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;
        
        x.direita = y;
        y.esquerda = T2;
        
        atualizarAltura(y);
        atualizarAltura(x);
        
        return x; 
    }
    
    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;
        
        y.esquerda = x;
        x.direita = T2;
        
        atualizarAltura(x);
        atualizarAltura(y);
        
        return y; 
    }
    
    private NoAVL rotacaoEsquerdaDireita(NoAVL no) {
        no.esquerda = rotacaoEsquerda(no.esquerda);
        return rotacaoDireita(no);
    }
    
    private NoAVL rotacaoDireitaEsquerda(NoAVL no) {
        no.direita = rotacaoDireita(no.direita);
        return rotacaoEsquerda(no);
    }
    
    public boolean inserir(int valor) {
        int tamanhoAntes = tamanho();
        raiz = inserirRecursivo(raiz, valor);
        boolean sucesso = tamanho() > tamanhoAntes;
        
        if (sucesso) {
            System.out.println("✓ Valor " + valor + " inserido com sucesso!");
        } else {
            System.out.println("✗ Valor " + valor + " já existe na árvore!");
        }
        
        return sucesso;
    }
    
    private NoAVL inserirRecursivo(NoAVL no, int valor) {
        if (no == null) {
            return new NoAVL(valor);
        }
        
        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            return no;
        }
        
        atualizarAltura(no);
        
        int fatorBalanceamento = no.getFatorBalanceamento();
        
        
        if (fatorBalanceamento > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }
        
        if (fatorBalanceamento < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }
        
        if (fatorBalanceamento > 1 && valor > no.esquerda.valor) {
            return rotacaoEsquerdaDireita(no);
        }
        
        if (fatorBalanceamento < -1 && valor < no.direita.valor) {
            return rotacaoDireitaEsquerda(no);
        }
        
        return no; 
    }
    
    public boolean remover(int valor) {
        int tamanhoAntes = tamanho();
        raiz = removerRecursivo(raiz, valor);
        boolean sucesso = tamanho() < tamanhoAntes;
        
        if (sucesso) {
            System.out.println("✓ Valor " + valor + " removido com sucesso!");
        } else {
            System.out.println("✗ Valor " + valor + " não encontrado na árvore!");
        }
        
        return sucesso;
    }
    
    private NoAVL removerRecursivo(NoAVL no, int valor) {
        if (no == null) {
            return no;
        }
        
        if (valor < no.valor) {
            no.esquerda = removerRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = removerRecursivo(no.direita, valor);
        } else {
            if (no.esquerda == null || no.direita == null) {
                NoAVL temp = (no.esquerda != null) ? no.esquerda : no.direita;
                
                if (temp == null) {
                    temp = no;
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAVL temp = encontrarMinimo(no.direita);
                
                no.valor = temp.valor;
                
                no.direita = removerRecursivo(no.direita, temp.valor);
            }
        }
        
        if (no == null) {
            return no;
        }
        
        atualizarAltura(no);
        
        int fatorBalanceamento = no.getFatorBalanceamento();
        
        if (fatorBalanceamento > 1 && no.esquerda.getFatorBalanceamento() >= 0) {
            return rotacaoDireita(no);
        }
        
        if (fatorBalanceamento > 1 && no.esquerda.getFatorBalanceamento() < 0) {
            return rotacaoEsquerdaDireita(no);
        }
        
        if (fatorBalanceamento < -1 && no.direita.getFatorBalanceamento() <= 0) {
            return rotacaoEsquerda(no);
        }
        
        if (fatorBalanceamento < -1 && no.direita.getFatorBalanceamento() > 0) {
            return rotacaoDireitaEsquerda(no);
        }
        
        return no;
    }
    
    private NoAVL encontrarMinimo(NoAVL no) {
        while (no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }
    
    public boolean buscar(int valor) {
        List<Integer> caminho = new ArrayList<>();
        boolean encontrado = buscarRecursivo(raiz, valor, caminho);
        
        System.out.print("Caminho da busca por " + valor + ": ");
        if (caminho.isEmpty()) {
            System.out.println("Árvore vazia");
        } else {
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print(caminho.get(i));
                if (i < caminho.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
        
        if (encontrado) {
            System.out.println("✓ Valor " + valor + " encontrado!");
        } else {
            System.out.println("✗ Valor " + valor + " não encontrado!");
        }
        
        return encontrado;
    }
    
    private boolean buscarRecursivo(NoAVL no, int valor, List<Integer> caminho) {
        if (no == null) {
            return false;
        }
        
        caminho.add(no.valor);
        
        if (valor == no.valor) {
            return true;
        } else if (valor < no.valor) {
            return buscarRecursivo(no.esquerda, valor, caminho);
        } else {
            return buscarRecursivo(no.direita, valor, caminho);
        }
    }
    
    public int tamanho() {
        return tamanhoRecursivo(raiz);
    }
    
    private int tamanhoRecursivo(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return 1 + tamanhoRecursivo(no.esquerda) + tamanhoRecursivo(no.direita);
    }
    
    public boolean estaVazia() {
        return raiz == null;
    }
    
    public void imprimirArvore() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }
        
        System.out.println("\n=== Estado atual da árvore ===");
        imprimirHierarquico(raiz, "", true);
        System.out.println("==============================");
    }
    
    private void imprimirHierarquico(NoAVL no, String prefixo, boolean ehUltimo) {
        if (no != null) {
            System.out.println(prefixo + (ehUltimo ? "└── " : "├── ") + no.valor);
            
            String novoPrefixo = prefixo + (ehUltimo ? "    " : "│   ");
            
            if (no.esquerda != null || no.direita != null) {
                if (no.direita != null) {
                    imprimirHierarquico(no.direita, novoPrefixo, no.esquerda == null);
                }
                if (no.esquerda != null) {
                    imprimirHierarquico(no.esquerda, novoPrefixo, true);
                }
            }
        }
    }
    
    public void imprimirParentizada() {
        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }
        
        System.out.println("\n=== Representação Parentizada ===");
        System.out.println(parentizarRecursivo(raiz));
        System.out.println("=================================");
    }
    
    private String parentizarRecursivo(NoAVL no) {
        if (no == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(no.valor);
        
        if (no.esquerda != null || no.direita != null) {
            sb.append("(");
            
            if (no.esquerda != null) {
                sb.append(parentizarRecursivo(no.esquerda));
            } else {
                sb.append("_");
            }
            
            sb.append(",");
            
            if (no.direita != null) {
                sb.append(parentizarRecursivo(no.direita));
            } else {
                sb.append("_");
            }
            
            sb.append(")");
        }
        
        return sb.toString();
    }
}
