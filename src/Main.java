import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public  class Main {

    private static Executor threadPool = Executors.newFixedThreadPool(10000);

    private Long nome;

    public static ThreadLocal<Long> df = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Main main = new Main();

        for (long i = 0; i < 10000; i++) {
            long id = i;
            threadPool.execute(() -> {
                System.out.print("Inicio: " + id + " ");

                main.setNome(id);

                System.out.print(main.getNome() + " Fim");
                System.out.println(id != main.getNome()? " True": " ");
            });
        }

        Thread.sleep(1000l);


    }

    public void setNome(Long nome) {
        df.set(nome);
//        this.nome = nome;
    }

    public Long getNome() {
       return df.get();
//        return nome;
    }
}
