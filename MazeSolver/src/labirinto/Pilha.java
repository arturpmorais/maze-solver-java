package labirinto;

public class Pilha <X> implements Cloneable
{
    protected Lista<X> lista;

    public Pilha()
    {
        this.lista = new Lista<>();
    }
    
    public void inserir(X novo) throws Exception
    {
        this.lista.inserirUmaInfo(novo);
    }
        
    public X getItem() throws Exception
    {
        return this.lista.getUltimo();
    }
    
    public void remover()
    {
        this.lista.removerUltimo();
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
        
        Pilha <X> pilha = (Pilha <X>)obj;
        
        return this.lista.equals(pilha.lista);
    }

    public Pilha(Pilha modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception("Modelo inexistente");
        
        this.lista = new Lista(modelo.lista);
    }

    @Override
    public Object clone()
    {
        Pilha<X> ret = null;

        try {
        ret = new Pilha<> (this);
        }
        catch (Exception erro) {}

        return ret;
    }
}
