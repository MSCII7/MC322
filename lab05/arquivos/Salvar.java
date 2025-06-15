package arquivos;
import java.io.*;
import java.nio.file.Paths;

public class Salvar {
    //fica salvo na base da propria pasta sendo executada (lab05)
    private static final String LOG_FILE = "missoes.log";

    public Salvar(){
    }

    //recebe uma mensagem no fim do codigo de execucao de missao para escrever no arquivo
    public static void escreverMissao(String mensagemMissao){
        String caminho = Paths.get(LOG_FILE).toString();

        try {
            File arquivo = new File(caminho);
            arquivo.createNewFile();


            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(caminho, true)));
            output.println(mensagemMissao);
            output.flush();
            output.close();
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

