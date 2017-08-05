import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by julianosilva on 04/08/17.
 */
public class ArvoreBinariaTest {

    @Test
    public void deveInserirMaioresNaDireita() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(1);
        arvore.inserir(2);
        arvore.inserir(3);

        Elemento raiz = arvore.getRaiz();
        assertEquals(1, raiz.getValor());
        assertEquals(2, raiz.getDireito().getValor());
        assertEquals(3, raiz.getDireito().getDireito().getValor());
    }

    @Test
    public void deveInserirMenoresNaEsquerda() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(3);
        arvore.inserir(2);
        arvore.inserir(1);

        Elemento raiz = arvore.getRaiz();
        assertEquals(3, raiz.getValor());
        assertEquals(2, raiz.getEsquerdo().getValor());
        assertEquals(1, raiz.getEsquerdo().getEsquerdo().getValor());
    }

    @Test
    public void deveInserirMenoresNaEsquerdaEMaioresADireita() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10); // raiz
        arvore.inserir(20); // direita
        arvore.inserir(5); // esquerda
        arvore.inserir(8); // esquerda > direita
        arvore.inserir(4); // esquerda > esquerda
        arvore.inserir(12); // direita > esquerda
        arvore.inserir(25); // direita > direita

        Elemento raiz = arvore.getRaiz();
        assertEquals(10, raiz.getValor());
        assertEquals(20, raiz.getDireito().getValor());
        assertEquals(5, raiz.getEsquerdo().getValor());
        assertEquals(8, raiz.getEsquerdo().getDireito().getValor());
        assertEquals(4, raiz.getEsquerdo().getEsquerdo().getValor());
        assertEquals(12, raiz.getDireito().getEsquerdo().getValor());
        assertEquals(25, raiz.getDireito().getDireito().getValor());
    }

    @Test
    public void contem() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(5);
        arvore.inserir(8);
        arvore.inserir(4);
        arvore.inserir(12);
        arvore.inserir(25);

        assertTrue(arvore.contem(10));
        assertTrue(arvore.contem(20));
        assertTrue(arvore.contem(8));
        assertTrue(arvore.contem(4));
        assertTrue(arvore.contem(12));
        assertTrue(arvore.contem(25));
        assertFalse(arvore.contem(11));
        assertFalse(arvore.contem(30));
        assertFalse(arvore.contem(21));
    }

    @Test
    public void deveSubstituirElementoRemovidoPeloMenorItemDaDireita() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10); // raiz
        arvore.inserir(20); // direita
        arvore.inserir(5); // esquerda
        arvore.inserir(8); // esquerda > direita
        arvore.inserir(4); // esquerda > esquerda
        arvore.inserir(12); // direita > esquerda
        arvore.inserir(25); // direita > direita
        arvore.inserir(11); // direita > esquerda > esquerda
        arvore.inserir(13); // direita > esquerda > direita

        arvore.remover(10);

        assertEquals(11, arvore.getRaiz().getValor());
    }

    @Test
    public void removeElementoComFilhoNoLadoDireito() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10); // raiz
        arvore.inserir(20); // direita
        arvore.inserir(5); // esquerda
        arvore.inserir(8); // esquerda > direita
        arvore.inserir(4); // esquerda > esquerda
        arvore.inserir(12); // direita > esquerda
        arvore.inserir(25); // direita > direita
        arvore.inserir(11); // direita > esquerda > esquerda
        arvore.inserir(13); // direita > esquerda > direita
        arvore.inserir(30); // direita > direita > direita
        arvore.inserir(23); // direita > direita > esquerda
        arvore.inserir(24); // direita > direita > esquerda > direita

        arvore.remover(20);

        // 23 substitui o 20
        assertEquals(23, arvore.getRaiz().getDireito().getValor());
        // 24 era filho direito do 23 e agora passa a ser filho esquerdo do  25
        assertEquals(24, arvore.getRaiz().getDireito().getDireito().getEsquerdo().getValor());
    }

    @Test
    public void deveSomarFilhos() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(10); // raiz
        arvore.inserir(20); // direita
        arvore.inserir(5); // esquerda
        arvore.inserir(8); // esquerda > direita
        arvore.inserir(4); // esquerda > esquerda
        arvore.inserir(12); // direita > esquerda
        arvore.inserir(25); // direita > direita
        arvore.inserir(11); // direita > esquerda > esquerda
        arvore.inserir(13); // direita > esquerda > direita
        arvore.inserir(30); // direita > direita > direita
        arvore.inserir(23); // direita > direita > esquerda
        arvore.inserir(24); // direita > direita > esquerda > direita

        long soma25 = arvore.somarFilhos(25);
        assertEquals(102, soma25);
        
        long soma20 = arvore.somarFilhos(20);
        assertEquals(158, soma20);
    }
}
