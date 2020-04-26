package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase >= 1039) {
			salarioBase = calcularInss(salarioBase);
			salarioBase = calcularIrrf(salarioBase);

			return Math.round(salarioBase);
		}

		return Math.round(0.0);
	}

	private double calcularInss(double salarioBase) {
		return salarioBase - salarioBase * getDescontoInss(salarioBase) / 100;
	}

	private double getDescontoInss(double salarioBase) {
		if (salarioBase <= 1500) {
			return 8;
		} else if (salarioBase > 1500 && salarioBase <= 4000) {
			return 9;
		} else {
			return 11;
		}
	}

	private double calcularIrrf(double salarioBase) {
		if (salarioBase < 3000) {
			return salarioBase;
		} else {
			return salarioBase - salarioBase * getDescontoIrrf(salarioBase) / 100;
		}
	}

	private double getDescontoIrrf(double salarioBase) {
		if (salarioBase < 6000) {
			return 7.5;
		} else {
			return 15;
		}
	}
}