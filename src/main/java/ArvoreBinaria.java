/**
 * Created by julianosilva on 04/08/17.
 */
public class ArvoreBinaria {

    private Elemento raiz;

    /**
     * @param valor
     * @return true se encontrou o elemento na árvore
     */
    public boolean contem(int valor) {
        return getElemento(valor) != null;
    }

    public Elemento getElemento(int valor) {
        Elemento elemento = raiz;
        while (elemento != null) {
            if (elemento.valor == valor) {
                return elemento;
            } else if (elemento.valor > valor) {
                elemento = elemento.esquerdo;
            } else {
                elemento = elemento.direito;
            }
        }
        return elemento != null && elemento.getValor() == valor ? elemento : null;
    }

    /**
     * Insere o elemento na árvore
     *
     * @param valor
     */
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

    /**
     * Imprime a árvore binária ordenada
     */
    public void imprimir() {
        imprimir(this.raiz);
    }

    private void imprimir(Elemento raiz) {
        if (raiz != null) {
            imprimir(raiz.esquerdo);
            System.out.print(" " + raiz.valor);
            imprimir(raiz.direito);
        }
    }

    public long somarFilhos(int valor) {
        Elemento elemento = getElemento(valor);
        if (elemento == null) {
            return 0;
        }
        long soma = elemento.getValor();
        soma = somarFilhos(soma, elemento.getDireito());
        soma = somarFilhos(soma, elemento.getEsquerdo());
        return soma;
    }

    private long somarFilhos(long soma, Elemento elemento) {
        if (elemento != null) {
            soma = soma + elemento.getValor();
            soma = somarFilhos(soma, elemento.getDireito());
            soma = somarFilhos(soma, elemento.getEsquerdo());
        }
        return soma;
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

        // se chegou aqui, significa que o elemento foi encontrado
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
