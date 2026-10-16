public class Tabuleiro implements Cloneable
{
    // a principio, toda igual a null
    private Marca matriz [][] = new Marca [3][3];

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        if (lin < 0 || lin >= this.matriz.length || col < 0 || col >= this.matriz[0].length) throw new Exception("Posicao invalida");
        return this.matriz[lin][col] != null;
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        if (lin < 0 || lin >= this.matriz.length || col < 0 || col >= this.matriz[0].length) throw new Exception("Posicao invalida");
        return this.matriz[lin][col];
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        if (lin < 0 || lin >= this.matriz.length || col < 0 || col >= this.matriz[0].length) throw new Exception("Posicao invalida");
        if (mrc == null) throw new Exception("Marca invalida");
        this.matriz[lin][col] = mrc;
    }

    @Override
    public String toString ()
    {
        String ret = "";

        for (int lin = 0; lin < this.matriz.length; lin++) {
            for (int col = 0; col < this.matriz[lin].length; col++) {
                if (col > 0) ret += " | ";
                if (this.matriz[lin][col] == null) ret += " ";
                else ret += this.matriz[lin][col].toString();
            }
            if (lin < this.matriz.length - 1) ret += "\n---------\n";
        }
        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Tabuleiro tabuleiro = (Tabuleiro)obj;

        for (int lin = 0; lin < this.matriz.length; lin++) {
            for (int col = 0; col < this.matriz[lin].length; col++) {
                if (this.matriz[lin][col] == null) {
                    if (tabuleiro.matriz[lin][col] != null) return false;
                } else if (!this.matriz[lin][col].equals(tabuleiro.matriz[lin][col]))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode ()
    {
        int retorno = 1;
        for (int lin = 0; lin < this.matriz.length; lin++){
            for (int col = 0; col < this.matriz[lin].length; col++) {
                retorno = retorno * 31 + (this.matriz[lin][col] == null ? 0 : this.matriz[lin][col].hashCode());
            }
        }
        return retorno;
    }

    // construtor de copia
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        if (modelo == null) throw new Exception("Tabuleiro invalido");

        for (int lin = 0; lin < this.matriz.length; lin++) {
            for (int col = 0; col < this.matriz[lin].length; col++) {
                if (modelo.matriz[lin][col] != null)
                    this.matriz[lin][col] = new Marca(modelo.matriz[lin][col].getSimbolo());
            }
        }
    }

    @Override
    public Object clone ()
    {
        Tabuleiro retorno = null;
        try {
            retorno = new Tabuleiro(this);
        } catch (Exception erro) {}
        return retorno;
    }
}
