package br.com.renan.verificadornumerosprimos.controller;

public class PrimeNumberController {

    public PrimeNumberController() {
    }

    /**
     * @param n número que se deseja verificar se é primo
     * @return se for primo [-1], se não [o menor divisor]
     */
    public int isPrime(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 0) {
            return 0;
        }
        // iterar de 2 até raiz quadrada de n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return i;
            }
        }
        return -1;
    }
}
