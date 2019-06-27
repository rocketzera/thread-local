
public class Exemplo1 {

    public static void main(String[] args) throws InterruptedException {

        for (long i = 0; i < 100000; i++) {
            long codigo = i;

            Parametro parametro = new Parametro();

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
        
        public Long codigo;

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }
    }
}
