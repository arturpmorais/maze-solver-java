package labirinto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Labirinto implements Cloneable
{
    protected char[][] labirinto;
    protected Coordenada E;
    protected Coordenada S;
    protected Coordenada atual;
    protected int Xs;
    protected int Ys;

    public Labirinto(String nomeArquivo) throws Exception
    {
        if (nomeArquivo == null || "".equals(nomeArquivo))
            throw new Exception("Caminho inexistente");

        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo)))
        {
            if (arquivo == null)
                throw new Exception("Arquivo inexistente");
            
            this.Xs = Integer.parseInt(arquivo.readLine());
            this.Ys = Integer.parseInt(arquivo.readLine());
            this.labirinto = new char[this.Xs][this.Ys];
            
            for (int x = 0; x < this.Xs; x++)
            {
                if (arquivo.ready())
                {
                    String linha = arquivo.readLine().trim();
                    for (int y = 0; y < this.Ys; y++)
                        this.labirinto[x][y] = linha.charAt(y);
                }
            }
        }
        
        this.acharEntradaSaida();
    }
    
    public void acharEntradaSaida() throws Exception
    {          
        int qtsE = 0, qtsS = 0;
        this.E = null;
        this.S = null;

        try {
            for(int i = 0; i < this.Xs; i++)
            {
                if(this.labirinto[i][0] == 'E')
                {
                    this.E = new Coordenada(i,0);
                    qtsE++;
                } 
                else if (this.labirinto[i][0] == 'S')
                {
                    this.S = new Coordenada(i,0);
                    qtsS++;
                }                 
            }

            for(int i = 0; i < this.Xs; i++)
            {
                if(this.labirinto[i][this.Ys-1] == 'E')
                {
                    this.E = new Coordenada(i,this.Ys-1);
                    qtsE++;
                } 
                else if (this.labirinto[i][this.Ys-1] == 'S')
                {
                    this.S = new Coordenada(i,this.Ys-1);
                    qtsS++;
                }  
            }

            for(int i = 0; i < this.Ys; i++)
            {
                if(this.labirinto[0][i] == 'E')
                {
                    this.E = new Coordenada(0,i);
                    qtsE++;
                } 
                else if (this.labirinto[0][i] == 'S')
                    qtsS++;
            }

            for(int i = 0; i < this.Ys; i++)
            {
                if(this.labirinto[this.Xs-1][i] == 'E')
                {
                    this.E = new Coordenada(this.Xs-1,i);
                    qtsE++;
                } 
                else if (this.labirinto[this.Xs-1][i] == 'S')
                {
                    this.S = new Coordenada(this.Xs-1,i);
                    qtsS++;
                }  
            }
        } catch(Exception e) {}

        if (qtsE != 1 && qtsS !=1) 
                throw new Exception("Quantidade invalida de entradas/saidas");

        this.atual = (Coordenada)this.E.clone();
    }

    public Pilha solucionarLabirinto() throws Exception
    {
        Pilha<Coordenada> caminho = null;
        Pilha<Fila<Coordenada>> possibilidades = null;
        
        try 
        {
            caminho = new Pilha<>();
            possibilidades = new Pilha<>();
        } catch(Exception e) {}

        boolean modoProgressivo = true;
        boolean achouSaida = false;

        Resolva:while(!achouSaida)
        {
            Fila<Coordenada> fila = null;
            
            try
            {
                fila = new Fila<>();
            } catch(Exception e) {}

            if(modoProgressivo)
            {
                Coordenada novaCoordenada = null;
                try
                {
                    if(this.labirinto[this.atual.getX() + 1][this.atual.getY()] == ' ' || this.labirinto[this.atual.getX() + 1][this.atual.getY()] == 'S') {
                        novaCoordenada = new Coordenada((this.atual.getX() + 1),this.atual.getY());
                        fila.inserirUmItem(novaCoordenada);
                    }
                } catch(Exception e) { }

                try
                {
                    if(this.labirinto[this.atual.getX() - 1][this.atual.getY()] == ' ' || this.labirinto[this.atual.getX() - 1][this.atual.getY()] == 'S') {
                        novaCoordenada = new Coordenada((this.atual.getX() - 1),this.atual.getY());
                        fila.inserirUmItem(novaCoordenada);
                    }
                } catch(Exception e) {} 

                try
                {
                    if(this.labirinto[this.atual.getX()][this.atual.getY() + 1] == ' ' || this.labirinto[this.atual.getX()][this.atual.getY() + 1] == 'S') {
                        novaCoordenada = new Coordenada(this.atual.getX(),(this.atual.getY() + 1));
                        fila.inserirUmItem(novaCoordenada);
                    }
                } catch(Exception e) {}

                try 
                {
                    if(this.labirinto[this.atual.getX()][this.atual.getY() - 1] == ' ' || this.labirinto[this.atual.getX()][this.atual.getY() - 1] == 'S') {
                        novaCoordenada = new Coordenada(this.atual.getX(),(this.atual.getY() - 1));
                        fila.inserirUmItem(novaCoordenada);
                    }
                } catch(Exception e) {}

                try 
                {
                    this.atual = fila.getItem();
                    fila.removerItem();

                    try 
                    {
                        if (!this.atual.equals(this.S))
                        this.labirinto[this.atual.getX()][this.atual.getY()] = '*';

                        caminho.inserir(this.atual);
                        possibilidades.inserir(fila);   
                    } 
                    catch(Exception e) {} 
                } 
                catch(Exception e)
                {
                    modoProgressivo = false;
                }
            } 
            else 
            {
                if (possibilidades.vazia() && caminho.vazia())
                    throw new Exception("Labirinto sem saida");

                try
                {
                    this.atual = caminho.getItem();
                    caminho.remover();

                    this.labirinto[this.atual.getX()][this.atual.getY()] = ' ';

                    fila = possibilidades.getItem();
                    possibilidades.remover();

                    this.atual = fila.getItem();
                    fila.removerItem();

                    this.labirinto[this.atual.getX()][this.atual.getY()] = '*';
                    caminho.inserir(this.atual);
                    possibilidades.inserir(fila);

                    modoProgressivo = true;
                } catch(Exception e) {}
            }

            if (this.atual.equals(this.S))
                    achouSaida = true;
        }

        Pilha<Coordenada> inverso = new Pilha<>();

        while (!caminho.vazia())
        {
            try
            {
                inverso.inserir(caminho.getItem());
                caminho.remover();
            } 
            catch(Exception e)
            { 
                System.out.print(e.getMessage());
            }
        }

        return inverso;            
    }

    public void arquivarSolucao(String caminho) throws Exception
    {
        if (caminho == null || caminho == "")
            throw new Exception("Caminho invalido");

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(caminho), "utf-8"));

        writer.write(new Integer(this.Xs).toString());
        writer.write(System.lineSeparator());
        writer.write(new Integer(this.Ys).toString());
        writer.write(System.lineSeparator());

        for (int i = 0; i < this.Xs; i++)
        {
            for (int j = 0; j < this.Ys; j++)
                writer.write(this.labirinto[i][j]);
            
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    public Coordenada getE() 
    {
        return E;
    }

    public Coordenada getS() 
    {
        return S;
    }

    public Coordenada getAtual() 
    {
        return atual;
    }

    public int getXs() 
    {
        return Xs;
    }

    public int getYs() 
    {
        return Ys;
    }

    public String toString() 
    {
        String ret = "";

        ret += "Entrada:" + this.E + " / ";
        ret += "Saida:" + this.S + " / ";
        ret += "Numero de linhas:" + this.Xs + " / ";
        ret += "Numero de colunas:" + this.Ys + " / ";

        ret += System.lineSeparator() + "{";
        
        for (int i = 0; i < this.Xs; i++) 
        {
            ret += System.lineSeparator();
            for (int j = 0; j < this.Ys; j++)
                ret += this.labirinto[i][j];
        }

        ret += System.lineSeparator() + "}";

        return ret;
    }

    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Labirinto other = (Labirinto)obj;

        if (other.E.equals(this.E))
            return false;

        if (other.S.equals(this.S))
            return false;

        if (other.atual.equals(this.atual))
            return false;

        if (other.Xs != this.Xs)
            return false;

        if (other.Ys != this.Ys)
            return false;

        for (int i = 0; i < this.Xs; i++)
            for (int j = 0; j < this.Ys; j++)
                if (!(this.labirinto[i][j] == other.labirinto[i][j]))
                    return false;

        return true;
    }

    public int hashCode() 
    {
        int ret = 3;

        ret = ret*7 + this.E.hashCode();
        ret = ret*7 + this.S.hashCode();
        ret = ret*7 + this.atual.hashCode();

        ret = ret*7 + this.Xs;
        ret = ret*7 + this.Ys;

        for (int i = 0; i < this.Xs; i++)
            for (int j = 0; j < this.Ys; j++)
                ret = ret*7 + new Character(this.labirinto[i][j]).hashCode();

        return ret;
    }

    public Labirinto(Labirinto modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo inexistente");

        this.E = (Coordenada)modelo.E.clone();
        this.S = (Coordenada)modelo.S.clone();
        this.atual = (Coordenada)modelo.atual.clone();

        this.Xs = modelo.Xs;
        this.Ys = modelo.Ys;

        this.labirinto = new char[modelo.Xs][modelo.Ys];

        for (int i = 0; i < modelo.Xs; i++)
            for (int j = 0; j < modelo.Ys; j++)
                this.labirinto[i][j] = modelo.labirinto[i][j];
    }

    public Object clone() {
        Labirinto ret = null;

        try {
            ret = new Labirinto(this);
        } 
        catch(Exception e) {}

        return ret;
    }
}