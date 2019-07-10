
public class Exemplo1 {

    public static  Parametro parametro;

    public static void main(String[] args) {

        for (long i = 0; i < 100000; i++) {
            long chave = i;

            Servico servico = new Servico();

            new Thread(() -> {
                servico.salvar(chave);
                Long chaveRetorno = servico.buscar();

                if (chave != chaveRetorno)
                    System.out.printf("%s %s efeito colateral\n", chave, chaveRetorno);

            }).start();
        }
    }

    public static class Servico {

        public void salvar(Long codigo) {
            parametro = new Parametro(codigo);
        }

        public Long buscar() {
            return parametro.getCodigo();
        }
    }
}
