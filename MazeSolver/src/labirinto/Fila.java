package labirinto;

public class Fila <X> implements Cloneable
{
    protected Lista <X> lista;

    public Fila()
    {
        this.lista = new Lista<>();
    }

    public void inserirUmItem(X novo) throws Exception
    {
        this.lista.inserirUmaInfo(novo);
    }
    
    public X getItem() throws Exception
    {
        if (this.vazia())
            throw new Exception("Lista Vazia");

        return this.lista.getInfo(this.lista.prim);
    }

    public void removerItem()
    {
        this.lista.removerPrimeiro();
    }

    public boolean vazia()
    {
        return this.lista.vazia();
    }

    @Override
    public String toString()
    {
        return this.lista.toString();
    }

    @Override
    public int hashCode()
    {
        return this.lista.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;
        
        Fila <X> fila = (Fila <X>)obj;
        
        return this.lista.equals(fila.lista);
    }
    
    public Fila(Fila modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo inexistente");
        
        this.lista = new Lista(modelo.lista);
    }

    @Override
    public Object clone()
    {        
        Fila<X> ret = null;

        try {
            ret = new Fila<> (this);
        }
        catch (Exception erro) {}

        return ret;
    }    
}
