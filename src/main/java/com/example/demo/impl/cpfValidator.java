package com.example.demo.impl;

import com.example.demo.services.cpfService;

public class cpfValidator implements cpfService {

    @Override
    public Boolean validarCPF(String cpf) {
        if(cpf.length() < 11)
            return false;
            
        int[] i = new int[] {0};
        var sum = cpf.chars()
                    .filter(c -> c >= '0' && c <= '9')
                    .limit(9)
                    .map(c -> Character.getNumericValue(c) * ++i[0])
                    .sum();
        
        if(((sum % 11 == 10) ? 0 : sum % 11) != Character.getNumericValue(cpf.charAt(cpf.length() - 2)))
            return false;

        i[0] = 0;
        
        var sum2 = cpf.chars()
                    .filter(c -> c >= '0' && c <= '9')
                    .limit(10)
                    .map(c -> Character.getNumericValue(c) * i[0]++)
                    .sum();

        return ((sum2 % 11 == 10) ? 0 : sum2 % 11) == Character.getNumericValue(cpf.charAt(cpf.length() - 1));
    }
}