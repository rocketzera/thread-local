import java.time.LocalDateTime;

public class Exemplo3 {

    public static void main(String[] args) throws InterruptedException {

        Servico servico = new Servico();

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            new Thread(() -> {
                servico.salvar(codigo);
                Long codigoRetorno = servico.buscar();

                if (codigo != codigoRetorno)
                    System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);

            }).start();
        }

        Thread.sleep(1000l);
    }

    public static class Servico {

        public Long buscar() {
            return Parametro.codigo.get();
        }

        public void salvar(Long codigo) {
            Parametro.codigo.set(codigo);
        }
    }

    public static class Parametro {
        public static ThreadLocal<Long> codigo = new ThreadLocal<>();
    }
}
