public class Exemplo3 {

    public static void main(String[] args) throws InterruptedException {

        Parametro parametro = new Parametro();

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            new Thread(() -> {
                parametro.setCodigo(codigo);
                Long codigoRetorno = parametro.getCodigo();

                if (codigo != codigoRetorno)
                    System.out.printf("%s %s efeito colateral\n", codigo, codigoRetorno);

            }).start();
        }

        Thread.sleep(1000l);
    }

    public static class Parametro {

        public static ThreadLocal<Long> codigo = new ThreadLocal<>();

        public Long getCodigo() {
            return codigo.get();
        }

        public void setCodigo(Long codigo) {
            this.codigo.set(codigo);
        }
    }
}
