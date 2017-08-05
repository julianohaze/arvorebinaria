/**
 * Created by julianosilva on 04/08/17.
 */
public class ArvoreBinaria {

    private Elemento raiz;

    public boolean contem(int valor) {
        Elemento current = raiz;
        while (current != null) {
            if (current.valor == valor) {
                return true;
            } else if (current.valor > valor) {
                current = current.esquerdo;
            } else {
                current = current.direito;
            }
        }
        return false;
    }

    public void inserir(int valor) {
        Elemento elemento = new Elemento(valor);
        if (arvoreVazia()) {
            raiz = elemento;
            return;
        }

        Elemento atual = raiz;
        Elemento pai = null;
        while (true) {
            pai = atual;
            if (valor < atual.valor) {
                atual = atual.esquerdo;
                if (atual == null) {
                    pai.esquerdo = elemento;
                    return;
                }
            } else {
                atual = atual.direito;
                if (atual == null) {
                    pai.direito = elemento;
                    return;
                }
            }
        }
    }

    public void imprimir() {
        imprimir(this.raiz);
    }

    public void imprimir(Elemento raiz) {
        if (raiz != null) {
            imprimir(raiz.esquerdo);
            System.out.print(" " + raiz.valor);
            imprimir(raiz.direito);
        }
    }

    /**
     * Remove um elemento da árvore binária
     *
     * @param valor
     * @return true se o elemento foi removido
     */
    public boolean remover(int valor) {
        Elemento pai = raiz;
        Elemento atual = raiz;
        boolean isFilhoEsquerdo = false;
        while (atual.valor != valor) {
            pai = atual;
            if (atual.valor > valor) {
                isFilhoEsquerdo = true;
                atual = atual.esquerdo;
            } else {
                isFilhoEsquerdo = false;
                atual = atual.direito;
            }
            if (atual == null) {
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if (!atual.possuiFilhos()) {
            if (atual == raiz) {
                raiz = null;
            }
            if (isFilhoEsquerdo) {
                pai.esquerdo = null;
            } else {
                pai.direito = null;
            }
        }
        // se o elemento possui apenas um filho
        else if (!atual.possuiFilhoDireito()) {
            if (atual == raiz) {
                raiz = atual.esquerdo;
            } else if (isFilhoEsquerdo) {
                pai.esquerdo = atual.esquerdo;
            } else {
                pai.direito = atual.esquerdo;
            }
        } else if (!atual.possuiFilhoEsquerdo()) {
            if (atual == raiz) {
                raiz = atual.direito;
            } else if (isFilhoEsquerdo) {
                pai.esquerdo = atual.direito;
            } else {
                pai.direito = atual.direito;
            }
        } else if (atual.possuiFilhos()) {

            Elemento elementoSubstituto = getMenorElementoNaArvoreDaDireita(atual);
            if (atual == raiz) {
                raiz = elementoSubstituto;
            } else if (isFilhoEsquerdo) {
                pai.esquerdo = elementoSubstituto;
            } else {
                pai.direito = elementoSubstituto;
            }
            elementoSubstituto.esquerdo = atual.esquerdo;
        }
        return true;
    }

    private Elemento getMenorElementoNaArvoreDaDireita(Elemento elemento) {
        Elemento substituto = null;
        Elemento substitutoPai = null;
        Elemento atual = elemento.direito;
        while (atual != null) {
            substitutoPai = substituto;
            substituto = atual;
            atual = atual.esquerdo;
        }
        // se o elemento substituto possuir filho à direita, nós o adicionaremos ao pai do elemento substituto no lado esquerdo
        if (!substituto.equals(elemento.direito)) {
            substitutoPai.esquerdo = substituto.direito;
            substituto.direito = elemento.direito;
        }
        return substituto;
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10); // raiz
        arvore.inserir(20); // direita
        arvore.inserir(5); // esquerda
        arvore.inserir(8); // esquerda > direita
        arvore.inserir(4); // esquerda > esquerda
        arvore.inserir(12); // direita > esquerda
        arvore.inserir(25); // direita > direita

        arvore.imprimir();
    }

    private boolean arvoreVazia() {
        return raiz == null;
    }

    public Elemento getRaiz() {
        return raiz;
    }
}
