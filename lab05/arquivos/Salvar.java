package arquivos;
import java.io.*;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class Salvar {
    String conteudo, titulo;
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = "missoes.log";

    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Salvar(){
    }
/*
    public void escrever(String mensagem){
        File file = new File(this.titulo);
        if (file.exists() && file.isFile()){
            file.write();
        }
        else{
            file.createNewFile();
        }
        String entrada = String.format("[%s] %s%n",
                LocalDateTime.now().format(TIMESTAMP_FORMAT), mensagem);
        Path caminho = Paths.get(LOG_DIR);
    }
*/
    public static void escreverMissao(String mensagemMissao){
        String caminho = Paths.get(LOG_FILE).toString();

        try {
            File arquivo = new File(caminho);
            arquivo.createNewFile();


            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(caminho, true)));
            output.println(mensagemMissao);
            output.flush();
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

