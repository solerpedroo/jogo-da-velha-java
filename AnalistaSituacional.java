public class AnalistaSituacional
{
    private Tabuleiro tabDoJogo;

    public AnalistaSituacional (Tabuleiro tab) throws Exception
    {
        // colocar tab em tabDoJogo
        if (tab == null) throw new Exception("Tabuleiro invalido");
        this.tabDoJogo = tab;
    }

    public Marca getMarcaDoVencedor ()
    {
        // descobrir se ha um vencedor e retornar
        // a marca dele; retornara null, quando
        // nao houver vencedor
        try {
            for (int lin = 0; lin < 3; lin++) {
                Marca primeira = this.tabDoJogo.getMarcaNaPosicao(lin, 0);
                if (primeira != null && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(lin, 1)) && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(lin, 2))) return primeira;
            }

            for (int col = 0; col < 3; col++) {
                Marca primeira = this.tabDoJogo.getMarcaNaPosicao(0, col);
                if (primeira != null && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(1, col)) && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(2, col))) return primeira;
            }

            Marca primeira = this.tabDoJogo.getMarcaNaPosicao(0, 0);
            if (primeira != null && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(1, 1)) && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(2, 2))) return primeira;

            primeira = this.tabDoJogo.getMarcaNaPosicao(0, 2);
            if (primeira != null && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(1, 1)) && primeira.equals(this.tabDoJogo.getMarcaNaPosicao(2, 0))) return primeira;
        }
        catch (Exception erro)
        {}

        return null;
    }

    private boolean tabuleiroCheio ()
    {
        // verificar se todas as posicoes do
        // tabuleiro estao preenchidas (!=null)
        // retornando true em caso afirmativo,
        // ou false em caso negativo
        try {
            for (int lin = 0; lin < 3; lin++) {
                for (int col = 0; col < 3; col++) {
                    if (!this.tabDoJogo.haMarcaNaPosicao(lin, col)) return false;
                }
            }
        }
        catch (Exception erro) {
            return false;
        }
        return true;
    }

    public boolean fimDeJogo ()
    {
        // se getMarcaDoVencedor != null,
        // retorna true
        // se tabuleiroCheio == true,
        // retorna true
        // caso contrario, retorna false
        return this.getMarcaDoVencedor() != null || this.tabuleiroCheio();
    }

    @Override
    public String toString ()
    {
        // faça
        return this.tabDoJogo.toString();
    }

    @Override
    public boolean equals (Object obj)
    {
        // retornar true se this for igual a obj ou false caso contrario
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        AnalistaSituacional analista = (AnalistaSituacional)obj;
        return this.tabDoJogo.equals(analista.tabDoJogo);
    }

    @Override
    public int hashCode ()
    {
        // faça
        int retorno = 1;
        retorno = retorno * 2 + this.tabDoJogo.hashCode();
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }
}