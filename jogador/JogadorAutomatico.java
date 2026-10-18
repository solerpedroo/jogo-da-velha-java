package jogador;

public class JogadorAutomatico implements Jogador
{
    private Marca     marca     = null;
    private Tabuleiro tabDoJogo = null;

    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro invalido");
        this.tabDoJogo = tab;
        if ((int)(Math.random() * 2) == 0) this.marca = new Marca('X');
        else this.marca = new Marca('O');
    }

    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro invalido");
        if (mrc == null) throw new Exception("Marca invalida");
        this.tabDoJogo = tab;
        this.marca = mrc;
    }

    public Marca getMarca ()
    {
        return this.marca;
    }

    public void facaSuaJogada ()
    {
        try
        {
            // Primeiro tento completar uma trinca para ganhar a partida.
            int[] posicao = this.buscarPosicao(this.marca, 2);
            if (posicao == null)
            {
                // Se nao posso ganhar agora, tento impedir a vitoria do adversario.
                Marca marcaAdversaria;
                if (this.marca.getSimbolo() == 'X') marcaAdversaria = new Marca('O');
                else marcaAdversaria = new Marca('X');
                posicao = this.buscarPosicao(marcaAdversaria, 2);
            }

            // Se nao preciso bloquear, procuro uma trinca com uma marca minha
            // e duas casas livres para preparar uma proxima jogada.
            if (posicao == null) posicao = this.buscarPosicao(this.marca, 1);

            if (posicao != null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, posicao[0], posicao[1]);
                return;
            }

            // Se nenhuma estrategia servir, jogo na primeira casa livre.
            for (int lin = 0; lin < 3; lin++)
            {
                for (int col = 0; col < 3; col++)
                {
                    if (!this.tabDoJogo.haMarcaNaPosicao(lin, col))
                    {
                        this.tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                        return;
                    }
                }
            }
        }
        catch (Exception erro)
        {}
    }

    private int[] buscarPosicao (Marca marca, int quantidadeNecessaria) throws Exception
    {
        // Guardo aqui as tres linhas, as tres colunas e as duas diagonais.
        int[][][] trincas = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}}
        };

        for (int i = 0; i < trincas.length; i++)
        {
            int quantidadeDaMarca = 0;
            int linVazia = -1;
            int colVazia = -1;

            for (int j = 0; j < trincas[i].length; j++)
            {
                int lin = trincas[i][j][0];
                int col = trincas[i][j][1];
                Marca atual = this.tabDoJogo.getMarcaNaPosicao(lin, col);

                if (atual == null)
                {
                    linVazia = lin;
                    colVazia = col;
                }
                else if (atual.equals(marca))
                    quantidadeDaMarca++;
                else
                {
                    // Descarto a trinca quando encontro uma marca adversaria.
                    quantidadeDaMarca = -1;
                    break;
                }
            }

            // Uso a mesma busca para ganhar, bloquear ou preparar uma jogada.
            if (quantidadeDaMarca == quantidadeNecessaria && linVazia >= 0)
                return new int[] {linVazia, colVazia};
        }

        return null;
    }
}