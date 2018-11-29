package labirinto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author 17164
 * @author 17175
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String pathArq = "";

        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Caminho do arquivo de labirinto?");
            
        try 
        {
            pathArq = teclado.readLine();
        } 
        
        catch(IOException e) 
        {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
            
        try {
            Labirinto labirinto = new Labirinto(pathArq);
            Pilha caminho = labirinto.solucionarLabirinto();

            System.out.println("Nome do arquivo a ser gravado?");
            pathArq = teclado.readLine();
            labirinto.arquivarSolucao(pathArq);
        } 
        catch(Exception e) 
        {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
