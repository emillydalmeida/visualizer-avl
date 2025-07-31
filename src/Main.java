import java.util.Scanner;

public class Main {
    private static ArvoreAVL arvore;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        arvore = new ArvoreAVL();
        scanner = new Scanner(System.in);
        
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    inserirValor();
                    break;
                case 2:
                    removerValor();
                    break;
                case 3:
                    buscarValor();
                    break;
                case 4:
                    arvore.imprimirArvore();
                    break;
                case 5:
                    arvore.imprimirParentizada();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Obrigado por usar o Visualizador de Árvores AVL!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│                  MENU                    │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│ 1. Inserir valor                        │");
        System.out.println("│ 2. Remover valor                        │");
        System.out.println("│ 3. Buscar valor                         │");
        System.out.println("│ 4. Visualizar árvore (hierárquica)      │");
        System.out.println("│ 5. Visualizar árvore (parentizada)      │");
        System.out.println("│ 0. Sair                                  │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine().trim());
            return opcao;
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
    
    private static int lerValor(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
            }
        }
    }
    
    private static void inserirValor() {
        System.out.println("\n=== INSERÇÃO ===");
        int valor = lerValor("Digite o valor a ser inserido: ");
        
        System.out.println("\nRealizando inserção...");
        boolean sucesso = arvore.inserir(valor);
        
        if (sucesso) {
            arvore.imprimirArvore();
        }
    }
    
    private static void removerValor() {
        if (arvore.estaVazia()) {
            System.out.println("\n=== REMOÇÃO ===");
            System.out.println("A árvore está vazia! Não há elementos para remover.");
            return;
        }
        
        System.out.println("\n=== REMOÇÃO ===");
        System.out.println("Estado atual da árvore:");
        arvore.imprimirArvore();
        
        int valor = lerValor("Digite o valor a ser removido: ");
        
        System.out.println("\nRealizando remoção...");
        boolean sucesso = arvore.remover(valor);
        
        if (sucesso) {
            arvore.imprimirArvore();
        }
    }
    
    private static void buscarValor() {
        if (arvore.estaVazia()) {
            System.out.println("\n=== BUSCA ===");
            System.out.println("A árvore está vazia! Não há elementos para buscar.");
            return;
        }
        
        System.out.println("\n=== BUSCA ===");
        System.out.println("Estado atual da árvore:");
        arvore.imprimirArvore();
        
        int valor = lerValor("Digite o valor a ser buscado: ");
        
        System.out.println("\nRealizando busca...");
        arvore.buscar(valor);
    }
}
