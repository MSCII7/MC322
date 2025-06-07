package arquivos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Salvar {
    String conteudo, titulo;
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = "application.log";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Salvar(){
    }
    public void escrever(String menssagem){
        File file = new File(this.titulo);
        if (file.exists() && file.isFile()){
            file.write;
        }
        String entrada = String.format("[%s] %s%n",
                LocalDateTime.now().format(TIMESTAMP_FORMAT), menssagem);
        Path caminho = Paths.get(LOG_DIR);
    }
}
