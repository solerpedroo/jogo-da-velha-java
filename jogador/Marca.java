package jogador;

public class Marca
{
    private char simbolo;

    public Marca (char smb) throws Exception
    {
        // validar smb, garantindo que ele
        // seja 'X' ou 'O' e coloca-lo em
        // simbolo
        // dar excecao em caso de valor invalido
        if (smb != 'X' && smb != 'O') throw new Exception("Simbolo invalido");

        this.simbolo = smb;
    }

    public char getSimbolo ()
    {
        // retornar simbolo
        return this.simbolo;
    }

    @Override
    public String toString ()
    {
        return ""+simbolo;
    }

    @Override
    public boolean equals (Object obj)
    {
        // retornar true se this for igual a obj ou false caso contrario
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Marca marca = (Marca)obj;
        return this.simbolo == marca.simbolo;
    }

    @Override
    public int hashCode ()
    {
        // faça
        int retorno = 1;
        retorno = retorno *2+((Character)this.simbolo).hashCode();
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }
}
