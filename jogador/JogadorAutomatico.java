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
            int[] posicao = this.buscarPosicaoParaCompletar(this.marca);
            if (posicao == null)
            {
                Marca marcaAdversaria;
                if (this.marca.getSimbolo() == 'X') marcaAdversaria = new Marca('O');
                else marcaAdversaria = new Marca('X');
                posicao = this.buscarPosicaoParaCompletar(marcaAdversaria);
            }

            if (posicao != null)
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, posicao[0], posicao[1]);
                return;
            }

            // prefere o centro quando ele estiver livre
            if (!this.tabDoJogo.haMarcaNaPosicao(1, 1))
            {
                this.tabDoJogo.setMarcaNaPosicao(this.marca, 1, 1);
                return;
            }

            // tenta primeiro os cantos; depois, qualquer outra posicao livre
            int[][] posicoes = {{0, 0}, {0, 2}, {2, 0}, {2, 2}, {0, 1}, {1, 0}, {1, 2}, {2, 1}};
            int inicio = (int)(Math.random() * posicoes.length);

            for (int i = 0; i < posicoes.length; i++)
            {
                int indice = (inicio + i) % posicoes.length;
                int lin = posicoes[indice][0];
                int col = posicoes[indice][1];

                if (!this.tabDoJogo.haMarcaNaPosicao(lin, col)) {
                    this.tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                    return;
                }
            }
        }
        catch (Exception erro)
        {}
    }

    private int[] buscarPosicaoParaCompletar (Marca marca) throws Exception
    {
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
                    quantidadeDaMarca = -1;
                    break;
                }
            }

            if (quantidadeDaMarca == 2 && linVazia >= 0)
                return new int[] {linVazia, colVazia};
        }

        return null;
    }
}