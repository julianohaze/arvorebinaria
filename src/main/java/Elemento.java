/**
 * Created by julianosilva on 04/08/17.
 */
public class Elemento {
    int valor;
    Elemento direito;
    Elemento esquerdo;

    public Elemento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public Elemento getDireito() {
        return direito;
    }

    public Elemento getEsquerdo() {
        return esquerdo;
    }

    public boolean possuiFilhos() {
        return this.esquerdo != null && this.direito != null;
    }

    public boolean possuiFilhoDireito() {
        return this.direito != null;
    }

    public boolean possuiFilhoEsquerdo() {
        return this.esquerdo != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Elemento)) return false;

        Elemento elemento = (Elemento) o;

        return valor == elemento.valor;
    }

    @Override
    public int hashCode() {
        return valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
