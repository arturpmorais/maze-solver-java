package labirinto;

public class Coordenada implements Cloneable{
    protected int x;
    protected int y;
    
    public Coordenada (int X, int Y) throws Exception
    {   
        if (X < 0 || Y < 0)
            throw new Exception("Coordenada invalida!");

        this.x = X;
        this.y = Y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setX(int X) throws Exception
    {
        if (X < 0)
            throw new Exception ("Valor invalido");
            
        this.x = X;
    }
    
    public void setY(int Y) throws Exception
    {
        if (Y < 0)
         throw new Exception ("Valor invalido");
         
        this.y = Y;
    }
    
    public String toString()
    {
        String ret = "";
        
        ret += "(";
        ret += this.x;
        ret += ",";
        ret += this.y;
        ret += ")";

        return ret;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordenada ret = (Coordenada) obj;
        if (this.x != ret.x) {
            return false;
        }
        if (this.y != ret.y) {
            return false;
        }
        return true;
    }
    
    public int hashCode() 
    {
        int ret = 3;
        
        ret += ret * 7 + new Integer(this.x).hashCode();
        ret += ret * 7 + new Integer(this.y).hashCode();
        
        return ret;
    }
    
    public Coordenada (Coordenada modelo) throws Exception 
    {
        if (modelo == null)
            throw new Exception("Modelo inexistente");
    
        this.x = modelo.x;
        this.y = modelo.y;
    }
    
    public Object clone() 
    {
        Coordenada ret = null;
    
        try
        {
            ret = new Coordenada(this);
        }
        catch(Exception e) {}
    
        return ret;
    }
}
