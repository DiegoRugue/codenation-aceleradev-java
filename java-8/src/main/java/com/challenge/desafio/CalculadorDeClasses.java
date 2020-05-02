package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    private BigDecimal somarAnotacoes(Object classe, Class anotacao) {
        BigDecimal soma = BigDecimal.ZERO;
        Field[] fields = classe.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(anotacao) && field.getType().equals(BigDecimal.class)) {
                try {
                    field.setAccessible(true);
                    BigDecimal valor = field.get(classe) != null ? (BigDecimal) field.get(classe) : BigDecimal.ZERO;
                    soma = soma.add(valor);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return soma;
    }

    @Override
    public BigDecimal somar(Object classe) {
        return somarAnotacoes(classe, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object classe) {
        return somarAnotacoes(classe, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object classe) {
        BigDecimal soma = somar(classe);
        BigDecimal subtracao = subtrair(classe);

        return soma.subtract(subtracao);
    }
}
