package jogador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        if (tab == null) throw new Exception("Tabuleiro invalido");
        this.tabDoJogo = tab;

        while (this.marca == null)
        {
            System.out.print("Escolha sua marca (X/O): ");

            String resposta = this.teclado.readLine();
            if (resposta == null) throw new Exception("Entrada encerrada");

            resposta = resposta.trim().toUpperCase();

            try
            {
                if (resposta.length() != 1)
                    throw new Exception("Escolha X ou O");

                this.marca = new Marca(resposta.charAt(0));
            }
            catch (Exception erro)
            {
                System.out.println("Marca invalida. Digite X ou O.");
            }
        }
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
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
        while (true)
        {
            try
            {
                System.out.println(this.tabDoJogo);
                System.out.println("Sua marca: " + this.marca);
                System.out.print("Digite linha e coluna (1 a 3), separadas por espaco: ");

                String entrada = this.teclado.readLine();
                if (entrada == null)
                {
                    System.out.println("Entrada encerrada.");
                    return;
                }

                String[] valores = entrada.trim().split("\\s+");
                if (valores.length != 2)
                {
                    System.out.println("Digite dois numeros: linha e coluna.");
                    continue;
                }

                int lin = Integer.parseInt(valores[0]) - 1;
                int col = Integer.parseInt(valores[1]) - 1;

                if (lin < 0 || lin >= 3 || col < 0 || col >= 3)
                {
                    System.out.println("Linha e coluna devem estar entre 1 e 3.");
                    continue;
                }

                if (this.tabDoJogo.haMarcaNaPosicao(lin, col))
                {
                    System.out.println("Essa posicao ja esta ocupada.");
                    continue;
                }

                this.tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                return;
            }
            catch (NumberFormatException erro)
            {
                System.out.println("Digite numeros validos para linha e coluna.");
            }
            catch (Exception erro)
            {
                System.out.println("Nao foi possivel registrar a jogada.");
                return;
            }
        }
    }

    public void vcGanhou ()
    {
        System.out.println("Parabens, voce ganhou!");
        System.out.println(this.tabDoJogo);
    }

    public void vcPerdeu ()
    {
        System.out.println("Voce perdeu. O computador ganhou.");
        System.out.println(this.tabDoJogo);
    }

    public void deuVelha ()
    {
        System.out.println("A partida terminou empatada.");
        System.out.println(this.tabDoJogo);
    }

    public boolean querMaisUmaPartida ()
    {
        while (true)
        {
            try
            {
                System.out.print("Deseja jogar outra partida? (S/N): ");
                String resposta = this.teclado.readLine();

                if (resposta == null)
                    return false;

                resposta = resposta.trim().toUpperCase();

                if (resposta.equals("S")) return true;
                if (resposta.equals("N")) return false;

                System.out.println("Digite S para sim ou N para nao.");
            }
            catch (Exception erro)
            {
                return false;
            }
        }
    }
}
