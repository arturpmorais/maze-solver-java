package labirinto;

import java.lang.reflect.*;

public class Lista <X>
{

    private class No
    {
        private X info;
        private No prox;

        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        public X getInfo () 
        {
            return this.info;
        }
        
        public void setInfo (X i) 
        {
            this.info = i;
        }

        public No getProx () 
        {
            return this.prox;
        }
        
        public void setProx (No p) 
        {
            this.prox = p;
        }
    }

    protected No prim;

    public Lista() 
    {
        this.prim = null;
    }

    public X getInfo(No n) 
    {
        return n.getInfo();
    }
    
    public X getUltimo() throws Exception
    {
        if (this.vazia())
            throw new Exception ("Lista Vazia");
        
        No atual = this.prim;
            
        while(atual.getProx() != null)
            atual = atual.getProx ();
        
        return atual.getInfo();
    }
    
    public void inserirUmaInfo (X i) throws Exception 
    {
        if (i == null)
            throw new Exception ("Informacao ausente");

        X info;
        if (i instanceof Cloneable)
            info = cloneDeX(i);
        else
            info = i;
        
        if (this.vazia()) 
        {
            No novo;
            novo = new No (info, null);
            this.prim = novo;
            return;
        }

        No atual = this.prim;
        
        while (atual.getProx() != null) 
        {
            atual = atual.getProx();
        }
        atual.setProx(new No (info, null));
    }
    
    public void removerUmaInfo (X info) throws Exception
    {
        if (info == null)
            throw new Exception ("Informação Ausente");
        
        if (this.vazia())
            return;
        
        No atual = this.prim.getProx();
        
        if (atual != null)
        {
            No ant = this.prim;
            
            while(atual.getProx () != null) 
            {
                if (atual.getInfo () == info)
                    ant.setProx (atual.getProx ());

                atual = atual.getProx ();
                ant = ant.getProx();
            }
            if (atual.getInfo () == info)
                ant.setProx(null);
        }
        
        if (this.prim.getInfo () == info)
            this.removerPrimeiro ();
    }
    
    public void removerPrimeiro ()
    {
        if (this.vazia ())
            return;
        
        this.prim = this.prim.getProx(); 
    }
    
    public void removerUltimo() 
    {
        if (this.vazia())
            return;
                
        if (this.prim.getProx() == null) 
        {
            this.prim = null;
            return;
        }
        
        No atual = this.prim;
            
        while(atual.getProx().getProx() != null)
            atual = atual.getProx ();
            atual.setProx(null);
         
    }
   
    public boolean vazia () 
    {
        return this.prim == null;
    }

    @Override
    public String toString ()
    {
        String ret="";

        No atual = this.prim;
        while (atual!=null) 
        {
            ret += atual.getInfo () + " ";
            atual = atual.getProx ();
        }

        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass () != obj.getClass ())
            return false;

        Lista <X> lis = (Lista <X>)obj;

        No atualThis = this.prim, atualLis = lis.prim;

        while (atualThis != null && atualLis != null) 
        {
                if (!atualThis.getInfo ().equals (atualLis.getInfo ()))
                        return false;

                atualThis = atualThis.getProx();
                atualLis = atualLis .getProx();
        }

        if (atualThis != null)
                return false;

        return atualLis == null;
    }

    @Override
    public int hashCode () 
    {
        int ret = 17;

        No atual = this.prim;
        while (atual != null) 
        {
            ret = ret * 7 + atual.getInfo ().hashCode ();
            atual = atual.getProx ();
        }

        return ret;
    }
    
    private X cloneDeX (X x) 
    {
        X ret = null;

        try 
        {
            Class<?> classe = x.getClass ();
            Class<?>[] tipoParametroFormal = null;
            Method metodo = classe.getMethod ("clone", tipoParametroFormal);
            Object[] parametroReal = null;
            ret = (X) metodo.invoke (x, parametroReal);
        }
        catch (Exception erro) {}

        return ret;
    }

    public Lista (Lista<X> modelo) throws Exception 
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        if (modelo.prim == null) 
        {
            this.prim = null;
            return;
        }

        this.prim = new No (modelo.prim.getInfo (), null);

        if (modelo.prim.getProx () == null)
            return;
    
        No atualThis = this.prim, atualModelo = modelo.prim;
        
        while (atualModelo.getProx () != null) 
        {
            atualThis.setProx (new No (atualModelo.getProx().getInfo(), null));
            atualThis = atualThis.getProx ();
            atualModelo = atualModelo.getProx ();
        }
    }

    @Override
    public Object clone () 
    {
        Lista<X> ret = null;

        try {
            ret = new Lista<> (this);
        }
        catch (Exception erro) {}

        return ret;
    }
}