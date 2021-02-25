package master.atrium.java;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class EjerciciosPropuestos1 {

	
	public static void main(String[] args) {
		var primerNumero = 1;
		var segundoNumero = 4;
		var multiplo = 2;
		var sueldo = 38000;
		var numHijos = 3;
		var isMujer = false;
		var tiempo = 12;
		System.out.println("�es segundoNumero par? " + isPar(primerNumero));
		System.out.println("�es segundoNumero par? " + isPar(segundoNumero));
		System.out.println("�qu� n�mero es mayor entre "+ primerNumero + " y " + segundoNumero + " ? El " + getMayor(primerNumero, segundoNumero));
		System.out.println("�qu� n�mero es mayor? El "+getMayor(primerNumero,segundoNumero,3,2,5,6));
		System.out.println("�Es un n�mero par y multiplo de otro? " + isParAndOrMultiploOfANumber(primerNumero, multiplo));
		System.out.println("�Es un n�mero par y multiplo de otro? " + isParAndOrMultiploOfANumber(segundoNumero, multiplo));
		System.out.println("�Es un conjunto de n�meros pares y m�ltiplos de "+multiplo+"? "+allParAndOrMultiploOfANumber(multiplo, primerNumero,segundoNumero,3,4,5,6));
		System.out.println("�Alg�n n�mero del conjunto es un n�mero par y m�ltiplo de "+multiplo+"? "+anyParAndOrMultiploOfANumber(multiplo, primerNumero,segundoNumero,3,4,5,6));
		System.out.println("Calculamos la renta: "+ calcularRenta(sueldo, tiempo));
		System.out.println("Calculamos el sueldo: " + calcularSueldo(isMujer, numHijos, sueldo));
	}	
	
	/**
	 * Devolver si un numero es par o impar
	 */
	private static boolean isPar(final int numero) {
		System.out.println(numero + "es par");
		return (numero%2)==0;
		// var esPar = (numero%2)==0;
		// return esPar;		
	}
	
	/**
	 * Comparar dos n�meros y decir cu�l es el mayor
	 */
	private static int getMayor(final int primerNumero, final int segundoNumero) {
		return (primerNumero > segundoNumero) ? primerNumero : segundoNumero;
		// var numMayor = (primerNumero>segundoNumero) ? primerNumero : segundoNumero;
		// return numMayor;
	}
	
	/**
	 * Comparar tres n�meros (o m�s) y decir cu�l es el mayor
	 * @throws Exception 
	 */
	private static int getMayor(final int... numeros) {
		// NoSuchElementException::new se refiere al constructor de ese objeto		
		return Arrays.stream(numeros).max().orElseThrow(NoSuchElementException::new);		
	}
	
	/**
	 * Compara si un n�mero es m�ltiplo de otro
	 * @param multiplo
	 * @param numero
	 * @return
	 */
	private static boolean isMultiploOfANumber(final int multiplo, final int numero) {
		System.out.println(numero + "es m�ltiplo de " + multiplo);
		return numero%multiplo==0;
	}
	
	/**
	 * Halla una renta de C x r x t / 1200
	 * Si el tiempo es <=24 meses �> 5% 
	 * Si el tiempo es <=60 meses �> 8% 
	 * Si el tiempo es >60 meses �> 10%
	 */
	private static double calcularRenta(final int capital, final int tiempo) {
		double renta = 0;
		if(tiempo <= 24) {
			renta = (tiempo * 0.05 * capital)/1200;
		} else if(tiempo <= 60) {
			renta = (tiempo * 0.08 * capital)/1200;
		} else {
			renta = (tiempo * 0.10 * capital)/1200;
		}
		return renta;
	}
	
	/**
	 * Ver si un n�mero es par y m�ltiplo de X y que saque mensajes del tipo: 
	 * Es par y m�ltiplo de X
	 * Es impar y m�ltiplo de X
	 * No es m�ltiplo de X pero es par 
	 * No es ni par ni m�ltiplo de X
	 */
	private static boolean isParAndOrMultiploOfANumber(final int multiplo, final int numero) {
		return ((isMultiploOfANumber(multiplo, numero) && isPar(numero)) || isMultiploOfANumber(multiplo, numero)
				|| isPar(numero));
	}
	
	/**
	 * Ver si todos los n�meros del conjunto de n�meros es par y m�ltiplo de X y que saque mensajes del tipo: 
	 * Es par y m�ltiplo de 3
	 * Es impar y m�ltiplo de 3
	 * No es m�ltiplo de 3 pero es par 
	 * No es ni par ni m�ltiplo de 3
	 */
	private static boolean allParAndOrMultiploOfANumber(final int multiplo, final int... numeros) {
		return (Arrays.stream(numeros).allMatch(numero -> isParAndOrMultiploOfANumber(multiplo, numero)));
	}
	
	/**
	 * Ver si hay un n�mero del conjunto de n�meros que sea par y m�ltiplo de X y que saque mensajes del tipo: 
	 * Es par y m�ltiplo de 3
	 * Es impar y m�ltiplo de 3
	 * No es m�ltiplo de 3 pero es par 
	 * No es ni par ni m�ltiplo de 3
	 */
	private static boolean anyParAndOrMultiploOfANumber(final int multiplo, final int... numeros) {
		return (Arrays.stream(numeros).anyMatch(numero -> isParAndOrMultiploOfANumber(multiplo, numero)));
	}
	
	/**
	 * Se va a subir el sueldo a los empleados de la siguiente manera: 
	 * Los que cobren < 15000 se les aumenta el 15%
	 * Los que cobren >= 15000 aumento del 12%
		
	 * adem�s:
	 * Si son mujeres tienen un 2% adicional
	 * Si tienen hijos tienen un 1% adicional por hijo
	 * @param isMujer
	 * @param numHijos
	 * @param sueldo
	 * @return
	 */
	private static double calcularSueldo(boolean isMujer, final int numHijos,
			final int sueldo) {
		double porcentaje = 0.0;
		
		if(sueldo < 15000) {
			porcentaje = 0.15;
			porcentaje += calcularPorcentajeTotal(isMujer, numHijos);
			System.out.println("Porcentaje es "+porcentaje);
		} else if(sueldo >= 15000) {
			porcentaje = 0.12;
			porcentaje += calcularPorcentajeTotal(isMujer, numHijos);
			System.out.println("Porcentaje es "+porcentaje);
		}
				
		return sueldo * porcentaje + sueldo;
	}
	
	/**
	 * Calcula el porcentaje total a aplicar en los sueldos
	 * @param isMujer
	 * @param numHijos
	 * @return
	 */
	private static double calcularPorcentajeTotal(final boolean isMujer, final int numHijos) {
		double porcentajeTotalAniadido = 0.0;
		porcentajeTotalAniadido += calcularPorcentajeParaMujer(isMujer);
		porcentajeTotalAniadido += calcularPorcentajeParaNumHijos(numHijos);		
		return porcentajeTotalAniadido;
	}
	
	/**
	 * Calcula el porcentaje para el n�mero de hijos
	 * @param numHijos
	 * @return
	 */
	private static double calcularPorcentajeParaNumHijos(final int numHijos) {
		double porcentajeConHijos = 0.01;
		double porcentajeTotalAniadido = 0.0;
		if(numHijos>0) {
			porcentajeTotalAniadido = (porcentajeConHijos*numHijos);			
		}
		return porcentajeTotalAniadido;
	}
	
	/**
	 * Retorna el porcentaje si es mujer.
	 * @param isMujer
	 * @return
	 */
	private static double calcularPorcentajeParaMujer(final boolean isMujer) {
		double porcentajeParaMujer = 0.02;
		double porcentajeTotalAniadido = 0.0;
		if(isMujer) {
			porcentajeTotalAniadido = porcentajeParaMujer;
		}
		return porcentajeTotalAniadido;
	}
}
